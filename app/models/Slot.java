package models;

import java.util.*;
import javax.persistence.*;
import play.Logger;

import play.data.validation.Constraints.*;
import play.db.ebean.*;

@Entity
public class Slot extends Model{
    
  @Id  
  public Long id;
  
  public String slotId;
  public String roomId;
  public String year;
  public int semester;
  public int week;
  public int day;
  public int startTime;
  public int duration;
  
  @ManyToOne
  public Module module;
  
  public static Model.Finder<Long, Slot> find = new Model.Finder(
          Long.class, Slot.class);
 
  public static List<Slot> all() {
    return find.all();
  }
  
  public static void create(Slot slot) {
      //creates slotID with a concatination of other fields, for use when searching
      //slot.slotId = slot.year + slot.semester + slot.week + slot.day + slot.startTime + slot.roomId + slot.duration;
      //creates slotID with a concatination of other fields, for use when searching - split with |
      slot.slotId = slot.year + "|"+ slot.semester +"|"+ slot.week + "|" + slot.day +"|" + slot.startTime +"|" + slot.roomId + "|" + slot.duration;
      slot.save();
  }
  
  public static void delete(Long id) {
      find.ref(id).delete();
  }
    
}
