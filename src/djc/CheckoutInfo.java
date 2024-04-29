package djc;

import java.util.ArrayList;

// Primary class for storing the data for Tools and their associated Prices.
public class CheckoutInfo {
	
	// The tools
	private ArrayList<Tool> tools = new ArrayList<Tool>();
	
	// The prices
	private ArrayList<Price> prices = new ArrayList<Price>();
	
	// Default constructor
	public CheckoutInfo()
	{
		tools.add(new Tool("CHNS", ToolType.CHAINSAW, "Stihl", "Chainsaw"));
		tools.add(new Tool("LADW", ToolType.LADDER, "Werner", "Ladder"));
		tools.add(new Tool("JAKD", ToolType.JACKHAMMER, "DeWalt", "Jackhammer"));
		tools.add(new Tool("JAKR", ToolType.JACKHAMMER, "Ridgid", "Jackhammer"));
		
		prices.add(new Price(ToolType.LADDER, 1.99, true, true, false));
		prices.add(new Price(ToolType.CHAINSAW, 1.49, true, false, true));
		prices.add(new Price(ToolType.JACKHAMMER, 2.99, true, false, false));
	}

	// Getters. Note only getters are necessary for this class since this data does not change.
	public ArrayList<Tool> getTools() {
		return tools;
	}

	public ArrayList<Price> getPrices() {
		return prices;
	}
}
