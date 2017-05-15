package beans;

public class ProductBean {
	
	private String itemID;
	private String itemName;
	private String itemPrice;
	private String itemDesc;
	private String itemCategory;
	private String itemQuality;
	private String add1;
	private String add2;
	private String country;
	private String state;
	private String city;
	private String emailId;
	//private String image;
	private String username;

	public ProductBean(){
		itemID="";
		itemName="";
		itemPrice="";
		itemDesc="";
		itemCategory="";
		itemQuality="";
		add1 ="";
		add2 = "";
		country="";
		state="";
		city = "";
		//image="";
		username="";
		emailId="";
	}

	//setters
	public void setEmailId(String emailId)
	{
		this.emailId=emailId;
	}
	
		public void setUserName(String username)
		{
			this.username=username;
		}
		
		public void setItemID(String itemID) {
			this.itemID = itemID;
		}

			public void setItemName(String itemName)
		{
			this.itemName=itemName;
		}
		
		public void setItemPrice(String itemPrice)
		{
			this.itemPrice = itemPrice;
		}
		
		public void setItemDesc(String itemDesc)
		{
			this.itemDesc = itemDesc;
		}
		
		public void setItemCategory(String itemCategory)
		{
			this.itemCategory=itemCategory;
		}
		
		public void setItemQuality(String itemQuality)
		{
			this.itemQuality = itemQuality;
		}
		
		public void setAdd1(String add1)
		{
			this.add1 = add1;
		}
		
		public void setAdd2(String add2)
		{
			this.add2=add2;
		}
		
		public void setCountry(String country)
		{
			this.country=country;
		}
		
		public void setState(String state)
		{
			this.state=state;
		}
		
		public void setCity(String city)
		{
			this.city = city;
		}
		
		/*public void setImage(String image)
		{
			this.image = image;
		}*/
		
		//setters end
		
		//getters begin
		public String getEmailId()
		{
			return emailId;
		}
		public String getUserName()
		{
			return username;
		}
		
		public String getItemID() {
			return itemID;
		}
		
		public String getItemName()
		{
			return itemName;
		}
		
		public String getItemPrice()
		{
			return itemPrice;
		}
		
		public String getItemDesc()
		{
			return itemDesc;
		}
		
		public String getItemCategory()
		{
			return itemCategory;
		}
		
		public String getItemQuality()
		{
			return itemQuality;
		}
		
		public String getAdd1()
		{
			 return add1;
		}
		
		public String getAdd2()
		{
			return add2;
		}
		
		public String getCountry()
		{
			return country;
		}
		
		public String getState()
		{
			return state;
		}
		
		public String getCity()
		{
			return city;
		}
		
		/*public String getImage()
		{
			return image;
		}*/

		

}
