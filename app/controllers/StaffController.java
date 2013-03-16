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
public class StaffController extends Controller{
    
public static Result staff() {
//    return TODO;
//    TODO is a 501 Not Implemented response
      return ok(
              views.html.staffInfo.render(Staff.all(), addStaffForm)
              );
  }
  
  public static Result newStaff() {
//    return TODO;
  Form<Staff> filledForm = addStaffForm.bindFromRequest();
  if(filledForm.hasErrors()) {
      System.out.println(filledForm.errors().toString());
      System.out.println("Errors Ahoy");
    return badRequest(
      views.html.staffInfo.render(Staff.all(), filledForm)
    );
  } else {
    Staff.create(filledForm.get());
    return redirect(routes.StaffController.staff());  
  }
  }
  
  public static Result deleteStaff(Long id) {
//    return TODO;
      Staff.delete(id);
      return redirect(routes.StaffController.staff());
  }
  
//  FORMS
  static Form<Staff> addStaffForm = Form.form(Staff.class);
}
