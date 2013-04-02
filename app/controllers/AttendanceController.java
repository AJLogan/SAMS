package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.*;

import models.*;

/**
 * Manage attendances
 */
public class attendanceController extends Controller {
        
    /**
     * Handle default path requests, redirect to attendance list
     */
    public static Result index() {
        return redirect(routes.attendanceController.list(0, "attendance_id", "asc", ""));
    }
    /**
     * Display the paginated list of attendances.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on attendance names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            listAttendances.render(
                Attendance.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Attendance.
     *
     * @param id Id of the attendance to edit
     */
    public static Result edit(Long id) {
        Form<Attendance> attendanceForm = form(Attendance.class).fill(
            Attendance.find.byId(id)
        );
        return ok(
            editAttendanceForm.render(id, attendanceForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the attendance to edit
     */
    public static Result update(Long id) {
        Form<Attendance> attendanceForm = form(Attendance.class).bindFromRequest();
        if(attendanceForm.hasErrors()) {
            return badRequest(editAttendanceForm.render(id, attendanceForm));
        }
        attendanceForm.get().update(id);
        flash("success", "Attendance " + attendanceForm.get().id + " has been updated");
        return redirect(routes.attendanceController.list(0, "attendance_id", "asc", ""));
    }
    
    /**
     * Display the 'new attendance form'.
     */
    public static Result create() {
        Form<Attendance> attendanceForm = form(Attendance.class);
        return ok(
            createAttendanceForm.render(attendanceForm)
        );
    }
    
    /**
     * Handle the 'new attendance form' submission 
     */
    public static Result save() {
        Form<Attendance> attendanceForm = form(Attendance.class).bindFromRequest();
        if(attendanceForm.hasErrors()) {
            return badRequest(createAttendanceForm.render(attendanceForm));
        }
        attendanceForm.get().save();
        flash("success", "Attendance " + attendanceForm.get().id + " has been created");
        return redirect(routes.attendanceController.list(0, "attendance_id", "asc", ""));
    }
    
    /**
     * Handle attendance deletion
     */
    public static Result delete(Long id) {
        Attendance.find.ref(id).delete();
        flash("success", "Attendance has been deleted");
        return redirect(routes.attendanceController.list(0, "attendance_id", "asc", ""));
    }
}
            
