package us.superkill.viral.facebook;

import java.io.InputStream;
import java.util.Properties;

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
		logger.debug("Using token: " + token);
		this.accessToken = token;
		this.facebookClient = new DefaultFacebookClient(this.accessToken);
		//insert an authentication check
		logger.debug("Authenticated");
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
		Properties prop = new Properties();
    	InputStream input = null;
    	
    	try {
    		String filename = "application.properties";
    		input = Authenticator.class.getClassLoader()
    				.getResourceAsStream(filename);
    		prop.load(input);
    		token = prop.getProperty("userToken");
    	} catch (Exception e) {
    		logger.error("Unable to find application properties or token.");
    	} finally {
    		if(input!=null){
        		try {
        			input.close();
				} catch (Exception e) {
					logger.error("Unable to close properties file.");
				}
        	}
    	}
		return token;
	}
	
	public FacebookClient getFacebookClient() {
		logger.debug("returning client");
		return this.facebookClient;
	}
}
