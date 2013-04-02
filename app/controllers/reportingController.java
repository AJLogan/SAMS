/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.FetchConfig;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import java.util.List;
import models.*;
import play.mvc.Controller;
import play.mvc.Result;

/**
 *
 * @author andrew
 */
public class reportingController extends Controller {
    
    
    
// This example has uses fetch() to fetch related order 
// and customer information after the initial RawSql
// query is executed
//String sql 
//= " select order_id, 'ignoreMe', sum(d.order_qty*d.unit_price) as totalAmount "
// + " from o_order_detail d"
// + " group by order_id ";
public static Result numberOfStudentsOnModule(String crn) {
    String sql
        = "SELECT DISTINCT module.crn, module.code, module.name, count(*) as NumStudents"
            + "FROM student"
            + "JOIN course ON student.course_id = course.id"
            + "JOIN course_module ON course.id = course_module.course_id"
            + "JOIN module ON course_module.module_id = module.id"
            + "where crn = " + crn;

RawSql rawSql = 
 RawSqlBuilder
 .parse(sql)
// .columnMapping("order_id", "order.id")
// .columnMappingIgnore("'ignoreMe'")
 // don't need this when using column alias
 //.columnMapping("sum(d.order_qty*d.unit_price)","totalAmount")
 .create();

Query<StudentModule> query = Ebean.find(StudentModule.class);
query.setRawSql(rawSql)
 // after the RawSql query executes Ebean can execute
// FetchConfig().query() joins ... 
.fetch("order", "status,orderDate",new FetchConfig().query())
 .fetch("order.customer", "name")
 .where().gt("order.id", 0)
 .having().gt("totalAmount", 20)
 .order().desc("totalAmount")
 .setMaxRows(10);

List<StudentModule> list = query.findList();
        return redirect(routes.courseController.list(0, "number", "asc", ""));
    }
}