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
 */
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ModuleAction {

	public Class<? extends Controleur> servlet();

	public String module() default "";

}
