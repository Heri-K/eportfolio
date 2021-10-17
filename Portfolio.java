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
		while (!command.equalsIgnoreCase("quit") && !command.equalsIgnoreCase("q")) {
			System.out.print("Enter the command (available commands are buy, sell, getGain, search, quit):");
			command = keyboard.nextLine();

			switch(command.toLowerCase().strip()){
				case "b":
				case "buy":
					here.buyStuff(here, keyboard, stonks, mutualFunds);
					break;
				default:
					System.out.println("Invalid command, try again.");
					break;
			}
		}
		keyboard.close();
	}

	/*********************************************************************************/	

	/**
	 * descriptions of the thing
	 * @param here	local class references
	 * @param keyboard	scanner object
	 * @param stonks	arraylist of stonks
	 * @param mutualFunds	arraylist of mutual funds
	 */
	public void buyStuff(Portfolio here, Scanner keyboard, ArrayList<Stock> stonks,
			ArrayList<MutualFund> mutualFunds) {// validatiot for buying stuff
		System.out.println("Enter the kind of investment(stock or mutualfund), followed by the symbol:");
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

	private void buyInvStock(ArrayList<Stock> stonks, Scanner keyboard, String symbol) {
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
	}

	private void buyInvMF(ArrayList<MutualFund> mutualFunds, Scanner keyboard, String symbol) {
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
				return;
			}
		}
		System.out.println("Enter the name of the mutual fund:");
		String stockName = keyboard.nextLine();
		while(true){
			if (stockName.isBlank() || stockName.isEmpty()){
				System.out.println("Invalid name, try again.");
				stockName = keyboard.nextLine();
			}
			break;
		}
		mutualFunds.add(new MutualFund(symbol, stockName, quantity, price));
	}
}