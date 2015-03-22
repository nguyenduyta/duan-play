package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Employ extends Controller {

	public static Result list() {
		return ok("List employ");
	}
	
	public static Result newEmploy() {
		return TODO;
	}
	
	public static Result save() {
		return ok("Save employ");
	}
	
	public static Result details(String id) {
		return ok("Info employ");
	}
}
