package controllers;

import play.*;
import models.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

/**
 *
 * @author andrew
 */
public class ModuleController extends Controller{
    
public static Result modules() {
//    return TODO;
//    TODO is a 501 Not Implemented response
      return ok(
              views.html.moduleInfo.render(Module.all(), addModuleForm)
              );
  }
  
  public static Result newModule() {
//    return TODO;
  Form<Module> filledForm = addModuleForm.bindFromRequest();
  if(filledForm.hasErrors()) {
      System.out.println(filledForm.errors().toString());
      System.out.println("Errors Ahoy");
    return badRequest(
      views.html.moduleInfo.render(Module.all(), filledForm)
    );
  } else {
    Module.create(filledForm.get());
    return redirect(routes.ModuleController.modules());  
  }
  }
  
  public static Result deleteModule(Long id) {
//    return TODO;
      Module.delete(id);
      return redirect(routes.ModuleController.modules());
  }
  
//  FORMS
  static Form<Module> addModuleForm = Form.form(Module.class);
}
