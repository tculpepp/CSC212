/**
 * The CalculatePropertyTaxInput implements an application
 * that collects input from the user and uses it to 
 * calculate a property tax amount
 * @author Tom Culpepper
 *
 */
import java.util.Scanner; //import the scanner class so we can get keyboard input

public class CalcPropertyTaxInput 
{
	static Scanner sc = new Scanner(System.in); //scanner implemented as part of the class to be able to make it static and available throughout
	public static void main(String[] args)
	{
		boolean run = true; //set the trigger to run again or exit
		while (run)
			run = getUserInput();
	}

	public static boolean getUserInput() //all user input is in here (unless it's a validation error then it's in 'validateUserInput')
	{
		System.out.print("Enter the deisred home value: $");
		float houseValue = validateUserInput('n'); //call the method to capture and validate the entry then assign it
		System.out.print("Enter the current property tax rate (%): "); 
		float taxRate = (validateUserInput('n')/100); //validate the input then convert the percentage to a decimal
		float propertyTax = houseValue * taxRate; //a little math to get the info we need
		System.out.println("Property tax due this year: $"+(String.format("%,.2f", propertyTax)));
		System.out.print("Would you like to calculate another tax value? (y/n): ");	
		return (validateUserInput('c') == 1); //return the user's option to run again or not
	}
	
	public static float validateUserInput(char type) //available types are n = numeric and t = text
	{
		if (type == 'n'){ //type 'n' = number and 'c'=char
			while (!sc.hasNextFloat()) { //check to see if anything other than a float was entered
				System.out.print("Please enter just a number: ");
				sc.next();
			}
			return sc.nextFloat(); //return the validated number to the calling method
		}
		else if (type == 'c') { //if the expected type is char, we work here
			char option = Character.toLowerCase((sc.next().charAt(0))); //set the 'option' variable converting from a string to a lowercase char
			while (option !='y' && option !='n') { //check to see if anything other than y or n was entered
				System.out.print("Please enter y or n: ");
				option = Character.toLowerCase((sc.next().charAt(0))); //update the variable and check again
			}
			if (option == 'y') { //if y then give some space and pass the restart variable back to the top
				System.out.print("\n\n");
				return 1;
			}
			else {  //if n then let the user know and exit
				System.out.println("Exiting Program...");
				return 0;
			}
		}
		else {//if somehow we get something in 'type' other than 'n' or 't' then handle it gracefully
			System.out.println("something went wrong, please try again");
			return 1;
		} //end if else
	} //end validateUserInput method
}

