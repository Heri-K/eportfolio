package ePortfolio;

public class Investement {
	private String name;
	private String symbol;
	private int quantity;
	private float price;
	private float bookValue = 0;
	
	public Investement() {
		name = "NaN";
		symbol = "NaN";
		quantity = 0;
		price = 0;
		bookValue = 0;
	}
	
	public Investement(String name, String symbol, int quantity, float price) {
		this.name = name;
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
	}

	public Investement(String name, String symbol, int quantity, float price, float bookValue) {
		this.name = name;
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
		this.bookValue = bookValue;
	}

	//getters
	public String 	getName() {
		return name;
	}
	public String 	getSymbol() {
		return symbol;
	}
	public int 		getQuantity() {
		return quantity;
	}
	public float 	getPrice() {
		return price;
	}
	public float 	getBookValue() {
		return bookValue;
	}

	//setters
	public void setName(String name) {
		this.name = name;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setBookValue(float bookValue) {
		this.bookValue = bookValue;
	}
	public void setBookValue(int quantity, float price){
		this.bookValue += (quantity * price);	//assume that previous value is already there (0 or bookValue)
	}
	
	
	public String toString() {
		return "Investment: " + symbol + "\nName: " + name + "\nPrice: " + price + "\nQuantity: " + quantity + "\nBook value: " + bookValue;
	}

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

	public void sellBookValue(int quantity) {
		bookValue *= ((float)quantity/this.quantity);	//need to typecast only numerator (basic C rules)
		this.quantity = quantity;						//update quantity
	}
	
	public float getPayment(float price, int quantity){
		return (price * quantity) - (float)45;
	}

	public float getGain(){
		return price * quantity - bookValue;
	}
	

	//"symbol= “AAPL”name= “Apple Inc.”quantity = “500”price = “142.23”bookValue= “55049.99”"
	
}
