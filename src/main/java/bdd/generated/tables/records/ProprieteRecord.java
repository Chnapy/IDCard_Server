/*
 * This file is generated by jOOQ.
 */
package bdd.generated.tables.records;

import bdd.generated.tables.Propriete;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
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
public class ProprieteRecord extends UpdatableRecordImpl<ProprieteRecord> implements Record9<Long, Boolean, Boolean, String, Integer, Integer, Integer, Integer, Integer> {

	private static final long serialVersionUID = -262723722;

	/**
	 * Setter for <code>public.propriete.id_propriete</code>.
	 */
	public void setIdPropriete(Long value) {
		set(0, value);
	}

	/**
	 * Getter for <code>public.propriete.id_propriete</code>.
	 */
	public Long getIdPropriete() {
		return (Long) get(0);
	}

	/**
	 * Setter for <code>public.propriete.supprimable</code>.
	 */
	public void setSupprimable(Boolean value) {
		set(1, value);
	}

	/**
	 * Getter for <code>public.propriete.supprimable</code>.
	 */
	public Boolean getSupprimable() {
		return (Boolean) get(1);
	}

	/**
	 * Setter for <code>public.propriete.modifiable</code>.
	 */
	public void setModifiable(Boolean value) {
		set(2, value);
	}

	/**
	 * Getter for <code>public.propriete.modifiable</code>.
	 */
	public Boolean getModifiable() {
		return (Boolean) get(2);
	}

	/**
	 * Setter for <code>public.propriete.nom</code>.
	 */
	public void setNom(String value) {
		set(3, value);
	}

	/**
	 * Getter for <code>public.propriete.nom</code>.
	 */
	public String getNom() {
		return (String) get(3);
	}

	/**
	 * Setter for <code>public.propriete.nbrvalmin</code>.
	 */
	public void setNbrvalmin(Integer value) {
		set(4, value);
	}

	/**
	 * Getter for <code>public.propriete.nbrvalmin</code>.
	 */
	public Integer getNbrvalmin() {
		return (Integer) get(4);
	}

	/**
	 * Setter for <code>public.propriete.nbrvalmax</code>.
	 */
	public void setNbrvalmax(Integer value) {
		set(5, value);
	}

	/**
	 * Getter for <code>public.propriete.nbrvalmax</code>.
	 */
	public Integer getNbrvalmax() {
		return (Integer) get(5);
	}

	/**
	 * Setter for <code>public.propriete.taillevalmin</code>.
	 */
	public void setTaillevalmin(Integer value) {
		set(6, value);
	}

	/**
	 * Getter for <code>public.propriete.taillevalmin</code>.
	 */
	public Integer getTaillevalmin() {
		return (Integer) get(6);
	}

	/**
	 * Setter for <code>public.propriete.taillevalmax</code>.
	 */
	public void setTaillevalmax(Integer value) {
		set(7, value);
	}

	/**
	 * Getter for <code>public.propriete.taillevalmax</code>.
	 */
	public Integer getTaillevalmax() {
		return (Integer) get(7);
	}

	/**
	 * Setter for <code>public.propriete.id_typeprop</code>.
	 */
	public void setIdTypeprop(Integer value) {
		set(8, value);
	}

	/**
	 * Getter for <code>public.propriete.id_typeprop</code>.
	 */
	public Integer getIdTypeprop() {
		return (Integer) get(8);
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
	// Record9 type implementation
	// -------------------------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row9<Long, Boolean, Boolean, String, Integer, Integer, Integer, Integer, Integer> fieldsRow() {
		return (Row9) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row9<Long, Boolean, Boolean, String, Integer, Integer, Integer, Integer, Integer> valuesRow() {
		return (Row9) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field1() {
		return Propriete.PROPRIETE.ID_PROPRIETE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Boolean> field2() {
		return Propriete.PROPRIETE.SUPPRIMABLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Boolean> field3() {
		return Propriete.PROPRIETE.MODIFIABLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return Propriete.PROPRIETE.NOM;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field5() {
		return Propriete.PROPRIETE.NBRVALMIN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field6() {
		return Propriete.PROPRIETE.NBRVALMAX;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field7() {
		return Propriete.PROPRIETE.TAILLEVALMIN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field8() {
		return Propriete.PROPRIETE.TAILLEVALMAX;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field9() {
		return Propriete.PROPRIETE.ID_TYPEPROP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value1() {
		return getIdPropriete();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean value2() {
		return getSupprimable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean value3() {
		return getModifiable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getNom();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value5() {
		return getNbrvalmin();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value6() {
		return getNbrvalmax();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value7() {
		return getTaillevalmin();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value8() {
		return getTaillevalmax();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value9() {
		return getIdTypeprop();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProprieteRecord value1(Long value) {
		setIdPropriete(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProprieteRecord value2(Boolean value) {
		setSupprimable(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProprieteRecord value3(Boolean value) {
		setModifiable(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProprieteRecord value4(String value) {
		setNom(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProprieteRecord value5(Integer value) {
		setNbrvalmin(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProprieteRecord value6(Integer value) {
		setNbrvalmax(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProprieteRecord value7(Integer value) {
		setTaillevalmin(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProprieteRecord value8(Integer value) {
		setTaillevalmax(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProprieteRecord value9(Integer value) {
		setIdTypeprop(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProprieteRecord values(Long value1, Boolean value2, Boolean value3, String value4, Integer value5, Integer value6, Integer value7, Integer value8, Integer value9) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		value9(value9);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------
	/**
	 * Create a detached ProprieteRecord
	 */
	public ProprieteRecord() {
		super(Propriete.PROPRIETE);
	}

	/**
	 * Create a detached, initialised ProprieteRecord
	 */
	public ProprieteRecord(Long idPropriete, Boolean supprimable, Boolean modifiable, String nom, Integer nbrvalmin, Integer nbrvalmax, Integer taillevalmin, Integer taillevalmax, Integer idTypeprop) {
		super(Propriete.PROPRIETE);

		set(0, idPropriete);
		set(1, supprimable);
		set(2, modifiable);
		set(3, nom);
		set(4, nbrvalmin);
		set(5, nbrvalmax);
		set(6, taillevalmin);
		set(7, taillevalmax);
		set(8, idTypeprop);
	}
}
