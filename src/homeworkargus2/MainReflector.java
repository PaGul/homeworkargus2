package homeworkargus2;

import java.lang.reflect.*;
import java.util.Iterator;
import java.util.Map;
/**
 *
 * @author pavelgulaev
 */
public class MainReflector {

    private boolean firstPrintElement = true;

    //В StringBuilder добавляют значения toString и resultApppend
    private StringBuilder result = new StringBuilder();

    //Вынес экзепляр в класс, чтобы не перекидывать его параметром из метода в метод
    private Object inst;

    /**
     * Основной метод, переводящий экземпляр класса в строку
     *
     * @param <T> тип класса
     * @param inst экзепляр класса
     * @return строка-результат
     * @throws IllegalAccessException при проблемах с рефлексией
     * @throws NullPointerException если T inst = null
     */
    public <T extends Object> String toString(T inst) throws IllegalAccessException {
        this.inst = inst;
        Class cl = inst.getClass();
        result.append(cl.getSimpleName() + " [");

        fieldsAnalyze();
        methodsAnalyze();

        result.append("]");
        return result.toString();
    }

    /**
     * Анализирует аннотации полей. Если поле имеет значение null и не имеет
     * аннотации PrintIfNull, то это значение не выводится. Анализирует аннотации
     * PrintIfNull, PrintNameAnnotation и MaxItemsToPrint
     *
     * @throws IllegalAccessException
     */
    private void fieldsAnalyze() throws IllegalAccessException {
        Class cl = inst.getClass();
        Field[] fields = cl.getDeclaredFields();
        String name;
        for (Field field : fields) {
            Object value = field.get(inst);

            //Если значение null и нет PrintIfNull аннотации, то поле не печатаю
            if ((value == null) && (!field.isAnnotationPresent(PrintIfNull.class))) {
                continue;
            }

            name = analyzePrintNameAnnotation(field);
            value = analyzeMaxItemsToPrint(field, value);
            appendResult(name, value);

        }
    }

    /**
     * При наличии аннотации PrintNameAnnotation, выводит поле с именем,
     * указанным в аннотации
     *
     * @param field поле для анализа
     * @return
     */
    private String analyzePrintNameAnnotation(Field field) {
        if (field.isAnnotationPresent(PrintName.class)) {
            PrintName annotation = field.getAnnotation(PrintName.class);
            return annotation.name();
        } else {
            return field.getName();
        }
    }

    /**
     * При наличии аннотации MaxItemsToPrint, ограничивает сверху количество
     * выводимых полей у Iterable или Map объектов. Значение аннотации по default - 10.
     *
     * @param field анализируемое поле
     * @param value значение экзепляра класса
     * @return
     */
    private Object analyzeMaxItemsToPrint(Field field, Object value) {
        if (field.isAnnotationPresent(MaxItemsToPrint.class)) {
            String begin;
            String end;
            Iterator it;
            if (Iterable.class.isInstance(value)) {
                begin = "[";
                end = "]";
                it = ((Iterable) value).iterator();
            } else if ((Map.class.isInstance(value))) {
                begin = "{";
                end = "}";
                it = ((Map) value).entrySet().iterator();
            } else {
                return value;
            }

            StringBuilder iterableOrMapValues = new StringBuilder();
            iterableOrMapValues.append(begin);
            MaxItemsToPrint annotation = field.getAnnotation(MaxItemsToPrint.class);
            int maxSize = annotation.max();
            for (int i = 0; ((i < maxSize) && (it.hasNext())); i++) {
                if (i != 0) {
                    iterableOrMapValues.append(", ");
                }
                iterableOrMapValues.append(it.next());
            }
            iterableOrMapValues.append(end);
            value = iterableOrMapValues;
        }
        return value;
    }

    /**
     * Анализирует аннотации методов (PrintMethodResult)
     */
    private void methodsAnalyze() {
        Method[] methods = inst.getClass().getDeclaredMethods();
        for (Method method : methods) {
            analyzePrintMethodResult(method);
        }
    }

    /**
     * При наличии аннотации PrintMethodResult выводится имя метода и его
     * возвращаемое значение, работает только если у метода нет аргументов
     *
     * @param method
     */
    private void analyzePrintMethodResult(Method method) {
        if ((method.isAnnotationPresent(PrintMethodResult.class)) && (method.getParameterTypes().length == 0)) {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            String name = method.getName();
            Object value = null;
            try {
                value = method.invoke(inst, null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            appendResult(name, value);
        }
    }

    /**
     * Добавляет очередную запись в StringBuilder
     *
     * @param name
     * @param value
     */
    private void appendResult(String name, Object value) {
        if (firstPrintElement) {
            firstPrintElement = false;
        } else {
            result.append(", ");
        }
        result.append(name + "=" + value);
    }
}
