package beans;
import java.util.ArrayList;
import java.util.List;

public class ProductsBean {

	private ProductBean productBean;
	private boolean isValidPost;
	private boolean isValidSearch;
	
	public boolean isValidSearch() {
		return isValidSearch;
	}
	public void setValidSearch(boolean isValidSearch) {
		this.isValidSearch = isValidSearch;
	}
	ArrayList<ProductBean> products = new ArrayList<ProductBean>();

	public void setUserName(String username)
	{
		productBean.setUserName(username);
	}
	public void setItemID(String itemID)
	{
		productBean.setItemID(itemID);
	}
	public void setEmailId(String emailId)
	{
		productBean.setEmailId(emailId);
	}
	
	public void setItemName(String itemName)
	{
		productBean.setItemName(itemName);
	}
	
	public void setItemPrice(String itemPrice)
	{
		productBean.setItemPrice(itemPrice);
	}
	
	public void setItemDesc(String itemDesc)
	{
		productBean.setItemDesc(itemDesc);
	}
	
	public void setItemCategory(String itemCategory)
	{
		productBean.setItemCategory(itemCategory);
	}
	
	public void setItemQuality(String itemQuality)
	{
		productBean.setItemQuality(itemQuality);
	}
	
	public void setAdd1(String add1)
	{
		productBean.setAdd1(add1);
	}
	
	public void setAdd2(String add2)
	{
		productBean.setAdd2(add2);
	}
	
	public void setCountry(String country)
	{
		productBean.setCountry(country);
	}
	
	public void setState(String state)
	{
		productBean.setState(state);
	}
	
	public void setCity(String city)
	{
		productBean.setCity(city);
	}
	
	/*public void setImage(String image)
	{
		productBean.setImage(image);
	}*/
	public void setValidation(boolean isValid)
	{
		this.isValidPost=isValid;
	}
	public void setValidationSearch(boolean isValidSearch){
		this.isValidSearch = isValidSearch;
	}
	
	//setters end
	
	//getters begin
	public String getUserName()
	{
		return productBean.getUserName();
	}
	public String getEmailId()
	{
		return productBean.getEmailId();
	}
	public String getItemID()
	{
		return productBean.getItemID();
	}
	public String getItemName()
	{
		return productBean.getItemName();
	}
	
	public String getItemPrice()
	{
		return productBean.getItemPrice();
	}
	
	public String getItemDesc()
	{
		return productBean.getItemDesc();
	}
	
	public String getItemCategory()
	{
		return productBean.getItemCategory();
	}
	
	public String getItemQuality()
	{
		return productBean.getItemQuality();
	}
	
	public String getAdd1()
	{
		 return productBean.getAdd1();
	}
	
	public String getAdd2()
	{
		return productBean.getAdd2();
	}
	
	public String getCountry()
	{
		return productBean.getCountry();
	}
	
	public String getState()
	{
		return productBean.getState();
	}
	
	public String getCity()
	{
		return productBean.getCity();
	}
	
	/*public String getImage()
	{
		return productBean.getImage();
	}*/
	public boolean isValidPost() {
		
		return isValidPost;
	}
	
	public void addProducts(ProductBean product)
	{
		products.add(product);
	}
	public int getListSize()
	{
		return products.size();
	}
	public String getEMAILID(int index)
	{
		return products.get(index).getEmailId();
	}
	public String getUSERNAME(int index)
	{
		return products.get(index).getUserName();
	}
	public String getITEMID(int index)
	{
		return products.get(index).getItemID();
	}
	public String getITEMNAME(int index)
	{
		return products.get(index).getItemName();
	}
	public String getITEMPRICE(int index)
	{
		return products.get(index).getItemPrice();
	}
	public String getITEMDESC(int index)
	{
		return products.get(index).getItemDesc();
	}
	public String getITEMCATEGORY(int index)
	{
		return products.get(index).getItemCategory();
	}
	public String getITEMQUALITY(int index)
	{
		return products.get(index).getItemQuality();
	}
	public String getADD1(int index)
	{
		return products.get(index).getAdd2();
	}
	public String getADD2(int index)
	{
		return products.get(index).getAdd1();
	}
	public String getCITY(int index)
	{
		return products.get(index).getCity();
	}
	public String getSTATE(int index)
	{
		return products.get(index).getState();
	}
	public String getCOUNTRY(int index)
	{
		return products.get(index).getCountry();
	}
	/*public String getIMAGE(int index)
	{
		return products.get(index).getImage();
	}*/
	public void getProducts()
	{
		System.out.println("size of books stored is: " + products.size() );
		for(int index = 0; index < products.size(); index++)
		{
			/*System.out.println("books isbn printed out: " + books.get(index).getIsbn());
			System.out.println("books title printed out: " + books.get(index).getTitle());
			System.out.println("books price printed out: " + books.get(index).getPrice());
			System.out.println("books quantity printed out: " + books.get(index).getInventory());
			System.out.println("books author printed out: " + books.get(index).getAuthor());
			System.out.println("----BOOK DIVIDER------------" );*/
			
			//System.out.println(book.getTitle());
		}	
	}

}
	
	




