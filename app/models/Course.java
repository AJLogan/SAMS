package models;

import java.util.*;
import javax.persistence.*;
import play.Logger;

import play.data.validation.Constraints.*;
import play.db.ebean.*;

@Entity
public class Course extends Model{
  
  @Id  
  public Long id;
  
  public String courseId;
  public String startYear;
  public String name;
  public String duration;
  public String modeStudy;
  
  @ManyToMany(mappedBy="courseModule")
  public Set<Module> courseModule = new HashSet<Module>();
  
  public static Model.Finder<Long, Course> find = new Model.Finder(
          Long.class, Course.class);
 
  public static List<Course> all() {
    return find.all();
  }
  
  public static void create(Course course) {
      course.save();
  }
  
  public static void delete(Long id) {
      find.ref(id).delete();
  }
    
}
