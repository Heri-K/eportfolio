package ukaparyk_a1.eportfolio;

import java.util.ArrayList;
import java.util.Scanner;

public class Portfolio {
	public static void main(String argv[]) {
		Portfolio here = new Portfolio();
		ArrayList<Stock> stonks = new ArrayList<Stock>();
		ArrayList<MutualFund> mutualFunds = new ArrayList<MutualFund>();

		Scanner keyboard = new Scanner(System.in);
		String command = new String();
		while (!command.strip().equalsIgnoreCase("quit") && !command.strip().equalsIgnoreCase("q")) {
			System.out.print("Enter the command (available commands are buy, sell, update, getGain, search, quit):");
			command = keyboard.nextLine();

			switch(command.toLowerCase().strip()){
				case "b":
				case "buy":
					here.buyStuff(here, keyboard, stonks, mutualFunds);
					break;
				case "s":
				case "sell":
					here.sellStuff(here, keyboard, stonks, mutualFunds);
					break;
				case "u":
				case "update":
					here.update(keyboard, stonks, mutualFunds);
					break;
				case "g":
				case "gg":
				case "getgain":
					here.getGain(here, keyboard, stonks, mutualFunds);
					break;
				case "q":
				case "quit":
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid command, try again.");
					break;
			}
		}
		keyboard.close();
	}

	/*********************************************************************************/	
	
	public void buyStuff(Portfolio here, Scanner keyboard, ArrayList<Stock> stonks,
	ArrayList<MutualFund> mutualFunds) {// validatiot for buying stuff
		System.out.println("Enter the kind of investment(stock or mutualfund), followed by the symbol (Eg. Stock APPL):");
		String invType = keyboard.nextLine();
		String[] split = new String[2];
		split = invType.split("[ ]+");
		while (true) {
			if (invType.isEmpty() || invType.isBlank()) { // checks if the input is blank
				System.out.println("Not an investement type, try again.");
				invType = keyboard.nextLine();
				split = invType.split("[ ]+");
				continue;
			}
			if (!split[0].equalsIgnoreCase("stock") && !split[0].equalsIgnoreCase("mutualfund")) { // checks if investememnt type is valid
				System.out.println("Not an investement type, try again.");
				invType = keyboard.nextLine();
				split = invType.split("[ ]+");
				continue;
			}
			try {
				if (split[1].isEmpty()) { // checks if 2 words were inputted
					System.out.println("Symbol missing, try again.");
					invType = keyboard.nextLine();
					split = invType.split("[ ]+");
					continue;
				}
			} catch (Exception e) {
				System.out.println("Symbol missing, try again.");
				invType = keyboard.nextLine();
				split = invType.split("[ ]+");
				continue;
			}
			break;
		}
		if (split[0].equalsIgnoreCase("stock"))
		here.buyInvStock(stonks, keyboard, split[1]);
		else if (split[0].equalsIgnoreCase("mutualfund"))
		here.buyInvMF(mutualFunds, keyboard, split[1]);
	}

	public void buyInvStock(ArrayList<Stock> stonks, Scanner keyboard, String symbol) {
		int quantity;
		System.out.println("Enter the quantity of stock to buy:");
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
		System.out.println("Enter the price of the stock to buy:");
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
		System.out.println("Enter the name of the stock:");
		String stockName = keyboard.nextLine();
		while(true){
			if (stockName.isBlank() || stockName.isEmpty()){
				System.out.println("Invalid name, try again.");
				stockName = keyboard.nextLine();
			}
			break;
		}
		stonks.add(new Stock(symbol, stockName, quantity, price));
		System.out.println("Bought " + symbol + " " + stockName + ": " + 
		quantity + " shares for " + String.format("%.2f", price) + " each. Total Book Value is: " + String.format("%.2f", (price * quantity + 9.99)));
	}

	public void buyInvMF(ArrayList<MutualFund> mutualFunds, Scanner keyboard, String symbol) {
		int quantity;
		System.out.println("Enter the quantity of Mutual Fund to buy:");
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
		System.out.println("Enter the price of the Mutual Fund to buy:");
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
		System.out.println("Enter the name of the mutual fund:");
		String mFundName = keyboard.nextLine();
		while(true){
			if (mFundName.isBlank() || mFundName.isEmpty()){
				System.out.println("Invalid name, try again.");
				mFundName = keyboard.nextLine();
			}
			break;
		}
		mutualFunds.add(new MutualFund(symbol, mFundName, quantity, price));
		System.out.println("Bought " + symbol + " " + mFundName + ": " + 
		quantity + " shares for " + String.format("%.2f", price) + " each. Total Book Value is: " + String.format("%.2f", (price * quantity)));
	}
	
	public void sellStuff(Portfolio here, Scanner keyboard, ArrayList<Stock> stonks,
	ArrayList<MutualFund> mutualFunds){
		System.out.println("Enter the symbol:");
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
				System.out.println("Enter the quantity of " + symbol + " to sell: ");
				while (true) {	//getting appropriate quantity
					try {	//quanity as integer, not string
						quantity = Integer.parseInt(keyboard.nextLine());
					} catch (Exception e) {
						System.out.println("Invalid value, try again.");
						continue;
					}
					if (quantity > stock.getQuantity() || quantity <= 0){ //proper range
						System.out.println("Value outside the range, try again;");
						continue;
					}
					break;
				}
				System.out.println("Enter the price:");
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
				gain = payment - stock.getBookValue();
				System.out.println("Successfully sold " + quantity + " of " + symbol + " " + stock.getName() + " for " + price + "each.");
				System.out.println("Total payment: $" + payment + ", with net gain of $" + gain + ".");
				if (stock.getQuantity() == 0){
					stonks.remove(stock);
					return;
				}
				stock.setPrice(price);
				stock.sellBookValue(stock.getQuantity() - quantity);
				System.out.println("Current status of " + symbol + " " + stock.getName() + ": \n" + 
					"\tQuantity: " + quantity + "\n\tPrice: " + price + "\n\tBook Value: $" + String.format("%.2f",stock.getBookValue()));
			}
			return;
		}
		for (MutualFund mFund : mutualFunds) {
			if (symbol.equalsIgnoreCase(mFund.getSymbol())) {
				if (mFund.equals(symbol)){
					System.out.println("Enter the quantity of " + symbol + " to sell: ");
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
					System.out.println("Enter the price:");
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
					gain = payment - mFund.getBookValue();
					System.out.println("Successfully sold " + quantity + " of " + symbol + " " + mFund.getName() + " for " + price + " each.");
					System.out.println("Total payment: $" + payment + ", with net gain of $" + gain + ".");
					if (mFund.getQuantity() == 0){
						mutualFunds.remove(mFund);
						return;
					}
					mFund.setPrice(price);
					mFund.sellBookValue(mFund.getQuantity() - quantity);
					System.out.println("Current status of " + symbol + " " + mFund.getName() + ": \n" + 
						"\tQuantity: " + quantity + "\n\tPrice: " + price + "\n\tBook Value: $" + String.format("%.2f", mFund.getBookValue()));
				}
			}
			return;
		}
		System.out.println("No stock nor mutual fund with symbol " + symbol + " was found. Cannot sell.");
		return;
	}

	public void update(Scanner keyboard, ArrayList<Stock> stonks, ArrayList<MutualFund> mutualFunds) {
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

	public void getGain(Portfolio here, Scanner keyboard, ArrayList<Stock> stonks, ArrayList<MutualFund> mutualFunds) {
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


}

	
