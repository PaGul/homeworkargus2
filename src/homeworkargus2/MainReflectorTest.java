/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworkargus2;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pavelgulaev
 */
public class MainReflectorTest {

    
    MainReflector instance;
    @Before
    public void setUp() {
        instance = new MainReflector();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testToString() throws Exception {
        String expResult = "PersonTest [annotationsInThisProject=[MaxItemsToPrint, PrintIfNull], vals={1=first, 2=second}, birthday=null, surname=Gulyaev, Base name=Paul, patronymic=Alexander, getInitials=Gulyaev P. A]";
        String result = instance.toString(new PersonTest());
        assertEquals(expResult, result);
    }
    
    @Test(expected = NullPointerException.class)
    public void testToStringWithNullArgument() throws IllegalAccessException{
        String result = instance.toString(null);
    }

}

class PersonTest {

    @MaxItemsToPrint(max = 2)
    List<String> annotationsInThisProject;

    @MaxItemsToPrint(max = 2)
    Map vals;

    public PersonTest() {
        vals = new LinkedHashMap();
        vals.put("1", "first");
        vals.put("2", "second");
        vals.put("3", "third");

        annotationsInThisProject = new LinkedList<>();
        annotationsInThisProject.add("MaxItemsToPrint");
        annotationsInThisProject.add("PrintIfNull");
        annotationsInThisProject.add("PrintName");
        annotationsInThisProject.add("PrintMethodResult");
    }

    @PrintIfNull
    String birthday = null;

    String surname = "Gulyaev";

    @PrintName(name = "Base name")
    String name = "Paul";

    String patronymic = "Alexander";

    String test = null;

    @PrintMethodResult
    public String getInitials() {
        String initials = surname + " " + name.charAt(0) + ". " + patronymic.charAt(0);
        return initials;
    }

    @PrintMethodResult
    public String getSurname(String name) {
        return "Значение этого метода не будет напечатано, несмотря на аннотацию";
    }

}
