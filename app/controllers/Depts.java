package controllers;

import play.data.*;
import play.mvc.*;
import views.html.depts.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;

@Security.Authenticated(Secured.class)
public class Depts extends Controller {

	private static final Form<Dept> deptForm = Form.form(Dept.class);
	private static final User user = User.findByEmail(request().username());

	public static Result index() {
		if (Secured.isAdmin()) {
			List<Dept> depts = Dept.findAll();
			return ok(index.render(depts, user));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result newRecord() {
		if (Secured.isAdmin()) {
			return ok(news.render(deptForm, user));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result create() {
		if (Secured.isAdmin()) {
			Form<Dept> boundForm = deptForm.bindFromRequest();
			if (boundForm.hasErrors()) {
				flash("error", "Please correct the form below.");
				return badRequest(news.render(boundForm, user));
			}
			Dept dept = boundForm.get();
			dept.save();
			flash("success", String.format("Successfully added dept."));
			return redirect(routes.Depts.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result update(Long id) {
		if (Secured.isAdmin()) {
			Form<Dept> boundForm = deptForm.bindFromRequest();
			if (boundForm.hasErrors()) {
				final Dept dept = Dept.findById(id);
				flash("error", "Please correct the form below.");
				return badRequest(edit.render(boundForm, dept, user));
			}
			Dept dept = boundForm.get();
			dept.update();
			flash("success", String.format("Successfully updated dept."));
			return redirect(routes.Depts.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result edit(Long id) {
		if (Secured.isAdmin()) {
			final Dept dept = Dept.findById(id);
			if (dept == null) {
				return notFound(String.format("Dept does not exist."));
			}
			Form<Dept> filledForm = deptForm.fill(dept);
			return ok(edit.render(filledForm, dept, user));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result destroy(Long id) {
		if (Secured.isAdmin()) {
			final Dept dept = Dept.findById(id);
			if (dept == null) {
				return notFound(String.format("Dept does not exists."));
			}
			dept.delete();
			return redirect(routes.Depts.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}
}