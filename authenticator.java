import java.util.List;

import org.apache.http.impl.client.HttpClientBuilder;

import org.jreddit.oauth.RedditOAuthAgent;
import org.jreddit.oauth.RedditToken;
import org.jreddit.oauth.app.RedditApp;
import org.jreddit.oauth.app.RedditInstalledApp;
import org.jreddit.oauth.client.RedditClient;
import org.jreddit.oauth.client.RedditHttpClient;
import org.jreddit.oauth.exception.RedditOAuthException;
import org.jreddit.parser.entity.Submission;
import org.jreddit.parser.exception.RedditParseException;
import org.jreddit.parser.listing.SubmissionsListingParser;
import org.jreddit.request.retrieval.param.SubmissionSort;
import org.jreddit.request.retrieval.submissions.SubmissionsOfSubredditRequest;

public class authenticator {

    public static void main(String[] args) throws RedditOAuthException, RedditParseException {

        // Information about the app
        String userAgent = "Reddit Grabber (NDSU_415_2017)";
        String clientID = "be710ec6a39867d9e8fb";
        String redirectURI = "http://students.cs.ndsu.nodak.edu/~sstamant/";
        
        // Reddit application
        RedditApp redditApp = new RedditInstalledApp(clientID, redirectURI);
        
        // Create OAuth agent
        RedditOAuthAgent agent = new RedditOAuthAgent(userAgent, redditApp);    
        
        // Create request executor 
        RedditClient client = new RedditHttpClient(userAgent, HttpClientBuilder.create().build());
        
        // Create token (will be valid for 1 hour)
        RedditToken token = agent.tokenAppOnly(false);

        // Create parser for request
        SubmissionsListingParser parser = new SubmissionsListingParser();

        // Create the request
        SubmissionsOfSubredditRequest request = (SubmissionsOfSubredditRequest) new SubmissionsOfSubredditRequest("programming", SubmissionSort.HOT).setLimit(100);

        // Perform and parse request, and store parsed result
        List<Submission> submissions = parser.parse(client.get(token, request));
        
        // Now print out the result (don't care about formatting)
        System.out.println(submissions);

    }
    
}
