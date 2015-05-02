package controllers;

import play.data.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;

public class Application extends Controller {
	public static Result login() {
		String email = session("email");
		if (email != null) {
			return redirect(routes.Employs.index());
		}
		return ok(login.render(Form.form(Login.class)));
	}

	public static Result authenticate() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			session().clear();
			session("email", loginForm.get().email);
			return redirect(routes.Employs.index());
		}
	}

	public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.Application.login());
    }

	public static class Login {
		public String email;
		public String password;

		public String validate() {
			if (!User.authenticate(email, password)) {
			  return "Invalid email or password";
			}
			return null;
		}
	}
	
}
