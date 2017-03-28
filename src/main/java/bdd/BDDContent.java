/*
 * 
 * 
 * 
 */
package bdd;

import exceptions.ExceptionGeree;
import org.jooq.DSLContext;

/**
 * BDDContent.java
 *
 * Contenu d'une transaction lors de requêtes à la base de donnée.
 *
 */
public interface BDDContent {

	/**
	 * Contient les requêtes (et de manière générale tout le code) lancées lors
	 * de la transaction.
	 * En cas d'exception la transaction roolback. Sinon commit, et ce sans
	 * action particulière à faire.
	 *
	 * @param create Objet JOOQ configuré utilisant une connexion SQL. Permet de
	 *               faire les actions SQL habituelles.
	 * @return Le résultat des requêtes dans le format voulu
	 * @throws Exception Pouvant être lancée manuellement
	 *                   ({@link ExceptionGeree}) ou non. Dans tous les cas
	 *                   interrompt la transaction + roolback
	 */
	public Object act(DSLContext create) throws Exception;

}
