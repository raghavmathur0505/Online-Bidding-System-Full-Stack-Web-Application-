package services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import DAO.DBOperation;
import beans.ProductBean;
import beans.ProductsBean;
import beans.RegisterBidBean;
import beans.RegisterBidsBean;
import beans.SearchBean;
import beans.UserBean;
import beans.ProductsBean;

@Path("/viewcartservice")
public class ViewCartService {
	
	@Path("/view")
	@POST
	@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
	public Response addNewUser(String data) 
	{
		
		boolean response = false;
		
		Gson gson = new Gson();
		SearchBean search = gson.fromJson(data, SearchBean.class);
		RegisterBidsBean products = new RegisterBidsBean();
		
		//search.getSearch();
		
		//System.out.println("this is the value of search: " + search);
		
		//sql code to add userInformation to database goes here
		String title = search.getSearch();
		
		ArrayList<ArrayList<String>> searchResult = DBOperation.viewCart(title);
		System.out.println("The search result in ViewCartServlet is: " + searchResult);
		search.setsearchResult(searchResult);
		System.out.println("index 0 is: " + searchResult.get(0).get(0));
		
		if(searchResult != null){
			response = true;
			search.setsearchResult(searchResult);
			products.setValidationSearch(response);
			
			
			for(int index=0;index < searchResult.size();index++)
			{
				RegisterBidBean product = new RegisterBidBean();
				product.setItemName(searchResult.get(index).get(0));
				product.setActPrice(searchResult.get(index).get(1));
				product.setBidUserEmail(searchResult.get(index).get(2));
				product.setPostUserEmail(searchResult.get(index).get(3));
				product.setItemCount(searchResult.get(index).get(4));
				product.setItemID(searchResult.get(index).get(5));
				product.setBidderId(searchResult.get(index).get(6));
				//product.setImage(searchResult.get(index).get(12));
				
				products.addProducts(product);
				
				//System.out.println("cart bis is : " + product.getItemName());
				
				//System.out.println(searchResult.get(index).get(0));
		
			}
			
			//books.getBooks();
		}
		else
		{
			response = false;
			search.setValidation(response);
			
		}

		/*
		if(isSearchSuccessful){
			response = true;
			
			List<Book> books = new ArrayList<Book>();
			//books.add(book);
			Book book = new Book();
			book.setAuthor("J.K. Rowling");
			book.setInventory(2);
			book.setIsbn("12345");
			book.setPrice(12.99);
			book.setTitle("Harry Potter and the Philosopher's Stone");
			//books.add();
			
		}
		else
		{
			response = false;
		}
		*/
		
		Gson searchResultJson = new Gson();
		String responseData = searchResultJson.toJson(products);
		//System.out.println("value of string is: " + responseData);
		return Response.ok().entity(responseData).build();
	}
	
	@Path("/availableusername/{username}")
	@GET
	public String availableUsername(@PathParam("username") String username) {
		//code here to see if userName exists		
		return username + "001";
	}

}
