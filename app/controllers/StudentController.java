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
public class StudentController extends Controller{
    
public static Result students() {
//    return TODO;
//    TODO is a 501 Not Implemented response
      return ok(
              views.html.studentInfo.render(Student.all(), addStudentForm)
              );
  }
  
  public static Result newStudent() {
//    return TODO;
  Form<Student> filledForm = addStudentForm.bindFromRequest();
  if(filledForm.hasErrors()) {
      System.out.println(filledForm.errors().toString());
      System.out.println("Errors Ahoy");
    return badRequest(
      views.html.studentInfo.render(Student.all(), filledForm)
    );
  } else {
    Student.create(filledForm.get());
    return redirect(routes.StudentController.students());  
  }
  }
  
  public static Result deleteStudent(Long id) {
//    return TODO;
      Student.delete(id);
      return redirect(routes.StudentController.students());
  }
  
//  FORMS
  static Form<Student> addStudentForm = Form.form(Student.class);
}
