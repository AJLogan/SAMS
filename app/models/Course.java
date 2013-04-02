package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Course entity managed by Ebean
 */
@Entity 
public class Course extends Model {

    @Id
    public Long id;
    
    @Constraints.Required
    public String number;
    @Constraints.Required
    @Constraints.MaxLength(4)
    public String start_year;
    @Constraints.Required
    public String name;
    @Constraints.Required
    public int duration;
    @Constraints.Required
    public String mode_study;    
    
    /**
     * Generic query helper for entity Course with id Long
     */
    public static Model.Finder<Long,Course> find = new Model.Finder<Long,Course>(Long.class, Course.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Course c: Course.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }

    /**
     * Return a page of courses
     *
     * @param page Page to display
     * @param pageSize Number of courses per page
     * @param sortBy Course property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Course> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

