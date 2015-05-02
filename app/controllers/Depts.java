package controllers;

import play.data.*;
import play.mvc.*;
import views.html.depts.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;

public class Depts extends Controller {

	private static final Form<Dept> deptForm = Form.form(Dept.class);

	public static Result index() {
		List<Dept> depts = Dept.findAll();
		return ok(index.render(depts));
	}

	public static Result newRecord() {
		return ok(news.render(deptForm));
	}

	public static Result create() {
		Form<Dept> boundForm = deptForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(news.render(boundForm));
		}
		Dept dept = boundForm.get();
		dept.save();
		flash("success", String.format("Successfully added dept."));
		return redirect(routes.Depts.index());
	}

	public static Result update(Long id) {
		Form<Dept> boundForm = deptForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			final Dept dept = Dept.findById(id);
			flash("error", "Please correct the form below.");
			return badRequest(edit.render(boundForm, dept));
		}
		Dept dept = boundForm.get();
		dept.update();
		flash("success", String.format("Successfully updated dept."));
		return redirect(routes.Depts.index());
	}

	public static Result edit(Long id) {
		final Dept dept = Dept.findById(id);
		if (dept == null) {
			return notFound(String.format("Dept does not exist."));
		}
		Form<Dept> filledForm = deptForm.fill(dept);
		return ok(edit.render(filledForm, dept));
	}

	public static Result destroy(Long id) {
		final Dept dept = Dept.findById(id);
		if (dept == null) {
			return notFound(String.format("Dept does not exists."));
		}
		dept.delete();
		return redirect(routes.Depts.index());
	}
}