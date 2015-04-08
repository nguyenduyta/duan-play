package controllers;

import play.data.Form;
import java.util.List;
import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.employs.*;
import models.Employ;

public class Employs extends Controller {

	private static final Form<Employ> EmployForm = Form.form(Employ.class);

	public static Result list() {
		List<Employ> employs = Employ.findAll();
		return ok(list.render(employs));
	}

	public static Result newEmploy() {
		return ok(details.render(EmployForm));
	}

	public static Result save() {
		Form<Employ> boundForm = EmployForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(details.render(boundForm));
		}
		Employ employ = boundForm.get();
		employ.save();
		flash("success", String.format("Successfully added product %s", employ));
		return redirect(routes.Employs.list());
	}

	public static Result details(String id) {
		final Employ employ = Employ.findById(id);
		if (employ == null) {
			return notFound(String.format("Product %s does not exist.", id));
		}
		Form<Employ> filledForm = EmployForm.fill(employ);
		return ok(details.render(filledForm));
	}

	public static Result delete(String id) {
		final Employ employ = Employ.findById(id);
		if (employ == null) {
			return notFound(String.format("Employ %s does not exists.", id));
		}
		Employ.remove(employ);
		return redirect(routes.Employs.list());
	}
}