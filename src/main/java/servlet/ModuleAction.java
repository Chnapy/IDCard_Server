/*
 * 
 * 
 * 
 */
package servlet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ModuleAction.java
 *
 * Annotation utilisée par les enfants de {@link ControleurAction}.
 * Définie le {@link Controleur} (servlet) lié à l'action. Ainsi que le nom du
 * module (par défaut "").
 *
 */
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ModuleAction {

	/**
	 *
	 * @return
	 */
	public Class<? extends Controleur> servlet();

	/**
	 *
	 * @return
	 */
	public String module() default "";

}
