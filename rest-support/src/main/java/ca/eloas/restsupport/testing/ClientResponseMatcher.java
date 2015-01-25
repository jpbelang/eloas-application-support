package ca.eloas.restsupport.testing;

import com.sun.jersey.api.client.ClientResponse;
import org.hamcrest.Description;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;


/**
 * @author JP
 */
public class ClientResponseMatcher {

    public static Matcher<ClientResponse> statusCode(final Matcher<Integer> matcher) {

        return new FeatureMatcher<ClientResponse, Integer>(matcher, "response status code", "status code") {

            @Override
            protected Integer featureValueOf(ClientResponse actual) {
                return actual.getStatus();
            }
        };
    }

    public static Matcher<ClientResponse> status(final Matcher<Response.Status> matcher) {

        return new FeatureMatcher<ClientResponse, Response.Status>(matcher, "response status", "status") {

            @Override
            protected Response.Status featureValueOf(ClientResponse actual) {
                return Response.Status.fromStatusCode(actual.getStatus());
            }
        };
    }

    public static Matcher<ClientResponse> isOK() {

        return new TypeSafeMatcher<ClientResponse>() {

            @Override
            public void describeTo(Description description) {

                description.appendText("response is ok");
            }

            @Override
            protected boolean matchesSafely(ClientResponse item) {
                return item.getStatus() / 100 == 2;
            }
        };
    }

}
