package controllers;

import play.data.*;
import play.mvc.*;
import views.html.employs.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;

import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

@Security.Authenticated(Secured.class)
public class Employs extends Controller {

    private static final Form<Employ> employForm = Form.form(Employ.class);

    public static Result GO_HOME = redirect(
        routes.Employs.list(0, "id", "asc", "")
    );

    public static Result index() {
        return GO_HOME;
    }

    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(list.render(
                Employ.page(page, 10, sortBy, order, filter),
                sortBy, order, filter, Users.findByEmail(request().username()))
        );
    }

    public static Result newRecord() {
        if (Secured.isAdmin()) {
            return ok(news.render(employForm, Users.findByEmail(request().username())));
        } else {
            return forbidden("You don't have permission to access on this server");
        }
    }

    public static Result create() {
        if (Secured.isAdmin()) {
            Form<Employ> boundForm = employForm.bindFromRequest();
            if (boundForm.hasErrors()) {
                flash("error", "Please correct the form below.");
                return badRequest(news.render(boundForm, Users.findByEmail(request().username())));
            }
            Employ employ = boundForm.get();
            employ.save();
            flash("success", String.format("Successfully added employ."));
            return redirect(routes.Employs.index());
        } else {
            return forbidden("You don't have permission to access on this server");
        }
    }

    public static Result update(Long id) {
        if (Secured.isAdmin()) {
            Form<Employ> boundForm = employForm.bindFromRequest();
            if (boundForm.hasErrors()) {
                final Employ employ = Employ.findById(id);
                flash("error", "Please correct the form below.");
                return ok(edit.render(boundForm, employ, Users.findByEmail(request().username())));
            }
            Employ employ = boundForm.get();
            employ.update();
            flash("success", String.format("Successfully updated employ."));
            return redirect(routes.Employs.index());
        } else {
            return forbidden("You don't have permission to access on this server");
        }
    }

    public static Result edit(Long id) {
        if (Secured.isAdmin()) {
            final Employ employ = Employ.findById(id);
            if (employ == null) {
                return notFound(String.format("Employ does not exist."));
            }
            Form<Employ> filledForm = employForm.fill(employ);
            return ok(edit.render(filledForm, employ, Users.findByEmail(request().username())));
        } else {
            return forbidden("You don't have permission to access on this server");
        }
    }

    public static Result destroy(Long id) {
        if (Secured.isAdmin()) {
            final Employ employ = Employ.findById(id);
            if (employ == null) {
                return notFound(String.format("Employ does not exists."));
            }
            employ.delete();
            return redirect(routes.Employs.index());
        } else {
            return forbidden("You don't have permission to access on this server");
        }
    }

    public static Result export() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employ");
        
        List<Employ> employs = Employ.findAll();

        Map<String, Object[]> data = new HashMap<String, Object[]>();
        data.put("1", new Object[] {"Name", "Email", "Birthday", "Phone", "Address"});
        for(int i = 0; i < employs.size(); i++) {
            data.put(String.valueOf(2 + i), new Object[] {employs.get(i).name, employs.get(i).email, employs.get(i).birthday,
                employs.get(i).phone_number, employs.get(i).address});
        }
        
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            HSSFRow row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                HSSFCell cell = row.createCell(cellnum++);
                if(obj instanceof Date) 
                    cell.setCellValue((Date)obj);
                else if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
            }
        }
         
        try {
            FileOutputStream out = 
                    new FileOutputStream(new File("new.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ok(new java.io.File("new.xlsx"));
    }
}