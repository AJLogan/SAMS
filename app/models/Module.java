package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Module entity managed by Ebean
 */
@Entity 
public class Module extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String crn;
    
    @Constraints.Required
    public String code;
    @Constraints.Required
    public String name;

    @ManyToOne
    public Staff staff;
    
    /**
     * Generic query helper for entity Module with id Long
     */
    public static Model.Finder<Long,Module> find = new Model.Finder<Long,Module>(Long.class, Module.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Module m: Module.find.orderBy("crn").findList()) {
            options.put(m.id.toString(), m.crn + " - " + m.name);
        }
        return options;
    }

    /**
     * Return a page of modules
     *
     * @param page Page to display
     * @param pageSize Number of modules per page
     * @param sortBy Module property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the forename column
     */
    public static Page<Module> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                // .ilike("crn", "%" + filter + "%")
                // .orderBy(sortBy + " " + order)
                // .fetch("staff")
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

