/*
 * This file is generated by jOOQ.
 */
package bdd.generated.tables.records;

import bdd.generated.tables.User_1;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
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
public class User_1Record extends UpdatableRecordImpl<User_1Record> implements Record4<Long, Date, Date, Long> {

	private static final long serialVersionUID = -488756857;

	/**
	 * Setter for <code>public.user_1.id_user</code>.
	 */
	public void setIdUser(Long value) {
		set(0, value);
	}

	/**
	 * Getter for <code>public.user_1.id_user</code>.
	 */
	public Long getIdUser() {
		return (Long) get(0);
	}

	/**
	 * Setter for <code>public.user_1.dateinscription</code>.
	 */
	public void setDateinscription(Date value) {
		set(1, value);
	}

	/**
	 * Getter for <code>public.user_1.dateinscription</code>.
	 */
	public Date getDateinscription() {
		return (Date) get(1);
	}

	/**
	 * Setter for <code>public.user_1.datederniereconnexion</code>.
	 */
	public void setDatederniereconnexion(Date value) {
		set(2, value);
	}

	/**
	 * Getter for <code>public.user_1.datederniereconnexion</code>.
	 */
	public Date getDatederniereconnexion() {
		return (Date) get(2);
	}

	/**
	 * Setter for <code>public.user_1.nbrconnexion</code>.
	 */
	public void setNbrconnexion(Long value) {
		set(3, value);
	}

	/**
	 * Getter for <code>public.user_1.nbrconnexion</code>.
	 */
	public Long getNbrconnexion() {
		return (Long) get(3);
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
	// Record4 type implementation
	// -------------------------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Long, Date, Date, Long> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Long, Date, Date, Long> valuesRow() {
		return (Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field1() {
		return User_1.USER_1.ID_USER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Date> field2() {
		return User_1.USER_1.DATEINSCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Date> field3() {
		return User_1.USER_1.DATEDERNIERECONNEXION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field4() {
		return User_1.USER_1.NBRCONNEXION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value1() {
		return getIdUser();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date value2() {
		return getDateinscription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date value3() {
		return getDatederniereconnexion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value4() {
		return getNbrconnexion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User_1Record value1(Long value) {
		setIdUser(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User_1Record value2(Date value) {
		setDateinscription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User_1Record value3(Date value) {
		setDatederniereconnexion(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User_1Record value4(Long value) {
		setNbrconnexion(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User_1Record values(Long value1, Date value2, Date value3, Long value4) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------
	/**
	 * Create a detached User_1Record
	 */
	public User_1Record() {
		super(User_1.USER_1);
	}

	/**
	 * Create a detached, initialised User_1Record
	 */
	public User_1Record(Long idUser, Date dateinscription, Date datederniereconnexion, Long nbrconnexion) {
		super(User_1.USER_1);

		set(0, idUser);
		set(1, dateinscription);
		set(2, datederniereconnexion);
		set(3, nbrconnexion);
	}
}
