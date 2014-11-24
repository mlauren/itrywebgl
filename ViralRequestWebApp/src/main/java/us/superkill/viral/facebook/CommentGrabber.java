package us.superkill.viral.facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.restfb.DefaultJsonMapper;
import com.restfb.JsonMapper;
import com.restfb.json.JsonObject;
import com.restfb.types.Comment;
import com.restfb.types.Post;
import com.restfb.types.Post.Comments;

public class CommentGrabber {
	
	private static final Logger logger = LogManager.getLogger(CommentGrabber.class.getName());
	
	/**
	 * Given a FacebookClient returns comments on first post of group without 
	 * any formatting from facebook
	 * @param fbClient a FacebookClient that is authenticated with group access
	 * @return Comments object
	 */
	private static Comments getComments(Authenticator auth, 
			String groupId) {
		logger.debug("Getting comments.");
		JsonMapper jsonMapper = new DefaultJsonMapper();
		com.restfb.json.JsonObject posts = auth.getFacebookClient()
				.fetchObject(groupId + "/feed", JsonObject.class);
		Post post = jsonMapper.toJavaObject(
				posts.getJsonArray("data").get(0).toString(), Post.class);
		Comments comments = post.getComments();
		return comments;
	}
	
	private static String getVideoLink(String message) {
		String value= "";
		Pattern pattern = Pattern.compile(
				"^(http(s)?\\:\\/\\/)?(www\\.)?(youtube\\.com|youtu\\.?be)"
				+ "\\/(v\\=)?.+$");
		Matcher matcher = pattern.matcher(message);
		
		if (matcher.find()) {
			value = matcher.group();
		}
		
		return value;
	}
	
	public static String[] formatComments(Comments input) {
		logger.debug("Filtering for youtube links.");
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
		logger.debug("Converting to JSON.");
		for (int i = 0; i < videoList.length; i++) {
			logger.debug(videoList[i]);
		}
		String videos = "";
		
		Gson gson = new Gson();
		videos = gson.toJson(videoList);
		
		return videos;
	}
	
	/**
	 * Stub
	 * @param fbClient
	 * @return
	 */
	public static String getVideos(Authenticator auth, String groupId) {
		logger.debug("Start retriving videos");
		String videos = "";
		//JsonObject test = auth.getFacebookClient().fetchObject(groupId + "/feed?limit=1", JsonObject.class);
		videos = toJson(formatComments(getComments(auth, groupId)));
		logger.debug("Finished retriving videos.");
		return videos;
	}
}
