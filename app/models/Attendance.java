package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Attendance entity managed by Ebean
 */
@Entity 
public class Attendance extends Model {

    @Id
    public Long id;
    
    @Constraints.Required
    public boolean attendance;
    
    @ManyToOne
    public Student student;

    @ManyToOne
    public Slot slot;
    /**
     * Generic query helper for entity Attendance with id Long
     */
    public static Model.Finder<Long,Attendance> find = new Model.Finder<Long,Attendance>(Long.class, Attendance.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Attendance a: Attendance.find.orderBy("attendance").findList()) {
            options.put(a.id.toString(), a.student.number);
        }
        return options;
    }


    /**
     * Return a page of attendances
     *
     * @param page Page to display
     * @param pageSize Number of attendances per page
     * @param sortBy Attendance property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Attendance> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

