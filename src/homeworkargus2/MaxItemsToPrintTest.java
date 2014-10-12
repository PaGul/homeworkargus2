package homeworkargus2;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pavelgulaev
 */
public class MaxItemsToPrintTest {

    /**
     * Тестирую аннотацию для Map
     */
    @Test
    public void testMaxMap() throws IllegalAccessException {
        MainReflector ins = new MainReflector();
        String expResult = "TestMap [vals={1=first, 2=second, 3=third}]";
        String result = ins.toString(new TestMap());
        assertEquals(expResult, result);
    }
    
    /**
     * Тестирую аннотацию для Iterable
     */
    @Test
    public void testMaxIterable() throws IllegalAccessException {
        MainReflector ins = new MainReflector();
        String expResult = "TestIterable [ints=[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]]";
        String result = ins.toString(new TestIterable());
        assertEquals(expResult, result);
    }
    
    /**
     * Тестирую без аннотации
     */
    @Test
    public void testWithoutMaxAnnotation() throws IllegalAccessException {
        MainReflector ins = new MainReflector();
        String expResult = "TestMap2 [vals={1=first, 2=second, 3=third, 4=fourth, 5=fifth}]";
        String result = ins.toString(new TestMap2());
        assertEquals(expResult, result);
    }
    
}

class TestMap {

    @MaxItemsToPrint(max=3)
    Map vals;
    public TestMap() {
        vals = new LinkedHashMap();
        vals.put("1", "first");
        vals.put("2", "second");
        vals.put("3", "third");
        vals.put("4", "fourth");
        vals.put("5", "fifth");
    }
    
}

class TestIterable {
    
    @MaxItemsToPrint
    List<Integer> ints;
    public TestIterable() {
        ints = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            ints.add(i);
        }
    }
    
}

class TestMap2 {

    Map vals;
    public TestMap2() {
        vals = new LinkedHashMap();
        vals.put("1", "first");
        vals.put("2", "second");
        vals.put("3", "third");
        vals.put("4", "fourth");
        vals.put("5", "fifth");
    }
    
}