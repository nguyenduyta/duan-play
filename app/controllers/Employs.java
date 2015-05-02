package controllers;

import play.data.*;
import play.mvc.*;
import views.html.employs.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;

@Security.Authenticated(Secured.class)
public class Employs extends Controller {

	private static final Form<Employ> employForm = Form.form(Employ.class);
	private static final User user = User.findByEmail(request().username());

	public static Result index() {
		List<Employ> employs = Employ.findAll();
		return ok(index.render(employs, user));
	}

	public static Result newRecord() {
		if (Secured.isAdmin()) {
			return ok(news.render(employForm, user));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result create() {
		if (Secured.isAdmin()) {
			Form<Employ> boundForm = employForm.bindFromRequest();
			if (boundForm.hasErrors()) {
				flash("error", "Please correct the form below.");
				return badRequest(news.render(boundForm, user));
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
				return ok(edit.render(boundForm, employ, user));
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
			return ok(edit.render(filledForm, employ, user));
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
}