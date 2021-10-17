package ukaparyk_a1.eportfolio;

public class Stock {
	private String name;
	private String symbol;
	private int quantity;
	private float price;
	private float bookValue = 0;	//how much to own a stock

	//constructors
	public Stock(){
		name = "NaN";
		symbol = "NaN";
		quantity = 0;
		price = 0;
		bookValue = 0;
	}
	public Stock(int quantity, float price){
		this.quantity = quantity;
		this.price = price;
		setBookValue(price, quantity);
	}
	public Stock(String symbol, String name, int quantity, float price){
		this.name = name;
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
		setBookValue(price, quantity);
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
	public void setBookValue(float price, int quantity){
		this.bookValue += (price * quantity + 9.99);
	}

	//equals
	public boolean equals(String name, String symbol, int quantity, float price, float bookValue){
		return 	name.equals(this.name) &&
				symbol.equals(this.symbol) &&
				quantity == this.quantity &&
				Float.compare(price, this.price) == 0 &&
				Float.compare(bookValue, this.bookValue) == 0;
	}

	//toString
	public String toString(){
		return "Investment: " + symbol + "\nName: " + name + "\nPrice: " + price + "\nQuantity: " + quantity + "\nBook value: " + bookValue;
	}

	/**
	 * changes book value and stonk quantity
	 * @param quantity	new quantity to sub in
	 */
	public void sellBookValue(int quantity){
		bookValue *= ((float)quantity/this.quantity);
		this.quantity = quantity;
	}
}
