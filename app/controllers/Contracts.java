package controllers;

import play.data.*;
import play.mvc.*;
import views.html.contracts.*;
import java.util.*;
import models.*;
import play.db.ebean.*;
import javax.persistence.*;

public class Contracts extends Controller {

	private static final Form<Contract> contractForm = Form.form(Contract.class);

	public static Result index() {
		List<Contract> contracts = Contract.findAll();
		return ok(index.render(contracts));
	}

	public static Result newRecord() {
		return ok(news.render(contractForm));
	}

	public static Result create() {
		Form<Contract> boundForm = contractForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(news.render(boundForm));
		}
		Contract contract = boundForm.get();
		contract.save();
		flash("success", String.format("Successfully added product."));
		return redirect(routes.Contracts.index());
	}

	public static Result update(Long id) {
		Form<Contract> boundForm = contractForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			final Contract contract = Contract.findById(id);
			flash("error", "Please correct the form below.");
			return badRequest(edit.render(boundForm, contract));
		}
		Contract contract = boundForm.get();
		contract.update();
		flash("success", String.format("Successfully updated product."));
		return redirect(routes.Contracts.index());
	}

	public static Result edit(Long id) {
		final Contract contract = Contract.findById(id);
		if (contract == null) {
			return notFound(String.format("Contract does not exist."));
		}
		Form<Contract> filledForm = contractForm.fill(contract);
		return ok(edit.render(filledForm, contract));
	}

	public static Result destroy(Long id) {
		final Contract contract = Contract.findById(id);
		if (contract == null) {
			return notFound(String.format("Contract does not exists."));
		}
		contract.delete();
		return redirect(routes.Contracts.index());
	}
}