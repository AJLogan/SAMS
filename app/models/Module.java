package models;

import java.util.*;
import javax.persistence.*;

import play.data.validation.Constraints.*;
import play.db.ebean.*;

@Entity
public class Module extends Model{
   @ManyToMany
  public Course course;
    
  @Id  
  public Long id;
  
  public String crn;
  public String moduleCode;
  public String moduleName;
  
  public static Model.Finder<Long, Module> find = new Model.Finder(
          Long.class, Module.class);
  
  public static List<Module> all() {
    return find.all();
  }
  
  public static void create(Module student) {
      student.save();
  }
  
  public static void delete(Long id) {
      find.ref(id).delete();
  }
    
}
