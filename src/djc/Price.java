package djc;

// Class for storing detailed price data about a tool
public class Price {
	
	// Default constructor
	public Price()
	{
		toolType = null;
		dailyCharge = 0.0;
		weekdayCharge = false;
		weekendCharge = false;
		holidayCharge = false;
	}

	// Non-default constructor
	public Price(
		ToolType toolType,
		double dailyCharge,
		boolean weekdayCharge,
		boolean weekendCharge,	 
		boolean holidayCharge)
	{
		this.toolType = toolType;
		this.dailyCharge = dailyCharge;
		this.weekdayCharge = weekdayCharge;
		this.weekendCharge = weekendCharge;
		this.holidayCharge = holidayCharge;
	}
	
	// Instance variables for price information
	// as described in ToolRentalDesign.pdf
	private ToolType toolType;
	private double dailyCharge;
	private boolean weekdayCharge;
	private boolean weekendCharge;
	private boolean holidayCharge;
	
	// Getters and setters
	public ToolType getToolType() {
		return toolType;
	}

	public void setToolType(ToolType toolType) {
		this.toolType = toolType;
	}

	public double getDailyCharge() {
		return dailyCharge;
	}

	public void setDailyCharge(double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	public boolean isWeekdayCharge() {
		return weekdayCharge;
	}

	public void setWeekdayCharge(boolean weekdayCharge) {
		this.weekdayCharge = weekdayCharge;
	}

	public boolean isWeekendCharge() {
		return weekendCharge;
	}

	public void setWeekendCharge(boolean weekendCharge) {
		this.weekendCharge = weekendCharge;
	}

	public boolean isHolidayCharge() {
		return holidayCharge;
	}

	public void setHolidayCharge(boolean holidayCharge) {
		this.holidayCharge = holidayCharge;
	}
	
	
}
