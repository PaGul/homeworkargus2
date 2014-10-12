package homeworkargus2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pavelgulaev
 */
public class PrintMethodResultTest {
    
    MainReflector ins = new MainReflector();
    @Before
    public void setUp() {
        ins = new MainReflector();
    }
    

    @Test
    public void testPrintResult() throws IllegalAccessException {
        String res = ins.toString(new MethodWithAnnotation());
        String expRes = "MethodWithAnnotation [getLetters=ab]";
        assertEquals(expRes, res);
    }
    
    @Test
    public void testDontPrintResult() throws IllegalAccessException {
        String res = ins.toString(new MethodWithoutAnnotation());
        String expRes = "MethodWithoutAnnotation []";
        assertEquals(expRes, res);
    }
    
    @Test
    public void testDontPrintResultDespiteTheAnnotation() throws IllegalAccessException {
        String res = ins.toString(new MethodWithWrongArgs());
        String expRes = "MethodWithWrongArgs []";
        assertEquals(expRes, res);
    }
    
}

class MethodWithAnnotation {
     @PrintMethodResult
    public String getLetters() {
        return "a"+"b";
    }
}

class MethodWithoutAnnotation {
    public String getLetters() {
        return "a"+"b";
    }
}

class MethodWithWrongArgs {
    String name = null;
    @PrintMethodResult
    public String getSurname(String name) {
        return "Значение этого метода не будет напечатано, несмотря на аннотацию";
    }
}