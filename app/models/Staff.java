package models;

import java.util.*;
import javax.persistence.*;

import play.data.validation.Constraints.*;
import play.db.ebean.*;

@Entity
public class Staff extends Model{
  @Id  
  public Long id;
  
  public String staffNum;
  public String title;
  public String forename;
  public String surname;
  public String office;
  
  @Email
  public String email;
  
  public String phone;
  
  @OneToMany
  public Module module;
          
  public static Model.Finder<Long, Staff> find = new Model.Finder(
          Long.class, Staff.class);
  
  public static List<Staff> all() {
    return find.all();
  }
  
  public static void create(Staff staff) {
      staff.save();
  }
  
  public static void delete(Long id) {
      find.ref(id).delete();
  }
}
