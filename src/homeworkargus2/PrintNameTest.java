package homeworkargus2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pavelgulaev
 */
public class PrintNameTest {
    MainReflector ins = new MainReflector();
    @Before
    public void setUp() {
        ins = new MainReflector();
    }

    /**
     * Тестирую работу аннотации PrintName
     */
    @Test
    public void testName() throws IllegalAccessException {
        String expResult = "ChangeName [Full name=Gulyaev Pavel]";
        String result = ins.toString(new ChangeName());
        assertEquals(expResult, result);
    }
}

class ChangeName {
    @PrintName(name = "Full name")
    String name = "Gulyaev Pavel";
}
