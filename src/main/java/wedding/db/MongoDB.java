package wedding.db;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by Shirney on 15/8/21.
 */
public class MongoDB {
    @Value("db.name")
    private static String dbName;

    private static DB db;

    @Value("db.account")
    private static String account;

    @Value("db.password")
    private static String password;

    public static DB getDB() throws UnknownHostException {
        if(db == null) {
            MongoCredential credential = MongoCredential.createCredential(account,
                    dbName,
                    password.toCharArray());
            MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));
//            MongoClient mongoClient = new MongoClient();
            db = mongoClient.getDB(dbName);
        }

        return db;
    }
}
