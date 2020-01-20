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
	static TaxBracket taxBrackets[]= new TaxBracket[6];
	
	public static void main(String[] args) {

		buildTaxBracketArray(); //load all the tax brackets here so we don't re-load for every run
		boolean run; //boolean to either run again or exit the loop.  true=run again / false=exit
		do {
			run = userInteraction();	
		}while (run);
	}
	//a method to interact with the user.  All strings that are not data validation are here.
	private static boolean userInteraction() {
		System.out.print("Enter your annual taxable income: $");
		float taxableIncome = getUserInputFloat(); //call the method to capture and validate the entry then assign it
		float taxRate = getTaxRate(taxableIncome); //pass the taxableIncome to getTaxRate to return the taxRate
		float incomeTax = TaxBracket.getTaxAmount(taxableIncome, taxRate); //a little math to calculate the incomeTax value
		System.out.println("Your tax rate for this income is: "+(taxRate*100)+"%"); //we convert a decimal to a percent to make it more readable
		System.out.println("Income tax due this year: $"+(String.format("%,.2f", incomeTax))); //format for readability ("%,.2f" inserts commas & 2 decimal places)
		System.out.print("Would you like to calculate another tax value? (y/n): ");	
		boolean runAgain = getRunAgain(); //get the user's input to go again or exit
		System.out.print(runAgain ? "\n" : "*** Program Closed ***"); //either skip a line before starting over or let the user know we exited
		return runAgain;//return the user's selection 
	}
	
	//a method to get and validate user input is a float
	private static float getUserInputFloat() {
		while (!sc.hasNextFloat()) { //check to see if anything other than a float is in scanner
			System.out.print("Please enter just a number: ");
			sc.next();
		}
		return sc.nextFloat(); //return the validated number to the calling method
	}
	
	//a method to get and validate the input and convert to a boolean
	private static boolean getRunAgain() {
		char runAgain = Character.toLowerCase((sc.next().charAt(0)));
		while (runAgain !='y' && runAgain !='n') { //check to see if anything other than y or n was entered
			System.out.print("Please enter y or n: ");
			runAgain = Character.toLowerCase((sc.next().charAt(0))); //update the variable and check again
		}
		return runAgain == 'y' ? true : false; //now that we are clean, return the appropriate boolean
	}
	
	//a method to load all our tax brackets into an array. See TaxBrackets class for details
	private static TaxBracket[] buildTaxBracketArray() {
		taxBrackets[0] = new TaxBracket(0, 8500, 0.10f); //0–$8,500: 10%
		taxBrackets[1] = new TaxBracket(8500, 34500, 0.15f); //$8,500–$34,500: 15%
		taxBrackets[2] = new TaxBracket(34500, 83600, 0.25f); //$34,500–$83,600: 25%
		taxBrackets[3] = new TaxBracket(83600, 174400, 0.28f); //$83,600–$174,400: 28%
		taxBrackets[4] = new TaxBracket(174400, 379150, 0.33f); //$174,400–$379,150: 33%
		taxBrackets[5] = new TaxBracket(379150, 0.35f); //$379,150 and above: 35% 
		return taxBrackets;
	}
	
	//a method to find the applicable taxRate based on the income given. Returns 0.0 if no match found.
	private static float getTaxRate(float taxableIncome) {
		float rate=0.0f; //variable to hold our taxRate for return
		for (TaxBracket tb : taxBrackets) {
			if (taxableIncome >= tb.low
					&& (taxableIncome <= tb.high|| tb.high==0)) {
				rate = tb.rate;
				break;
			}
		}
		return rate;
	}
}
/**
 * The TaxBracket class is use to create TaxBracket objects based on the needs 
 * of the assignment. 
 * constructor example: TaxBracket(1000, 12000, 0.10)
 * @author tculpepp
 * 
 */
class TaxBracket {
	int low = 0; //low limit of the tax bracket
	int high = 0; //high limit of the tax bracket
	float rate = 0.0f; //tax rate as a decimal

	public TaxBracket (int low, int high, float rate) {
		this.low = low;
		this.high = high;
		this.rate = rate;
	}
	
	public TaxBracket (int low, float rate) {
		this.low = low;
		this.rate = rate;
	}
	
	//a method to calculate the amount of taxes due
	public static float getTaxAmount (float taxableIncome, float taxRate) {
		float taxAmount = taxableIncome * taxRate;
		return taxAmount;
	}
}