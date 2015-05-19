package controllers;

import play.data.*;
import play.mvc.*;
import views.html.user.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

@Security.Authenticated(Secured.class)
public class User extends Controller {

	private static final Form<Users> userForm = Form.form(Users.class);

	public static Result index() {
		if (Secured.isAdmin()) {
			List<Users> users = Users.findAll();
			return ok(index.render(users, Users.findByEmail(request().username())));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result newRecord() {
		if (Secured.isAdmin()) {
			return ok(news.render(userForm, Users.findByEmail(request().username())));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result create() {
		if (Secured.isAdmin()) {
			Form<Users> boundForm = userForm.bindFromRequest();

			String password_confirmation = boundForm.field("password_confirmation").valueOr("");
			String password = boundForm.field("password").valueOr("");
			if (!password.isEmpty() && !password_confirmation.equals(password)) {
	            boundForm.reject("password_confirmation",
	             "Password don't match.");
	        }

			if (boundForm.hasErrors()) {
				flash("error", "Please correct the form below.");
				return badRequest(news.render(boundForm, Users.findByEmail(request().username())));
			}

			Users user = boundForm.get();
			user.password = BCrypt.hashpw(user.password, BCrypt.gensalt());
			user.save();
			flash("success", String.format("Successfully added user."));
			return redirect(routes.User.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result update(Long id) {
		Form<Users> boundForm = userForm.bindFromRequest();

		Users user = Users.findById(id);
		if (!user.email.equals(request().username()) && !Secured.isAdmin()) {
			return forbidden("You don't have permission to access on this server");
		}
		String password = boundForm.field("password").valueOr("");
		String password_confirmation = boundForm.field("new_password_confirmation").valueOr("");
		String new_password = boundForm.field("new_password").valueOr("");
		if (!password.isEmpty()) {
			if (!BCrypt.checkpw(password, user.password)) {
				boundForm.reject("password",
             		"Current password don't match.");
			} else if (!new_password.isEmpty() && !password_confirmation.equals(new_password)) {
	            boundForm.reject("new_password_confirmation",
	            	"New password don't match.");
        	}
		}

		if (boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(edit.render(boundForm, user, Users.findByEmail(request().username())));
		}
		user = boundForm.get();
		if (!new_password.isEmpty()) {
			user.password = BCrypt.hashpw(new_password, BCrypt.gensalt());
		} else {
			user.password = BCrypt.hashpw(password, BCrypt.gensalt());
		}
		user.update();
		flash("success", String.format("Successfully updated user."));
		if (Secured.isAdmin()) {
			return redirect(routes.User.index());
		} else {
			return redirect(routes.Employs.index());
		}
	}

	public static Result edit(Long id) {
		final Users user = Users.findById(id);
		Users current_user = Users.findByEmail(request().username());
		if (user == null) {
			return notFound(String.format("User does not exist."));
		}
		if (!user.equals(current_user)) {
			return forbidden("You don't have permission to access on this server");
		}
		Form<Users> filledForm = userForm.fill(user);
		return ok(edit.render(filledForm, user, current_user));
	}

	public static Result destroy(Long id) {
		if (Secured.isAdmin()) {
			final Users user = Users.findById(id);
			Users current_user = Users.findByEmail(request().username());
			if (user == null) {
				return notFound(String.format("User does not exists."));
			}
			if (user.equals(current_user)) {
				flash("error", String.format("You aren't delete yoursefl."));
			} else {
				user.delete();
			}
			return redirect(routes.User.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}
}