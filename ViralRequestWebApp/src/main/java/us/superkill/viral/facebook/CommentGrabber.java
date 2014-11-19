package us.superkill.viral.facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.restfb.FacebookClient;
import com.restfb.types.Comment;
import com.restfb.types.Post;
import com.restfb.types.Post.Comments;

public class CommentGrabber {
	
	/**
	 * Given a FacebookClient returns comments on first post of group without 
	 * any formatting from facebook
	 * @param fbClient a FacebookClient that is authenticated with group access
	 * @return Comments object
	 */
	private static Comments getComments(FacebookClient fbClient) {
		
		Post post = fbClient.fetchObject("320161474742175/feed?limit=1", 
				Post.class);
		Comments comments = post.getComments();
		
		return comments;
	}
	
	private static String getVideoLink(String message) {
		String value= "";
		Pattern pattern = Pattern.compile(
				"http\\:\\/\\/www\\.youtube\\.com\\/watch\\?v\\=[a-Z0-9]*");
		
		return value;
	}
	
	private static String[] formatComments(Comments input) {
		List<Comment> comments = input.getData();
		ArrayList<String> formatted = new ArrayList<String>();
		
		for(int i = 0; i < comments.size(); i += 1) {
			formatted.add(comments.get(i).getMessage());
		}
		
		return ;
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
