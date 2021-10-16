package ukaparyk_a1.eportfolio;

public class Stock {
	private String name;
	private String symbol;
	private int quantity;
	private float price;
	private float bookValue;

	//accessors
	public String getName(){
		return name;
	}
	public String getSymbol(){
		return symbol;
	}
	public int getQuantity(){
		return quantity;
	}
	public float getPrice(){
		return price;
	}
	public float getBookValue(){
		return bookValue;
	}

	//mutators
	public void setName(String name){
		this.name = name;
	}
	public void setSymbol(String symbol){
		this.symbol = symbol;
	}
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	public void setPrice(float price){
		this.price = price;
	}
	
}
