/**
 * The CalcPropertyTax implements an application
 * that collects input from the user and uses it to 
 * calculate a property tax amount
 * @author Tom Culpepper
 * 
 * Pseudocode:
 * -import scanner
 * -start userInteraction loop
 * -request and get hoseValue and taxRate (validate inputs)
 * -use getPropertytax method to get propertyTax (houseValue * taxRate)
 * -print propertyTax
 * -ask user: run again or exit? (validate input)
 * -start over or end
 * 
 */
import java.util.Scanner;

public class CalcPropertyTax {

	static Scanner sc = new Scanner(System.in); //load the scanner
	
	public static void main(String[] args) {
		boolean run; //boolean to either run again or exit the loop.  true=run again / false=exit
		do {
			run = userInteraction();	
		}while (run);
	}//end main method
	
	//a method to interact with the user.  All strings that are not data validation are here.
	private static boolean userInteraction() {
		System.out.print("Enter the deisred home value: $");
		float houseValue = getUserInputFloat(); //call the method to capture and validate the entry then assign it
		System.out.print("Enter the current property tax rate percentage (%): "); 
		float taxRate = (getUserInputFloat()/100); //validate the input then convert the percentage to a decimal
		float propertyTax = getPropertyTax(houseValue, taxRate);
		System.out.println("Property tax due this year: $"+(String.format("%,.2f", propertyTax)));//format for readability ("%,.2f" inserts commas & 2 decimal places)
		System.out.print("Would you like to calculate another tax value? (y/n): ");	
		boolean runAgain = getRunAgain(); //get the user's input to go again or exit
		System.out.print(runAgain ? "\n" : "*** Program Closed ***"); //either skip a line before starting over or let the user know we exited
		return runAgain;//return the user's selection 
	}
	
	//a method to get and validate user input is a float
	private static float getUserInputFloat() {
		while (!sc.hasNextFloat()) { //check to see if anything other than a float was entered
			System.out.print("Please enter just a number: ");
			sc.next();
		}
		return sc.nextFloat(); //return the validated number to the calling method
	}
	
	//a method to get and validate the input and convert to a boolean
	private static boolean getRunAgain() {
		char runAgain = getScannerChar();
		while (runAgain !='y' && runAgain !='n') { //check to see if anything other than y or n was entered
			System.out.print("Please enter y or n: ");
			runAgain = getScannerChar(); //update the variable and check again
		}
		return runAgain == 'y' ? true : false; //now that we are clean, return the appropriate boolean
	}
	
	//a method to convert scanner string to a single lower case char
	private static char getScannerChar() {
		return Character.toLowerCase((sc.next().charAt(0)));
	}
	
	//a method to calculate and return the property tax amount
	private static float getPropertyTax(float houseValue, float taxRate) {
		return houseValue * taxRate;
	}
}
