package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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

import beans.SearchBean;
import beans.UserBean;
import beans.ProductsBean;
import beans.RegisterBidsBean;
import beans.ProductBean;

import java.util.ArrayList;

/**
 * Servlet implementation class UpdateCountServlet
 */
@WebServlet("/UpdateCountServlet")
public class UpdateCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json");
		System.out.println("Update Count Servlet Called Up");
		String item = request.getParameter("itemname");
		String username = request.getParameter("users");
		//String itemCount = request.getParameter("itemcount");
		String itemCount1 = request.getParameter("itemcount1");
		//String itemCount2 = request.getParameter("itemcount2");
		
		System.out.println(item);
		System.out.println(username);
		//System.out.println(itemCount);
		System.out.println(itemCount1);
		//System.out.println(itemCount2);
		RegisterBidsBean products = new RegisterBidsBean();
		SearchBean searchBean = new SearchBean();
		searchBean.setSearch(item);
		searchBean.setSearch2(username);
		searchBean.setSearch3(itemCount1);
		
		boolean status = false;
		try {
			 
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:9443/OnlineBiddingServices/rest/updateitemcount/updatecount");
			
			Gson userJson = new Gson();
			String data = userJson.toJson(searchBean);
			
			//MultivaluedMap formData = new MultivaluedMapImpl();
			//formData.add("itemcount2", itemCount1);
			
			//ClientResponse restResponse = webResource
			  //  .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			   // .post(ClientResponse.class, formData);
			ClientResponse restResponse = webResource
			    .type(MediaType.APPLICATION_JSON)
			  .post(ClientResponse.class, data);
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			
			Gson gson = new Gson();
			RegisterBidsBean searchResult = gson.fromJson(restResponse.getEntity(String.class), RegisterBidsBean.class);
			
			System.out.println("servlet printing now: hello");
			//searchResult.getBooks();
			
			products = searchResult;
 
			String statusString = restResponse.getEntity(String.class);
			//status = Boolean.parseBoolean(statusString);
			
			status = products.isValidSearch();
			System.out.println(status);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if(true){
			HttpSession session = request.getSession();
			session.setAttribute("PRODUCT1", products);
			System.out.println("Entered here.......");
			RequestDispatcher rd=request.getRequestDispatcher("ShoppingCart.jsp");
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("MainPage.jsp");
			rd.forward(request, response);
		}
	
	}

}

