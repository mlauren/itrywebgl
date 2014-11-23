package us.superkill.viral.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import us.superkill.viral.facebook.Authenticator;
import us.superkill.viral.facebook.CommentGrabber;

/**
 * Servlet implementation class VideoList
 */
@WebServlet("/VideoList")
public class VideoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = 
    		LogManager.getLogger(VideoList.class.getName());
	
	private Authenticator auth;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoList() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	this.auth = new Authenticator(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MTV3 is 320161474742175
		//Test group is 728985000520376
		String groupId = request.getParameter("group");
		logger.debug("Getting Videos from" + groupId);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
//		Post post = auth.getFacebookClient().fetchObject("320161474742175/feed?limit=1", 
//				Post.class);
//		Comments comments = post.getComments();

//		JsonObject post = auth.getFacebookClient().fetchObject("320161474742175/feed?limit=1", 
//				JsonObject.class);
		out.println(CommentGrabber.getVideos(auth, groupId));
//		JsonObject test = auth.getFacebookClient().fetchObject(groupId + "/feed?limit=1", JsonObject.class);
//		out.println(post.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
