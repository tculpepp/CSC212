/**
 * The IncomeTaxCalculator implements an application
 * that collects annual taxable income from the user and uses 
 * it to find the applicable tax rate then calculates the annual 
 * income tax for the income given.
 * @author tculpepp
 * 
 * Pseudocode:
 * -import scanner
 * -initialize scanner
 * -get taxable income (validate input)
 * -find correct tax rate
 * -calculate tax amount
 * -print tax amount 
 */
import java.util.Scanner;

public class IncomeTaxCalculator {
	
	public static void main(String[] args) {
		System.out.print("Enter your annual taxable income: $");
		Scanner sc = new Scanner(System.in); //load the scanner,
		while (!sc.hasNextFloat()) { //check to see if anything other than a float is in scanner
			System.out.print("Please enter just a number: ");
			sc.next();
		}
		float taxableIncome = sc.nextFloat(); //call the method to capture and validate the entry then assign it
		sc.close();
		float taxRate = 0f; //tax rate as a decimal
		if (taxableIncome >= 0 && taxableIncome <= 8500) { //start stepping through tax brackets to find the rate
			taxRate = 0.1f;
		}
		else if (taxableIncome >= 8500 && taxableIncome <= 34500) {
			taxRate = 0.15f;
		}
		else if (taxableIncome >= 34500 && taxableIncome <= 83600) {
			taxRate = 0.25f;
		}
		else if (taxableIncome >= 83600 && taxableIncome <= 174400) {
			taxRate = 0.28f;
		}
		else if (taxableIncome >= 174400 && taxableIncome <= 379150) {
			taxRate = 0.33f;
		}
		else if (taxableIncome >= 379150) {
			taxRate = 0.35f;
		}
		if (taxRate==0) {
			System.out.println("Something has gone wrong, please try again");
			System.exit(1);
		}
		float incomeTax = (taxableIncome * taxRate); //calculate the income tax
		System.out.println("Income tax due this year: $"+(String.format("%,.2f", incomeTax))); //format for readability ("%,.2f" inserts commas & 2 decimal places)
	}
}