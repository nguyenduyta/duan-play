package controllers;

import play.data.*;
import play.mvc.*;
import views.html.degrees.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;

public class Degrees extends Controller {

	private static final Form<Degree> degreesForm = Form.form(Degree.class);

	public static Result index() {
		List<Degree> degrees = Degree.findAll();
		return ok(index.render(degrees));
	}

	public static Result newRecord() {
		return ok(news.render(degreesForm));
	}

	public static Result create() {
		Form<Degree> boundForm = degreesForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(news.render(boundForm));
		}
		Degree degree = boundForm.get();
		degree.save();
		flash("success", String.format("Successfully added degree."));
		return redirect(routes.Degrees.index());
	}

	public static Result update(Long id) {
		Form<Degree> boundForm = degreesForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			final Degree degree = Degree.findById(id);
			flash("error", "Please correct the form below.");
			return badRequest(edit.render(boundForm, degree));
		}
		Degree degree = boundForm.get();
		degree.update();
		flash("success", String.format("Successfully updated degree."));
		return redirect(routes.Degrees.index());
	}

	public static Result edit(Long id) {
		final Degree degree = Degree.findById(id);
		if (degree == null) {
			return notFound(String.format("Degree does not exist."));
		}
		Form<Degree> filledForm = degreesForm.fill(degree);
		return ok(edit.render(filledForm, degree));
	}

	public static Result destroy(Long id) {
		final Degree degree = Degree.findById(id);
		if (degree == null) {
			return notFound(String.format("Degree does not exists."));
		}
		degree.delete();
		return redirect(routes.Degrees.index());
	}
}