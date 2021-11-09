package ePortfolio;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.HashMap;
/**
 * Class contains methods to manipulate private arraylist variables, display them, and interacts with Stock and MutiualFund classes
 */
public class Portfolio {
	private ArrayList<Investement> inv;
	private HashMap<String, ArrayList<Integer>> index;
	/**
	 *  Class constructor. initializes the lists as instances. 
	 */
	public Portfolio(){
		inv = new ArrayList<Investement>();
		index = new HashMap<String, ArrayList<Integer>>();
	}

	/**
	 * Asks user for a type of investement, and a symbol for said investement. calls buyStock() or buyMF() depending on the input. 
	 * @param keyboard Passed by "reference" from main. used in the method for user input
	 */
	public void buy(Scanner keyboard) {// validatiot for buying stuff
		System.out.println("Running buy() method.");
		System.out.print("Enter the kind of Investment(stock or mutualfund): ");
		String invType = keyboard.nextLine().strip();	//strip to get rid of extra spaces beggining/end
		char type = 'm';
		while (true){	//validation for proper investement type
			if (!invType.equalsIgnoreCase("stock") && !invType.equalsIgnoreCase("s") && 
			!invType.equalsIgnoreCase("mutualfund") && !invType.equalsIgnoreCase("mf") && !invType.equalsIgnoreCase("m")){
				System.out.println("Not an investement type, try again.");	//if not vaid, call string.nextLine(); and loop to see if it's still valid;
				invType = keyboard.nextLine().strip();
			}
			break;	//if valid, will bypass if statement, break the loop
		}
		invType = invType.strip();
	
		if (invType.equalsIgnoreCase("stock") || invType.equalsIgnoreCase("s"))
			type = 's';
		
		System.out.print("Enter a symbol for the Investement: ");
		String symbol = keyboard.nextLine();
		while (true) { //checks if the symbol is valid
			if (symbol.isEmpty() || symbol.isBlank()) { 
				System.out.print("Invalid symbol, try again. Enter a symbol for the Investement: ");
				symbol = keyboard.nextLine();
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
				System.out.print("Invalid input. Try again. Enter the quantity to buy: ");
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
				System.out.print("Invalid price. Try again. Enter the price of the investement to buy: ");	
				continue;
			}
			break;
		}
		//call buyInvestement depending on investement type
		buyInv(keyboard, symbol, quantity, price, type);
	}

	/**
	 * Verifies that symbol's investement already exists. If so, modify said inv with passed paramter values. if not, creates new inv object, and passes parameter values into constructor
	 * @param keyboard 	passed by reference. Used for user input for inv name.
	 * @param symbol	passed by value. used to determine if inv exists in the ArrayList. if not, used as symbol for new inv. passed into inv() constructor to be initialized into new stock
	 * @param price		passed by value. used to determine new bookValue of the inv (both pre-existsing and new). passed into inv() constructor to be initialized into new inv 
	 * @param quantity	passed by value. used to determine new bookValue of the inv. passed into inv() constructor if needed.
	 */
	public void buyInv(Scanner keyboard, String symbol, int quantity, float price, char type) {
		for (Investement i : inv) {	//goes through every inv object, checking there will be no duplicates
			if (symbol.equalsIgnoreCase(i.getSymbol())) {	//duplicate found, update quantity, price, bookvalue
				i.updateBuy(quantity, price);
				System.out.println("Bought " + symbol + " " + i.getName() + ": " + 
				quantity + " shares for " + String.format("%.2f", price) + " each. Total Book Value is: " + String.format("%.2f", i.getBookValue()));	//prtint the output, and return
				return;
							// TODO check if more than 1 symbol exists
							//if does, check if type matches
			}
		}
		if (type == 's')
			System.out.print("Enter the name of the stock: ");	//stock doesnt exist, need the name
		else
			System.out.print("Enter the name of the mutual fund: ");
		
		String invName = keyboard.nextLine();
		while(true){	//validating name isn't blank
			if (invName.isBlank() || invName.isEmpty()){
				System.out.print("Invalid name, try again. Enter the name: ");
				invName = keyboard.nextLine();
			}
			break;
		}
		invName = invName.strip(); //no name whitespace

		inv.add(((type == 's') ? new Stock(invName, symbol, quantity, price) : new MutualFund(invName, symbol, quantity, price)));	//creating new stock
		hashAdd(invName, inv.size() - 1);		
		System.out.println("Bought " + symbol + " " + invName + ": " + 
		quantity + " shares for $" + String.format("%.2f", price) + " each. Total Book Value is: $" + String.format("%.2f", (price * quantity + 9.99)));
	}

