package us.superkill.viral.facebook;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

public class Authenticator {
	
	private static final Logger logger = 
			LogManager.getLogger(Authenticator.class.getName());
	
	private String accessToken;
	private FacebookClient facebookClient;
	
	/**
	 * Uses specific token
	 * @param token String of authentication token
	 */
	public Authenticator(String token) {
		logger.debug("!Using token: " + token);
		this.accessToken = token;
		this.facebookClient = new DefaultFacebookClient(token);
		logger.debug("Authenticated w/ app token");
	}
	
	/**
	 * Uses token configured in .properties file
	 */
	public Authenticator() {
		this(getToken());
	}
	
	/**
	 * Stub for accessing .properties to get actual token
	 * @return String of facebook api token
	 */
	private static String getToken() {
		String token = "";
		//stub i will put in properties file eventually
		token = "CAACEdEose0cBAE4dRzCVZCXENZA8jx3VcBJepiZBR14MTQFOWPnY7g0MxjTXTJowfXiD5jIZCFT2HIXDeoNbg3YlpNxbijC989ZB7kuWhtFHIRKqzWwrp8285oZBBvVGsaTO1WqEXGqT60FgGYZCXwIQ8OT5AmYpt3Sip3y3PseZCb2GbqEXRuc5rpKtBVhL3GHNimeIOzgZBU3fHuVJ6Vi9znIBvBEkOF7gZD";
		return token;
	}
	
	public FacebookClient getFacebookClient() {
		logger.debug("returning client");
		return this.facebookClient;
	}
}
