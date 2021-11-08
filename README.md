# ePortfolio

Student: Uladzislau Kaparykha
Student ID: 1096425
Course: F21-CIS2430
Due Date: 18/10/2021


<-------------------------Program Intentions--------------------------> 

The program is aimed at helping investors manage their portfolios. They can add stocks and mutual funds that interest them, buy and sell them, update their prices, and get overall gain from them. The program starts with a menu, and requests a user for the input. After function is done, program reprints the menu, until "quit" is called. 
"Buy" menu function allows you to buy certain amount of stock and/or mutual fund at a specific price, and the program keeps track of it until program quits. 
"Sell" prompts user for a symbol, quantity, and price, and then searches stock and mutualFunds lists for said symbol. If it finds said symbol, updates quantity, price and bookValue of said investement, and displays the payment recieved, alongside with overall gain from seeling said investement (even partially). If it does doesnt find the investemtn with said symbol, returns and error, and brings up the menu. 
"Update" goes through every investement (both stock and mutual fund), displays current status of the investement (price and quantity), and prompts user to enter a new price. Displays updated information about the stock afterwards. If no investements were added, throws an error, and returns to the menu.
"getGain" calculates gain for each individual investement (gain = payment - currBookValue = (quantity * price - comm) - currBookValue), and displays it per investement. After looping through both investement types lists, displays overall gain. If no investements were added to the lists, displays gain of 0, throws an error of "empty list, can't read", andreturns to the menu. 
"search" prompts user for input on symbol, key words for the name, and price range. If input field was left blank, search condition is ignored. Program then goes through every element in investement lists, comparing if the element's contents match the search condition. If a mismatch is found (requested: APPS, currItem: AAPL), investement is discarded, and next one is searched. If the investement meets all search criteria, investement information is displayed, and next investement is searched. If no mathes were found or the lists are empty, throws an error and returns to the menu.
"printAll" goes through both investement lists, and prints their information.


<-------------------------Limitations--------------------------> 

