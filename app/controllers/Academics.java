package controllers;

import play.data.*;
import play.mvc.*;
import views.html.academics.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;

public class Academics extends Controller {

	private static final Form<Academic> academicForm = Form.form(Academic.class);

	public static Result index() {
		List<Academic> academics = Academic.findAll();
		return ok(index.render(academics));
	}

	public static Result newRecord() {
		return ok(news.render(academicForm));
	}

	public static Result create() {
		Form<Academic> boundForm = academicForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(news.render(boundForm));
		}
		Academic academic = boundForm.get();
		academic.save();
		flash("success", String.format("Successfully added academic."));
		return redirect(routes.Academics.index());
	}

	public static Result update(Long id) {
		Form<Academic> boundForm = academicForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			final Academic academic = Academic.findById(id);
			flash("error", "Please correct the form below.");
			return badRequest(edit.render(boundForm, academic));
		}
		Academic academic = boundForm.get();
		academic.update();
		flash("success", String.format("Successfully updated academic."));
		return redirect(routes.Academics.index());
	}

	public static Result edit(Long id) {
		final Academic academic = Academic.findById(id);
		if (academic == null) {
			return notFound(String.format("Academic does not exist."));
		}
		Form<Academic> filledForm = academicForm.fill(academic);
		return ok(edit.render(filledForm, academic));
	}

	public static Result destroy(Long id) {
		final Academic academic = Academic.findById(id);
		if (academic == null) {
			return notFound(String.format("Academic does not exists."));
		}
		academic.delete();
		return redirect(routes.Academics.index());
	}
}