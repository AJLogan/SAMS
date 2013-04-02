package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * CourseModule entity managed by Ebean
 */
@Entity 
public class CourseModule extends Model {

    @Id
    public Long id;

    @ManyToOne
    public Course course;

    @ManyToOne
    public Module module;
    
    /**
     * Generic query helper for entity CourseModule with id Long
     */
    public static Model.Finder<Long,CourseModule> find = new Model.Finder<Long,CourseModule>(Long.class, CourseModule.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(CourseModule cm: CourseModule.find.orderBy("id").findList()) {
            options.put(cm.id.toString(), cm.course.number.toString());
        }
        return options;
    }


    /**
     * Return a page of courseModules
     *
     * @param page Page to display
     * @param pageSize Number of courseModules per page
     * @param sortBy CourseModule property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<CourseModule> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

