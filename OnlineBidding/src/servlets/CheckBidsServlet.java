package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import beans.BidBean;
import beans.PostBean;
import beans.ProductsBean;
import beans.RegisterBidBean;
import beans.RegisterBidsBean;

/**
 * Servlet implementation class CheckBidsServlet
 */
@WebServlet("/CheckBidsServlet")
public class CheckBidsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckBidsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do post is running!!");
		response.setContentType("text/json");
		PrintWriter out=response.getWriter();
		
		
		//write code for Prod_ID and U_ID here, everything else is taken care of
		
		//String bidID = request.getParameter("bidID");    
		String searchText = request.getParameter("itemid"); 
		//String username = request.getParameter("users"); 
		//String postUserID = request.getParameter("username");
		//String itemDesc = request.getParameter("itemdesc"); 
		//String itemQuality = request.getParameter("itemquality");
		//String itemPrice = request.getParameter("itemprice");
		//System.out.println("controller : "+postUserID );
		//String searchText = request.getParameter("users");
		//searchText = "%" + searchText  + "%";
		
		//ProductsBean products = new ProductsBean();
		PostBean postBean = new PostBean();
		System.out.println("searchtext:" + searchText);
		postBean.setPost(searchText);
		
		RegisterBidsBean products=new RegisterBidsBean();
		Boolean status = false;
		try {
			 
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:9443/OnlineBiddingServices/rest/displaypostbids/display");
			
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
			RegisterBidsBean searchResult = gson.fromJson(restResponse.getEntity(String.class), RegisterBidsBean.class);
				
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
			session.setAttribute("BIDS", products);
			RequestDispatcher rd=request.getRequestDispatcher("BiddingDescriptionPage.jsp");
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
			rd.forward(request, response);
		}
		
		
		//now send request to service
		
	}


}
