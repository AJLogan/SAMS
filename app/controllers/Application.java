package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.*;

import models.*;

/**
 * Manage the student attendance database
 */
public class Application extends Controller {
    
    /**
     * This result directly redirect to application home.
     */
    public static Result index() {
        return ok(index.render("Index"));
    }
}           
