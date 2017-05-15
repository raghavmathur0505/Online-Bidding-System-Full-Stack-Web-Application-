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
import beans.RegisterBidsBean;

/**
 * Servlet implementation class EmailCartServlet
 */
@WebServlet("/EmailCartServlet")
public class EmailCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailCartServlet() {
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
		String itemID = request.getParameter("itemid"); 
		String bidderId = request.getParameter("biduser"); 
		String postUserID = request.getParameter("postuser");
		String itemPrice = request.getParameter("itemprice");
		String itemName = request.getParameter("itemname");
		String itemCount = request.getParameter("itemcount");
		String postUserEmail= request.getParameter("postuseremail");
		String bidUserEmail = request.getParameter("biduseremail");
		System.out.println(itemID);
		System.out.println("emailCartServlet-postuserid : "+ postUserID + itemID +itemName);
		RegisterBidsBean products = new RegisterBidsBean();
		RegisterBidBean bean=new RegisterBidBean();
		
		//write set functions for Prod_ID and U_ID
		
		
		//bean.setBidID(bidID);
		bean.setItemName(itemName);
		bean.setPostUserEmail(postUserEmail);
		bean.setBidUserEmail(bidUserEmail);
		bean.setItemID(itemID);
		bean.setBidderId(bidderId);
		bean.setPostUserID(postUserID);
		
		bean.setActPrice(itemPrice);
		bean.setItemCount(itemCount);
		
		request.setAttribute("bean",bean);
		
		Boolean status = false;
		try {
			 
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:9443/OnlineBiddingServices/rest/emailcartservice/newbid");
			
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
			
			Gson gson = new Gson();
			RegisterBidsBean searchResult = gson.fromJson(restResponse.getEntity(String.class), RegisterBidsBean.class);
				
			System.out.println("servlet printing now: ");
			//searchResult.getBooks();
			
			products = searchResult;
			//System.out.print(products.getACTPRICE(0));
			
			
			//String statusString = restResponse.getEntity(String.class);
			//status = Boolean.parseBoolean(statusString);
			status = products.isValidSearch();
			System.out.println("servlet status: " + status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(status){
			System.out.println("status is good!");
			HttpSession session = request.getSession();
			session.setAttribute("PRODUCT1", products);
			RequestDispatcher rd=request.getRequestDispatcher("OrderConfirmation.jsp");
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
			rd.forward(request, response);
		}
		
 /*
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
		*/
	
    }
}
 