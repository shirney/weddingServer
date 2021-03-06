package wedding; /**
 * Created by Shirney on 15/8/2.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wedding.db.GuestDao;
import wedding.document.Guest;

import java.net.UnknownHostException;

@RestController
@EnableAutoConfiguration
@ComponentScan("wedding")
@Configuration
public class Controller {

    @Autowired
    private GuestDao guestDao;

    @RequestMapping(method = RequestMethod.GET, value = "/guest")
    public String getGuest(Authentication authentication) throws UnknownHostException {
        final String guestID = authentication.getName();
        return this.guestDao.query(guestID);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/guest", produces = "application/json")
    public String postGuest(@RequestBody Guest guest, Authentication authentication) throws JsonProcessingException {
        try {
            System.out.println("user id: " + authentication.getName());
            final String guestID = authentication.getName();
            guest.setId(guestID);
            this.guestDao.upsert(guestID, guest.buildDBObject());

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(guest);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Controller.class, args);
    }
}
