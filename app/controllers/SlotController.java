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
public class SlotController extends Controller{
    
public static Result slots() {
//    return TODO;
//    TODO is a 501 Not Implemented response
      return ok(
              views.html.slotInfo.render(Slot.all(), addSlotForm)
              );
  }
  
  public static Result newSlot() {
//    return TODO;
    Form<Slot> filledForm = addSlotForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            System.out.println(filledForm.errors().toString());
            System.out.println("Errors Ahoy");
          return badRequest(
            views.html.slotInfo.render(Slot.all(), filledForm)
          );
        } else { 
          Slot.create(filledForm.get());
          return redirect(routes.SlotController.slots());  
        }
  }
  
  public static Result deleteSlot(Long id) {
//    return TODO;
      Slot.delete(id);
      return redirect(routes.SlotController.slots());
  }
  
//  FORMS
  static Form<Slot> addSlotForm = Form.form(Slot.class);
}