	/**
	 * Promts user for a symbol. uses that symbol to verify investement exists. if so, sells of said investement, displaying payment and gain. Also modifies stock price, quantity, and book value as needed. If symbol's investement doesn't exist, print out error message
	 * @param keyboard passed by reference. Used for user input for symbol, price and quantity
	 */
	public void sell(Scanner keyboard){
		System.out.println("Running sell() method.");
		System.out.print("Enter the symbol: ");
		String symbol = keyboard.nextLine();
		
		int quantity;
		float price, gain, payment;
		while(true){	//validating symbol
			if(symbol.isEmpty() || symbol.isBlank()){
				System.out.print("Invalid input, try again. Enter the symbol: ");
				symbol = keyboard.nextLine();
				continue;
			}
			break;
		}
		symbol = symbol.strip();
		for (Investement i : inv) {	//checking to see if stock exists
			if (symbol.equalsIgnoreCase(i.getSymbol())){
				System.out.print("Current quantity of " + i.getSymbol() + ":" + i.getQuantity() + ". Enter the quantity to sell: ");
				while (true) {	//getting appropriate quantity
					try {	//quanity as integer, not string
						quantity = Integer.parseInt(keyboard.nextLine());
					} catch (Exception e) {
						System.out.print("Invalid value, try again. Enter the quantity: ");
						continue;
					}	//making sure quaninty is not larger than what we have, and not <= 0
					if (quantity > i.getQuantity() || quantity <= 0){ //proper range
						System.out.print("Value outside the range, try again. Enter the Quantity: ");
						continue;
					}
					break;
				}
				System.out.print("Current price: " + i.getPrice() + ". Enter the price ");
				while (true) {	//getting price
					try {	//price as float, not string
						price = Float.parseFloat(keyboard.nextLine());
					} catch (Exception e) {
						System.out.print("Invalid price, try again. Enter the price: ");
						continue;
					}
					break;
				}
				payment = i.getPayment(price, quantity);	//payment as to how much user "recieves"
				float temp = i.getBookValue();
				i.sellBookValue(i.getQuantity() - quantity);
				gain = payment - (temp - i.getBookValue());	//overall gain for said stocks
				System.out.println("Successfully sold " + quantity + " of " + symbol + " " + i.getName() + " for " + price + " each.");
				System.out.println("Total payment: $" + payment + ", with net gain of $" + String.format("%.2f", gain) + ".");
				if (i.getQuantity() == 0){ //if quaninty 0, we don't have anymore stock, no need to keep track of it
					hashDelete(i.getName(), inv.indexOf(i));
					inv.remove(i);
					return;
				}
				i.setPrice(price);
				System.out.println("Current status of " + symbol + " " + i.getName() + ": \n" + 
					"\tQuantity: " + i.getQuantity() + "\n\tPrice: " + price + "\n\tBook Value: $" + String.format("%.2f",i.getBookValue()));
				return;
			}
		}
		System.out.println("No stock nor mutual fund with symbol " + symbol + " was found. Cannot sell.");	//no investement found, return error
		return;
	}

