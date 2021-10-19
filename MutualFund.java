package ePortfolio;
/**
 * Contains private variables, methods related to accessing/mutating said variables, method toString and some custom methods 
 */
public class MutualFund {
	private String name;
	private String symbol;
	private int quantity;
	private float price;
	private float bookValue = 0;

	//constructors
	/**
	 * Parameter-less constructor. sets string values to "NaN" and int/float values to 0
	 */
	public MutualFund(){
		name = "NaN";
		symbol = "NaN";
		quantity = 0;
		price = 0;
		bookValue = 0;
	}
	
	/**
	 * takes user input for all values, and creates MutualFund object using them. assume no previous info was given. calculates bookValue as well
	 * @param symbol		user input. will be used to set MutualFund.symbol
	 * @param name			user input. will be used to set MutualFund.name
	 * @param quantity		user input. will be used to set MutualFund.quanity. will be used to calculate bookValue
	 * @param price			user input. will be used to set MutualFund.price. will be used to calculate bookValue
	 */
	public MutualFund(String symbol, String name, int quantity, float price) {
		this.name = name;
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
		setBookValue(quantity, price);
	}
	
//accessors
	/**
	 * returns the value of MutualFund.name
	 * @return	string MutualFund.name
	 */
	public String getName(){
		return name;
	}
	/**
	 * returns the value of MutualFund.symbol
	 * @return	string MutualFund.symbol
	 */
	public String getSymbol(){
		return symbol;
	}
	/**
	 * returns the value of MutualFund.quantity
	 * @return	int MutualFund.quantity
	 */
	public int getQuantity(){
		return quantity;
	}
	/**
	 * returns the value of MutualFund.price
	 * @return	float MutualFund.price
	 */
	public float getPrice(){
		return price;
	}
	/**
	 * returns the value of MutualFund.bookValue
	 * @return	float MutualFund.bookValue
	 */
	public float getBookValue(){
		return bookValue;
	}

	//mutators
	/**
	 * takes parameter and sets local class variable to it
	 * @param name	user input to set MutualFund.name to 
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * takes parameter and sets local class variable to it
	 * @param symbol	user input to set MutualFund.symbol to 
	 */
	public void setSymbol(String symbol){
		this.symbol = symbol.strip();
	}
	/**
	 * takes parameter and sets local class variable to it
	 * @param quantity	user input to set MutualFund.quantity to 
	 */
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	/**
	 * takes parameter and sets local class variable to it
	 * @param price	user input to set MutualFund.price to 
	 */
	public void setPrice(float price){
		this.price = price;
	}
	/**
	 * takes parameters and calculates local class bookValue based around it. adds calculated bookValue to pre-existing value (most likely 0)
	 * @param price		user input for price to calculate bookValue with 
	 * @param quantity	user input for quantity to calculate bookValue with 
	 */
	public void setBookValue(int quantity, float price){
		this.bookValue += (quantity * price);	//assume that previous value is already there (0 or bookValue)
	}

	//toString
	/**
	 * takes all variables of the class, and returns their values in a string
	 * @return string of all values combined into 1
	 */
	public String toString(){
		return "Investment: " + symbol + "\nName: " + name + "\nPrice: " + price + "\nQuantity: " + quantity + "\nBook value: " + bookValue;
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
	
	/**
	 * adjusts book value and quanity of MF
	 * @param quantity	//new quantity of the MF
	 */
	public void sellBookValue(int quantity) {
		bookValue *= ((float)quantity/this.quantity);	//need to typecast only numerator (basic C rules)
		this.quantity = quantity;						//update quantity
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
}
