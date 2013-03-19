package models;

import java.util.*;
import javax.persistence.*;

import play.data.validation.Constraints.*;
import play.db.ebean.*;

@Entity
public class Module extends Model{
    
  @Id  
  public Long id;
  
  public String crn;
  public String moduleCode;
  public String moduleName;
  
  @ManyToMany(cascade=CascadeType.ALL)
    public Set<Course> courseModule = new HashSet<Course>();  
  
  
  @ManyToOne
  public Staff staff;
  
  public static Model.Finder<Long, Module> find = new Model.Finder(
          Long.class, Module.class);
  
  public static List<Module> all() {
    return find.all();
  }
  
  public static void create(Module module) {
      module.save();
  }
  
  public static void delete(Long id) {
      find.ref(id).delete();
  }
}