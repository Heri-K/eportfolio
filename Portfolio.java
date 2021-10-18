package ukaparyk_a1.eportfolio;

import java.util.ArrayList;
import java.util.Scanner;

public class Portfolio {
	private ArrayList<Stock> stonks;
	private ArrayList<MutualFund> mutualFunds;

	public Portfolio(){
		stonks = new ArrayList<Stock>();
		mutualFunds = new ArrayList<MutualFund>();
	}

	public void buy(Scanner keyboard) {// validatiot for buying stuff
		System.out.print("Enter the kind of investment(stock or mutualfund): ");
		String invType = keyboard.nextLine().strip();
		System.out.println(invType);
		while (true){
			if (!invType.equalsIgnoreCase("stock") && !invType.equalsIgnoreCase("s") && 
			!invType.equalsIgnoreCase("mutualfund") && !invType.equalsIgnoreCase("mf") && !invType.equalsIgnoreCase("m")){
				System.out.println("Not an investement type, try again.");
				invType = keyboard.nextLine();
			}
			break;
		}
		System.out.print("Enter a symbol for the Investement: ");
		String symbol = keyboard.nextLine();
		while (true) {
			if (symbol.isEmpty() || symbol.isBlank()) { // checks if 2 words were inputted
				System.out.println("Invalid symbol, try again.");
				invType = keyboard.nextLine();
			}
			break;
		}
		symbol = symbol.strip();
		if (invType.equalsIgnoreCase("stock") || invType.equalsIgnoreCase("s"))
			buyInvStoc(keyboard, symbol);
		else
			buyInvMF(keyboard, symbol);
	}

