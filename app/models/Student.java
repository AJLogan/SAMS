package models;

import java.util.*;
import javax.persistence.*;

import play.data.validation.Constraints.*;
import play.db.ebean.*;

@Entity
public class Student extends Model{
  @Id  
  public Long id;
  
  public String studentId;
  
  public String title;
  public String forename;
  public String surname;
  
  @Email
  public String email;
  
  public String gender;
  
  public String addressLine1;
  public String addressLine2;
  public String addressLine3;
  public String postcode;
  
  public boolean international;
          
  public static Model.Finder<Long, Student> find = new Model.Finder(
          Long.class, Student.class);
  
  public static List<Student> all() {
    return find.all();
  }
  
  public static void create(Student student) {
      student.save();
  }
  
  public static void delete(Long id) {
      find.ref(id).delete();
  }
    
}
