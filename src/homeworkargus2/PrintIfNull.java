package homeworkargus2;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Если поле имеет значение null и не имеет
 * аннотации PrintIfNull, то это значение не выводится в MainReflector.
 *
 * @author pavelgulaev
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={FIELD})
public @interface PrintIfNull {
}
