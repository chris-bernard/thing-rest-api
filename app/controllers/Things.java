package controllers;

import static play.libs.Json.toJson;

import java.util.List;

import models.Thing;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(SecuredJson.class)
public class Things extends Controller {

	/**
	 * Get a list of items in JSON format<br>
	 * GET Http request
	 * 
	 * @return
	 */
	public static Result getAll() { // GET
		List<Thing> list = Thing.findAll();
		return ok(toJson(list));
	}

	/**
	 * Get an item from id in JSON format<br>
	 * GET Http request<br>
	 * If not existing, an error is thrown
	 * 
	 * @param id
	 * @return
	 */
	public static Result getById(Long id) { // GET
		Thing modelToFind = Thing.find.byId(id);
		if (modelToFind != null) {
			return ok(toJson(modelToFind));
		} else {
			return badRequest("not found");
		}
	}

	/**
	 * Create an item<br>
	 * POST Http request
	 * 
	 * @return
	 */
	public static Result create() { // POST
		// bindFromRequest gets the parameters
		Form<Thing> form = form(Thing.class).bindFromRequest();
		Thing model = form.get();
		model.save();
		return ok(toJson(model));
	}

	/**
	 * Update an item<br>
	 * PUT Http request
	 * 
	 * @return
	 */
	public static Result update(Long id) { // PUT
		// bindFromRequest gets the parameters
		Form<Thing> form = form(Thing.class).bindFromRequest();
		Thing model = form.get();
		// model.id = id;
		// model.update();
		return ok(toJson(model));
	}

	/**
	 * Delete an item from id<br>
	 * DELETE Http request
	 * 
	 * @param id
	 * @return
	 */
	public static Result delete(Long id) { // DELETE
		Thing modelToFind = Thing.find.byId(id);
		if (modelToFind != null) {
			modelToFind.delete();
			return ok(toJson(true));
		} else {
			return badRequest("not found");
		}

	}

	/**
	 * Get any items from field value in JSON format<br>
	 * GET Http request<br>
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public static Result query(String fieldName, String value) { // GET
		// things/name/equals/morane
		List<Thing> list = Thing.find.where().eq(fieldName, value).findList();
		return ok(toJson(list));
	}

}