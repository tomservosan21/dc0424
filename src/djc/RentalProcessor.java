package djc;

import java.time.LocalDate;
import java.util.ArrayList;

// Main business logic class
public class RentalProcessor {
	
	// Rental details
	private String toolCode = "0";
	private int dayCountInt = 0;
	private String discountPercent = "0";
	private int discountPercentInt = 0;
	private LocalDate checkoutDate = null;
	private boolean processed = false;
	private CheckoutInfo checkoutInfo;
	private String toolType = null;
	private String brand = null;
	
	private LocalDate dueDate = null;
	private int chargableDays = 0;
	private double prediscountCharge = 0.0;
	private double discountAmt = 0.0;
	private double finalCharge = 0.0;
	
	private double discountAmtExact = 0.0;
	private double finalChargeExact = 0.0;
	
	private Tool foundTool = null;
	private Price foundPrice = null;

	// Non-default constructor
	RentalProcessor(
		String toolCode,
		int dayCountInt,
		String discountPercent,
		int discountPercentInt,
		LocalDate checkoutDate,
		CheckoutInfo checkoutInfo)
	{
		this.toolCode = toolCode;
		this.dayCountInt = dayCountInt;
		this.discountPercent = discountPercent;
		this.discountPercentInt = discountPercentInt;
		this.checkoutDate = checkoutDate;
		this.checkoutInfo = checkoutInfo;
		processed = false;     // Indicates if processRental() has been called on this instance.
	}
	
	
	
	// This function generates the main data used by this application
	// in order to produce the rental agreement for the data entered by the user.
	public void processRental() throws Exception
	{
		processed = true;
		
		// Get the tools and prices.
		ArrayList<Tool> tools = checkoutInfo.getTools();
		ArrayList<Price> prices = checkoutInfo.getPrices();
		
		foundTool = null;
		foundPrice = null;
		
		// Did we find the tool?
		for(Tool tool : tools)
		{
			if(tool.getToolCode().compareTo(toolCode) == 0)
			{
				foundTool = tool;
				break;
			}
		}
		
		if(foundTool == null)
		{
			// This should not happen.
			throw new Exception("Could not find tool!");
		}
		
		// Did we find the price?
		for(Price price : prices)
		{
			if(price.getToolType() == foundTool.getToolType())
			{
				foundPrice = price;
				break;
			}
		}
		
		if(foundPrice == null)
		{
			// This should not happen.
			throw new Exception("Could not find price!");
		}
		
		// OK, the user data looks good so continue processing.
		toolType = foundTool.getToolType().toString();
		brand = foundTool.getBrand();
		
		LocalDate currentDay = checkoutDate;
		
		// dayCountInt is the number of days entered by the user.
		// chargableDays is the number of days the user will be charged for.
		
		// This variable counts the number of days the user will be charged.
		chargableDays = 0;
		
		for(int day = 1; day <= dayCountInt; day++)
		{
			// holiday indicates if this day lies on a holiday
			// isCharge indicates if the user will be charged for this day
			boolean holiday = false;
			boolean isCharge = true;
			
			// Are we July 4th?
			if(ToolRentalUtil.isIndependenceDay(currentDay))
			{
				holiday = true;
			}
			
			// Are we labor day?
			if(ToolRentalUtil.isLaborDay(currentDay))
			{
				holiday = true;
			}
			
			if(holiday)
			{
				// Don't charge on holidays unless the price object indicates to do so.
				if(!foundPrice.isHolidayCharge())
				{
					isCharge = false;
				}

			}
			
			// Only charge for weekdays if the functional specification
			// says to do say for this price object.
			if(ToolRentalUtil.isWeekday(currentDay))
			{
				if(!foundPrice.isWeekdayCharge())
				{
					isCharge = false;
				}
			}
			
			// Only charge for weekends if the functional specification
			// says to do say for this price object.
			if(ToolRentalUtil.isWeekend(currentDay))
			{
				if(!foundPrice.isWeekendCharge())
				{
					isCharge = false;
				}
			}
			
			// Should we charge for this day?
			if(isCharge)
			{
				// This is a day the renter should pay for.
				chargableDays++;
			}
				
			// Ensure the date is valid by using plusDays.
			// Note that this will ensure a valid date
			// and account for leap year.
			currentDay = currentDay.plusDays(1);
		}
		
		// Business logic per the functional specification.
		// currentDay is the last day of the date range entered by the user
		// so we will treat this as the due date for the rental.
		dueDate = currentDay;
		
		// Compute the price of this rental prior to the discount,
		// rounded to the nearest cent.
		long prediscountChargeCents = (long)((double)chargableDays * foundPrice.getDailyCharge() * 100.0 + 0.5);
		
		// Now compute the price in dollars.
		prediscountCharge = (double)(prediscountChargeCents) / 100.0;
		
		// Now compute the exact discount amount.
		discountAmtExact = prediscountCharge * ((double)discountPercentInt / 100.0);
		
		// The exact final charge is the before-discount charge minus the discount.
		finalChargeExact = prediscountCharge - discountAmtExact;
		
		// Now that we have the exact discount and price,
		// round them to the nearest cent.
		
		// This will determine the discount amount in cents that
		// will appear in the rental agreement.
		long discountAmtCents = (long)((double)discountAmtExact * 100.0 + 0.5);
		discountAmt = (double)discountAmtCents / 100.0;
		
		// This is the amount the renter will be charged and
		// will appear in the rental agreement.
		long finalChargeCents = (long)((double)finalChargeExact * 100.0 + 0.5);
		finalCharge = (double)finalChargeCents / 100.0;
	}
	
	// Display the rental agreement values per the functional specification.
	public void displayRentalAgreement() throws Exception
	{
		// Process the rental if not processed yet to avoid errors
		if(!processed)
		{
			processRental();
		}
		
		// Add extra new lines for consistent formatting.
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Rental Agreement:");
		System.out.println();
		System.out.println("Tool Code:");		
		System.out.println(toolCode);
		System.out.println();
		System.out.println("Tool Type:");
		System.out.println(ToolRentalUtil.prettyFormat(toolType));
		System.out.println();
		System.out.println("Brand:");
		System.out.println(brand);
		System.out.println();
		System.out.println("Rental days:");
		System.out.println(dayCountInt);
		System.out.println();
		System.out.println("Check out date:");
		System.out.println(checkoutDate.toString());
		System.out.println();
		System.out.println("Due Date:");
		System.out.println(dueDate.toString());
		System.out.println();
		System.out.println("Daily Rental Charge:");
		System.out.println(foundPrice.getDailyCharge());
		System.out.println();
		System.out.println("Charge Days:");
		System.out.println(chargableDays);
		System.out.println();
		System.out.println("Pre-discount charge:");
		System.out.println(prediscountCharge);
		System.out.println();
		System.out.println("Discount percent:");
		System.out.println(discountPercent);
		System.out.println();
		System.out.println("Discount amount:");
		System.out.println(discountAmt);
		System.out.println();
		System.out.println("Final Charge:");
		System.out.println(finalCharge);
		System.out.println();
		System.out.println("Discount amount exact:");
		System.out.println(discountAmtExact);
		System.out.println();
		System.out.println("Final Charge exact:");
		System.out.println(finalChargeExact);
		System.out.println();
		System.out.println();
	}
	
}
