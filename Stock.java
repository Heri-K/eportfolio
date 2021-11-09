package ePortfolio;
/**
 * Contains private variables, methods related to accessing/mutating said variables, method toString and some custom methods 
 */
public class Stock extends Investement{
	
	//TODO accessible from any file, call main

	//constructors
	/**
	 * Parameter-less constructor. sets string values to "NaN" and int/float values to 0
	 */
	public Stock(){
		super();
	}
	
	/**
	 * takes user input for all values, and creates Stock object using them. assume no previous info was given. calculates bookValue as well
	 * @param symbol		user input. will be used to set Stock.symbol
	 * @param name			user input. will be used to set Stock.name
	 * @param quantity		user input. will be used to set Stock.quanity. will be used to calculate bookValue
	 * @param price			user input. will be used to set Stock.price. will be used to calculate bookValue
	 */
	public Stock(String name, String symbol, int quantity, float price) {
		super(name, symbol, quantity, price);
		setBookValue(quantity, price);
	}
	/**
	 * takes user input for all values, and creates Stock object using them. assume no previous info was given. calculates bookValue as well
	 * @param name			user input. will be used to set Stock.symbol
	 * @param symbol		user input. will be used to set Stock.name
	 * @param quantity		user input. will be used to set Stock.quanity. will be used to calculate bookValue
	 * @param price			user input. will be used to set Stock.price. will be used to calculate bookValue
	 * @param bookValue		predefined bookValue. will be used to set stock.bookValue
	 */
	public Stock(String name, String symbol, int quantity, float price, float bookValue) {
		super(name, symbol, quantity, price, bookValue);
	}

	/**
	 * takes parameters and calculates local class bookValue based around it. adds calculated bookValue to pre-existing value (most likely 0)
	 * @param price		user input for price to calculate bookValue with 
	 * @param quantity	user input for quantity to calculate bookValue with 
	 */
	@Override
	public void setBookValue(int quantity, float price){
		super.setBookValue(super.getBookValue() + (super.getPrice() * super.getQuantity() + (float)9.99)); //assuming value already exists (either as 0, or something else), will be adding to it
	}
	/**
	 * getPayment isdifferent, need to ovverride specifically for stock
	 */
	@Override
	public float getPayment(float price, int quantity){
		return (price * quantity) - (float)9.99;
	}
	/**
	 * getGain is calculated differently. need to override for stock
	 */
	@Override
	public float getGain(){
		return super.getGain() - (float)9.99;
	}

	//toString
	/**
	 * takes all variables of the class, and returns their values in a string
	 * @return string. tpye of investement + everything else
	 */
	public String toString(){
		return "Type: Stock\n" +  super.toString();
	}
	/**
	 * formatted for FileIO. includes type of investement, on top of predefined values
	 */
	@Override
	public String toFile(){
		return "type = \"stock\"\n" + super.toFile();
	}
}
