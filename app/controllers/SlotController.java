package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.*;

import models.*;

/**
 * Manage slots
 */
public class slotController extends Controller {
        
    /**
     * Handle default path requests, redirect to computers list
     */
    public static Result index() {
        return redirect(routes.slotController.list(0, "number", "asc", ""));
    }
    /**
     * Display the paginated list of slots.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on slot names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            listSlots.render(
                Slot.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Slot.
     *
     * @param id Id of the slot to edit
     */
    public static Result edit(Long id) {
        Form<Slot> slotForm = form(Slot.class).fill(
            Slot.find.byId(id)
        );
        return ok(
            editSlotForm.render(id, slotForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the slot to edit
     */
    public static Result update(Long id) {
        Form<Slot> slotForm = form(Slot.class).bindFromRequest();
        if(slotForm.hasErrors()) {
            return badRequest(editSlotForm.render(id, slotForm));
        }
        slotForm.get().update(id);
        flash("success", "Slot " + slotForm.get().number + " has been updated");
        return redirect(routes.slotController.list(0, "number", "asc", ""));
    }
    
    /**
     * Display the 'new slot form'.
     */
    public static Result create() {
        Form<Slot> slotForm = form(Slot.class);
        return ok(
            createSlotForm.render(slotForm)
        );
    }
    
    /**
     * Handle the 'new slot form' submission 
     */
    public static Result save() {
        Form<Slot> slotForm = form(Slot.class).bindFromRequest();
        if(slotForm.hasErrors()) {
            return badRequest(createSlotForm.render(slotForm));
        }
        slotForm.get().save();
        flash("success", "Slot " + slotForm.get().number + " has been created");
        return redirect(routes.slotController.list(0, "number", "asc", ""));
    }
    
    /**
     * Handle slot deletion
     */
    public static Result delete(Long id) {
        Slot.find.ref(id).delete();
        flash("success", "Slot has been deleted");
        return redirect(routes.slotController.list(0, "number", "asc", ""));
    }
}
            
