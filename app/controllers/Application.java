package controllers;

import static play.libs.Json.toJson;

import java.util.List;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	private static final String APPLI_TITLE = "API";

	public static Result index() {
		return ok(index.render(APPLI_TITLE, User.findByEmail(session("email"))));
	}

	public static Result getUsers() {
		List<User> list = User.findAll();
		return ok(toJson(list));
	}
}