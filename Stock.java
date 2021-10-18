package ukaparyk_a1.eportfolio;

public class Stock {
	private String name;
	private String symbol;
	private int quantity;
	private float price;
	private float bookValue = 0;	//how much to own a stock

	//constructors
	/**
	 * Parameter-less constructor. sets string values to "NaN" and int/float values to 0
	 */
	public Stock(){
		name = "NaN";
		symbol = "NaN";
		quantity = 0;
		price = 0;
		bookValue = 0;
	}
	/**
	 * takes user inputs for quantity and price and sets local class cariables to them. also caluclates bookvlaue, and modifies it. assumes symbol and name already exist
	 * @param quantity	user input for quantity. will change Stock.quantity value
	 * @param price		user input for price. will change Stock.price value
	 */
	public  Stock(int quantity, float price){
		this.quantity = quantity;
		this.price = price;
		setBookValue(price, quantity);
	}
	/**
	 * takes user input for all values, and creates Stock object using them. assume no previous info was given. calculates bookValue as well
	 * @param symbol		user input. will be used to set Stock.symbol
	 * @param name			user input. will be used to set Stock.name
	 * @param quantity		user input. will be used to set Stock.quanity. will be used to calculate bookValue
	 * @param price			user input. will be used to set Stock.price. will be used to calculate bookValue
	 */
	public Stock(String symbol, String name, int quantity, float price){
		this.name = name;
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
		setBookValue(price, quantity);
	}
	
	//accessors
	/**
	 * returns the value of Stock.name
	 * @return	string Stock.name
	 */
	public String getName(){
		return name;
	}
	/**
	 * returns the value of Stock.symbol
	 * @return	string Stock.symbol
	 */
	public String getSymbol(){
		return symbol;
	}
	/**
	 * returns the value of Stock.quantity
	 * @return	int Stock.quantity
	 */
	public int getQuantity(){
		return quantity;
	}
	/**
	 * returns the value of Stock.price
	 * @return	float Stock.price
	 */
	public float getPrice(){
		return price;
	}
	/**
	 * returns the value of Stock.bookValue
	 * @return	float Stock.bookValue
	 */
	public float getBookValue(){
		return bookValue;
	}

	//mutators
	/**
	 * takes parameter and sets local class variable to it
	 * @param name	user input to set Stock.name to 
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * takes parameter and sets local class variable to it
	 * @param symbol	user input to set Stock.symbol to 
	 */
	public void setSymbol(String symbol){
		this.symbol = symbol.strip();
	}
	/**
	 * takes parameter and sets local class variable to it
	 * @param quantity	user input to set Stock.quantity to 
	 */
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	/**
	 * takes parameter and sets local class variable to it
	 * @param price	user input to set Stock.price to 
	 */
	public void setPrice(float price){
		this.price = price;
	}
	/**
	 * takes parameters and calculates local class bookValue based around it. adds calculated bookValue to pre-existing value (most likely 0)
	 * @param price		user input for price to calculate bookValue with 
	 * @param quantity	user input for quantity to calculate bookValue with 
	 */
	public void setBookValue(float price, int quantity){
		this.bookValue += (price * quantity + 9.99);	//assuming value already exists (either as 0, or something else), will be adding to it
	}

	//equals
	/**
	 * takes parameters from Portfolio(most likley) and checks if they are exactly the same as this particular object. all parameters must be equal, then returns true, otherwise, false.
	 * @param name		provded by user to check if this. is the same
	 * @param symbol	provded by user to check if this. is the same
	 * @param quantity	provded by user to check if this. is the same
	 * @param price		provded by user to check if this. is the same
	 * @param bookValue	provded by user to check if this. is the same
	 * @return		//true or false
	 */
	public boolean equals(String name, String symbol, int quantity, float price, float bookValue){
		return 	name.equals(this.name) &&
				symbol.equals(this.symbol) &&
				quantity == this.quantity &&
				Float.compare(price, this.price) == 0 &&
				Float.compare(bookValue, this.bookValue) == 0;
	}

	//toString
	/**
	 * takes all variables of the class, and returns their values in a string
	 * @return string of all values combined into 1
	 */
	public String toString(){
		return "Investment: " + symbol + "\nName: " + name + "\nPrice: " + price + "\nQuantity: " + quantity + "\nBook value: " + bookValue;
	}

	/**
	 * changes book value and stonk quantity
	 * @param quantity	new quantity to sub in
	 */
	public void sellBookValue(int quantity){
		bookValue *= ((float)quantity/this.quantity);	//need to typecast only numerator (basic C rules) 	
		this.quantity = quantity;						//update quantity
	}
}
