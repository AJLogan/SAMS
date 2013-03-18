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
public class AttendanceController extends Controller{
    
public static Result attendances() {
//    return TODO;
//    TODO is a 501 Not Implemented response
      return ok(
              views.html.attendanceInfo.render(Attendance.all(), addAttendanceForm)
              );
  }
  
  public static Result newAttendance() {
//    return TODO;
  Form<Attendance> filledForm = addAttendanceForm.bindFromRequest();
  if(filledForm.hasErrors()) {
      System.out.println(filledForm.errors().toString());
      System.out.println("Errors Ahoy");
    return badRequest(
      views.html.attendanceInfo.render(Attendance.all(), filledForm)
    );
  } else {
    Attendance.create(filledForm.get());
    return redirect(routes.AttendanceController.attendances());  
  }
  }
  
  public static Result deleteAttendance(Long id) {
//    return TODO;
      Attendance.delete(id);
      return redirect(routes.AttendanceController.attendances());
  }
  
//  FORMS
  static Form<Attendance> addAttendanceForm = Form.form(Attendance.class);
}
