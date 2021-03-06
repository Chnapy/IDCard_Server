/*
 * This file is generated by jOOQ.
 */
package bdd.generated.tables.records;

import bdd.generated.tables.Typefermeture;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

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
public class TypefermetureRecord extends UpdatableRecordImpl<TypefermetureRecord> implements Record2<Integer, String> {

	private static final long serialVersionUID = -1518935447;

	/**
	 * Setter for <code>public.typefermeture.id_typefermeture</code>.
	 */
	public void setIdTypefermeture(Integer value) {
		set(0, value);
	}

	/**
	 * Getter for <code>public.typefermeture.id_typefermeture</code>.
	 */
	public Integer getIdTypefermeture() {
		return (Integer) get(0);
	}

	/**
	 * Setter for <code>public.typefermeture.typefermeture</code>.
	 */
	public void setTypefermeture(String value) {
		set(1, value);
	}

	/**
	 * Getter for <code>public.typefermeture.typefermeture</code>.
	 */
	public String getTypefermeture() {
		return (String) get(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<Integer, String> fieldsRow() {
		return (Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<Integer, String> valuesRow() {
		return (Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return Typefermeture.TYPEFERMETURE.ID_TYPEFERMETURE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Typefermeture.TYPEFERMETURE.TYPEFERMETURE_;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getIdTypefermeture();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getTypefermeture();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TypefermetureRecord value1(Integer value) {
		setIdTypefermeture(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TypefermetureRecord value2(String value) {
		setTypefermeture(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TypefermetureRecord values(Integer value1, String value2) {
		value1(value1);
		value2(value2);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------
	/**
	 * Create a detached TypefermetureRecord
	 */
	public TypefermetureRecord() {
		super(Typefermeture.TYPEFERMETURE);
	}

	/**
	 * Create a detached, initialised TypefermetureRecord
	 */
	public TypefermetureRecord(Integer idTypefermeture, String typefermeture) {
		super(Typefermeture.TYPEFERMETURE);

		set(0, idTypefermeture);
		set(1, typefermeture);
	}
}
