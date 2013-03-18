package models;

import java.util.*;
import javax.persistence.*;

import play.data.validation.Constraints.*;
import play.db.ebean.*;

@Entity
public class Attendance extends Model{

    @Id  
  public Long id;
  
  public boolean attendance;
  
  public static Model.Finder<Long, Attendance> find = new Model.Finder(
          Long.class, Attendance.class);
  
  public static List<Attendance> all() {
    return find.all();
  }
  
  public static void create(Attendance attendance) {
      attendance.save();
  }
  
  public static void delete(Long id) {
      find.ref(id).delete();
  }
    
}
