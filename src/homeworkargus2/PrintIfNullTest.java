package homeworkargus2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pavelgulaev
 */
public class PrintIfNullTest {

    @Test
    public void testPrintNull() throws IllegalAccessException {
        MainReflector ins = new MainReflector();
        String res = ins.toString(new WithAnnotation());
        String expRes = "WithAnnotation [birthday=null]";
        assertEquals(expRes, res);
    }
    
    @Test
    public void testNotPrintNull() throws IllegalAccessException {
        MainReflector ins = new MainReflector();
        String res = ins.toString(new WithoutAnnotation());
        String expRes = "WithoutAnnotation []";
        assertEquals(expRes, res);
    }
    
}

class WithAnnotation {
    @PrintIfNull
    String birthday = null;
}

class WithoutAnnotation {
    String birthday = null;
}
