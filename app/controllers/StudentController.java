package controllers;

import java.util.*;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.*;

import views.html.*;

import models.*;

/**
 * Manage students
 */
public class studentController extends Controller {
        
    /**
     * Handle default path requests, redirect to student list
     */
    public static Result index() {
        return redirect(routes.studentController.list(0, "number", "asc", ""));
    }
    /**
     * Display the paginated list of students.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on student names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            listStudents.render(
                Student.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Student.
     *
     * @param id Id of the student to edit
     */
    public static Result edit(Long id) {
        Form<Student> studentForm = form(Student.class).fill(
            Student.find.byId(id)
        );
        return ok(
            editStudentForm.render(id, studentForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the student to edit
     */
    public static Result update(Long id) {
        Form<Student> studentForm = form(Student.class).bindFromRequest();
        if(studentForm.hasErrors()) {
            return badRequest(editStudentForm.render(id, studentForm));
        }
        studentForm.get().update(id);
        flash("success", "Student " + studentForm.get().number + " has been updated");
        return redirect(routes.studentController.list(0, "number", "asc", ""));
    }
    
    /**
     * Display the 'new student form'.
     */
    public static Result create() {
        Form<Student> studentForm = form(Student.class);
        return ok(
            createStudentForm.render(studentForm)
        );
    }
    
    /**
     * Handle the 'new student form' submission 
     */
    public static Result save() {
        Form<Student> studentForm = form(Student.class).bindFromRequest();
        if(studentForm.hasErrors()) {
            return badRequest(createStudentForm.render(studentForm));
        }
        studentForm.get().save();
        flash("success", "Student " + studentForm.get().forename + " " + studentForm.get().surname + " has been created");
        return redirect(routes.studentController.list(0, "number", "asc", ""));
    }
    
    /**
     * Handle student deletion
     */
    public static Result delete(Long id) {
        Student.find.ref(id).delete();
        flash("success", "Student has been deleted");
        return redirect(routes.studentController.list(0, "number", "asc", ""));
    }
}         
