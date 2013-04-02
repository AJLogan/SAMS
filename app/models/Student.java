package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Student entity managed by Ebean
 */
@Entity 
public class Student extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String number;
    
    @Constraints.Required
    public String title;
    @Constraints.Required
    public String forename;
    @Constraints.Required
    public String surname;

    public String email;

    public String gender;

    public String address_line_1;
    public String address_line_2;
    public String address_line_3;
    public String postcode;

    public boolean international;


    @ManyToOne
    public Course course;
    
    /**
     * Generic query helper for entity Student with id Long
     */
    public static Model.Finder<Long,Student> find = new Model.Finder<Long,Student>(Long.class, Student.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Student s: Student.find.orderBy("number").findList()) {
            options.put(s.id.toString(), s.number);
        }
        return options;
    }

    /**
     * Return a page of students
     *
     * @param page Page to display
     * @param pageSize Number of students per page
     * @param sortBy Student property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the forename column
     */
    public static Page<Student> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("forename", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("course")
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

