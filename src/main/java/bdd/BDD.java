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
 * Représente l'accès à la base de donnée.
 * Pour le moment limité à PostgreSQL.
 *
 * S'utilise via {@link #act(bdd.BDDContent)}
 *
 */
class BDD {

	/**
	 * Défini si le pool de connexion a été initialisé (static)
	 */
	private static boolean INITIALISED = false;

	/**
	 * Pool de connexion
	 */
	private static Jdbc3PoolingDataSource SOURCE;

	private Exception ex;

	public BDD() {
	}

	/**
	 * Fonction gérant les requêtes à la BDD.
	 *
	 * Initialise la BDD si nécessaire.
	 * Récupère une connexion depuis le pool de connexion.
	 * Puis lance une transaction avec les actions passées en paramètre.
	 *
	 * En cas d'exception, on la throw à l'extérieur de la fonction.
	 *
	 * @param <T>  Utilisé afin que la fonction puisse retourner n'importe quel
	 *             type d'objet sans avoir à faire un cast
	 * @param bddC Représente le contenu de la transaction
	 * @return L'objet retourné par bddC
	 * @throws SQLException Lancé lors d'erreurs liées au SQL
	 * @throws Exception    Lancé manuellement (ou inattendu)
	 */
	public <T> T act(BDDContent bddC) throws SQLException, Exception {
		if (!INITIALISED) {
			init();
		}

		try (Connection conn = SOURCE.getConnection();
				DSLContext create = DSL.using(conn, SQLDialect.POSTGRES)) {
			try {
				return create.transactionResult(configuration -> {
					try {
						DSLContext ctx = DSL.using(configuration);
						return (T) bddC.act(ctx);
					} catch (Exception e) {
						ex = e;
						throw e;
					}
				});
			} catch (Exception e) {
				throw ex;
			}
		}
	}

	/**
	 * Initialise la base de donnée pour PostgreSQL via les infos de
	 * {@link BDDInfos}
	 */
	private static void init() {
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

}
