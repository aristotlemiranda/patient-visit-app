package sg.com.nets.test.patient.visit.app.config;

import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/***
 *@author Miranda Aristotle
 **/

public class ClientRequestAuthenticator implements ClientHttpRequestInterceptor {

    private final String username;
    private final String password;

    public ClientRequestAuthenticator( String username, String password ) {
        this.username = username;
        this.password = password;
    }

    @Override
    public ClientHttpResponse intercept( HttpRequest request, byte[] body, ClientHttpRequestExecution execution ) throws IOException {
       
    	final String auth = username + ":" + password;
        final byte[] encodedAuth = Base64.encodeBase64( auth.getBytes( Charset.forName( "US-ASCII" ) ) );
        final String authHeader = "Basic " + new String( encodedAuth );

        //Adding the authentication header
        request.getHeaders().add( "Authorization", authHeader );
        return execution.execute( request, body );
    }
}