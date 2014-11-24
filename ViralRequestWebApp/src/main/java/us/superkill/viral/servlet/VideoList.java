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
    	logger.debug("Initializing servlet.");
    	this.auth = new Authenticator(); 
    }

	/**
	 * Takes group id as param and returns all youtube videos in the first post
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MTV3 is 320161474742175
		//Test group is 728985000520376
		String groupId = request.getParameter("group");
		logger.debug("Getting Videos from group " + groupId);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		try {
			out.println(CommentGrabber.getVideos(auth, groupId));
			logger.debug("Successfully delived video list.");
		} catch (Exception e) {
			logger.error("Error retriving results. Possibly need new token.");
			out.println("[]");
			
			logger.debug("Retring connection.");
			this.auth = new Authenticator();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
