package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.*;

import models.*;

/**
 * Manage staff
 */
public class staffController extends Controller {
        
    /**
     * Handle default path requests, redirect to staff list
     */
    public static Result index() {
        return redirect(routes.staffController.list(0, "number", "asc", ""));
    }
    /**
     * Display the paginated list of staff.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on staff names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            listStaff.render(
                Staff.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Staff.
     *
     * @param id Id of the staff to edit
     */
    public static Result edit(Long id) {
        Form<Staff> staffForm = form(Staff.class).fill(
            Staff.find.byId(id)
        );
        return ok(
            editStaffForm.render(id, staffForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the staff to edit
     */
    public static Result update(Long id) {
        Form<Staff> staffForm = form(Staff.class).bindFromRequest();
        if(staffForm.hasErrors()) {
            return badRequest(editStaffForm.render(id, staffForm));
        }
        staffForm.get().update(id);
        flash("success", "Staff " + staffForm.get().forename + " " + staffForm.get().surname + " has been updated");
        return redirect(routes.staffController.list(0, "number", "asc", ""));
    }
    
    /**
     * Display the 'new staff form'.
     */
    public static Result create() {
        Form<Staff> staffForm = form(Staff.class);
        return ok(
            createStaffForm.render(staffForm)
        );
    }
    
    /**
     * Handle the 'new staff form' submission 
     */
    public static Result save() {
        Form<Staff> staffForm = form(Staff.class).bindFromRequest();
        if(staffForm.hasErrors()) {
            return badRequest(createStaffForm.render(staffForm));
        }
        staffForm.get().save();
        flash("success", "Staff " + staffForm.get().forename + " " + staffForm.get().surname + " has been created");
        return redirect(routes.staffController.list(0, "number", "asc", ""));
    }
    
    /**
     * Handle staff deletion
     */
    public static Result delete(Long id) {
        Staff.find.ref(id).delete();
        flash("success", "Staff has been deleted");
        return redirect(routes.staffController.list(0, "number", "asc", ""));
    }
}
            
