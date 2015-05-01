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

	public static Result index() {
		List<Employ> employs = Employ.findAll();
		return ok(index.render(employs));
	}

	public static Result newRecord() {
		return ok(news.render(employForm));
	}

	public static Result create() {
		Form<Employ> boundForm = employForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return ok(news.render(boundForm));
		}
		Employ employ = boundForm.get();
		employ.save();
		flash("success", String.format("Successfully added employ."));
		return redirect(routes.Employs.index());
	}

	public static Result update(Long id) {
		Form<Employ> boundForm = employForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			final Employ employ = Employ.findById(id);
			flash("error", "Please correct the form below.");
			return ok(edit.render(boundForm, employ));
		}
		Employ employ = boundForm.get();
		employ.update();
		flash("success", String.format("Successfully updated employ."));
		return redirect(routes.Employs.index());
	}

	public static Result edit(Long id) {
		final Employ employ = Employ.findById(id);
		if (employ == null) {
			return notFound(String.format("Employ does not exist."));
		}
		Form<Employ> filledForm = employForm.fill(employ);
		return ok(edit.render(filledForm, employ));
	}

	public static Result destroy(Long id) {
		final Employ employ = Employ.findById(id);
		if (employ == null) {
			return notFound(String.format("Employ does not exists."));
		}
		employ.delete();
		return redirect(routes.Employs.index());
	}
}