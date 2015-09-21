package wedding.db;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by Shirney on 15/8/21.
 */
@Service
public class MongoDB {
    @Value("${db.name}")
    private String dbName;

    private DB db;

    @Value("${db.account}")
    private String account;

    @Value("${db.password}")
    private String password;

    @Value("${db.host}")
    private String dbHost;

    public DB getDB() throws UnknownHostException {
        if(db == null) {
            MongoCredential credential = MongoCredential.createCredential(account,
                    dbName,
                    password.toCharArray());
            MongoClient mongoClient = new MongoClient(new ServerAddress(this.dbHost), Arrays.asList(credential));
//            MongoClient mongoClient = new MongoClient();
            db = mongoClient.getDB(dbName);
        }

        return db;
    }
}