	/**
	 * Goes through every single investement, showing the investement details, and prompting user for the new price. Price is then updated for each investement object. 
	 * @param keyboard passed by reference. Used to get user input for new prices
	 */
	public void update(Scanner keyboard) {
		System.out.println("Running update().");
		boolean found = false;	//making sure at least one investement gets update
		for (Investement i : inv) {
			found = true;	//investement found
			System.out.print("Current price of " + i.getSymbol() + " " + i.getName() + " is $" + 
			String.format("%.2f", i.getPrice()) + ".\n" + "Enter the new price or leave blank to keep as is: ");	//current stats
			float price;
			String priceString;
			while(true){	//getting valid price
				priceString = keyboard.nextLine().trim();
				try {
					price = Float.parseFloat(priceString);
				} catch (Exception e) {
					if (priceString.isEmpty() || priceString.isBlank())
						price = i.getPrice();
					else{
						System.out.print("Invalid price, try again. Enter the price: ");
						continue;
					}
				}
				break;
			}
			i.setPrice(price);
			System.out.println("Updated " + i.toString());
		}
		
		if (!found)
			System.out.println("No stock of mutual fund has been purchased. Cannot update prices."); //no investement found, throw error
	}

	/**
	 * Goes through every investement, calculating gain based on new prices vs old ones used in bookValue. Displays gain for every single investement, and then overall gain for all investemetn. If no investements exists, prints error message.
	 */
	public void getGain() {
		System.out.println("Running getGain().");
		float gain = 0;
		boolean found = false;
		for (Investement i : inv) {	//go thorugh ever investement, calculate gain with current price
			found = true;	//at least one investement found
			float tempGain = i.getGain();
			System.out.println("Gain for " + i.getSymbol() + " " + i.getName() + " is $" + String.format("%.2f",tempGain) + "."); //print gain for this particual investement
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
		System.out.println("Running search() method.");
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
		String[] nameSplit = name.toLowerCase().split("[ ]+");	//split name into key *words*

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
					System.out.print("Invalid price input, try again. Enter the price range: ");
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
					System.out.print("Invalid upper bound, try again. Enter the price range: ");	
					continue;
				}
				break; //info gathered, exit the loop
			}
			else if (dashIndex == (priceRangeRaw.length()-1)){	//dash is at the las position (form of "number-")
				try {
					lowerBound = Float.parseFloat(priceRangeRaw.substring(0, dashIndex));	//parse to float substring from index 0 to right before '-'
				} catch (Exception e) {
					System.out.print("Invalid lower bound, try again. Enter the price range: ");
					continue;
				}
				break; //info gathered, exit loop
			}
			else{	//'-' is somewhere in the middle 
				try {	//parse to float from 0 to right before '-' and right after '-' to end
					lowerBound = Float.parseFloat(priceRangeRaw.substring(0, dashIndex));
					upperBound = Float.parseFloat(priceRangeRaw.substring(dashIndex+1, priceRangeRaw.length()));
				} catch (Exception e) {
					System.out.print("Invalid bounds, try again. Enter the price range: ");
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
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < inv.size(); i++) {
			temp.add(i);
		}

		if (nameExists) {	//name is not blank, checking
			for (int j = 0; j < nameSplit.length; j++) {
				ArrayList<Integer> temp2 = index.get(nameSplit[j]);
				System.out.println(temp2);
				if (temp2 == null){
					temp = new ArrayList<Integer>();
					break;
				}
				temp.retainAll(index.get(nameSplit[j]));
			}
			if (temp.isEmpty()){
				System.out.println("No matches found for: \"Symbol: " + symbol + "; Name: " + name + "; Price: " + lowerBound + "-" + upperBound + "\".");
				return;
			}
		}
		for (Integer i : temp) {	//go though every object
			if (symbolExists){	//if symbol was entered, should be true, testing
				if (!symbol.equals(inv.get(i).getSymbol())){
					continue;	//if symbol doesn't match stock's symbol, go to condition check, object is discarded
				}
			}
			
			if (priceRangeExists){//checks if the price of the stock is less than upperbound and larger than lower bound
				if (Float.compare(inv.get(i).getPrice(), upperBound) == 1 || Float.compare(inv.get(i).getPrice(), lowerBound) == -1){	
					continue; //if not, object is discarded
				}
			}
			foundAtLeastOne = true;	//if object passed all search parameters, print out it's contents
			System.out.println("Investement found matching \"" + ((symbolExists) ? "Symbol: " + symbol + "; " : "" ) + ((nameExists) ? "Name: " + name + "; " : "") + ((priceRangeExists) ? "Price: " + lowerBound + "-" + upperBound  : "" )+"\": ");
			System.out.println(inv.get(i).toString() + "\n");
		}
		
		if (!foundAtLeastOne){	//if every single object got discarded, print out error message
			System.out.println(" No matches found for: \"Symbol: " + symbol + "; Name: " + name + " Price: " + lowerBound + "-" + upperBound + "\".");
		}

	}

