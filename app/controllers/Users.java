package controllers;

import static play.libs.Json.toJson;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(SecuredJson.class)
public class Users extends Controller {

	/**
	 * Get a list of items in JSON format<br>
	 * GET Http request
	 * 
	 * @return
	 */
	public static Result getAll() { // GET
		return ok(toJson(User.findAll()));
	}

}