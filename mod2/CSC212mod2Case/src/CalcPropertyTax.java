/**
 * The CalcPropertyTax implements an application
 * that collects input from the user and uses it to 
 * calculate a property tax amount
 * @author Tom Culpepper
 * 
 * Pseudocode:
 * -import the scanner from util
 * -set while loop in main to loop through userInteraction method
 * -userInteraction method outputs strings and captures inputs using scanner.  (All inputs passed to validating methods then assigned to variables.)
 *  requests ask for homeValue and taxRate then assign propertyTax to be homeValue * taxRate.
 * -homeValue and taxRate are validated with validateUserInputFloat method to ensure only numbers are entered
 * -print propertyTax
 * -ask the user if they want to do another
 * -validate the Y or N with validateRunAgain which returns it as a boolean
 * -userInteraction does some interface housekeeping then returns the runAgain boolean to main
 * -loop through again or say goodbye and exit depending on user boolean
 */
import java.util.Scanner;

public class CalcPropertyTax {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean run; //boolean to either run again or exit the loop.  true=run again / false=exit
		do {
			run = userInteraction();	
		}while (run);
	}//end main method
	
	private static boolean userInteraction() {
		System.out.print("Enter the deisred home value: $");
		float houseValue = validateUserInputFloat(); //call the method to capture and validate the entry then assign it
		System.out.print("Enter the current property tax rate percentage (%): "); 
		float taxRate = (validateUserInputFloat()/100); //validate the input then convert the percentage to a decimal
		float propertyTax = houseValue*taxRate;
		System.out.println("Property tax due this year: $"+(String.format("%,.2f", propertyTax)));
		System.out.print("\nWould you like to calculate another tax value? (y/n): ");	
		boolean runAgain = (validateRunAgain(Character.toLowerCase((sc.next().charAt(0))))); //convert the input string to a single char and pass it to validation which will return a boolean
		System.out.print(runAgain ? "\n" : "*** Program Closed ***"); //either skip a line before starting over or let the user know we exited
		return runAgain;//return the user's selection 
	} //end userInteraction method
	
	private static float validateUserInputFloat() {
		while (!sc.hasNextFloat()) { //check to see if anything other than a float was entered
			System.out.print("Please enter just a number: ");
			sc.next();
		}
		return sc.nextFloat(); //return the validated number to the calling method
	} //end validateUserInputFloat method
	
	private static boolean validateRunAgain(char runAgainChar) {
		while (runAgainChar !='y' && runAgainChar !='n') { //check to see if anything other than y or n was entered
			System.out.print("Please enter y or n: ");
			runAgainChar = Character.toLowerCase((sc.next().charAt(0))); //update the variable and check again
		}
		return runAgainChar == 'y' ? true : false; //now that we are clean, return the appropriate boolean
	} //end validateRunAgain method
}
