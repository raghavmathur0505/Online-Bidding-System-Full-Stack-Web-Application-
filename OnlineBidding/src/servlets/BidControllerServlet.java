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

/**
 * Servlet implementation class PostControllerServlet
 */
@WebServlet("/BidControllerServlet")
public class BidControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidControllerServlet() {
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
		String username = request.getParameter("users"); 
		String postUserID = request.getParameter("username");
		String itemDesc = request.getParameter("itemdesc"); 
		String itemQuality = request.getParameter("itemquality");
		String itemPrice = request.getParameter("itemprice");
		String postUserEmail = request.getParameter("postuseremail");
		String itemName = request.getParameter("itemname");
		String bidUserEmail = request.getParameter("useremail");
		System.out.println("controller : "+postUserID );
		
		BidBean bean=new BidBean();
		
		//write set functions for Prod_ID and U_ID
		
		
		//bean.setBidID(bidID);
		bean.setBidUserEmail(bidUserEmail);
		bean.setItemName(itemName);
		bean.setPostUserEmail(postUserEmail);
		bean.setItemID(itemID);
		bean.setUsername(username);
		bean.setPostUserID(postUserID);
		bean.setItemDesc(itemDesc);
		bean.setItemQuality(itemQuality);
		bean.setItemPrice(itemPrice);
		
		request.setAttribute("bean",bean);
		
		/* Boolean status = false;
		try {
			 
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8086/OnlineBidding/rest/bidservices/newbid");
			
			Gson userJson = new Gson();
			String data = userJson.toJson(bean);
			
			
			MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("username", username);
			formData.add("password", password);
			formData.add("firstName", firstName);
			formData.add("lastName", lastName);
			formData.add("address", address);
			formData.add("email", email);
			formData.add("phone", phone);
			
			
			
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
		}*/
		//Boolean x=request.setAttribute("bean",bean);
		
		//if(request.setAttribute("bean",bean)){
			HttpSession session = request.getSession();
			session.setAttribute("BID",bean);
			
			RequestDispatcher rd=request.getRequestDispatcher("ProductDescriptionPage.jsp");
			rd.forward(request, response);
	//	}
		//else{
		//	RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
		//	rd.forward(request, response);
		}
	
	}
    
 