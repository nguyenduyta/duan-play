package controllers;

import play.data.*;
import play.mvc.*;
import views.html.users.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

@Security.Authenticated(Secured.class)
public class Users extends Controller {

	private static final Form<User> userForm = Form.form(User.class);
	private static final User current_user = User.findByEmail(request().username());

	public static Result index() {
		if (Secured.isAdmin()) {
			List<User> users = User.findAll();
			return ok(index.render(users, current_user));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result newRecord() {
		if (Secured.isAdmin()) {
			return ok(news.render(userForm, current_user));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result create() {
		if (Secured.isAdmin()) {
			Form<User> boundForm = userForm.bindFromRequest();

			String password_confirmation = boundForm.field("password_confirmation").valueOr("");
			String password = boundForm.field("password").valueOr("");
			if (!password.isEmpty() && !password_confirmation.equals(password)) {
	            boundForm.reject("password_confirmation",
	             "Password don't match.");
	        }

			if (boundForm.hasErrors()) {
				flash("error", "Please correct the form below.");
				return badRequest(news.render(boundForm, current_user));
			}

			User user = boundForm.get();
			user.password = BCrypt.hashpw(user.password, BCrypt.gensalt());
			user.save();
			flash("success", String.format("Successfully added user."));
			return redirect(routes.Users.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result update(Long id) {
		Form<User> boundForm = userForm.bindFromRequest();

		User user = User.findById(id);
		if (user != current_user) {
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
			return badRequest(edit.render(boundForm, user, current_user));
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
			return redirect(routes.Users.index());
		} else {
			return redirect(routes.Employs.index());
		}
	}

	public static Result edit(Long id) {
		final User user = User.findById(id);
		if (user == null) {
			return notFound(String.format("User does not exist."));
		}
		if (user.email != request().username() && Secured.isAdmin() == false) {
			return forbidden("You don't have permission to access on this server");
		}
		Form<User> filledForm = userForm.fill(user);
		return ok(edit.render(filledForm, user, current_user));
	}

	public static Result destroy(Long id) {
		if (Secured.isAdmin()) {
			final User user = User.findById(id);
			if (user == null) {
				return notFound(String.format("User does not exists."));
			}
			user.delete();
			return redirect(routes.Users.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}
}