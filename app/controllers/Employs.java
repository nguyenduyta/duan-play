package controllers;

import play.data.*;
import play.mvc.*;
import views.html.employs.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;

public class Employs extends Controller {

	private static final Form<Employ> employForm = Form.form(Employ.class);

	public static Result list() {
		List<Employ> employs = Employ.findAll();
		return ok(list.render(employs));
	}

	public static Result newEmploy() {
		return ok(news.render(employForm));
	}

	public static Result save() {
		Form<Employ> boundForm = employForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(details.render(boundForm));
		}
		Employ employ = boundForm.get();
		Employ employ_other = Employ.findById(employ.id);
		if (employ_other == null) {
			employ.save();
			flash("success", String.format("Successfully added product."));
		} else {
			employ.update();
			flash("success", String.format("Successfully updated product."));
		}
		return redirect(routes.Employs.list());
	}

	public static Result details(Long id) {
		final Employ employ = Employ.findById(id);
		if (employ == null) {
			return notFound(String.format("Product does not exist."));
		}
		Form<Employ> filledForm = employForm.fill(employ);
		return ok(details.render(filledForm));
	}

	public static Result delete(Long id) {
		final Employ employ = Employ.findById(id);
		if (employ == null) {
			return notFound(String.format("Employ does not exists."));
		}
		employ.delete();
		return redirect(routes.Employs.list());
	}
}