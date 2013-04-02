package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.*;

import models.*;

/**
 * Manage a database of computers
 */
public class Application extends Controller {
    
    /**
     * This result directly redirect to application home.
     */
    public static Result index() {
        return ok(index.render("Index"));
//        return ok("Hello World");
//        This is a redirect to the tasks list page
//        return redirect(routes.StudentController.students());
    }
}           
