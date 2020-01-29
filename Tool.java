/**
 * Ethan Shen
 * Gallatin 5
 * 17/05/08
 * Tool class for the database class
 */
import java.util.*;

/**
 * Tool class creates a tool object and keeps track of its characteristics
 */
public class Tool 
{
	private String name;
	private double price;
	private int numUses;
	private String lastUse;
	private boolean power;
	
	/**
	 * Constructs a default tool object
	 */
    public Tool() 
    {
    	name = "";
    	price = 0;
    	numUses = 0;
    	lastUse = "00/00/00";
    	power = false;
    }
    
    /**
     * Constructs a tool with only a name
     * @param n the name to assign
     */
    public Tool(String n)
    {
    	name = n;
    	price = 0;
    	numUses = 0;
    	lastUse = "00/00/00";
    	power = false;
    }
    
    /**
     * Constructs a tool with a name, price, number of uses, last use, and type
     * @param n the name to assign
     * @param p the price to assign
     * @param u the number of uses
     * @param l the last use
     * @param t the type that the tool is
     */
    public Tool(String n, double p, int u, String l, boolean t)
    {
    	name = n;
    	price = p;
    	numUses = u;
    	lastUse = l;
    	power = t;
    }
        
    /**
     * getName returns the name of the tool
     * @return the name
     */
    public String getName()
    {
    	return name;
    }
    
    /**
     * getPrice returns the price of the tool
     * @return the price
     */
    public double getPrice()
    {
    	return price;
    }
    
    /**
     * getNumUses returns the number of uses for the tool
     * @return the number of uses
     */
    public int getNumUses()
    {
    	return numUses;
    }
    
    /**
     * getType returns the either "Power" or "Hand" for the type of the tool
     * @return a string representing the type
     */
    public String getType()
    {
    	if (power == true)
    		return "Power";
    	else
    		return "Hand";
    }
    
    /**
     * getLastUse returns the string version of the date of last use
     * @return the last use
     */
    public String getLastUse()
    {
    	return lastUse;
    }
    
    /**
     * getLastIntUse returns the int version of the date of last use
     * @return the last use
     */
    public int getLastIntUse()
    {
    	String d = lastUse.substring(0,2);
    	d += lastUse.substring(3,5);
    	d += lastUse.substring(6);
    	int date = Integer.parseInt(d);
    	
    	return date;
    }
    
    /**
     * change price changes the price of the tool
     * @param p the new price
     */
    public void changePrice(double p)
    {
    	price = p;
    }
    
    /**
     * addUses adds uses to the tool
     * @param a the number of uses to add
     */
    public void addUses(int a)
    {
    	numUses += a;
    }
    
    /**
     * toString returns a string version of a tool
     * @return the string version of the tool
     */
    public String toString()
    {
    	String type = "";
    	if (power == true)
    		type += "Power";
    	else
    		type += "Hand";
    	return String.format("%-20s%-10d%-20s%-10s$%10.2f", name, numUses, lastUse, type, price);
    }
    
    /**
     * equals compares two tools by name
     * @param tool the tool to check against
     * @return whether or not they are equal
     */
    public boolean equals(Object tool)
    {
    	Tool temp = (Tool) tool;
    	return this.getName().equalsIgnoreCase(temp.getName());
    }
}