package beans;

public class BidBean{
	
	
	//add variables for prod_id and u_id
	
	private String bidID;
	private String itemID;
	private String username;  //actual name of the user not the username
	private String postUserID;
	private String itemDesc;
	private String itemQuality;
	
	private String itemPrice;
	private String bidUserEmail;
	private String itemName;
	private String postUserEmail;
	
	
	public BidBean(){
		
		
		//initialize prod_id and u_id
		
		bidID="";
		itemID="";
		username="";
		postUserID="";
		itemDesc="";
		itemQuality="";
		itemPrice="";
		postUserEmail="";
		bidUserEmail="";
		itemName="";
		
	}

	public void setBidUserEmail(String bidUserEmail) {
		this.bidUserEmail = bidUserEmail;
	}
	public void setPostUserEmail(String postUserEmail) {
		this.postUserEmail = postUserEmail;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public void setBidID(String bidID) {
		this.bidID = bidID;
	}



	public void setItemID(String itemID) {
		this.itemID = itemID;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public void setPostUserID(String postUserID) {
		this.postUserID = postUserID;
	}



	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}



	public void setItemQuality(String itemQuality) {
		this.itemQuality = itemQuality;
	}



	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}


	public String getPostUserEmail() {
		return postUserEmail;
	}



	public String getBidUserEmail() {
		return bidUserEmail;
	}


	public String getItemName() {
		return itemName;
	}


	public String getItemID() {
		return itemID;
	}



	public String getUsername() {
		return username;
	}



	public String getPostUserID() {
		return postUserID;
	}



	public String getItemDesc() {
		return itemDesc;
	}



	public String getItemQuality() {
		return itemQuality;
	}



	public String getItemPrice() {
		return itemPrice;
	}
	
	
	
}
	
	