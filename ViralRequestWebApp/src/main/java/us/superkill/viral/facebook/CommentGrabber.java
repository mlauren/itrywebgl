package us.superkill.viral.facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
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
	private static Comments getComments(FacebookClient fbClient, 
			String groupId) {
		
		Post post = fbClient.fetchObject(groupId + "/feed?limit=1", 
				Post.class);
		Comments comments = post.getComments();
		
		return comments;
	}
	
	private static String getVideoLink(String message) {
		String value= "";
		Pattern pattern = Pattern.compile(
				"http\\:\\/\\/www\\.youtube\\.com\\/watch\\?v\\=[^\\s]*");
		Matcher matcher = pattern.matcher(message);
		
		if (matcher.find()) {
			value = matcher.group();
		}
		
		return value;
	}
	
	private static String[] formatComments(Comments input) {
		List<Comment> comments = input.getData();
		ArrayList<String> formatted = new ArrayList<String>();
		
		for(int i = 0; i < comments.size(); i += 1) {
			if (!getVideoLink(comments.get(i).getMessage())
					.equalsIgnoreCase("")) {
				formatted.add(getVideoLink(comments.get(i).getMessage()));
			}
		}
		
		return formatted.toArray(new String[formatted.size()]);
	}
	
	private static String toJson(String[] videoList) {
		String videos = "";
		
		Gson gson = new Gson();
		gson.toJson(videoList);
		videos = gson.toString();
		
		return videos;
	}
	
	/**
	 * Stub
	 * @param fbClient
	 * @return
	 */
	public static String getVideos(FacebookClient fbClient, String groupId) {
		String videos = "";
		videos = toJson(formatComments(getComments(fbClient, groupId)));
		return videos;
	}
}
