/*
 * This file is generated by jOOQ.
*/
package bdd.generated.tables.records;


import bdd.generated.tables.Domaine;

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
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DomaineRecord extends UpdatableRecordImpl<DomaineRecord> implements Record2<Long, String> {

    private static final long serialVersionUID = 1251306002;

    /**
     * Setter for <code>public.domaine.id_domaine</code>.
     */
    public void setIdDomaine(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.domaine.id_domaine</code>.
     */
    public Long getIdDomaine() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.domaine.ip</code>.
     */
    public void setIp(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.domaine.ip</code>.
     */
    public String getIp() {
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
        return Domaine.DOMAINE.ID_DOMAINE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Domaine.DOMAINE.IP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getIdDomaine();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getIp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DomaineRecord value1(Long value) {
        setIdDomaine(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DomaineRecord value2(String value) {
        setIp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DomaineRecord values(Long value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DomaineRecord
     */
    public DomaineRecord() {
        super(Domaine.DOMAINE);
    }

    /**
     * Create a detached, initialised DomaineRecord
     */
    public DomaineRecord(Long idDomaine, String ip) {
        super(Domaine.DOMAINE);

        set(0, idDomaine);
        set(1, ip);
    }
}
