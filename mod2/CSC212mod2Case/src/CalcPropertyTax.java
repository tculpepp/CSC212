/**
 * The CalcPropertyTax implements an application
 * that collects input from the user and uses it to 
 * calculate a property tax amount
 * @author Tom Culpepper
 * 
 * Pseudocode:
 * -import scanner
 * -request and get hoseValue and taxRate (validate inputs)
 * -calculate property tax
 * -print propertyTax
 */
import java.util.Scanner;

public class CalcPropertyTax {

	static Scanner sc = new Scanner(System.in); //load the scanner
	
	public static void main(String[] args) {
		System.out.print("Enter the property value of the house or 0 to exit: $");
		float houseValue = getUserInputFloat(); //call the method to capture and validate the entry then assign it
		if (houseValue==0) {
			System.exit(0);
		}
		System.out.print("Enter the property tax rate percentage (%): "); 
		float taxRate = (getUserInputFloat()/100); //validate the input then convert the percentage to a decimal
		float propertyTax = houseValue * taxRate; //calculate the property tax
		System.out.println("Property tax due this year: $"+(String.format("%,.2f", propertyTax)));//format for readability ("%,.2f" inserts commas & 2 decimal places)
	}
		
	//a method to get and validate user input is a float
	static float getUserInputFloat() {
		while (!sc.hasNextFloat()) { //check to see if anything other than a float was entered
			System.out.print("Please enter just a number: ");
			sc.next();
		}
		return sc.nextFloat(); //return the validated number to the calling method
	}
}
