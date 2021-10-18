package ukaparyk_a1.eportfolio;

import java.util.ArrayList;
import java.util.Scanner;

public class Portfolio {
	private ArrayList<Stock> stonks;
	private ArrayList<MutualFund> mutualFunds;
	/**
	 *  Class constructor. initializes the lists as instances. 
	 */
	public Portfolio(){
		stonks = new ArrayList<Stock>();
		mutualFunds = new ArrayList<MutualFund>();
	}

	/**
	 * Asks user for a type of investement, and a symbol for said investement. calls buyStock() or buyMF() depending on the input. 
	 * @param keyboard Passed by "reference" from main. used in the method for user input
	 */
	public void buy(Scanner keyboard) {// validatiot for buying stuff
		System.out.print("Enter the kind of investment(stock or mutualfund): ");
		String invType = keyboard.nextLine().strip();	//strip to get rid of extra spaces beggining/end
		while (true){	//validation for proper investement type
			if (!invType.equalsIgnoreCase("stock") && !invType.equalsIgnoreCase("s") && 
			!invType.equalsIgnoreCase("mutualfund") && !invType.equalsIgnoreCase("mf") && !invType.equalsIgnoreCase("m")){
				System.out.println("Not an investement type, try again.");	//if not vaid, call string.nextLine(); and loop to see if it's still valid;
				invType = keyboard.nextLine();
			}
			break;	//if valid, will bypass if statement, break the loop
		}
		System.out.print("Enter a symbol for the Investement: ");
		String symbol = keyboard.nextLine();
		while (true) { //checks if the symbol is valid
			if (symbol.isEmpty() || symbol.isBlank()) { 
				System.out.println("Invalid symbol, try again.");
				invType = keyboard.nextLine();
			}
			break;
		}
		symbol = symbol.strip();	//making sure symbol doesn't have whitespace
		int quantity;
		System.out.print("Enter the quantity to buy: ");
		while(true){	//validates the quaninty
			try {	//if input is 10a, parsin would thro error. catching said errors
				quantity = Integer.parseInt(keyboard.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid input. Try again.");
				continue;	//still loop, still validation until proper
			}
			break;
		}
		float price;
		System.out.print("Enter the price of the investement to buy: ");
		while(true){	//validates the price
			try {	//explained in quaninty
				price = Float.parseFloat(keyboard.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid price. Try again.");	
				continue;
			}
			break;
		}
		//call buyInvestement depending on investement type
		if (invType.equalsIgnoreCase("stock") || invType.equalsIgnoreCase("s"))
			buyInvStoct(keyboard, symbol, quantity, price);
		else
			buyInvMF(keyboard, symbol, quantity, price);
	}

	/**
	 * Verifies that symbol's stock already exists. If so, modify said stock with passed paramter values. if not, creates new stock object, and passes parameter values into constructor
	 * @param keyboard 	passed by reference. Used for user input for stock name.
	 * @param symbol	passed by value. used to determine if stock exists in the ArrayList. if not, used as symbol for new stock. passed into Stock() constructor to be initialized into new stock
	 * @param price		passed by value. used to determine new bookValue of the stock (both pre-existsing and new). passed into Stock() constructor to be initialized into new stock 
	 * @param quantity	passed by value. used to determine new bookValue of the stock. passed into Stock() constructor if needed.
	 */
	public void buyInvStoct(Scanner keyboard, String symbol, int quantity, float price) {
		for (Stock stock : stonks) {	//goes through every stock object, checking there will be no duplicates
			if (symbol.equalsIgnoreCase(stock.getSymbol())) {	//duplicate found, update quantity, price, bookvalue
				stonks.add(new Stock(quantity, price));
				System.out.println("Bought " + symbol + " " + stock.getName() + ": " + 
				quantity + " shares for " + String.format("%.2f", price) + " each. Total Book Value is: " + String.format("%.2f", stock.getBookValue()));	//prtint the output, and return
				return;
			}
		}
		System.out.print("Enter the name of the stock: ");	//stock doesnt exist, need the name
		String stockName = keyboard.nextLine();
		while(true){	//validating name isn't blank
			if (stockName.isBlank() || stockName.isEmpty()){
				System.out.println("Invalid name, try again.");
				stockName = keyboard.nextLine();
			}
			break;
		}
		stockName = stockName.strip(); //no name whitespace
		stonks.add(new Stock(symbol, stockName, quantity, price));	//creating new stock
		System.out.println("Bought " + symbol + " " + stockName + ": " + 
		quantity + " shares for " + String.format("%.2f", price) + " each. Total Book Value is: " + String.format("%.2f", (price * quantity + 9.99)));
	}

	/**
	 * Verifies that symbol's mutual fund exists. If so, modify said mutual fund with passed parameter values. if not, create new mutual fund object, and pass parameter values into it's contructor.
	 * @param keyboard 	passed by reference. Used for user input for mutual Fund name.
	 * @param symbol	passed by value. used to determine if mutual Fund exists in the ArrayList. if not, used as symbol for new mutual Fund. passed into mutualFund() constructor to be initialized into new mutual Fund
	 * @param price		passed by value. used to determine new bookValue of the mutual Fund (both pre-existsing and new). passed into mutualFund() constructor to be initialized into new mutual Fund 
	 * @param quantity	passed by value. used to determine new bookValue of the mutual Fund. passed into mutualFund() constructor if needed.
	 */
	public void buyInvMF(Scanner keyboard, String symbol, int quantity, float price) {
		for (MutualFund mFund: mutualFunds) {	//checking for duplicates
			if (symbol.equalsIgnoreCase(mFund.getSymbol())) {	//duplicate found
				mutualFunds.add(new MutualFund(quantity, price));
				System.out.println("Bought " + symbol + " " + mFund.getName() + ": " + 
				quantity + " shares for " + String.format("%.2f", price) + " each. Total Book Value is: " + String.format("%.2f", mFund.getBookValue()));
				return;
			}
		}
		System.out.print("Enter the name of the mutual fund: ");	//no duplicates found, creating new MF, need name 
		String mFundName = keyboard.nextLine();
		while(true){ //name validation
			if (mFundName.isBlank() || mFundName.isEmpty()){
				System.out.println("Invalid name, try again.");
				mFundName = keyboard.nextLine();
			}
			break;
		}
		mFundName = mFundName.strip();
		mutualFunds.add(new MutualFund(symbol, mFundName, quantity, price)); //creating new
		System.out.println("Bought " + symbol + " " + mFundName + ": " + 
		quantity + " shares for " + String.format("%.2f", price) + " each. Total Book Value is: " + String.format("%.2f", (price * quantity)));
	}
	
	/**
	 * Promts user for a symbol. uses that symbol to verify investement exists. if so, sells of said investement, displaying payment and gain. Also modifies stock price, quantity, and book value as needed. If symbol's investement doesn't exist, print out error message
	 * @param keyboard passed by reference. Used for user input for symbol, price and quantity
	 */
	public void sell(Scanner keyboard){
		System.out.print("Enter the symbol: ");
		String symbol = keyboard.nextLine();
		
		int quantity;
		float price, gain, payment;
		while(true){	//validating symbol
			if(symbol.isEmpty() || symbol.isBlank()){
				System.out.println("Invalid input, try again.");
				symbol = keyboard.nextLine();
				continue;
			}
			break;
		}
		symbol = symbol.strip().toLowerCase();
		for (Stock stock: stonks) {	//checking to see if stock exists
			if (symbol.equalsIgnoreCase(stock.getSymbol())){
				System.out.print("Enter the quantity of " + symbol + " to sell: ");
				while (true) {	//getting appropriate quantity
					try {	//quanity as integer, not string
						quantity = Integer.parseInt(keyboard.nextLine());
					} catch (Exception e) {
						System.out.println("Invalid value, try again.");
						continue;
					}	//making sure quaninty is not larger than what we have, and not <= 0
					if (quantity > stock.getQuantity() || quantity <= 0){ //proper range
						System.out.println("Value outside the range, try again.");
						continue;
					}
					break;
				}
				System.out.print("Enter the price: ");
				while (true) {	//getting price
					try {	//price as float, not string
						price = Float.parseFloat(keyboard.nextLine());
					} catch (Exception e) {
						System.out.println("Invalid price, try again.");
						continue;
					}
					break;
				}
				payment = (price * quantity) - (float)9.99;	//payment as to how much user "recieves"
				float temp = stock.getBookValue();
				stock.sellBookValue(stock.getQuantity() - quantity);
				gain = payment - (temp - stock.getBookValue());	//overall gain for said stocks
				System.out.println("Successfully sold " + quantity + " of " + symbol + " " + stock.getName() + " for " + price + "each.");
				System.out.println("Total payment: $" + payment + ", with net gain of $" + gain + ".");
				if (stock.getQuantity() == 0){ //if quaninty 0, we don't have anymore stock, no need to keep track of it
					stonks.remove(stock);
					return;
				}
				stock.setPrice(price);
				System.out.println("Current status of " + symbol + " " + stock.getName() + ": \n" + 
					"\tQuantity: " + quantity + "\n\tPrice: " + price + "\n\tBook Value: $" + String.format("%.2f",stock.getBookValue()));
			}
			return;
		}
		for (MutualFund mFund : mutualFunds) {	//checking to see if MF exists
			if (symbol.equalsIgnoreCase(mFund.getSymbol())) {	//match found based on symbol
				if (symbol.equalsIgnoreCase(mFund.getSymbol())){
					System.out.print("Enter the quantity of " + symbol + " to sell: ");
					while (true) {	//getting appropriate quantity
						try {	//quanity as integer, not string
							quantity = Integer.parseInt(keyboard.nextLine());
						} catch (Exception e) {
							System.out.println("Invalid value, try again.");
							continue;
						}
						if (quantity > mFund.getQuantity() || quantity <= 0){ //proper range
							System.out.println("Value outside the range, try again;");
							continue;
						}
						break;
					}
					System.out.print("Enter the price: ");
					while (true) {	//getting price
						try {	//price as float, not string
							price = Float.parseFloat(keyboard.nextLine());
						} catch (Exception e) {
							System.out.println("Invalid price, try again.");
							continue;
						}
						break;
					}
					payment = (price * quantity) - 45;	//payment recieved
					float temp = mFund.getBookValue();
					mFund.sellBookValue(mFund.getQuantity() - quantity);
					gain = payment - (temp - mFund.getBookValue());	//overall gain
					System.out.println("Successfully sold " + quantity + " of " + symbol + " " + mFund.getName() + " for " + price + " each.");
					System.out.println("Total payment: $" + payment + ", with net gain of $" + gain + ".");
					if (mFund.getQuantity() == 0){
						mutualFunds.remove(mFund);
						return;
					}
					mFund.setPrice(price);	//new price for the MF
					System.out.println("Current status of " + symbol + " " + mFund.getName() + ": \n" + 
						"\tQuantity: " + quantity + "\n\tPrice: " + price + "\n\tBook Value: $" + String.format("%.2f", mFund.getBookValue()));
				}
			}
			return;
		}
		System.out.println("No stock nor mutual fund with symbol " + symbol + " was found. Cannot sell.");	//no investement found, return error
		return;
	}

	/**
	 * Goes through every single investement, showing the investement details, and prompting user for the new price. Price is then updated for each investement object. 
	 * @param keyboard passed by reference. Used to get user input for new prices
	 */
	public void update(Scanner keyboard) {
		boolean found = false;	//making sure at least one investement gets update
		for (Stock stock : stonks) {
			found = true;	//investement found
			System.out.print("Current price of " + stock.getSymbol() + " " + stock.getName() + " is $" + 
			String.format("%.2f", stock.getPrice()) + ".\n" + "Enter the new price: ");	//current stats
			float price;
			while(true){	//getting valid price
				try {
					price = Float.parseFloat(keyboard.nextLine());
				} catch (Exception e) {
					System.out.println("Invalid price, try again.");
					continue;
				}
				break;
			}
			stock.setPrice(price);
			System.out.println("Updated " + stock.toString());
		}
		for (MutualFund mFund : mutualFunds) {
			found = true;	//investement found
			System.out.print("Current price of " + mFund.getSymbol() + " " + mFund.getName() + " is $" + 
			String.format("%.2f", mFund.getPrice()) + ".\n" + "Enter the new price: ");	//current status
			float price;
			while(true){	//getting valid price
				try {
					price = Float.parseFloat(keyboard.nextLine());
				} catch (Exception e) {
					System.out.println("Invalid price, try again.");
					continue;
				}
				break;
			}
			mFund.setPrice(price);
			System.out.println("Updated " + mFund.toString());
		}
		
		if (!found)
			System.out.println("No stock of mutual fund has been purchased. Cannot update prices."); //no investement found, throw error
	}

	/**
	 * Goes through every investement, calculating gain based on new prices vs old ones used in bookValue. Displays gain for every single investement, and then overall gain for all investemetn. If no investements exists, prints error message.
	 */
	public void getGain() {
		float gain = 0;
		boolean found = false;
		for (Stock stock : stonks) {	//go thorugh ever investement, calculate gain with current price
			found = true;	//at least one investement found
			float tempGain = stock.getPrice() * stock.getQuantity() - (float)9.99 - stock.getBookValue();
			System.out.println("Gain for " + stock.getSymbol() + " " + stock.getName() + " is $" + String.format("%.2f",tempGain) + "."); //print gain for this particual investement
			gain += tempGain; //add to overall gain
		}
		for (MutualFund mFund : mutualFunds) { //go thorugh ever investement, calculate gain with current price
			found = true;	//at least one investement found
			float tempGain = mFund.getPrice() * mFund.getQuantity() - 45 - mFund.getBookValue();
			System.out.println("Gain for " + mFund.getSymbol() + " " + mFund.getName() + " is $" + String.format("%.2f",tempGain) + ".");	//display gain for this particular MF
			gain += tempGain; //add to overall gain
		}
		System.out.println("Overall gain: $" + String.format("%.2f", gain)); //total gain
		if (!found){	//not one investement found
			System.out.println("No stock of mutual fund has been purchased. Cannot get gains.");
		}
	}

	/**
	 * Promts user to enter the values to search for, such as symbol, key words for the name, and price range. Loops through all investements and checks if objects meet search requirements. 
	 * If yes, print the object. If no objects found, throw an error
	 * @param keyboard passed by reference. used for user input for symbol, name and price range.
	 */
	public void search(Scanner keyboard) {
		boolean symbolExists = true;	//confirmation if search parameters will be used
		boolean nameExists = true;
		boolean priceRangeExists = true;
		boolean foundAtLeastOne = false;
		//TODO test all 8 possibilites
		System.out.print("Enter a (case-insenstitive) symbol to search for (blank is acceptable): ");
		String symbol = keyboard.nextLine();	//get symbol, make usre not empty
		if (symbol.isBlank() || symbol.isEmpty())
			symbolExists = false;
		symbol = symbol.strip();
		System.out.print("Enter a (case-insensitive) name to search for (blank is acceptable): ");
		String name = keyboard.nextLine();
		if (name.isEmpty() || name.isBlank())
			nameExists = false; 
		name = name.strip();
		String[] nameSplit = name.split("[ ]+");	//split name into key *words*

		System.out.print("Enter the range of prices to search for (blank is acceptable): ");	//fucky part begins
		float lowerBound = Float.NEGATIVE_INFINITY;	//by default, lowe and upper bounds are set to neg/pos infinity respectively
		float upperBound = Float.POSITIVE_INFINITY;
		while(true){
			String priceRangeRaw = keyboard.nextLine();
			int dashIndex;
			if (priceRangeRaw.isBlank() || priceRangeRaw.isEmpty()){
				priceRangeExists = false;
				break;
			}
			priceRangeRaw = priceRangeRaw.strip(); //getting proper range input, not blank, no whitespace
			dashIndex = priceRangeRaw.indexOf("-");
			if (dashIndex == -1){	//if there is no dash, then only precise input (10)
				try {	//proper value
					lowerBound = Float.parseFloat(priceRangeRaw);	
				} catch (Exception e) {
					System.out.println("Invalid price input, try again.");
					continue;
				}
				upperBound = lowerBound;	//exact value == lowerbound = upperbound
				break;	//exit loop, needed information gathered
			}
			// otherwise dash exists
			if (dashIndex == 0){	// either in the form of "-number" or "-"
				if(priceRangeRaw.length() == 1){	//if "-", then assume range of values is 'neg inf - pos inf'
					break;
				}
				try {//change upper bound only, lower bound is still neg inf
					upperBound = Float.parseFloat(priceRangeRaw.substring(1, priceRangeRaw.length()));	//substring of right after '-' to end, parse to float
				} catch (Exception e) {
					System.out.println("Invalid upper bound, try again.");	
					continue;
				}
				break; //info gathered, exit the loop
			}
			else if (dashIndex == (priceRangeRaw.length()-1)){	//dash is at the las position (form of "number-")
				try {
					lowerBound = Float.parseFloat(priceRangeRaw.substring(0, dashIndex));	//parse to float substring from index 0 to right before '-'
				} catch (Exception e) {
					System.out.println("Invalid lower bound, try again.");
					continue;
				}
				break; //info gathered, exit loop
			}
			else{	//'-' is somewhere in the middle 
				try {	//parse to float from 0 to right before '-' and right after '-' to end
					lowerBound = Float.parseFloat(priceRangeRaw.substring(0, dashIndex));
					upperBound = Float.parseFloat(priceRangeRaw.substring(dashIndex+1, priceRangeRaw.length()));
				} catch (Exception e) {
					System.out.println("Invalid bounds, try again.");
					continue;
				}
			}
			break;
			
		}
		
		//making sure at least one field is filled
		if (!nameExists && !priceRangeExists && !symbolExists){
			System.out.println("Cannot search with no search conditions. returning to main menu.");
			return;
		}
	
		for (Stock stock : stonks) {	//go though every object
			if (symbolExists){	//if symbol was entered, should be true, testing
				if (!symbol.equals(stock.getSymbol())){
					continue;	//if symbol doesn't match stock's symbol, go to condition check, object is discarded
				}
			}
			if (nameExists) {	//name is not blank, checking
				String[] stockNameSplit = stock.getName().split("[ ]+");
				boolean match = true;	//checks if a mismatch in the word was found "apple != appl"
				for (String itemString : nameSplit) {
					boolean found = false;	//if went through all the words, and no match was found
					for (String string : stockNameSplit) {
						if(itemString.equalsIgnoreCase(string)){	//compares every word
							found = true;	//if found, ticking time bomnb wont go off
							break;
						}
					}
					if (!found){	//no match in the name was found, then word we're searching for isn't there, therefore, no match. discard
						match = false;
						break;
					}
				}
				if (!match){
					continue;
				}
			}
			if (priceRangeExists){//checks if the price of the stock is less than upperbound and larger than lower bound
				if (Float.compare(stock.getPrice(), upperBound) == 1 || Float.compare(stock.getPrice(), lowerBound) == -1){	
					continue; //if not, object is discarded
				}
			}
			foundAtLeastOne = true;	//if object passed all search parameters, print out it's contents
			System.out.println("Stock found matching \"Symbol: " + symbol + ", Name: " + name + ", Price: " + lowerBound + "-" + upperBound + "\": ");
			System.out.println(stock.toString());
		}
		for (MutualFund mFund : mutualFunds) {	//same logic applies as to stock, refer to that for explanation
			if (symbolExists){
				if (!symbol.equals(mFund.getSymbol())){
					continue;
				}
			}
			if (nameExists) {
				String[] stockNameSplit = mFund.getName().split("[ ]+");
				boolean match = true;
				for (String itemString : nameSplit) {
					boolean found = false;
					for (String string : stockNameSplit) {
						if(itemString.equalsIgnoreCase(string)){
							found = true;
							break;
						}
					}
					if (!found){
						match = false;
						break;
					}
				}
				if (!match){
					continue;
				}
			}
			if (priceRangeExists){
				if (Float.compare(mFund.getPrice(), upperBound) == 1 || Float.compare(mFund.getPrice(), lowerBound) == -1){
					continue;
				}
			}
			foundAtLeastOne = true;
			System.out.println("Mutual Fund found matching \"Symbol: " + symbol + ", Name: " + name + ", Price: " + lowerBound + "-" + upperBound + ": ");
			System.out.println(mFund.toString());
		}
		
		if (!foundAtLeastOne){	//if every single object got discarded, print out error message
			System.out.println(" No matches found for:\"Symbol: " + symbol + "; Name: " + name + " Price: " + lowerBound + "-" + upperBound + "\".");
		}

	}

	/**
	 * Additional method. Goes through every investement, and displays it's contents.
	 */
	public void printAll() {	//goes through all investememnts, printing info
		for (Stock i : stonks) {
			System.out.println(i.toString());
		}
		for (MutualFund i : mutualFunds) {
			System.out.println(i.toString());
		}
	}
}