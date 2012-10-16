package controllers;

import static play.libs.Json.toJson;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class SecuredJson extends Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("email");
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		return ok(toJson("failed"));
	}
}
