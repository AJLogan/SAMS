package controllers;

import java.util.ArrayList;
import java.util.List;
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
  
  //Get Lecturers for Drop Down
  public static List<String> getLecturerNames() {
//      List<Staff> lecturers = Staff.find.all();
//      System.out.println(lecturers);
//      return lecturers;      
   
        List<String> all = new ArrayList<String>();
        List<Staff> lecturers = Staff.find.all();
        
        all.add(lecturers.get(0).toString());
        return all;
    
  }
  
//  FORMS
  static Form<Staff> addStaffForm = Form.form(Staff.class);
}
