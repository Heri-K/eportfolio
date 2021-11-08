package ePortfolio;
/**
 * Contains private variables, methods related to accessing/mutating said variables, method toString and some custom methods 
 */
public class Stock extends Investement{
	
	//constructors
	/**
	 * Parameter-less constructor. sets string values to "NaN" and int/float values to 0
	 */
	public Stock(){
		super();
	}
	public Stock(String name, String symbol, int quantity, float price) {
		super(name, symbol, quantity, price);
		setBookValue(quantity, price);
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

	


	//toString
	/**
	 * takes all variables of the class, and returns their values in a string
	 * @return string of all values combined into 1
	 */
	public String toString(){
		return "Type: Stock\n" +  super.toString();
	}

	/**
	 * changes book value and stonk quantity
	 * @param quantity	new quantity to sub in
	 */
	public void sellBookValue(int quantity){
		super.setBookValue(super.getBookValue() * ((float)quantity/super.getQuantity()));
		super.setQuantity(quantity);	 					//update quantity
	}

	/**
	 * takes parameters and updates the values. also calculates bookValue
	 * @param quantity	provided by the user. also used in updating bookValue 
	 * @param price		provided by the user. also used in updating bookValue
	 */
	public void updateBuy(int quantity, float price){
		this.price = price;
		this.quantity += quantity;
		setBookValue(price, quantity);
	}
}
