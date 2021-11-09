package ePortfolio;

public class Investement {
	private String name;
	private String symbol;
	private int quantity;
	private float price;
	private float bookValue = 0;
	
	/**
	 * Argument-less constructor for investement
	 */
	public Investement() {
		name = "NaN";
		symbol = "NaN";
		quantity = 0;
		price = 0;
		bookValue = 0;
	}
	/**
	 * Takes name, symbol, quantity, and price and sets them as their values. does not calculate bookValue
	 * @param name	name
	 * @param symbol	symbol
	 * @param quantity	quantity
	 * @param price	price
	 */
	public Investement(String name, String symbol, int quantity, float price) {
		this.name = name;
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * Constructor that takes bookValue as well. Assume has been pre-calculated to insert
	 */
	public Investement(String name, String symbol, int quantity, float price, float bookValue) {
		this.name = name;
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
		this.bookValue = bookValue;
	}

	/**
	 * return name of the investement
	 * @return	string .name
	 */
	public String 	getName() {
		return name;
	}
	/**
	 * returns symbol of the investment
	 * @return string .symbol
	 */
	public String 	getSymbol() {
		return symbol;
	}
	/**
	 * returns quantity of the investement
	 * @return int .quantity
	 */
	public int 		getQuantity() {
		return quantity;
	}
	/**
	 * returns price of the investemnt
	 * @return	float .price
	 */
	public float 	getPrice() {
		return price;
	}
	/**
	 * returns bookValue of the investemetn
	 * @return	float .bookValue
	 */
	public float 	getBookValue() {
		return bookValue;
	}

	//Muutators
	/**
	 * sets investment's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * sets investment's symbol
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * set's investements quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * sets investements price
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * sets investemnts bookValue
	 */
	public void setBookValue(float bookValue) {
		this.bookValue = bookValue;
	}
	/**
	 * sets investment's book value by calculating it with price and quantity. defaults to mutual fund
	 * @param quantity
	 * @param price
	 */
	public void setBookValue(int quantity, float price){
		this.bookValue += (quantity * price);	//assume that previous value is already there (0 or bookValue)
	}
	
	/**
	 * toString of the investment. returns string to display
	 */
	public String toString() {
		return "Investment: " + symbol + "\nName: " + name + "\nPrice: " + price + "\nQuantity: " + quantity + "\nBook value: " + bookValue;
	}

	/**
	 * toFile. just like toString, but formatted specifically for the file IO
	 * @return
	 */
	public String toFile(){
		return "symbol = \"" + symbol + "\"\nname = \"" + name + "\"\nquantity = \"" + quantity + "\"\nprice = \"" + price +  "\"\nbookValue = \"" + bookValue + "\"\n";
	}
	
	/**
	 * takes parameters and updates the values. also calculates bookValue
	 * @param quantity	provided by the user. also used in updating bookValue 
	 * @param price		provided by the user. also used in updating bookValue
	 */
	public void updateBuy(int quantity, float price){
		this.price = price;
		this.quantity += quantity;
		setBookValue(quantity, price);
	}

	/**
	 * adjust bookValue as needed with a given quantity
	 */
	public void sellBookValue(int quantity) {
		bookValue *= ((float)quantity/this.quantity);	//need to typecast only numerator (basic C rules)
		this.quantity = quantity;						//update quantity
	}
	/**
	 * calculates payment. defaults to mutualFund
	 */
	public float getPayment(float price, int quantity){
		return (price * quantity) - (float)45;
	}
	/**
	 * calculates gain. does not default to anything specific
	 */
	public float getGain(){
		return price * quantity - bookValue;
	}
}
