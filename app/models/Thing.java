package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;

import org.codehaus.jackson.annotate.JsonProperty;

import play.db.ebean.Model;

import com.mongodb.DBCollection;

@Entity(name = "things")
public class Thing extends Model {

	private static final String MONGO_COLLECTION = "things";

	@Id
	@ObjectId
	@JsonProperty("_id")
	public String id;

	public String name;

	/******************/
	/** CRUD METHODS **/
	/******************/

	public static Finder<Long, Thing> find = new Finder<Long, Thing>(Long.class, Thing.class);

	public static List<Thing> findAll() {
		return getCollection().find().toArray();
	}

	public static Thing findById(Long id) {
		return find.byId(id);
	}

	public static JacksonDBCollection<Thing, String> getCollection() {
		DBCollection collection = DBMongoConnection.getInstance().getCollection(MONGO_COLLECTION);
		return JacksonDBCollection.wrap(collection, Thing.class, String.class);
	}

}
