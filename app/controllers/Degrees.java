package controllers;

import play.data.*;
import play.mvc.*;
import views.html.degrees.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;

@Security.Authenticated(Secured.class)
public class Degrees extends Controller {

	private static final Form<Degree> degreesForm = Form.form(Degree.class);
	private static final User user = User.findByEmail(request().username());

	public static Result index() {
		if (Secured.isAdmin()) {
			List<Degree> degrees = Degree.findAll();
			return ok(index.render(degrees, user));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result newRecord() {
		if (Secured.isAdmin()) {
			return ok(news.render(degreesForm, user));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result create() {
		if (Secured.isAdmin()) {
			Form<Degree> boundForm = degreesForm.bindFromRequest();
			if (boundForm.hasErrors()) {
				flash("error", "Please correct the form below.");
				return badRequest(news.render(boundForm, user));
			}
			Degree degree = boundForm.get();
			degree.save();
			flash("success", String.format("Successfully added degree."));
			return redirect(routes.Degrees.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result update(Long id) {
		if (Secured.isAdmin()) {
			Form<Degree> boundForm = degreesForm.bindFromRequest();
			if (boundForm.hasErrors()) {
				final Degree degree = Degree.findById(id);
				flash("error", "Please correct the form below.");
				return badRequest(edit.render(boundForm, degree, user));
			}
			Degree degree = boundForm.get();
			degree.update();
			flash("success", String.format("Successfully updated degree."));
			return redirect(routes.Degrees.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result edit(Long id) {
		if (Secured.isAdmin()) {
			final Degree degree = Degree.findById(id);
			if (degree == null) {
				return notFound(String.format("Degree does not exist."));
			}
			Form<Degree> filledForm = degreesForm.fill(degree);
			return ok(edit.render(filledForm, degree, user));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result destroy(Long id) {
		if (Secured.isAdmin()) {
			final Degree degree = Degree.findById(id);
			if (degree == null) {
				return notFound(String.format("Degree does not exists."));
			}
			degree.delete();
			return redirect(routes.Degrees.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}
}