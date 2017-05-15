package services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import DAO.DBOperation;
import beans.RegisterBidBean;
import beans.RegisterBidsBean;
import beans.UserBean;

@Path("/emailcartservice")
public class EmailCartService {
	
	@Path("/newbid")
	@POST
	@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
	public Response addNewUser(String data) {
		boolean response = false;
		//ArrayList<ArrayList<String>> isAddNewUserSuccessful = true; //should be set to false
		Gson gson = new Gson();
		RegisterBidBean user = gson.fromJson(data, RegisterBidBean.class);
		RegisterBidsBean products= new RegisterBidsBean();
		String itemId = user.getItemID();
		String itemName = user.getItemName();
		String itemPrice = user.getActPrice();
		String bidderId = user.getBidderId();
		String postUserId = user.getPostUserID();
		String postUserEmail = user.getPostUserEmail();
		String bidUserEmail = user.getBidUserEmail();
		String itemCount = user.getItemCount();
		
		System.out.println("this is the email address entered are: " + postUserEmail + " " + bidUserEmail );
		//DBOperation dao = new DBOperation();
		System.out.println(bidderId);
		System.out.println(postUserId);
		ArrayList<ArrayList<String>> isAddNewUserSuccessful = DBOperation.emailCart(itemId, itemName, itemPrice, bidderId, postUserId, postUserEmail,bidUserEmail,itemCount);
		System.out.println("isPlaceOrderSuccessful results: " + isAddNewUserSuccessful);
		
		//sql code to add userInformation to database goes here
		

		if(isAddNewUserSuccessful != null){
			response = true;
			//search.setsearchResult(searchResult);
			products.setValidationSearch(response);
			
			EmailService email = new EmailService();
			email.setEmailTo(bidUserEmail);
			email.setEmailFrom("onlinebiddingrad@gmail.com");
			email.setHost("smtp.gmail.com");
			email.setProperties();
			email.setSession();
			// debug code -> System.out.println(emailAddress);
			
			//default message for now
			String subject = "Online Bidding successful registration";
			String msg = "Congratulations " + bidderId + " you've product has been bought by " + postUserId +
						"\n\nYou will receive the payment and shipping details soon !!!";
			
			email.sendEmail(subject, msg); 
			EmailService email2 = new EmailService();
			email2.setEmailTo(postUserEmail);
			email2.setEmailFrom("onlinebiddingrad@gmail.com");
			email2.setHost("smtp.gmail.com");
			email2.setProperties();
			email2.setSession();
			// debug code -> System.out.println(emailAddress);
			
			//default message for now
			String subject1 = "Online Bidding successful registration";
			String msg1 = "Congratulations " + postUserId + " you've have purchased a product from " + bidderId +
						"\n\nYou will receive the payment and shipping details soon !!!";
			
			email2.sendEmail(subject1, msg1); 
			
			for(int index=0;index < isAddNewUserSuccessful.size();index++)
			{
				RegisterBidBean product = new RegisterBidBean();
				product.setItemName(isAddNewUserSuccessful.get(index).get(0));
				product.setActPrice(isAddNewUserSuccessful.get(index).get(1));
				product.setBidUserEmail(isAddNewUserSuccessful.get(index).get(2));
				product.setPostUserEmail(isAddNewUserSuccessful.get(index).get(3));
				product.setItemCount(isAddNewUserSuccessful.get(index).get(4));
				product.setItemID(isAddNewUserSuccessful.get(index).get(5));
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
			//search.setValidation(response);
			
		}
		//System.out.println("value of string is: " + String.valueOf(response));
		//return Response.ok().entity(String.valueOf(response)).build();
		
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
