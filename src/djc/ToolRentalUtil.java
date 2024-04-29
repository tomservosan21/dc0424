package djc;

import java.time.DayOfWeek;
import java.time.LocalDate;

// Contains utility functions that are used by the business logic for this application.
public class ToolRentalUtil {
	
	// Convert the ToolType enum to a String to avoid code duplication.
	public static String getToolTypeDisplay(ToolType type)
	{
		return prettyFormat(type.toString());
	}
	
	// Format a string so that it is properly capitalized.
	public static String prettyFormat(String str)
	{
		String toReturn = str;
		
		if(str.length() > 0)
		{
			String lowerCase = str.toLowerCase();
			
			String firstchar = lowerCase.substring(0, 1).toUpperCase();
			
			String remainingChars = "";
			
			if(str.length() > 1)
			{
				remainingChars = lowerCase.substring(1);
			}
			
			toReturn = firstchar + remainingChars;
		}
		
		return toReturn;
	}
	
	// Is the date provided July 4th?
	public static boolean isIndependenceDay(LocalDate date)
	{
		if(date.getMonthValue() == 7 && date.getDayOfMonth() == 4)
			return true;
		else
			return false;
	}
		
	// Is the date provided Labor day?
	public static boolean isLaborDay(LocalDate date)
	{
		// Are we in September?
		if(date.getMonthValue() != 9)
		{
			return false;
		}
		
		// Is it the first day of the week?
		if(date.getDayOfMonth() > 7)
		{
			return false;
		}
		
		// Is it a Monday?
		DayOfWeek dow = date.getDayOfWeek();
		
		if(dow.toString().compareTo("MONDAY") != 0)
		{
			return false;
		}
		
		// Must be Labor day!
		return true;
	}
	
	// Is the data provided a weekday?
	public static boolean isWeekday(LocalDate date)
	{
		DayOfWeek dow = date.getDayOfWeek();
		
		if(dow.toString() == "MONDAY")
		{
			return true;
		}
		if(dow.toString() == "TUESDAY")
		{
			return true;
		}
		if(dow.toString() == "WEDNESDAY")
		{
			return true;
		}
		if(dow.toString() == "THURSDAY")
		{
			return true;
		}
		if(dow.toString() == "FRIDAY")
		{
			return true;
		}
		
		return false;
	}
	
	// Does the data provided lie on a weekend?
	public static boolean isWeekend(LocalDate date)
	{
		DayOfWeek dow = date.getDayOfWeek();
		
		if(dow.toString() == "SATURDAY")
		{
			return true;
		}
		if(dow.toString() == "SUNDAY")
		{
			return true;
		}
		
		return false;
	}
}
