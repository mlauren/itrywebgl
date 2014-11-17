package us.superkill.viral.facebook;

import com.restfb.FacebookClient;
import com.restfb.types.Post;

public class CommentGrabber {
	
	private static String[] getComments(FacebookClient fbClient) {
		String[] comments = {"",""};
		
		fbClient.fetchObject("320161474742175/feed?limit=1", Post.class);
		
		return comments;
	}
	
	/**
	 * Stub
	 * @param fbClient
	 * @return
	 */
	public static String[] getVideos(FacebookClient fbClient) {
		String[] videos = {""};
		getComments(fbClient);
		return videos;
	}
}
