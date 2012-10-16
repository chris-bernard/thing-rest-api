package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.DBQuery;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;

import org.codehaus.jackson.annotate.JsonProperty;

import play.db.ebean.Model;

import com.mongodb.DBCollection;

@Entity(name = "users")
public class User extends Model {

	@Id
	@ObjectId
	@JsonProperty("_id")
	public String id;

	public String email;

	public String name;

	public String password;

	public boolean activated;

	public static Model.Finder<String, User> find = new Model.Finder<String, User>(String.class, User.class);

	public static List<User> findAll() {
		DBCursor<User> dbCursor = getCollection().find();
		List<User> users = new ArrayList<>();
		while (dbCursor.hasNext()) {
			users.add(dbCursor.next());
		}
		return users;
	}

	public static User findByEmail(String email) {
		return getCollection().findOne(DBQuery.is("email", email));
	}

	public static User authenticate(String email, String password) {
		return getCollection().findOne(DBQuery.is("email", email).is("password", password));
	}

	public String toString() {
		return "User(" + email + ")";
	}

	public static JacksonDBCollection<User, String> getCollection() {
		DBCollection collection = DBMongoConnection.getInstance().getCollection("users");
		return JacksonDBCollection.wrap(collection, User.class, String.class);
	}

}