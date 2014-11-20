package us.superkill.viral.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.superkill.viral.facebook.Authenticator;
import us.superkill.viral.facebook.CommentGrabber;

import com.restfb.FacebookClient;

/**
 * Servlet implementation class VideoList
 */
@WebServlet("/VideoList")
public class VideoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Authenticator auth;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoList() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) {
    	this.auth = new Authenticator(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		try {
			out.println(CommentGrabber.getVideos(auth.getFacebookClient(), 
					"320161474742175"))
		} catch {
			System.out.println("OOPS");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
