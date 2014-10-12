package homeworkargus2;

import java.util.*;

/** Образец класса для анализа  MainReflector(в дальнейшем можно удалить).
 *
 * @author pavelgulaev
 */
public class Person {

     @MaxItemsToPrint(max=2)
    List<String> annotationsInThisProject;
     
    @MaxItemsToPrint(max=3)
    Map vals;
    
    public Person() {
        vals = new LinkedHashMap();
        vals.put("1", "first");
        vals.put("2", "second");
        vals.put("3", "third");
        vals.put("4", "fourth");
        vals.put("5", "fifth");
        
        annotationsInThisProject = new LinkedList<>();
        annotationsInThisProject.add("MaxItemsToPrint");
        annotationsInThisProject.add("PrintIfNull");
        annotationsInThisProject.add("PrintName");
        annotationsInThisProject.add("PrintMethodResult");
    }
    
    @PrintIfNull
    String birthday = null;
    
    String surname = "Gulyaev";
    
    @PrintName(name="Base name")
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
    
    @PrintMethodResult
    private void returnNullValue() {
        
    }
    
    
}
