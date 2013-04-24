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
public class moduleController extends Controller {
        
    /**
     * Handle default path requests, redirect to module list
     */
    public static Result index() {
        return redirect(routes.moduleController.list(0, "module_crn", "asc", ""));
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
            listModules.render(
                Module.page(page, 10, sortBy, order, filter),
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
        Form<Module> moduleForm = form(Module.class).fill(
            Module.find.byId(id)
        );
        return ok(
            editModuleForm.render(id, moduleForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the module to edit
     */
    public static Result update(Long id) {
        Form<Module> moduleForm = form(Module.class).bindFromRequest();
        if(moduleForm.hasErrors()) {
            return badRequest(editModuleForm.render(id, moduleForm));
        }
        moduleForm.get().update(id);
        flash("success", "Module " + moduleForm.get().code + " " + moduleForm.get().description + " has been updated");
        return redirect(routes.moduleController.list(0, "module_crn", "asc", ""));
    }
    
    /**
     * Display the 'new module form'.
     */
    public static Result create() {
        Form<Module> moduleForm = form(Module.class);
        return ok(
            createModuleForm.render(moduleForm)
        );
    }
    
    /**
     * Handle the 'new module form' submission 
     */
    public static Result save() {
        Form<Module> moduleForm = form(Module.class).bindFromRequest();
        if(moduleForm.hasErrors()) {
            return badRequest(createModuleForm.render(moduleForm));
        }
        moduleForm.get().save();
        flash("success", "Module " + moduleForm.get().code + " " + moduleForm.get().description + " has been created");
        return redirect(routes.moduleController.list(0, "module_crn", "asc", ""));
    }
    
    /**
     * Handle module deletion
     */
    public static Result delete(Long id) {
        Module.find.ref(id).delete();
        flash("success", "Module has been deleted");
        return redirect(routes.moduleController.list(0, "module_crn", "asc", ""));
    }
}
            
