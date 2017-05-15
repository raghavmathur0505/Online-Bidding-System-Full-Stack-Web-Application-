package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import beans.PostBean;
import beans.ProductBean;
import beans.ProductsBean;
//import beans.SearchBean;
import beans.UserBean;

import java.util.ArrayList;

/**
 * Servlet implementation class ViewPostsServlet
 */
@WebServlet("/ViewPostsServlet")
public class ViewPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPostsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPOST(HttpServletRequest request, HttpServletResponse response)
	 */
    //String user="";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json");
			//ServletRequest session2 = null;
			//@SuppressWarnings("null")
			//UserBean userBean=(UserBean)session2.getAttribute("USER");
			//user=userBean.getUserName();
		System.out.println("in Viewpostsservlet" );
		String searchText = request.getParameter("users");
		//searchText = "%" + searchText  + "%";
		ProductsBean products = new ProductsBean();
		PostBean postBean = new PostBean();
		System.out.println("searchtext:" + searchText);
		postBean.setPost(searchText);
		
		Boolean status = false;
		try {
			 
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:9443/OnlineBiddingServices/rest/displayservices/display");
			
			Gson userJson = new Gson();
			String data = userJson.toJson(postBean);
			
			
			//ClientResponse restResponse = webResource
			//    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			//    .post(ClientResponse.class, formData);
			ClientResponse restResponse = webResource
				    .type(MediaType.APPLICATION_JSON)
				    .post(ClientResponse.class, data);
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
 
			Gson gson = new Gson();
			ProductsBean searchResult = gson.fromJson(restResponse.getEntity(String.class), ProductsBean.class);
				
			System.out.println("servlet printing now: ");
			//searchResult.getBooks();
			
			products = searchResult;
			
			
			//String statusString = restResponse.getEntity(String.class);
			//status = Boolean.parseBoolean(statusString);
			status = products.isValidPost();
			System.out.println("servlet status: " + status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		if(status){
			System.out.println("status is good!");
			HttpSession session = request.getSession();
			session.setAttribute("PRODUCTS", products);
			RequestDispatcher rd=request.getRequestDispatcher("ExistingPosts.jsp");
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
			rd.forward(request, response);
		}
		
		
		//now send request to service
		
	}


}
