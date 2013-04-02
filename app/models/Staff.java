package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Staff entity managed by Ebean
 */
@Entity 
public class Staff extends Model {

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
    public String office;
    public String email;
    public String phone;   
    
    /**
     * Generic query helper for entity Staff with id Long
     */
    public static Model.Finder<Long,Staff> find = new Model.Finder<Long,Staff>(Long.class, Staff.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Staff s: Staff.find.orderBy("surname").findList()) {
            options.put(s.id.toString(), s.title + " " + s.forename + " " + s.surname);
        }
        return options;
    }


    /**
     * Return a page of staff
     *
     * @param page Page to display
     * @param pageSize Number of staff per page
     * @param sortBy Staff property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Staff> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

