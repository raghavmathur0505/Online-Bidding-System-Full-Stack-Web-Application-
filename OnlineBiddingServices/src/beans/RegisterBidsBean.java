package beans;
import java.util.ArrayList;
import java.util.List;

public class RegisterBidsBean {

	private RegisterBidBean productBean;
	private boolean isValidPost;
	private boolean isValidSearch;
	


	ArrayList<RegisterBidBean> products = new ArrayList<RegisterBidBean>();

	public boolean isValidSearch() {
		return isValidSearch;
	}

	public void setValidSearch(boolean isValidSearch) {
		this.isValidSearch = isValidSearch;
	}	
	public void setItemID(String itemID)
	{
		productBean.setItemID(itemID);
	}
	public void setItemCount(String itemCount)
	{
		productBean.setItemCount(itemCount);
	}
	public void setItemName(String itemName)
	{
		productBean.setItemName(itemName);
	}
	public void setPostUserEmail(String postUserEmail)
	{
		productBean.setPostUserEmail(postUserEmail);
	}
	public void setBidUserEmail(String bidUserEmail)
	{
		productBean.setBidUserEmail(bidUserEmail);
	}
	
	public void setBidID(String bidID)
	{
		productBean.setBidID(bidID);
	}
	
	public void setBidderId(String bidderId)
	{
		productBean.setBidderId(bidderId);
	}
	
	public void setPostUserID(String postUserID)
	{
		productBean.setPostUserID(postUserID);
	}
	
	public void setExpDesc(String expDesc) 
	{
		productBean.setExpDesc(expDesc);
	}
	
	public void setActDesc(String actDesc)
	{
		productBean.setActDesc(actDesc);
	}
	
	public void setExpQuality(String expQuality)
	{
		productBean.setExpQuality(expQuality);
	}
	
	public void setActQuality(String actQuality)
	{
		productBean.setActQuality(actQuality);
	}
	
	public void setExpPrice(String expPrice)
	{
		productBean.setExpPrice(expPrice);
	}
	
	public void setActPrice(String actPrice)
	{
		productBean.setActPrice(actPrice);
	}
	
public void setValidationSearch(boolean isValid){
	this.isValidSearch = isValid;
}
	public void setValidation(boolean isValid)
	{
		this.isValidPost=isValid;
	}
public boolean isValidPost() {
		
		return isValidPost;
	}
	//setters end
	
	//getters begin
	public String getBidID() {
		return productBean.getBidID();
	}
	public String getItemCount() {
		return productBean.getItemCount();
	}
	public String getItemName() {
		return productBean.getItemName();
	}
	
	public String getPostUserEmail() {
		return productBean.getPostUserEmail();
	}
	public String getBidUserEmail() {
		return productBean.getBidUserEmail();
	}
	
	public String getItemID()
	{
		return productBean.getItemID();
	}
	
	public String getBidderId() 
	{
		return productBean.getBidderId();
	}
	
	public String getPostUserID() 
	{
		return productBean.getPostUserID();
	}
	
	public String getExpDesc()
	{
		return productBean.getExpDesc();
	}
	
	public String getActDesc()
	{
		return productBean.getActDesc();
	}
	
	public String getExpQuality()
	{
		 return productBean.getExpQuality();
	}
	
	public String getActQuality() 
	{
		return productBean.getActQuality();
	}
	
	public String getExpPrice()
	{
		return productBean.getExpPrice();
	}
	
	public String getActPrice() 
	{
		return productBean.getActPrice();
	}
	
	public void addProducts(RegisterBidBean product)
	{
		products.add(product);
	}
	public int getListSize()
	{
		return products.size();
	}
	public String getITEMCOUNT(int index)
	{
		return products.get(index).getItemCount();
	}
	public String getBIDDERID(int index)
	{
		return products.get(index).getBidderId();
	}
	public String getITEMNAME(int index)
	{
		return products.get(index).getItemName();
	}
	public String getPOSTUSEREMAIL(int index)
	{
		return products.get(index).getPostUserEmail();
	}
	public String getBIDUSEREMAIL(int index)
	{
		return products.get(index).getBidUserEmail();
	}
	public String getITEMID(int index)
	{
		return products.get(index).getItemID();
	}
	public String getBIDID(int index)
	{
		return products.get(index).getBidID();
	}
	public String getPOSTUSERID(int index)
	{
		return products.get(index).getPostUserID();
	}
	public String getEXPDESC(int index)
	{
		return products.get(index).getExpDesc();
	}
	public String getACTDESC(int index)
	{
		return products.get(index).getActDesc();
	}
	public String getEXPQUALITY(int index)
	{
		return products.get(index).getExpQuality();
	}
	public String getACTQUALITY(int index)
	{
		return products.get(index).getActQuality();
	}
	public String getEXPPRICE(int index)
	{
		return products.get(index).getExpPrice();
	}
	public String getACTPRICE(int index)
	{
		return products.get(index).getActPrice();
	}

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
	
	




