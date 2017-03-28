/*
 * This file is generated by jOOQ.
*/
package bdd.generated.tables;


import bdd.generated.Keys;
import bdd.generated.Public;
import bdd.generated.tables.records.DomaineRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
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
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Domaine extends TableImpl<DomaineRecord> {

    private static final long serialVersionUID = 1916554174;

    /**
     * The reference instance of <code>public.domaine</code>
     */
    public static final Domaine DOMAINE = new Domaine();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DomaineRecord> getRecordType() {
        return DomaineRecord.class;
    }

    /**
     * The column <code>public.domaine.id_domaine</code>.
     */
    public final TableField<DomaineRecord, Long> ID_DOMAINE = createField("id_domaine", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('domaine_id_domaine_seq_1'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.domaine.ip</code>.
     */
    public final TableField<DomaineRecord, String> IP = createField("ip", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "");

    /**
     * Create a <code>public.domaine</code> table reference
     */
    public Domaine() {
        this("domaine", null);
    }

    /**
     * Create an aliased <code>public.domaine</code> table reference
     */
    public Domaine(String alias) {
        this(alias, DOMAINE);
    }

    private Domaine(String alias, Table<DomaineRecord> aliased) {
        this(alias, aliased, null);
    }

    private Domaine(String alias, Table<DomaineRecord> aliased, Field<?>[] parameters) {
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
    public Identity<DomaineRecord, Long> getIdentity() {
        return Keys.IDENTITY_DOMAINE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DomaineRecord> getPrimaryKey() {
        return Keys.DOMAINE_PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DomaineRecord>> getKeys() {
        return Arrays.<UniqueKey<DomaineRecord>>asList(Keys.DOMAINE_PK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Domaine as(String alias) {
        return new Domaine(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Domaine rename(String name) {
        return new Domaine(name, null);
    }
}
