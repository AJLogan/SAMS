package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.*;

import models.*;

/**
 * Manage modules
 */
public class courseModuleController extends Controller {
        
    /**
     * Handle default path requests, redirect to courseModule list
     */
    public static Result index() {
        return redirect(routes.courseModuleController.list(0, "courseModule_id", "asc", ""));
    }
    /**
     * Display the paginated list of modules.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on module names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            listCourseModules.render(
                CourseModule.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Module.
     *
     * @param id Id of the module to edit
     */
    public static Result edit(Long id) {
        Form<CourseModule> courseModuleForm = form(CourseModule.class).fill(
            CourseModule.find.byId(id)
        );
        return ok(
            editCourseModuleForm.render(id, courseModuleForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the module to edit
     */
    public static Result update(Long id) {
        Form<CourseModule> courseModuleForm = form(CourseModule.class).bindFromRequest();
        if(courseModuleForm.hasErrors()) {
            return badRequest(editCourseModuleForm.render(id, courseModuleForm));
        }
        courseModuleForm.get().update(id);
        flash("success", "CourseModule " + courseModuleForm.get().id + " has been updated");
        return redirect(routes.courseModuleController.list(0, "courseModule_id", "asc", ""));
    }
    
    /**
     * Display the 'new module form'.
     */
    public static Result create() {
        Form<CourseModule> courseModuleForm = form(CourseModule.class);
        return ok(
            createCourseModuleForm.render(courseModuleForm)
        );
    }
    
    /**
     * Handle the 'new module form' submission 
     */
    public static Result save() {
        Form<CourseModule> courseModuleForm = form(CourseModule.class).bindFromRequest();
        if(courseModuleForm.hasErrors()) {
            return badRequest(createCourseModuleForm.render(courseModuleForm));
        }
        courseModuleForm.get().save();
        flash("success", "CourseModule " + courseModuleForm.get().id + " has been created");
        return redirect(routes.courseModuleController.list(0, "courseModule_id", "asc", ""));
    }
    
    /**
     * Handle module deletion
     */
    public static Result delete(Long id) {
        CourseModule.find.ref(id).delete();
        flash("success", "CourseModule has been deleted");
        return redirect(routes.courseModuleController.list(0, "courseModule_id", "asc", ""));
    }
}
            
