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
import beans.RegisterBidBean;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
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
		
		String bidID = request.getParameter("bidId");    
		String itemID = request.getParameter("itemId"); 
		String bidderId = request.getParameter("bidderId"); 
		String postUserID = request.getParameter("postUserId");
		String expDesc = request.getParameter("expDesc"); 
		String actDesc = request.getParameter("actDesc"); 
		String expQuality = request.getParameter("expQuality");
		String actQuality = request.getParameter("actQuality");
		String expPrice = request.getParameter("expPrice");
		String actPrice = request.getParameter("actPrice");
		String itemName = request.getParameter("itemName");
		String postUserEmail = request.getParameter("postUserEmail");
		String bidUserEmail = request.getParameter("bidUserEmail");
		System.out.println("in addtocartservlet: " + itemID+ bidderId + postUserID+expDesc + actDesc+expQuality+  actQuality+ expPrice+ actPrice+ itemName+ postUserEmail + bidUserEmail);
		System.out.println("RegisterBidServlet-postuserid : "+ postUserID );
		
		RegisterBidBean bean=new RegisterBidBean();
		
		//write set functions for Prod_ID and U_ID
		
		
		//bean.setBidID(bidID);
		bean.setItemCount("1");
		bean.setBidID(bidID);
		bean.setItemID(itemID);
		bean.setBidderId(bidderId);
		bean.setPostUserID(postUserID);
		bean.setExpDesc(expDesc);
		bean.setExpQuality(expQuality);
		bean.setExpPrice(expPrice);
		bean.setActDesc(actDesc);
		bean.setActQuality(actQuality);
		bean.setActPrice(actPrice);
		bean.setItemName(itemName);
		bean.setPostUserEmail(postUserEmail);
		bean.setBidUserEmail(bidUserEmail);
		
		request.setAttribute("bean",bean);
		
		Boolean status = false;
		try {
			 
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:9443/OnlineBiddingServices/rest/addtocartservice/newbid");
			
			Gson userJson = new Gson();
			String data = userJson.toJson(bean);
			
			
			/*MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("username", username);
			formData.add("password", password);
			formData.add("firstName", firstName);
			formData.add("lastName", lastName);
			formData.add("address", address);
			formData.add("email", email);
			formData.add("phone", phone);*/
			
			
			
			//ClientResponse restResponse = webResource
			//    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			//    .post(ClientResponse.class, formData);
			ClientResponse restResponse = webResource
				    .type(MediaType.APPLICATION_JSON)
				    .post(ClientResponse.class, data);
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
 
			String statusString = restResponse.getEntity(String.class);
			status = Boolean.parseBoolean(statusString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(status){
			//HttpSession session = request.getSession();
			//session.setAttribute("USER",user);
			System.out.println("session at the end with postuserId= "+ bean.getPostUserID() );
			RequestDispatcher rd=request.getRequestDispatcher("MainPage.jsp");
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
			rd.forward(request, response);
		}
	
    }
}
 