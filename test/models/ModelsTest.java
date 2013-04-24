package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

/**
 *
 * @author Andrew Logan
 */

public class ModelsTest extends WithApplication{
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }
    
    @Test
    public void createAndRetrieveStudent() {
      new Student("B00520366", "Mr", "Andrew", "Logan", "logan-a4@email.ulster.ac.uk", "Male", "15 Park Lane", "Crossgar Road", 
              "Saintfield", "BT24 7PR", false).save();
      Student student = Student.find.where().eq("number", "B00520366").findUnique();
      assertNotNull(student);
      assertEquals("B00520366", student.number);
      assertEquals("Andrew", student.forename);
      assertEquals("Logan", student.surname);
      assertEquals("logan-a4@email.ulster.ac.uk", student.email); 
      assertEquals("Male", student.gender);
      assertEquals("15 Park Lane", student.address_line_1);
      assertEquals("Crossgar Road", student.address_line_2);
      assertEquals("Saintfield", student.address_line_3);
      assertEquals("BT24 7PR", student.postcode);
      assertEquals(false, student.international);
  }
    
}