package djc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

// This class contains the entry point for this program as well as functions to interact with the user.
public class ToolRentalMain {
	
	private static CheckoutInfo checkoutInfo = new CheckoutInfo();
	
	private static String toolCode = "0";           // Tool Code as entered by the user
	private static String dayCount = "0";           // Day count as entered by the user
	private static String discountPercent = "0";    // Discount percentage as entered by the user
	private static int discountPercentInt = 0;      // Discount percentage as an integer
	private static LocalDate checkOutDate = null;   // Checkout date as entered by the user
	private static int dayCountInt = 0;             // Day count as an integer
	
	// Nicely display the tool and price options for the user.
	private static void printOptions()
	{
		ArrayList<Price> prices = checkoutInfo.getPrices();
		ArrayList<Tool> tools = checkoutInfo.getTools();
		
		// Print out the tools
		for(Tool tool : tools)
		{
			System.out.println(
				"Tool Code: " +
				tool.getToolCode() + "\t\tToolType: " +
				ToolRentalUtil.getToolTypeDisplay(tool.getToolType()) +
				"\tBrand: " +
				tool.getBrand());
		}
		
		System.out.println();
		
		// Print out the prices
		for(int i = 0; i < prices.size(); i++)
		{
			Price price = prices.get(i);
			
			System.out.println(
				"Tool Type: " +
				ToolRentalUtil.getToolTypeDisplay(price.getToolType()) + "\t" +
				" Daily Charge: " +
				price.getDailyCharge() + "\t" +
				" Weekday Charge? " +
				(price.isWeekdayCharge() ? "Yes" : "No") + "\t" +
				" Weekend Charge? " +
				(price.isWeekendCharge() ? "Yes" : "No") + "\t" +
				" Holiday Charge? " +
				(price.isHolidayCharge() ? "Yes" : "No"));
		}
		
		System.out.println();
	}
	
	// Get the day count entered by the user.
	// Note the date is validated by this function.
	private static void getDayCount(BufferedReader reader)
	{
		boolean keepLooping = true;		// Indicates if we keep going
		
		while(keepLooping)
		{	
			try
			{
				// Get the data from the user
				
				System.out.println("Please enter the Rental day count: ");
				
				dayCount = reader.readLine();
				dayCount = dayCount.trim();
				
				try
				{
					dayCountInt = Integer.parseInt(dayCount);
				}
				catch(Exception ex)
				{
					throw new Exception("Sorry, day count is not valid!");
				}
				
				if(dayCountInt < 1)
				{
					throw new Exception("Sorry, day count must be 1 or greater!");
				}
				else
				{
					// This is valid, so we can stop looping
					System.out.println("Entered Day Count " + dayCount);
					System.out.println();
					keepLooping = false;
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
				System.out.println();
			}
		}
	}
	
	// Get the discount percent entered by the user.
	// Convert the discount percent entered by the user from a string to an integer.
	private static void getDiscountPercent(BufferedReader reader)
	{
		boolean keepLooping = true;    // Indicates if we keep going
		
		while(keepLooping)
		{	
			try
			{
				// Get the discount percent as a string and an integer.
				
				System.out.println("Please enter the Discount percent: ");
				
				discountPercent = reader.readLine();
				discountPercent = discountPercent.trim();
				
				discountPercentInt = 0;
				
				try
				{
					discountPercentInt = Integer.parseInt(discountPercent);
				}
				catch(Exception ex)
				{
					throw new Exception("Sorry, discount percent is not valid!");
				}
				
				if(discountPercentInt < 0 || discountPercentInt > 100)
				{
					throw new Exception("Sorry, discount percent must be between zero and 100!");
				}
				else
				{
					// This is valid, so we can stop looping
					System.out.println("Entered Discount percent: " + discountPercent);
					keepLooping = false;
					System.out.println();
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
				System.out.println();
			}
		}
	}
	
	// Get the checkout date entered by the user.
	// Convert the discount percent entered by the user from a string to a date object.
	private static void getCheckoutDate(BufferedReader reader)
	{
		boolean keepLooping = true;
		
		while(keepLooping)
		{	
			try
			{
				// Get the checkout date as a date object.
				
				System.out.println("Please enter the Checkout date: ");
				
				String checkoutDateStr = reader.readLine();
				checkoutDateStr = checkoutDateStr.trim();
				
				// For example: String string = "January 2, 2010";
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
				LocalDate date = LocalDate.parse(checkoutDateStr, formatter);
				checkOutDate = date;
				System.out.println(checkOutDate.toString());
				keepLooping = false;
			}
			catch(Exception ex)
			{
				System.out.println("Date entered is not valid");
				System.out.println("Example Format: January 2, 2010");
				System.out.println();
			}
		}
	}
	
	// Primary function for getting the options the user and entered
	// and validates those options.
	// The return value is true if we should abort processing, else false is returned.
	private static boolean getOptions(BufferedReader reader) throws Exception
	{ 
		toolCode = "0";	  
		dayCount = "0";
		discountPercent = "0"; 
		checkOutDate = null;
		
		// Get the tool code from the user
		 
		System.out.println();
		System.out.println("Please enter the Tool Code or zero to quit: ");

		// Reading data using readLine
		toolCode = reader.readLine();
		toolCode = toolCode.trim();
		
		// Should we quit?
		if(toolCode.compareTo("0") == 0)
		{
			// Yes, the user indicated to quit.
			return true;
		}	
		
		// Process the tool code
		ArrayList<Tool> tools = checkoutInfo.getTools();
		
		boolean foundToolCode = false;
		
		for(Tool tool : tools)
		{
			if(tool.getToolCode().compareTo(toolCode) == 0)
			{
				System.out.println("Entered Tool Code: " + ToolRentalUtil.prettyFormat(toolCode));
				foundToolCode = true;
				
				System.out.println();
			}
		}
		
		if(!foundToolCode)
		{
			throw new Exception("Sorry, Tool Code is not valid!");
		}
		
		// Get the data from the user.
		getDayCount(reader);
		
		getDiscountPercent(reader);
		
		getCheckoutDate(reader);
		
		System.out.println();
		
		return false;
	}
	
	// Indicates if we should keep running the program.
	private static boolean keepRunning = true;
	
	// Entry point for this program
	public static void main(String args[])
	{
		while(keepRunning)
		{
			try
			{	
				// All values are entered as strings and converted to
				// the appropriate types for the business logic.
				
				BufferedReader reader = new BufferedReader(    
						new InputStreamReader(System.in));
				
				System.out.println("Welcome to DC's Rental Store!");
				System.out.println();
				System.out.println("Here is our product information:");
				System.out.println();
				
				// Print out the user's options
				printOptions();
				
				// Get the user-entered options
				boolean stopRunning = getOptions(reader);
				
				if(stopRunning)
				{
					// A true value for stopRunning indicates stop running.
					keepRunning = false;
					
					System.out.println();
					System.out.println("Thank you for using DC's Rental Store!");
					System.out.println("Please have a wonderful day.");
					
					reader.close();
				}
				else
				{
					// Initialize the rental processor, which contains the main business logic
					// for generating the rental agreement.
					RentalProcessor processor = new RentalProcessor(
						toolCode,
						dayCountInt,
						discountPercent,
						discountPercentInt,
						checkOutDate,
						checkoutInfo);
					
					// Process the data that is part of the rental agreement
					processor.processRental();
					
					// Display the rental agreement
					processor.displayRentalAgreement();
					
					System.out.println();
					System.out.println();
					
					System.out.println();
					System.out.println();
				
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
				System.out.println();
			}
		}
	}
}
