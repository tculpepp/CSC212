/**
 * The CalculatePropertyTax implements an application
 * that calculates property tax
 * @author Tom Culpepper
 *
 */
public class CalculatePropertyTax 
{
	public static void main(String[] args)
	{
		float houseValue = 750_000; //house value in US Dollars
		float taxRate = (float)0.02; //current tax rate as a decimal
		float propertyTax = houseValue * taxRate;
		
		//The "%,.2f" formats the number string with commas and two decimal places for readability
		System.out.print("Assessed current home value: $"+(String.format("%,.2f", houseValue))+"\n"
						+"Current property tax rate: "+(taxRate*100)+"%\n"
						+"Property tax due this year: $"+(String.format("%,.2f", propertyTax)));
	}
	

}