	/**
	 * Additional method. Goes through every investement, and displays it's contents.
	 */
	public void printAll() {	//goes through all investememnts, printing info
		for (Investement i : inv) {
			System.out.println(i.toString() + "\n");
		}
	}

	public void saveFile(String fileName){
		PrintWriter outStream = null;
		try {
			outStream = new PrintWriter(new FileOutputStream(fileName), true);
		} catch (FileNotFoundException e) {
			System.out.println("Error opening file " + fileName + ". ");
			return;
		}
		for (Investement i: inv) {
			outStream.println(i.toFile());
		}
		outStream.close();
	}

	public void readFile(String filename){
		File file = new File(filename);
		Scanner scanner = null;
		String investement = "";

		if(!file.exists()){
			System.out.println("File " + filename + " does not exists. cannot read.");
			return;
		}
		try {
			scanner = new Scanner(new FileInputStream(filename));
		} catch (Exception e) {
			System.out.println("Cannot open file " + filename + ". Returning to main.");
			return;
		}

		while (scanner.hasNextLine()) {
			String temp = scanner.nextLine().trim();
			if (!temp.isBlank()){
				investement = investement.concat(temp);
			}
			else{
				String[] split = investement.split(" = \"|\"");
				if (split.length == 0)
					continue;
				inv.add((split[1].equalsIgnoreCase("stock")) ? 
				(new Stock(split[5], split[3], Integer.parseInt(split[7]), Float.parseFloat(split[9]), Float.parseFloat(split[11]))) : 
				new MutualFund(split[5], split[3], Integer.parseInt(split[7]), Float.parseFloat(split[9]), Float.parseFloat(split[11])));
				hashAdd(split[5], inv.size() - 1);
				investement = "";
				}
		}

	}

	private void hashAdd(String invName, int i) {
		String[] split = invName.toLowerCase().split("[ ]+");
		for (String el : split) {
			ArrayList<Integer> temp = index.get(el);
			if (temp == null){
				temp = new ArrayList<Integer>();
			}
			temp.add(i);
			index.putIfAbsent(el, temp);
			index.replace(el, temp);
		}
	}

	private void hashDelete(String name, int ind) {
		String[] split = name.toLowerCase().split("[ ]+");
		for (String i : index.keySet()) {
			ArrayList<Integer> temp = index.get(i);
			for (int j = 0; j < temp.size(); j++) {
				System.out.println(i + " " + j + "  " + ind);
				if (temp.get(j) == ind)
					temp.remove(temp.get(j--));
				else if (temp.get(j) > ind)
					temp.set(temp.indexOf(temp.get(j)), temp.get(j)-1);
			}
			index.replace(i, temp);
		}
		for (String i : split) {
			ArrayList<Integer> temp = index.get(i);
			if (temp.isEmpty())
				index.remove(i);
		}
	}
}