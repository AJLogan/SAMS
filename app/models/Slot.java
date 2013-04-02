package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


import com.avaje.ebean.*;


/**
 * Slot entity managed by Ebean
 */
@Entity 
public class Slot extends Model {

    @Id
    public Long id;
    
    @Constraints.Required
    public String number;
    @Constraints.Required
    public String room;
    @Constraints.Required
    @Constraints.MaxLength(4)
    public String year;
    @Constraints.Required
    public int semester;
    @Constraints.Required
    public int week;    
    @Constraints.Required
    public String day;    
    @Constraints.Required
    @Constraints.MaxLength(4)
    public String start_time;
    @Constraints.Required
    public int duration;

    @ManyToOne
    public Module module;
    
    /**
     * Generic query helper for entity Slot with id Long
     */
    public static Model.Finder<Long,Slot> find = new Model.Finder<Long,Slot>(Long.class, Slot.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Slot s: Slot.find.orderBy("number").findList()) {
            options.put(s.id.toString(), s.number);
        }
        return options;
    }


    /**
     * Return a page of slots
     *
     * @param page Page to display
     * @param pageSize Number of slots per page
     * @param sortBy Slot property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Slot> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}