One of the biggest current limitations is not being able to convert some strings to ints/float. For instance, input: 10a would throw an error as "invalid input" and not convert to "10".
Another limitation is for_each loop for stocks goes before for_each loop for mutualFund. meaning, if by chance, stock and mutual fund have the same symbol (which they shouldn't, according to the prof), only stock would be detected, and not both. Some functions do not immediatly return after finding said element, such as getGain, and they function as intended, but function that do like Sell, will have some problems.


<-------------------------Compiling and Running--------------------------> 

(assuming the ukaparyk_a1.rar file has been un-archived and you are currently in directory ukaparyk_a1)
(example: 
	pwdir: (userID/Desktop/CIS2430)/ukaparyk_a1; 
	ls: ePortfolio README.md javadocs.resources
)

  > javac ePortfolio/*.java 
  > java ePortfolio/Main


<-------------------------Test Plan (Outline)--------------------------> 
Testplan outline for main()
	Initializes Portfolio and Scanner objects to work with.
	Repeatedly calls for userInput to enter a menu option until "quit" is called. 
	Available options are (case insensitive):
		b, buy for "buy()"
		s, sell for "sell()"
		u, upd, update for "update()"
		g, gg, getgain for "getGain()"
		sr, search for "search()"
		p, print, printall for "printAll()"
		q, quit to exit the loop and exit the program.
	If the input is not one of the afformentioned commands, throws an error and re-asks for input.

Testplan for buy()
	Asks user to enter the investement type (stock of mutual fund), and re-asks them until the answer is valid.
	Valid inputs are:
		s, stock for Stocks
		m, mf, mutualfund for mutual fund
	Asks user for input on the symbol. If isBlank, asks user to re-enter the value until is valid. 
	Asks the user for the quantity. if isBlank or not parse-able to int, re-ask user for input, until valid.
	Asks user for price, if isBlank or is not parse-able, re-ask user for input until is valid. 
	Depending on the investementType, call byStock() or buyMF();
	(The only difference between the 2 is which list it gets added to)
	Loops through the investenty type list, checking if the desired symbol exists. If so, modify the vallues accordingly. 
	If not, promp the user for a name. if left blank, re-prompt until is valid. 
	Add the newly constructed investement to the list.

Testplan for sell():
	Prompts user for the symbol. If left blank, re-prompts for input until valid. 
	Checks if the symbol exists in the investement list. 
	If so, prompts user for quantity and price to sell. If left blank or not parse-able, re-prompts user for input until valid. 
	Displays payment recieved and overall gain for the sold stock. Updates investement quantity,price and bookvalue.
	If the investemnet with a given symbol DNE, or list is empty, throws an error.

Testplan for update():
	Loops through both investement lists, prompting user for input. If input is blank or is not parse-able, re-prompt until valid; 
	Updates the price of the investement and displays the updated info. 
	If list is empty, throws an error. 

Testplan for getGain():
	initializes a variable ouside the loops.
	Loops through both lists, gettignthe info. Displays info. Calculates the gain for each individual investement, and displays it. Add said gain value4 to the variable outside the loop. 
	Once loops finish, displays overall gain for all investemetns.
	If the lists are empty, shows gain of 0, and throws an error. 

Testplan outline for: search()
	Sets some booleans to dedault to true (inputs are given by default).
	Ask for symbol, and checks if the symbol is empty. If yes, sets boolean symbolExists to false
	Asks for name, if isBlank, set nameExists to false
	If not, split the input into words with " " as regex, store into string array.
	
	Ask user for price range, ifBlank, set priceRangeExists to false
	Find index of "-", if used in the input. If it wasnt used, assume that "precise" input was used, attempt to parse to float. If failed, ask user to re-enter.
	Otherwise, check if the position of "-" is 0, as in "-number". If so, keep lowerbound as neg inf, attempt to parse "everything after the dash" into the float upper bound. if failed, ask to re-enter the value.
	Otherwise, check if position is the last in the string, as in "number-". if so, keep upper bound as positive infinity, and attempt to set lower bound as the "everything before the dash". If failed, ask user for input. 
	Otherwise, (therefore dash is somewhere in the middle, since we got this far), attempt to parse to float "everything before the dash" and "everything after the dash" into lower bound and upper bound respectively. If failed, ask for a re-enter.
	
	By now, search conditions were set, proceeds to searching through the lists using loops. 
	If symbolExists, nameExists, or priceRange exists were set to false, ignore that condition and search only via other ones. If a mismatch happens (i.e. symbol "AAPL" vs "APPL"), continue the loop, discarding that particular invetement, moving onto the next one. 
	Mismatch can occur as: symbol != Investement.symbol, (price is larger than upper bound or price is lower than lower bound), or keyword is missing.
	
	While checking for a keyword, split the name of the stock into String array with regex " ". create nested for loop which goes "number of keywords" x "number of words in Name". If a match between keyword and term in name is found, set boolean termFound to true, and break inner loop. In outer loop check if said boolean is false. If not, continue the loop on next keyword. If boolean is indeed false, means keyworm wasn't matched to anything in the name, meaning tempName failed to be mentioned, meaning investement doesn't match.

	If the investement wasn't discarded at any moment, means it passed all search conditions, means investement matches the requirement. display the stock information. 

	If all fields we left empty, and all booleans set to false, throw an error "can't search with no terms"
	If no investement matches the search requirements, or the list is empty, throw an error "no investements found"


<-------------------------Possible Improvements--------------------------> 

One of the functions that can be improved upon is "sell". Right now, the program asks the user for the input on symbol, price and qunatity in the beggining, but only then checks if said symbol exists. It will still throw an error if the symbol doesn't exists, but it would be more efficent and more user-friendly to:
 - ask for symbol, check if investement with such symbol exists, if so, then aks for new price and quantity
 - if price is not given(field left blank), assume we are using same price as current
 - and, if the investement with a matching symbol is not found, throw an error, instead of aksing for more inputs and onlythen throwingan error

Another function that can be improved on is "update". as of now, user needs to enter each price manually. My idea is:
 - if input field is left blank, keep the price as the original,

Last function to improve on is "search". As of now, it only searches for the exact case-insenstitive matches, which includes punctuation. So, "apple" and "Apple" would be considered a match, but "Inc" and "Inc." not. 
 - Plan on implementing where the program ignores punctuation and checks the words, and hopefully "Inc" and "Inc." match.  


 <-------------------------Test Plan Examples--------------------------> 
 
Program promts will be represented as (Prompt) and user inputs will be represented as (> input)
Comments will be represented via [comment]
Some noticeable abbreviations: 
 - "Enter the command (available commands are buy, sell, update, getGain, search, printAll, quit): " == Menu:
 - "\n" - *blank*

-> Testing buy():
	-> Testing buy() stock: 
		-> Testing buy() stock properly:
Menu:
> buy
Running buy() method. 
Enter the kind of investment(stock or mutualfund): 
> stock
Enter a symbol for the Investement: 
> AAPL
Enter the quantity to buy: 
> 500
Enter the price of the investement to buy: 
> 142.23
[assuming this is a new stock]
Enter the name of the stock:
> Apple Inc.
Bought AAPL Apple Inc.: 500 shares for $142.23 each. Total Book Value is: $71124.99
Menu: ...

	->Testing buy()
		->Testing buy() stock error checking:
[Assuming no AAPL stock was bought beforehand, running first time]
Menu:
> by
Invalid input, try again.
> b
Running buy() method.
Enter the kind of investment(stock or mutualfund): 
> stoc
Not an investement type, try again.
> s
Enter a symbol for the Invesetement:
> *blank*
Invalid symbol, try again.
>         AAPL
Enter the quantity to buy:
> 100ab
Invalid input, try again.
> 100.5
Invalid input, try again.
> 500
Enter the price of the investement to buy:
> *blank*
Invalid price, try again.
> 100ab
Invalid price, try again.
> 142.23
Enter the name of the stock:
> *blank*
Invalid name, try again.
> Apple Inc.
Bought AAPL Apple Inc.: 500 shares for $142.23 each. Total Book Value is: $71124.99
Menu:
...	
	-> testing buy() mutualFund
		-> testing buy() mutualFund proper:
Menu:
> buy
Enter the kind of Investment(stock or mutualfund):
> mutualfund
Enter a symbol for the Investement:
> SSETX
Enter the quantity to buy:
> 450
Enter the price of the investement to buy:
> 42.21
Enter the name of the mutual Fund:
> BNY Mellon Growth Fund Class I
Bought SSETX BNY Mellon Growth Fund Class I: 450 shares for $42.21 each. Total Book Value is: $18994.50
Menu:...
	-> testing buy() mutualFund
		->testinf buy() mutualFund error checking:
[Assuming no SSETX mutual fund was bought beforehand, running first time]
Menu:
> b
Running buy() method.
Enter the kind of investment(stock or mutualfund): 
> mutfund
Not an investement type, try again.
> m
Enter a symbol for the Invesetement:
> *blank*
Invalid symbol, try again.
>         SSETX
Enter the quantity to buy:
> 100ab
Invalid input, try again.
> 100.5
Invalid input, try again.
> 450
Enter the price of the investement to buy:
> *blank*
Invalid price, try again.
> 100ab
Invalid price, try again.
> 42.21
Enter the name of the mutual fund:
> *blank*
Invalid name, try again.
> BNY Mellon Growth Fund Class I
Bought SSETX BNY Mellon Growth Fund Class I: 450 shares for $42.21 each. Total Book Value is: $18994.50
Menu:
...	

-> Testing sell()
	-> Testing sell() properly
[Assuming that AAPL Apple Inc.: 500 shares @ 142.23 stock and 									]
[SSETX BNY Mellon Growth Fund Class I: 450 units @ 42.21 mutual funds were already in the system]
Menu:
> sell
Running sell() method.
Enter the symbol:
> AAPL
Enter the quantity of AAPL to sell:
> 200
Enter the price: 
> 153.46
Successfully sold 200 of AAPL Apple Inc. for 153.46 each.
Total payment: $30682.012, with net gain of $2232.02.
Current status of AAPL Apple Inc.: 
        Quantity: 300
        Price: 153.46
        Book Value: $42675.00
Menu: ...
	-> Testing sell() error checking
[Assuming that AAPL Apple Inc.: 500 shares @ 142.23 stock and 									]
[SSETX BNY Mellon Growth Fund Class I: 450 units @ 42.21 mutual funds were already in the system]
Menu:
> sel
Invalid input, try again.
> s
Running sell() method.
Enter the symbol: 
> *blank*
Invalid symbol, try again.
> aapl
Enter the quantity to sell:
> 200ab
Invalid quantity, try again.
> 200
Enter the price:
> 1.24as
Invalid input, try again.
> 153.46
Successfully sold 200 of AAPL Apple Inc. for 153.46 each.
Total payment: $30682.012, with net gain of $2232.02.
Current status of AAPL Apple Inc.: 
        Quantity: 300
        Price: 153.46
        Book Value: $42675.00
Menu: ...
	
	-> Testing sell() list empty
[No investements were bought]
Running sell() method.
Enter the symbol: 
> AAPL
No stock nor mutual fund with symbol AAPL was found. Cannot sell.
Menu: ...


-> Testing update()
	-> Testing update() properly
[Assuming that AAPL Apple Inc.: 500 shares @ 142.23 stock and 									]
[SSETX BNY Mellon Growth Fund Class I: 450 units @ 42.21 mutual funds were already in the system]

Menu: 
> update
Running update().
Current price of AAPL Apple Inc. is $142.23
Enter the new price:
> 153.25
Updated Investment: AAPL
Name: Apple Inc.
Price: 153.25
Quantity: 500
Book value: 71124.99
Current price of SSETX BNY Mellon Growth Fund Class I is $42.21.
Enter the new price: 
> 53.55
Updated Investment: SSETX
Name: BNY Mellon Growth Fund Class I
Price: 53.55
Quantity: 450
Book value: 18994.5
Menu: ...

-> Testing update()
	-> Testing update() error checking
[Assuming that AAPL Apple Inc.: 500 shares @ 142.23 stock and 									]
[SSETX BNY Mellon Growth Fund Class I: 450 units @ 42.21 mutual funds were already in the system]
Menu:
> up
Invalid input, try again.
> upd
Running update().
Current price of AAPL Apple Inc. is $142.23
Enter the new price:
> *blank*
Invalid price, try again. 
> 100a
Invalid price, try again.
> 153.25
Updated Investment: AAPL
Name: Apple Inc.
Price: 153.25
Quantity: 500
Book value: 71124.99
Current price of SSETX BNY Mellon Growth Fund Class I is $42.21.
Enter the new price: 
> 53.55
Updated Investment: SSETX
Name: BNY Mellon Growth Fund Class I
Price: 53.55
Quantity: 450
Book value: 18994.5
Menu: ...

-> Testing update()
	-> Testing update() with empty lists
[Assume no investements were bought beforehand]
Menu: 
> u
Running update().
No stock of mutual fund has been purchased. Cannot update prices.
Menu...


-> Testing getGain()
	-> Testing getGain() properly
[Assuming that AAPL Apple Inc.: 500 shares @ 142.23 stock and 									]
[SSETX BNY Mellon Growth Fund Class I: 450 units @ 42.21 mutual funds were already in the system]
[Assume that update() has run, updating prices to 153.25 and 41.00 repsectively					]
Menu:
>getGain
Running getGain().
Gain for AAPL Apple Inc. is $5490.02.
Gain for SSETX BNY Mellon Growth Fund Class I is $-589.50.
Overall gain: $4900.52
Menu:...

-> Testing getGain()
	-> Testing getGain() with empty list;
[Assume no investements were bought beforehand, list is empty]
Menu:
> getG
Invalid Input, try again.
> gg
Running getGain().
Overall gain: $0.00
No stock of mutual fund has been purchased. Cannot get gains.
Menu:...