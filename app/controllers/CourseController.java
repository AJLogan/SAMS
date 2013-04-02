package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.*;

import models.*;

/**
 * Manage courses
 */
public class courseController extends Controller {
        
    /**
     * Handle default path requests, redirect to computers list
     */
    public static Result index() {
        return redirect(routes.courseController.list(0, "number", "asc", ""));
    }
    /**
     * Display the paginated list of courses.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on course names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            listCourses.render(
                Course.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Course.
     *
     * @param id Id of the course to edit
     */
    public static Result edit(Long id) {
        Form<Course> courseForm = form(Course.class).fill(
            Course.find.byId(id)
        );
        return ok(
            editCourseForm.render(id, courseForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the course to edit
     */
    public static Result update(Long id) {
        Form<Course> courseForm = form(Course.class).bindFromRequest();
        if(courseForm.hasErrors()) {
            return badRequest(editCourseForm.render(id, courseForm));
        }
        courseForm.get().update(id);
        flash("success", "Course " + courseForm.get().number + " has been updated");
        return redirect(routes.courseController.list(0, "number", "asc", ""));
    }
    
    /**
     * Display the 'new course form'.
     */
    public static Result create() {
        Form<Course> courseForm = form(Course.class);
        return ok(
            createCourseForm.render(courseForm)
        );
    }
    
    /**
     * Handle the 'new course form' submission 
     */
    public static Result save() {
        Form<Course> courseForm = form(Course.class).bindFromRequest();
        if(courseForm.hasErrors()) {
            return badRequest(createCourseForm.render(courseForm));
        }
        courseForm.get().save();
        flash("success", "Course " + courseForm.get().name + " has been created");
        return redirect(routes.courseController.list(0, "number", "asc", ""));
    }
    
    /**
     * Handle course deletion
     */
    public static Result delete(Long id) {
        Course.find.ref(id).delete();
        flash("success", "Course has been deleted");
        return redirect(routes.courseController.list(0, "number", "asc", ""));
    }
}
            
