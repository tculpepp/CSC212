import java.util.Scanner;

/**
 * The IncomeTaxCalculator implements an application
 * that collects annual taxable income from the user and uses 
 * it to find the applicable tax rate then calculates the annual 
 * income tax for the income given.
 * @author tculpepp
 *
 */
public class IncomeTaxCalculator {

	static Scanner sc = new Scanner(System.in); //load the scanner, we'll need it later
	
	public static void main(String[] args) {

		boolean run; //boolean to either run again or exit the loop.  true=run again / false=exit
		do {
			run = userInteraction();	
		}while (run);
	}//end main method
	
	private static boolean userInteraction() {
		System.out.print("Enter your annual taxable income: $");
		float taxableIncome = validateUserInputFloat(); //call the method to capture and validate the entry then assign it
		float taxRate = taxRateSearch(taxableIncome); //pass the taxableIncome to taxRateSearch method to return the taxRate
		float incomeTax = taxableIncome * taxRate; //a little math to calculate the incomeTax value
		System.out.println("Your tax rate for this income is: "+(taxRate*100)+"%"); //we convert a decimal to a percent to make it more readable
		System.out.println("Income tax due this year: $"+(String.format("%,.2f", incomeTax))); //format the incomeTax to be more readable ("%,.2f" inserts commas and 2 decimal places)
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
	
	private static float taxRateSearch(float taxableIncome) {
		//initialize an array of TaxBracket objects  for each bracket given.  For the final bracket (everything > X) input 0 for the high limit.  See TaxBracket class for details
		TaxBracket taxBrackets[]= new TaxBracket[6];
		taxBrackets[0] = new TaxBracket(0, 8500, 0.10f); //0–$8,500: 10%
		taxBrackets[1] = new TaxBracket(8500, 34500, 0.15f); //$8,500–$34,500: 15%
		taxBrackets[2] = new TaxBracket(34500, 83600, 0.25f); //$34,500–$83,600: 25%
		taxBrackets[3] = new TaxBracket(83600, 174400, 0.28f); //$83,600–$174,400: 28%
		taxBrackets[4] = new TaxBracket(174400, 379150, 0.33f); //$174,400–$379,150: 33%
		taxBrackets[5] = new TaxBracket(379150, 0, 0.35f); //$379,150 and above: 35% (note the 0 for the high limit)
		
		float rate=0.0f; //variable to hold our taxRate for return
		//This loop iterates through the array matching the taxableIncome to a TaxBracket then then returns the rate from the matching bracket. If no match found, it returns 0.0
		for (TaxBracket tb : taxBrackets) {
			if (taxableIncome >= tb.low && (taxableIncome <= tb.high || tb.high==0)) {
				rate = tb.rate;
				break;
			}
		}
		return rate;
	} //end taxRateSearch method
} //end IncomeTaxCalculator class

class TaxBracket {
	int low; //low limit of the tax bracket
	int high; //high limit of the tax bracket
	float rate; //tax rate as a decimal

	public TaxBracket (int low, int high, float rate) {
		this.low = low;
		this.high = high;
		this.rate = rate;
	}
}//end TaxBracket class