/*
 * This file is generated by jOOQ.
*/
package bdd.generated.tables.records;


import bdd.generated.tables.Valeurmdp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
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
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ValeurmdpRecord extends UpdatableRecordImpl<ValeurmdpRecord> implements Record3<Long, String, String> {

    private static final long serialVersionUID = 578634722;

    /**
     * Setter for <code>public.valeurmdp.id_valeurtypee</code>.
     */
    public void setIdValeurtypee(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.valeurmdp.id_valeurtypee</code>.
     */
    public Long getIdValeurtypee() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.valeurmdp.valeur</code>.
     */
    public void setValeur(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.valeurmdp.valeur</code>.
     */
    public String getValeur() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.valeurmdp.salt</code>.
     */
    public void setSalt(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.valeurmdp.salt</code>.
     */
    public String getSalt() {
        return (String) get(2);
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
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Long, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Long, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Valeurmdp.VALEURMDP.ID_VALEURTYPEE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Valeurmdp.VALEURMDP.VALEUR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Valeurmdp.VALEURMDP.SALT;
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
    public String value3() {
        return getSalt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValeurmdpRecord value1(Long value) {
        setIdValeurtypee(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValeurmdpRecord value2(String value) {
        setValeur(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValeurmdpRecord value3(String value) {
        setSalt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValeurmdpRecord values(Long value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ValeurmdpRecord
     */
    public ValeurmdpRecord() {
        super(Valeurmdp.VALEURMDP);
    }

    /**
     * Create a detached, initialised ValeurmdpRecord
     */
    public ValeurmdpRecord(Long idValeurtypee, String valeur, String salt) {
        super(Valeurmdp.VALEURMDP);

        set(0, idValeurtypee);
        set(1, valeur);
        set(2, salt);
    }
}
