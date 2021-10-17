package ukaparyk_a1.eportfolio;

public class Stock {
	private String name;
	private String symbol;
	private int quantity;
	private float price;
	private float bookValue = 0;	//how much to own a stock

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
	public void setBookValue(float price, int quantity){
		this.bookValue += (price * quantity + 9.99);
	}
	
	//constructor
	public Stock(int quantity, float price){
		this.quantity = quantity;
		this.price = price;
		setBookValue(price, quantity);
	}
	public Stock(){
		name = "NaN";
		symbol = "NaN";
		quantity = 0;
		price = 0;
		bookValue = 0;
	}

	public Stock(String symbol, String name, int quantity, float price){
		this.name = name;
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
		setBookValue(price, quantity);
	}

	public String toString(){
		return "Investment: " + symbol + "\nName: " + name + "\nPrice: " + price + "\nQuantity: " + quantity + "\nBook value: " + bookValue;
	}

}
