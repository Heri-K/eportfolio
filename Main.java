package ukaparyk_a1.eportfolio;
import java.util.Scanner;

public class Main {
	public static void main(String argv[]) {
		Portfolio portfolio = new Portfolio();

		Scanner keyboard = new Scanner(System.in);
		String command = new String();
		while (!command.strip().equalsIgnoreCase("quit") && !command.strip().equalsIgnoreCase("q")) {
			System.out.print("Enter the command (available commands are buy, sell, update, getGain, search, quit):");
			command = keyboard.nextLine();

			switch(command.toLowerCase().strip()){
				case "b":
				case "buy":
					portfolio.buy(keyboard);
					break;
				case "s":
				case "sell":
					portfolio.sell(keyboard);
					break;
				case "u":
				case "update":
					portfolio.update(keyboard);
					break;
				case "g":
				case "gg":
				case "getgain":
					portfolio.getGain(keyboard);
					break;
				case "sr":
				case "search":
					portfolio.search(keyboard);
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
}
