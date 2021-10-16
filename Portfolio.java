package ukaparyk_a1.eportfolio;

import java.util.ArrayList;
import java.util.Scanner;

public class Portfolio{
	public static void main(String argv[]){
		Portfolio here = new Portfolio();
		ArrayList<Stock> stonks = new ArrayList<Stock>();
		ArrayList<MutualFund> mutualFunds = new ArrayList<MutualFund>();
		

		Scanner keyboard = new Scanner(System.in);
		String command = new String();
		while (!command.equalsIgnoreCase("quit") && !command.equalsIgnoreCase("q")){
			System.out.print("Enter the command (available commands are nuy, sell, getGain, search, quit):");
			command = keyboard.nextLine();
			if(!here.validateCommand(command)){
				System.out.println("Invalid command, try again.");
				continue;
			}
			if (command.equalsIgnoreCase("buy")){
				here.buyStuff(here, keyboard, stonks, mutualFunds);
			}
		}
		keyboard.close();
	}
	/*********************************************************************************/
	private boolean validateCommand(String command) {							//checks if parameter string matches allowed commands
		if (command.equalsIgnoreCase("q") || command.equalsIgnoreCase("quit"))	//did it in if/esle statemetns instead of return ...;
		return true;														//in case i need to add stuff like "q" instead of "quit"
		else if (command.equalsIgnoreCase("buy"))
		return true;
		else if(command.equalsIgnoreCase("sell"))
		return true;
		else if(command.equalsIgnoreCase("update"))
		return true;
		else if(command.equalsIgnoreCase("getgain"))
		return true;
		else if(command.equalsIgnoreCase("search"))
		return true;
		return false;
	}

	private void buyStuff(Portfolio here, Scanner keyboard, ArrayList<Stock> stonks, ArrayList<MutualFund> mutualFunds) {//validatiot for buying stuff
	System.out.println("Enter the kind of investment(stock or mutualfund), followed by the symbol:");
	String invType = keyboard.nextLine();
		String[] split = new String[2];
		while(true){
			if (invType.isEmpty() || invType.isBlank()){	//checks if the input is blank
				System.out.println("Not an investement type, try again.");
				invType = keyboard.nextLine();
				continue;
			}
			if(split[1].isEmpty()){	//checks if 2 words were inputted
				System.out.println("Symbol missing, try again.");
				invType = keyboard.nextLine();
				continue;
			}
			if (!split[0].equalsIgnoreCase("stock") || !split[0].equalsIgnoreCase("mutualfund")){	//checks if investememnt type is valid
				System.out.println("Not an investement type, try again.");
				invType = keyboard.nextLine();
				continue;
			}
			break;
		}
		if (split[0].equalsIgnoreCase("stock"))
			here.buyInvStock(stonks, keyboard, split[1]);
		else if (invType.equalsIgnoreCase("mutualfund"))
			here.buyInvMF(mutualFunds, keyboard, split[1]);
	}
	
	private void buyInvStock(ArrayList<Stock> stonks, Scanner keyboard, String symbol) {
		
	}
	
	private void buyInvMF(ArrayList<MutualFund> mutualFunds, Scanner keyboard, String split) {
	
	}
}