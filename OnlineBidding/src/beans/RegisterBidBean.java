package beans;

public class RegisterBidBean{
	
	
	//add variables for prod_id and u_id
	
	private String bidID;
	private String itemID;
	private String bidderId;  
	private String postUserID;
	private String expDesc;
	private String expQuality;
	private String expPrice;
	private String actDesc;
	private String actQuality;
	private String actPrice;
	private String bidUserEmail;
	private String itemName;
	private String postUserEmail;
	private String itemCount;
	
	
	
	public RegisterBidBean(){
		
		
		//initialize prod_id and u_id
		
		bidID="";
		itemID="";
		bidderId="";
		postUserID="";
		expDesc="";
		expQuality="";
		expPrice="";
		actDesc="";
		actQuality="";
		actPrice="";
		postUserEmail="";
		bidUserEmail="";
		itemName="";
		itemCount="";
		
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


	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
		
	}
	

	public void setBidID(String bidID) {
		this.bidID = bidID;
	}



	public void setItemID(String itemID) {
		this.itemID = itemID;
	}



	public void setBidderId(String bidderId){
		this.bidderId = bidderId;
	}



	public void setPostUserID(String postUserID) {
		this.postUserID = postUserID;
	}



	public void setExpDesc(String expDesc) {
		this.expDesc = expDesc;
	}

	public void setActDesc(String actDesc) {
		this.actDesc = actDesc;
	}

	public void setExpQuality(String expQuality) {
		this.expQuality = expQuality;
	}
	public void setActQuality(String actQuality) {
		this.actQuality = actQuality;
	}



	public void setExpPrice(String expPrice) {
		this.expPrice = expPrice;
	}

	public void setActPrice(String actPrice) {
		this.actPrice = actPrice;
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

	public String getBidID() {
		return bidID;
	}



	public String getItemID() {
		return itemID;
	}



	public String getBidderId() {
		return bidderId;
	}



	public String getPostUserID() {
		return postUserID;
	}



	public String getExpDesc() {
		return expDesc;
	}

	public String getActDesc() {
		return actDesc;
	}


	public String getExpQuality() {
		return expQuality;
	}
	public String getActQuality() {
		return actQuality;
	}



	public String getExpPrice() {
		return expPrice;
	}
	public String getActPrice() {
		return actPrice;
	}
	public String getItemCount() {
		return itemCount;
	}


	
	
}
	
	