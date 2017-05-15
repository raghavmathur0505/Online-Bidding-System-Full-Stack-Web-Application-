package services;

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
import beans.ProductBean;

@Path("/postservices")
public class PostServices {
	
	@Path("/newpost")
	@POST
	@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
	public Response postProduct(String data) {
		boolean response = false;
		boolean isAddNewUserSuccessful = true; //should be set to false
		Gson gson = new Gson();
		ProductBean user = gson.fromJson(data, ProductBean.class);
		
		String itemName = user.getItemName();
		String itemPrice = user.getItemPrice();
		String itemDesc = user.getItemDesc();
		String itemCategory = user.getItemCategory();
		String itemQuality = user.getItemQuality();
		String add1 = user.getAdd1();
		String add2 = user.getAdd2();
		String country = user.getCountry();
		String state = user.getState();
		String city = user.getCity();
		//String image = user.getImage();
		String username = user.getUserName();
		
		
		System.out.println("this is the item name address entered " + itemName);
		//DBOperation dao = new DBOperation();
		isAddNewUserSuccessful = DBOperation.prodPost(username, itemName, itemPrice, itemDesc, itemCategory, itemQuality, add1, add2,country, state,city);
		System.out.println(" Product add succussful?: " + isAddNewUserSuccessful);
		
		//sql code to add userInformation to database goes here
		
		
		if(isAddNewUserSuccessful){
			response = true;
			System.out.println("value of string is: " + String.valueOf(response));
			
			/**
			EmailService email = new EmailService();
			email.setEmailTo(emailAddress);
			email.setEmailFrom("ecommerceutdbookstore@gmail.com");
			email.setHost("smtp.gmail.com");
			email.setProperties();
			email.setSession();
			// debug code -> System.out.println(emailAddress);
			
			//default message for now
			String subject = "F&N Bookstore succesful registration";
			String msg = "Congratulations " + firstName + " you've successfully created an account " +
						"\nyour username is " + username +
						"\n\nEnjoy our service!!!";
			
			email.sendEmail(subject, msg);
			
			*/
		}
		else{
			response = false;
		}
		//System.out.println("value of string is: " + String.valueOf(response));
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/availableusername/{username}")
	@GET
	public String availableUsername(@PathParam("username") String username) {
		//code here to see if userName exists		
		return username + "001";
	}

}
