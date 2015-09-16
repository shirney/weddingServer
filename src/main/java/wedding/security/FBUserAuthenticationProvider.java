package wedding.security;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONObject;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Created by Shirney on 15/9/2.
 */
public class FBUserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null || StringUtils.isEmpty(authentication.getName()) ) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
        }

        String fbToken = authentication.getCredentials().toString();

        try {
            if(!this.validateFBToken(fbToken, authentication.getName())) {
                throw new BadCredentialsException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);

            }
        } catch (Exception e) {
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
        }
    }

    private boolean validateFBToken(final String fbToken, final String userID) throws IOException {
        final String url = "https://graph.facebook.com/me?access_token=" + fbToken;
        final HttpClient client = new HttpClient();
        final GetMethod method = new GetMethod(url);

        int statusCode = client.executeMethod(method);

        if (statusCode != HttpStatus.SC_OK) {
            return false;
        }

        String responseBody = method.getResponseBodyAsString();
        JSONObject jsonObject = new JSONObject(responseBody);
        String fbUserID = (String) jsonObject.get("id");

        return userID.equals(fbUserID);
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return new User(username, authentication.getCredentials().toString(), true, true, true, true,
                AuthorityUtils.createAuthorityList("USER"));
    }
}
