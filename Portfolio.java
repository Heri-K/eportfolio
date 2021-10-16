package ukaparyk_a1.eportfolio;

import java.util.Scanner;

public class Portfolio{
	public static void main(String argv[]){
		Scanner keyboard = new Scanner(System.in);
		String command = new String();
		Portfolio here = new Portfolio();

		while (!command.equalsIgnoreCase("quit") && !command.equalsIgnoreCase("q")){
			System.out.print("Enter the command (available commands are nuy, sell, getGain, search, quit):");
			command = keyboard.nextLine();
			if(!here.validateCommand(command)){
				System.out.println("Invalid command, try again.");
				continue;
			}
			
		}
		keyboard.close();
	}

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


}