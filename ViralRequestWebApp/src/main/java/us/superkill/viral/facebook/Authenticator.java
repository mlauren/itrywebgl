package us.superkill.viral.facebook;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

public class Authenticator {
	
	private String accessToken;
	private FacebookClient facebookClient;
	
	public Authenticator(String token) {
		if (token == null || token.equalsIgnoreCase("")) {
			this.accessToken = "";
			this.facebookClient = new DefaultFacebookClient();
		} else {
			this.accessToken = token;
			this.facebookClient = new DefaultFacebookClient(this.accessToken);
		}
	}
	
	public Authenticator() {
		this(getToken());
	}
	
	/**
	 * Stub for accessing .properties to get actual token
	 * @return String of facebook api token
	 */
	private static String getToken() {
		String token = "";
		
		return token;
	}
	
	public FacebookClient getFacebookClient() {
		return this.facebookClient;
	}
}
