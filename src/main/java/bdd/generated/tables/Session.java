/*
 * This file is generated by jOOQ.
 */
package bdd.generated.tables;

import bdd.generated.Keys;
import bdd.generated.Public;
import bdd.generated.tables.records.SessionRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;

/**
 * This class is generated by jOOQ.
 */
@Generated(
		value = {
			"http://www.jooq.org",
			"jOOQ version:3.9.1"
		},
		comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Session extends TableImpl<SessionRecord> {

	private static final long serialVersionUID = 999152836;

	/**
	 * The reference instance of <code>public.session</code>
	 */
	public static final Session SESSION = new Session();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<SessionRecord> getRecordType() {
		return SessionRecord.class;
	}

	/**
	 * The column <code>public.session.id_session</code>.
	 */
	public final TableField<SessionRecord, Long> ID_SESSION = createField("id_session", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('session_id_session_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

	/**
	 * The column <code>public.session.id_user</code>.
	 */
	public final TableField<SessionRecord, Long> ID_USER = createField("id_user", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>public.session.id_domaine</code>.
	 */
	public final TableField<SessionRecord, Long> ID_DOMAINE = createField("id_domaine", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>public.session.dateouverture</code>.
	 */
	public final TableField<SessionRecord, Date> DATEOUVERTURE = createField("dateouverture", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

	/**
	 * The column <code>public.session.datefermeture</code>.
	 */
	public final TableField<SessionRecord, Date> DATEFERMETURE = createField("datefermeture", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

	/**
	 * The column <code>public.session.id_typefermeture</code>.
	 */
	public final TableField<SessionRecord, Integer> ID_TYPEFERMETURE = createField("id_typefermeture", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>public.session</code> table reference
	 */
	public Session() {
		this("session", null);
	}

	/**
	 * Create an aliased <code>public.session</code> table reference
	 */
	public Session(String alias) {
		this(alias, SESSION);
	}

	private Session(String alias, Table<SessionRecord> aliased) {
		this(alias, aliased, null);
	}

	private Session(String alias, Table<SessionRecord> aliased, Field<?>[] parameters) {
		super(alias, null, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Schema getSchema() {
		return Public.PUBLIC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<SessionRecord, Long> getIdentity() {
		return Keys.IDENTITY_SESSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<SessionRecord> getPrimaryKey() {
		return Keys.SESSION_PK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<SessionRecord>> getKeys() {
		return Arrays.<UniqueKey<SessionRecord>>asList(Keys.SESSION_PK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<SessionRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<SessionRecord, ?>>asList(Keys.SESSION__USER_SESSION_FK, Keys.SESSION__DOMAINE_SESSION_FK, Keys.SESSION__TYPEFERMETURE_SESSION_FK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Session as(String alias) {
		return new Session(alias, this);
	}

	/**
	 * Rename this table
	 */
	@Override
	public Session rename(String name) {
		return new Session(name, null);
	}
}
