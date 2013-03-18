package controllers;

import play.*;
import models.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
//        return ok(index.render(Task.all(), taskForm));
//        return ok("Hello World");
//        This is a redirect to the tasks list page
        return redirect(routes.StudentController.students());
    }
    
//  public static Result tasks() {
////    return TODO;
////    TODO is a 501 Not Implemented response
//      return ok(
//              views.html.index.render(Task.all(), taskForm)
//              );
//  }
//  
//  public static Result newTask() {
////    return TODO;
//  Form<Task> filledForm = taskForm.bindFromRequest();
//  if(filledForm.hasErrors()) {
//    return badRequest(
//      views.html.index.render(Task.all(), filledForm)
//    );
//  } else {
//    Task.create(filledForm.get());
//    return redirect(routes.Application.tasks());  
//  }
//  }
//  
//  public static Result deleteTask(Long id) {
////    return TODO;
//      Task.delete(id);
//      return redirect(routes.Application.tasks());
//  }
//  
////  FORMS
//  static Form<Task> taskForm = Form.form(Task.class);
  
  
}
