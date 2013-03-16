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
public class CourseController extends Controller{
    
public static Result courses() {
//    return TODO;
//    TODO is a 501 Not Implemented response
      return ok(
              views.html.courseInfo.render(Course.all(), addCourseForm)
              );
  }
  
  public static Result newCourse() {
//    return TODO;
  Form<Course> filledForm = addCourseForm.bindFromRequest();
  if(filledForm.hasErrors()) {
      System.out.println(filledForm.errors().toString());
      System.out.println("Errors Ahoy");
    return badRequest(
      views.html.courseInfo.render(Course.all(), filledForm)
    );
  } else {
    Course.create(filledForm.get());
    return redirect(routes.CourseController.courses());  
  }
  }
  
  public static Result deleteCourse(Long id) {
//    return TODO;
      Course.delete(id);
      return redirect(routes.CourseController.courses());
  }
  
//  FORMS
  static Form<Course> addCourseForm = Form.form(Course.class);
}
