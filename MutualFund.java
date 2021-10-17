package ukaparyk_a1.eportfolio;

public class MutualFund {
	private String name;
	private String symbol;
	private int quantity;
	private float price;
	private float bookValue = 0;

	public MutualFund(int quantity, float price) {
		this.quantity = quantity;
		this.price = price;
		setBookValue(quantity, price);
	}
	public MutualFund(String symbol, String stockName, int quantity, float price) {
	}
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

	public void setBookValue(int quantity, float price){
		this.bookValue += (quantity * price);
	}

	public String toString(){
		return "Investment: " + symbol + "\nName: " + name + "\nPrice: " + price + "\nQuantity: " + quantity + "\nBook value: " + bookValue;
	}
}
