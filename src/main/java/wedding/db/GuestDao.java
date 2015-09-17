package wedding.db;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MongoDB mongoDB;

    private DBCollection collection;

    public void upsert(final String id, final BasicDBObject update) {
        try {
            GuestDBQuery query = new GuestDBQuery();
            query.setID(id);
            this.getCollection().update(query, update, true, false);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String query(final String id) {
        DBObject result = null;
        try {
            GuestDBQuery query = new GuestDBQuery();
            query.setID(id);
            result = this.getCollection().findOne(query, new GuestDBFields());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result == null ? "" : result.toString();
    }

    public DBCollection getCollection() throws UnknownHostException {
        if(collection == null) {
            final DB db = this.mongoDB.getDB();
            this.collection = db.getCollection(collectionName);
        }
        return this.collection;
    }
}
