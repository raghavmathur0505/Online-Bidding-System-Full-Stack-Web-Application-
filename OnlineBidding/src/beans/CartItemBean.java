package beans;

public class CartItemBean {
	
    private String title;
    private String author;
    private String isbn;
    private double dblUnitCost;
    private int iQuantity;
    private double dblTotalCost;
     
    public String getTitle() {
        return title;
    }
    public void setIsbn(String isbn)
    {
    	this.isbn=isbn;
    }
    
    public String getIsbn()
    {
    	return isbn;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String setAuthor) {
        this.author = setAuthor;
    }
    public double getUnitCost() {
        return dblUnitCost;
    }
    public void setUnitCost(double dblUnitCost) {
        this.dblUnitCost = dblUnitCost;
    }
    public int getQuantity() {
        return iQuantity;
    }
    public void setQuantity(int quantity) {
        iQuantity = quantity;
    }
    public double getTotalCost() {
        return dblTotalCost;
    }
    public void setTotalCost(double dblTotalCost) {
        this.dblTotalCost = dblTotalCost;
    }
}