	public void buyInvStoc(Scanner keyboard, String symbol) {
		int quantity;
		System.out.print("Enter the quantity of stock to buy: ");
		while(true){
			try {
				quantity = Integer.parseInt(keyboard.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid input. Try again.");
				continue;
			}
			break;
		}
		float price;
		System.out.print("Enter the price of the stock to buy: ");
		while(true){
			try {
				price = Float.parseFloat(keyboard.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid price. Try again.");
				continue;
			}
			break;
		}
		for (Stock stock : stonks) {
			if (symbol.equalsIgnoreCase(stock.getSymbol())) {
				stonks.add(new Stock(quantity, price));
				System.out.println("Bought " + symbol + " " + stock.getName() + ": " + 
				quantity + " shares for " + String.format("%.2f", price) + " each. Total Book Value is: " + String.format("%.2f", stock.getBookValue()));
				return;
			}
		}
		System.out.print("Enter the name of the stock: ");
		String stockName = keyboard.nextLine();
		while(true){
			if (stockName.isBlank() || stockName.isEmpty()){
				System.out.println("Invalid name, try again.");
				stockName = keyboard.nextLine();
			}
			break;
		}
		stockName = stockName.strip();
		stonks.add(new Stock(symbol, stockName, quantity, price));
		System.out.println("Bought " + symbol + " " + stockName + ": " + 
		quantity + " shares for " + String.format("%.2f", price) + " each. Total Book Value is: " + String.format("%.2f", (price * quantity + 9.99)));
	}

	public void buyInvMF(Scanner keyboard, String symbol) {
		int quantity;
		System.out.print("Enter the quantity of mutual fund to buy: ");
		while(true){
			try {
				quantity = Integer.parseInt(keyboard.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid input. Try again.");
				continue;
			}
			break;
		}
		float price;
		System.out.print("Enter the price of the mutual fund to buy: ");
		while(true){
			try {
				price = Float.parseFloat(keyboard.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid price. Try again.");
				continue;
			}
			break;
		}
		for (MutualFund mFund: mutualFunds) {
			if (symbol.equalsIgnoreCase(mFund.getSymbol())) {
				mutualFunds.add(new MutualFund(quantity, price));
				System.out.println("Bought " + symbol + " " + mFund.getName() + ": " + 
				quantity + " shares for " + String.format("%.2f", price) + " each. Total Book Value is: " + String.format("%.2f", mFund.getBookValue()));
				return;
			}
		}
		System.out.print("Enter the name of the mutual fund: ");
		String mFundName = keyboard.nextLine();
		while(true){
			if (mFundName.isBlank() || mFundName.isEmpty()){
				System.out.println("Invalid name, try again.");
				mFundName = keyboard.nextLine();
			}
			break;
		}
		mFundName = mFundName.strip();
		mutualFunds.add(new MutualFund(symbol, mFundName, quantity, price));
		System.out.println("Bought " + symbol + " " + mFundName + ": " + 
		quantity + " shares for " + String.format("%.2f", price) + " each. Total Book Value is: " + String.format("%.2f", (price * quantity)));
	}
	
	public void sell(Scanner keyboard){
		System.out.print("Enter the symbol: ");
		String symbol = keyboard.nextLine();
		
		int quantity;
		float price, gain, payment;
		while(true){	//getting symbol
			if(symbol.isEmpty() || symbol.isBlank()){
				System.out.println("Invalid input, try again.");
				symbol = keyboard.nextLine();
				continue;
			}
			break;
		}
		symbol = symbol.strip().toLowerCase();
		for (Stock stock: stonks) {
			if (symbol.equalsIgnoreCase(stock.getSymbol())){
				System.out.print("Enter the quantity of " + symbol + " to sell: ");
				while (true) {	//getting appropriate quantity
					try {	//quanity as integer, not string
						quantity = Integer.parseInt(keyboard.nextLine());
					} catch (Exception e) {
						System.out.println("Invalid value, try again.");
						continue;
					}
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
				payment = (price * quantity) - (float)9.99;
				float temp = stock.getBookValue();
				stock.sellBookValue(stock.getQuantity() - quantity);
				gain = payment - (temp - stock.getBookValue());
				System.out.println("Successfully sold " + quantity + " of " + symbol + " " + stock.getName() + " for " + price + "each.");
				System.out.println("Total payment: $" + payment + ", with net gain of $" + gain + ".");
				if (stock.getQuantity() == 0){
					stonks.remove(stock);
					return;
				}
				stock.setPrice(price);
				System.out.println("Current status of " + symbol + " " + stock.getName() + ": \n" + 
					"\tQuantity: " + quantity + "\n\tPrice: " + price + "\n\tBook Value: $" + String.format("%.2f",stock.getBookValue()));
			}
			return;
		}
		for (MutualFund mFund : mutualFunds) {
			if (symbol.equalsIgnoreCase(mFund.getSymbol())) {
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
					payment = (price * quantity) - 45;
					float temp = mFund.getBookValue();
					mFund.sellBookValue(mFund.getQuantity() - quantity);
					gain = payment - (temp - mFund.getBookValue());
					System.out.println("Successfully sold " + quantity + " of " + symbol + " " + mFund.getName() + " for " + price + " each.");
					System.out.println("Total payment: $" + payment + ", with net gain of $" + gain + ".");
					if (mFund.getQuantity() == 0){
						mutualFunds.remove(mFund);
						return;
					}
					mFund.setPrice(price);
					System.out.println("Current status of " + symbol + " " + mFund.getName() + ": \n" + 
						"\tQuantity: " + quantity + "\n\tPrice: " + price + "\n\tBook Value: $" + String.format("%.2f", mFund.getBookValue()));
				}
			}
			return;
		}
		System.out.println("No stock nor mutual fund with symbol " + symbol + " was found. Cannot sell.");
		return;
	}

	public void update(Scanner keyboard) {
		boolean found = false;
		for (Stock stock : stonks) {
			found = true;
			System.out.println("Current price of " + stock.getSymbol() + " " + stock.getName() + " is $" + 
			String.format("%.2f", stock.getPrice()) + ".\n" + "Enter the new price: ");
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
			found = true;
			System.out.println("Current price of " + mFund.getSymbol() + " " + mFund.getName() + " is $" + 
			String.format("%.2f", mFund.getPrice()) + ".\n" + "Enter the new price: ");
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
		
		if (!found){
			System.out.println("No stock of mutual fund has been purchased. Cannot update prices.");
		}
	}

	public void getGain(Scanner keyboard) {
		float gain = 0;
		boolean found = false;
		for (Stock stock : stonks) {
			found = true;
			float tempGain = stock.getPrice() * stock.getQuantity() - (float)9.99 - stock.getBookValue();
			System.out.println("Gain for " + stock.getSymbol() + " " + stock.getName() + " is $" + String.format("%.2f",tempGain) + ".");
			gain += tempGain; 
		}
		for (MutualFund mFund : mutualFunds) {
			found = true;
			float tempGain = mFund.getPrice() * mFund.getQuantity() - 45 - mFund.getBookValue();
			System.out.println("Gain for " + mFund.getSymbol() + " " + mFund.getName() + " is $" + String.format("%.2f",tempGain) + ".");
			gain += tempGain; 
		}
		System.out.println("Overall gain: $" + String.format("%.2f", gain));
		if (!found){
			System.out.println("No stock of mutual fund has been purchased. Cannot get gains.");
		}
	}

	public void search(Scanner keyboard) {
		boolean symbolExists = true;
		boolean nameExists = true;
		boolean priceRangeExists = true;
		boolean foundAtLeastOne = false;
		//TODO test all 8 possibilites
		//todo lare -small
		System.out.println("Enter a (case-insenstitive) symbol to search for: ");
		String symbol = keyboard.nextLine();
		if (symbol.isBlank() || symbol.isEmpty())
			symbolExists = false;
		
		System.out.println("Enter a (case-insensitive) name to search for: ");
		String name = keyboard.nextLine();
		if (name.isEmpty() || name.isBlank())
			nameExists = false; 
		name = name.strip();
		String[] nameSplit = name.split("[ ]+");

		System.out.println("Enter the range of prices to search for: ");
		float lowerBound = Float.NEGATIVE_INFINITY;
		float upperBound = Float.POSITIVE_INFINITY;
		while(true){
			String priceRangeRaw = keyboard.nextLine();
			int dashIndex;
			priceRangeRaw = priceRangeRaw.strip(); 
			if (priceRangeRaw.isBlank() || priceRangeRaw.isEmpty()){
				priceRangeExists = false;
				break;
			}
			//if there is no dash, then only precise input
			dashIndex = priceRangeRaw.indexOf("-");
			if (dashIndex == -1){	//exact value == lowerbound = upperbound
				try {
					lowerBound = Float.parseFloat(priceRangeRaw);	
				} catch (Exception e) {
					System.out.println("Invalid price input, try again.");
					continue;
				}
				upperBound = lowerBound;
				break;
			}
			//dash exists
			if (dashIndex == 0){	//change upper bound only, lower bound is still neg inf
				if(priceRangeRaw.length() == 1){
					break;
				}
				try {
					upperBound = Float.parseFloat(priceRangeRaw.substring(1, priceRangeRaw.length()));
					
				} catch (Exception e) {
					System.out.println("Invalid upper bound, try again.");
					continue;
				}
			}
			else if (dashIndex == (priceRangeRaw.length()-1)){
				try {
					lowerBound = Float.parseFloat(priceRangeRaw.substring(0, dashIndex));
				} catch (Exception e) {
					System.out.println("Invalid lower bound, try again.");
					continue;
				}
			}
			else{
				try {
					lowerBound = Float.parseFloat(priceRangeRaw.substring(0, dashIndex));
					upperBound = Float.parseFloat(priceRangeRaw.substring(dashIndex+1, priceRangeRaw.length()));
				} catch (Exception e) {
					System.out.println("Invalid bounds, try again.");
					continue;
				}
			}
			break;
			
		}
		
		for (Stock stock : stonks) {
			if (symbolExists){
				if (!symbol.equals(stock.getSymbol())){
					continue;
				}
			}
			if (nameExists) {
				String[] stockNameSplit = stock.getName().split("[ ]+");
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
				if (Float.compare(stock.getPrice(), upperBound) == 1 || Float.compare(stock.getPrice(), lowerBound) == -1){
					continue;
				}
			}
			foundAtLeastOne = true;
			System.out.println("Stock found matching \"Symbol: " + symbol + ", Name: " + name + ", Price: " + lowerBound + "-" + upperBound + "\": ");
			System.out.println(stock.toString());
		}
		for (MutualFund mFund : mutualFunds) {
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
		
		if (!foundAtLeastOne){
			System.out.println(" No matches found for:\"Symbol: " + symbol + "; Name: " + name + " Price: " + lowerBound + "-" + upperBound + "\".");
		}

	}
}

	
