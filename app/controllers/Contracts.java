package controllers;

import play.data.*;
import play.mvc.*;
import views.html.contracts.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;

@Security.Authenticated(Secured.class)
public class Contracts extends Controller {

	private static final Form<Contract> contractForm = Form.form(Contract.class);

	public static Result index() {
		if (Secured.isAdmin()) {
			List<Contract> contracts = Contract.findAll();
			return ok(index.render(contracts, Users.findByEmail(request().username())));
		} else {
			return forbidden("You don't have permission to access on this server");
		}	
	}

	public static Result newRecord() {
		if (Secured.isAdmin()) {
			return ok(news.render(contractForm, Users.findByEmail(request().username())));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result create() {
		if (Secured.isAdmin()) {
			Form<Contract> boundForm = contractForm.bindFromRequest();
			if (boundForm.hasErrors()) {
				flash("error", "Please correct the form below.");
				return badRequest(news.render(boundForm, Users.findByEmail(request().username())));
			}
			Contract contract = boundForm.get();
			contract.save();
			flash("success", String.format("Successfully added product."));
			return redirect(routes.Contracts.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result update(Long id) {
		if (Secured.isAdmin()) {
			Form<Contract> boundForm = contractForm.bindFromRequest();
			if (boundForm.hasErrors()) {
				final Contract contract = Contract.findById(id);
				flash("error", "Please correct the form below.");
				return badRequest(edit.render(boundForm, contract, Users.findByEmail(request().username())));
			}
			Contract contract = boundForm.get();
			contract.update();
			flash("success", String.format("Successfully updated product."));
			return redirect(routes.Contracts.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result edit(Long id) {
		if (Secured.isAdmin()) {
			final Contract contract = Contract.findById(id);
			if (contract == null) {
				return notFound(String.format("Contract does not exist."));
			}
			Form<Contract> filledForm = contractForm.fill(contract);
			return ok(edit.render(filledForm, contract, Users.findByEmail(request().username())));
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}

	public static Result destroy(Long id) {
		if (Secured.isAdmin()) {
			final Contract contract = Contract.findById(id);
			if (contract == null) {
				return notFound(String.format("Contract does not exists."));
			}
			contract.delete();
			return redirect(routes.Contracts.index());
		} else {
			return forbidden("You don't have permission to access on this server");
		}
	}
}