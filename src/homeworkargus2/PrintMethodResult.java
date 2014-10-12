package homeworkargus2;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * При наличии аннотации PrintMethodResult выводится в MainReflector имя метода
 * и его возвращаемое значение, работает только если у метода нет аргументов
 *
 * @author pavelgulaev
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {METHOD})
public @interface PrintMethodResult {

}
