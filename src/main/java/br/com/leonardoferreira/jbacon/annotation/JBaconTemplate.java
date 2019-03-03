package br.com.leonardoferreira.jbacon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define a template method
 *
 * <br/><br/>
 *
 * Validations:
 * <ul>
 *     <li>Template methods should return the same type of factory</li>
 *     <li>Template methods should not have parameters</li>
 *     <li>Template methods should be protected</li>
 * </ul>
 *
 * Example:
 *
 * <pre>
 * {@code
 *     public class MyFactory extends JBacon<MyObject> {
 *
 *         ...
 *
 *         @JBaconTemplate
 *         protected MyObject myTemplate() {
 *             return new MyObject("basedOnMyTemplate");
 *         }
 *
 *     }
 *
 *     ...
 *
 *     myFactory.build("myTemplate"); // => MyFactory(txt = "basedOnMyTemplate")
 *     myFactory.create("myTemplate"); // => MyFactory(txt = "basedOnMyTemplate")
 *
 *     ...
 * }
 *
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JBaconTemplate {
    String value() default "METHOD_NAME";
}
