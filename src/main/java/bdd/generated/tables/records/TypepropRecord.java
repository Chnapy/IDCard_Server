/*
 * This file is generated by jOOQ.
*/
package bdd.generated.tables.records;


import bdd.generated.tables.Typeprop;

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
public class TypepropRecord extends UpdatableRecordImpl<TypepropRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = -1649707867;

    /**
     * Setter for <code>public.typeprop.id_typeprop</code>.
     */
    public void setIdTypeprop(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.typeprop.id_typeprop</code>.
     */
    public Integer getIdTypeprop() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.typeprop.typeprop</code>.
     */
    public void setTypeprop(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.typeprop.typeprop</code>.
     */
    public String getTypeprop() {
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
        return Typeprop.TYPEPROP.ID_TYPEPROP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Typeprop.TYPEPROP.TYPEPROP_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getIdTypeprop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getTypeprop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypepropRecord value1(Integer value) {
        setIdTypeprop(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypepropRecord value2(String value) {
        setTypeprop(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypepropRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TypepropRecord
     */
    public TypepropRecord() {
        super(Typeprop.TYPEPROP);
    }

    /**
     * Create a detached, initialised TypepropRecord
     */
    public TypepropRecord(Integer idTypeprop, String typeprop) {
        super(Typeprop.TYPEPROP);

        set(0, idTypeprop);
        set(1, typeprop);
    }
}