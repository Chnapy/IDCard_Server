/*
 * This file is generated by jOOQ.
 */
package bdd.generated.tables;

import bdd.generated.Keys;
import bdd.generated.Public;
import bdd.generated.tables.records.TypefermetureRecord;

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
public class Typefermeture extends TableImpl<TypefermetureRecord> {

	private static final long serialVersionUID = 1227481123;

	/**
	 * The reference instance of <code>public.typefermeture</code>
	 */
	public static final Typefermeture TYPEFERMETURE = new Typefermeture();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<TypefermetureRecord> getRecordType() {
		return TypefermetureRecord.class;
	}

	/**
	 * The column <code>public.typefermeture.id_typefermeture</code>.
	 */
	public final TableField<TypefermetureRecord, Integer> ID_TYPEFERMETURE = createField("id_typefermeture", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>public.typefermeture.typefermeture</code>.
	 */
	public final TableField<TypefermetureRecord, String> TYPEFERMETURE_ = createField("typefermeture", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "");

	/**
	 * Create a <code>public.typefermeture</code> table reference
	 */
	public Typefermeture() {
		this("typefermeture", null);
	}

	/**
	 * Create an aliased <code>public.typefermeture</code> table reference
	 */
	public Typefermeture(String alias) {
		this(alias, TYPEFERMETURE);
	}

	private Typefermeture(String alias, Table<TypefermetureRecord> aliased) {
		this(alias, aliased, null);
	}

	private Typefermeture(String alias, Table<TypefermetureRecord> aliased, Field<?>[] parameters) {
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
	public UniqueKey<TypefermetureRecord> getPrimaryKey() {
		return Keys.TYPEFERMETURE_PK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<TypefermetureRecord>> getKeys() {
		return Arrays.<UniqueKey<TypefermetureRecord>>asList(Keys.TYPEFERMETURE_PK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Typefermeture as(String alias) {
		return new Typefermeture(alias, this);
	}

	/**
	 * Rename this table
	 */
	@Override
	public Typefermeture rename(String name) {
		return new Typefermeture(name, null);
	}
}
