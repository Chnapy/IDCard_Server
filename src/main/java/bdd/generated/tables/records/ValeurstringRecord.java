/*
 * This file is generated by jOOQ.
 */
package bdd.generated.tables.records;

import bdd.generated.tables.Valeurstring;

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
public class ValeurstringRecord extends UpdatableRecordImpl<ValeurstringRecord> implements Record2<Long, String> {

	private static final long serialVersionUID = 375383373;

	/**
	 * Setter for <code>public.valeurstring.id_valeurtypee</code>.
	 */
	public void setIdValeurtypee(Long value) {
		set(0, value);
	}

	/**
	 * Getter for <code>public.valeurstring.id_valeurtypee</code>.
	 */
	public Long getIdValeurtypee() {
		return (Long) get(0);
	}

	/**
	 * Setter for <code>public.valeurstring.valeur</code>.
	 */
	public void setValeur(String value) {
		set(1, value);
	}

	/**
	 * Getter for <code>public.valeurstring.valeur</code>.
	 */
	public String getValeur() {
		return (String) get(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Long> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<Long, String> fieldsRow() {
		return (Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<Long, String> valuesRow() {
		return (Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field1() {
		return Valeurstring.VALEURSTRING.ID_VALEURTYPEE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Valeurstring.VALEURSTRING.VALEUR;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value1() {
		return getIdValeurtypee();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getValeur();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ValeurstringRecord value1(Long value) {
		setIdValeurtypee(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ValeurstringRecord value2(String value) {
		setValeur(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ValeurstringRecord values(Long value1, String value2) {
		value1(value1);
		value2(value2);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------
	/**
	 * Create a detached ValeurstringRecord
	 */
	public ValeurstringRecord() {
		super(Valeurstring.VALEURSTRING);
	}

	/**
	 * Create a detached, initialised ValeurstringRecord
	 */
	public ValeurstringRecord(Long idValeurtypee, String valeur) {
		super(Valeurstring.VALEURSTRING);

		set(0, idValeurtypee);
		set(1, valeur);
	}
}
