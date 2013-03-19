package controllers;

import play.*;
import models.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("Index"));
//        return ok("Hello World");
//        This is a redirect to the tasks list page
//        return redirect(routes.StudentController.students());
    }
}
