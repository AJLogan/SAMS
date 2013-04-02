/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.annotation.Sql;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author andrew
 */
@Entity
@Sql
public class StudentModule {
    
    @OneToOne
    Student student;
    
    @OneToOne 
    Module module;
    
    @Id
    Double NumStudents;

    public Double getNumStudents() {
        return NumStudents;
    }

    public void setNumStudents(Double NumStudents) {
        this.NumStudents = NumStudents;
    }
    
}
