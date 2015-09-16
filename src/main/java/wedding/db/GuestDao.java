package wedding.db;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.springframework.stereotype.Component;
import wedding.document.GuestDBFields;
import wedding.document.GuestDBQuery;

import java.net.UnknownHostException;

/**
 * Created by Shirney on 15/8/21.
 */
@Component
public class GuestDao {

    public static String collectionName = "guests";

    private DBCollection collection;

    public GuestDao() throws UnknownHostException {
        final DB db = MongoDB.getDB();
        this.collection = db.getCollection(collectionName);
    }

    public void upsert(final String id, final BasicDBObject update) {
        GuestDBQuery query = new GuestDBQuery();
        query.setID(id);
        this.collection.update(query, update, true, false);
    }

    public String query(final String id) {
        GuestDBQuery query = new GuestDBQuery();
        query.setID(id);
        DBObject result = this.collection.findOne(query, new GuestDBFields());

        return result == null ? "" : result.toString();
    }
}
