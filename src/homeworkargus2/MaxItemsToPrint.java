package homeworkargus2;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * При наличии аннотации MaxItemsToPrint, ограничивает сверху количество
 * выводимых полей в MainReflector у Iterable или Map объектов. 
 * Значение аннотации по default - 10.
 *
 * @author pavelgulaev
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {FIELD})
public @interface MaxItemsToPrint {

    int max() default 10;
}
