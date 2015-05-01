package controllers;

import play.data.*;
import play.mvc.*;
import views.html.users.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

public class Users extends Controller {

	private static final Form<User> userForm = Form.form(User.class);

	public static Result index() {
		List<User> users = User.findAll();
		return ok(index.render(users));
	}

	public static Result newRecord() {
		return ok(news.render(userForm));
	}

	public static Result create() {
		Form<User> boundForm = userForm.bindFromRequest();

		String password_confirmation = boundForm.field("password_confirmation").valueOr("");
		String password = boundForm.field("password").valueOr("");
		if (!password.isEmpty() && !password_confirmation.equals(password)) {
            boundForm.reject("password_confirmation",
             "Password don't match.");
        }

		if (boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(news.render(boundForm));
		}

		User user = boundForm.get();
		user.password = BCrypt.hashpw(user.password, BCrypt.gensalt());
		user.save();
		flash("success", String.format("Successfully added user."));
		return redirect(routes.Users.index());
	}

	public static Result update(Long id) {
		Form<User> boundForm = userForm.bindFromRequest();

		User user = User.findById(id);
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
			return badRequest(edit.render(boundForm, user));
		}
		user = boundForm.get();
		if (!new_password.isEmpty()) {
			user.password = BCrypt.hashpw(new_password, BCrypt.gensalt());
		} else {
			user.password = BCrypt.hashpw(password, BCrypt.gensalt());
		}
		user.update();
		flash("success", String.format("Successfully updated user."));
		return redirect(routes.Users.index());
	}

	public static Result edit(Long id) {
		final User user = User.findById(id);
		if (user == null) {
			return notFound(String.format("User does not exist."));
		}
		Form<User> filledForm = userForm.fill(user);
		return ok(edit.render(filledForm, user));
	}

	public static Result destroy(Long id) {
		final User user = User.findById(id);
		if (user == null) {
			return notFound(String.format("User does not exists."));
		}
		user.delete();
		return redirect(routes.Users.index());
	}
}