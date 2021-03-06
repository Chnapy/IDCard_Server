/*
 * This file is generated by jOOQ.
 */
package bdd.generated.tables;

import bdd.generated.Keys;
import bdd.generated.Public;
import bdd.generated.tables.records.TypepropRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
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
public class Typeprop extends TableImpl<TypepropRecord> {

	private static final long serialVersionUID = -1224180525;

	/**
	 * The reference instance of <code>public.typeprop</code>
	 */
	public static final Typeprop TYPEPROP = new Typeprop();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<TypepropRecord> getRecordType() {
		return TypepropRecord.class;
	}

	/**
	 * The column <code>public.typeprop.id_typeprop</code>.
	 */
	public final TableField<TypepropRecord, Integer> ID_TYPEPROP = createField("id_typeprop", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>public.typeprop.typeprop</code>.
	 */
	public final TableField<TypepropRecord, String> TYPEPROP_ = createField("typeprop", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "");

	/**
	 * Create a <code>public.typeprop</code> table reference
	 */
	public Typeprop() {
		this("typeprop", null);
	}

	/**
	 * Create an aliased <code>public.typeprop</code> table reference
	 */
	public Typeprop(String alias) {
		this(alias, TYPEPROP);
	}

	private Typeprop(String alias, Table<TypepropRecord> aliased) {
		this(alias, aliased, null);
	}

	private Typeprop(String alias, Table<TypepropRecord> aliased, Field<?>[] parameters) {
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
	public UniqueKey<TypepropRecord> getPrimaryKey() {
		return Keys.TYPEPROP_PK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<TypepropRecord>> getKeys() {
		return Arrays.<UniqueKey<TypepropRecord>>asList(Keys.TYPEPROP_PK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Typeprop as(String alias) {
		return new Typeprop(alias, this);
	}

	/**
	 * Rename this table
	 */
	@Override
	public Typeprop rename(String name) {
		return new Typeprop(name, null);
	}
}
