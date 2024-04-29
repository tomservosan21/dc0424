package djc;

// Class for storing main tool data
public class Tool {
	
	// Default constructor
	public Tool()
	{
		toolCode = null;
		toolType = null;
		brand = null;
	}
	
	// Non-default constructor
	public Tool(
		String toolCode,
		ToolType toolType,
		String brand,
		String toolTypeDisplay)
	{
		this.toolCode = toolCode;
		this.toolType = toolType;
		this.brand = brand;
	}
	
	// Instance variables for Tool information
	// as described in ToolRentalDesign.pdf
	private String toolCode;
	private ToolType toolType;
	private String brand;

	// Getters and setters
	public String getToolCode() {
		return toolCode;
	}

	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public ToolType getToolType() {
		return toolType;
	}

	public void setToolType(ToolType toolType) {
		this.toolType = toolType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
