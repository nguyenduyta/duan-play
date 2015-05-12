package controllers;

import play.data.*;
import play.mvc.*;
import views.html.academics.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;

@Security.Authenticated(Secured.class)
public class Academics extends Controller {

	private static final Form<Academic> academicForm = Form.form(Academic.class);

	public static Result index() {
		if (Secured.isAdmin()) {
			List<Academic> academics = Academic.findAll();
			return ok(index.render(academics, User.findByEmail(request().username())));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result newRecord() {
		if (Secured.isAdmin()) {
			return ok(news.render(academicForm, User.findByEmail(request().username())));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result create() {
		if (Secured.isAdmin()) {
			Form<Academic> boundForm = academicForm.bindFromRequest();
			if (boundForm.hasErrors()) {
				flash("error", "Please correct the form below.");
				return badRequest(news.render(boundForm, User.findByEmail(request().username())));
			}
			Academic academic = boundForm.get();
			academic.save();
			flash("success", String.format("Successfully added academic."));
			return redirect(routes.Academics.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result update(Long id) {
		if (Secured.isAdmin()) {
			Form<Academic> boundForm = academicForm.bindFromRequest();
			if (boundForm.hasErrors()) {
				final Academic academic = Academic.findById(id);
				flash("error", "Please correct the form below.");
				return badRequest(edit.render(boundForm, academic, User.findByEmail(request().username())));
			}
			Academic academic = boundForm.get();
			academic.update();
			flash("success", String.format("Successfully updated academic."));
			return redirect(routes.Academics.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result edit(Long id) {
		if (Secured.isAdmin()) {
			final Academic academic = Academic.findById(id);
			if (academic == null) {
				return notFound(String.format("Academic does not exist."));
			}
			Form<Academic> filledForm = academicForm.fill(academic);
			return ok(edit.render(filledForm, academic, User.findByEmail(request().username())));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result destroy(Long id) {
		if (Secured.isAdmin()) {
			final Academic academic = Academic.findById(id);
			if (academic == null) {
				return notFound(String.format("Academic does not exists."));
			}
			academic.delete();
			return redirect(routes.Academics.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}
}