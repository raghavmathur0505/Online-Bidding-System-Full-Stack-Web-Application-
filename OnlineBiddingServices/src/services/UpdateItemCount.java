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
import beans.SearchBean;

@Path("/updateitemcount")
public class UpdateItemCount {
	
	@Path("/updatecount")
	@POST
	@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
	public Response addNewUser(String data) {
		boolean response = false;
		//boolean isAddNewProductSuccessful = false; //should be set to false
		Gson gson = new Gson();
		SearchBean bean = gson.fromJson(data, SearchBean.class);
		RegisterBidsBean products = new RegisterBidsBean();
		
		String item = bean.getSearch();
		String username = bean.getSearch2();
		String itemCount = bean.getSearch3();
		//DBOperation dao = new DBOperation();
		//isAddNewProductSuccessful = DBOperation.updateItemCount(item, username, itemCount);
		ArrayList<ArrayList<String>> searchResult=DBOperation.updateItemCount(item, username, itemCount);
		System.out.println("isUpdateProductuccessful: " + searchResult);
		
		//ArrayList<ArrayList<String>> searchResult = DBOperation.viewCart(item);
		bean.setsearchResult(searchResult);
		System.out.println("index 0 is: " + searchResult.get(0).get(0));
		
		//sql code to add userInformation to database goes here

		if(searchResult != null){
			response = true;
			bean.setsearchResult(searchResult);
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
				
				System.out.println("service: "+ searchResult.get(index).get(0));
		
			}
			
			//books.getBooks();
		}
		else
		{
			response = false;
			bean.setValidation(response);
			
		}
		/*
		if(isAddNewProductSuccessful && searchResult != null){
			response = true;
			System.out.println("Following Item Count has been updated");
			bean.setsearchResult(searchResult);
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
			
			
		}
		else{
			response = false;
			bean.setValidation(response);
		}*/		//System.out.println("value of string is: " + String.valueOf(response));
		Gson searchResultJson = new Gson();
		String responseData = searchResultJson.toJson(products);
		System.out.println("return response: " + responseData);
		return Response.ok().entity(responseData).build();
		//return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/availableusername/{username}")
	@GET
	public String availableUsername(@PathParam("username") String username) {
		//code here to see if userName exists		
		return username + "001";
	}

}
