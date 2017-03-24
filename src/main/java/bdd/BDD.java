/*
 * 
 * 
 * 
 */
package bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

/**
 * BDD.java
 *
 */
class BDD {

	private static boolean INITIALISED = false;

	private static Jdbc3PoolingDataSource SOURCE;

	public BDD() {
	}

	public <T> T act(BDDContent bddC) throws SQLException, Exception {
		if (!INITIALISED) {
			init();
		}

		try (Connection conn = SOURCE.getConnection();
				DSLContext create = DSL.using(conn, SQLDialect.POSTGRES)) {

			return create.transactionResult(configuration -> {
				DSLContext ctx = DSL.using(configuration);
				return (T) bddC.act(ctx);
			});
		}
	}

	public static void init() {
		if (INITIALISED) {
			throw new UnsupportedOperationException("BDD déjà initialisée");
		}
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
			throw new Error(ex);
		}

		BDDInfos bddInfos = new BDDInfos();
		SOURCE = new Jdbc3PoolingDataSource();
		SOURCE.setDataSourceName("A Data Source");
		SOURCE.setServerName(bddInfos.hote);
		SOURCE.setPortNumber(bddInfos.port);
		SOURCE.setDatabaseName(bddInfos.base);
		SOURCE.setUser(bddInfos.user);
		SOURCE.setPassword(bddInfos.mdp);

		INITIALISED = true;

		System.out.println("BDD Max Connexions: " + SOURCE.getMaxConnections());
	}

	public static enum TypeProp {

		STRING(1),
		INTEGER(2),
		BOOLEAN(3),
		DATE(4);

		public final int id;

		private TypeProp(int id) {
			this.id = id;
		}
	}

}
