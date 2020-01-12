/**
 * The CalculatePropertyTaxInput implements an application
 * that collects input from the user and uses it to 
 * calculate a property tax amount
 * @author Tom Culpepper
 *
 */
import java.util.Scanner;

public class CalcPropertyTaxInput 
{
	public static void main(String[] args)
	{
		//a whole bunch of stuff I stole for user input examples
		Scanner sc = new Scanner(System.in);
		System.out.print("What is your name?: ");
		String name = sc.nextLine();
		System.out.print("What is your gender?: ");
		char gender = sc.next().charAt(0);
		System.out.print("What is your age?: ");
		int age = sc.nextInt(); 
        long mobileNo = sc.nextLong(); 
        double cgpa = sc.nextDouble();
        
        System.out.println("Name: "+name); 
        System.out.println("Gender: "+gender); 
        System.out.println("Age: "+age); 
        System.out.println("Mobile Number: "+mobileNo); 
        System.out.println("CGPA: "+cgpa);
        
        sc.close();
	}
}
