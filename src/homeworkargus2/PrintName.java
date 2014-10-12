package homeworkargus2;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * При наличии аннотации PrintNameAnnotation, выводит в MainReflector поле с
 * именем, указанным в аннотации (значение name)
 *
 * @author pavelgulaev
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {FIELD})
public @interface PrintName {

    String name();
}
