/**
 * Ethan Shen
 * Gallatin 5
 * 17/05/08
 * Workshop class for the database project
 */

 import java.util.ArrayList;
 import java.io.*;
 import java.util.Scanner;
 import javax.swing.JFileChooser;
 import javax.swing.*;

/**
 * Workshop class that stores tools in an array list
 */
public class Workshop {

    	private ArrayList <Tool> shop;

		/**
		 * Constructs an empty workshop
	 	 */
		public Workshop()
		{
			shop = new ArrayList <Tool>();
		}
    	
		/**
	 	 * addTool adds a specified Tool to the Workshop
	 	 * @param thing the Tool to be added
	 	 */
		public void addTool(Tool thing)
		{
			shop.add(thing);
		}
		
		/**
		 * empties the workshop
		 */
		public void emptyShop()
		{
			shop.clear();
		}
		
		/**
		 * getShop returns an arraylist of the tools in the workshop
		 * @return the workshop
		 */
		public ArrayList<Tool> getShop()
		{
			return shop;
		}
		
		/**
	 	 * toString provides a String version of the workshop with
	 	 * each tool on a separate line
	 	 * @return the String version of the workshop
	 	 */
		public String toString()
		{
			String out = "";
			for(Tool i: shop)
				out += i + "\n";
				// out += i.toString() + "\n";
			return out;
		}
		
		/**
	 	 * findTool returns a reference to the Tool from the workshop that matches the name of the parameter item
	 	 * @param thing the Tool to find (based on name)
	 	 * @return the found Tool or null if not found
	 	 */
		public Tool findTool(Tool thing)
		{
			int index = shop.indexOf(thing);
			if (index == -1)
				return null;
			else
				return shop.get(index);
		}
		
		/**
		 * updateUses increments the number of uses of a tool
		 * @param thing the tool to find (based on name)
		 * @param add the number of uses to add
		 * @return a boolean representing whether or not the tool exists in the workshop
		 */
		public boolean updateUses(Tool thing, int add)
		{
			int index = shop.indexOf(thing);
			if (index == -1)
				return false;
			else
			{
				shop.get(index).addUses(add);
				return true;
			}
		}
		
		/**
		 * updatePrice assigns a new price for the tool
		 * @param thing the tool to find (based on name)
		 * @param a the new price to assign
		 * @return a boolean representing whether or not the tool exists in the workshop
		 */
		public boolean updatePrice(Tool thing, double a)
		{
			int index = shop.indexOf(thing);
			if (index == -1)
				return false;
			else
			{
				shop.get(index).changePrice(a);
				return true;
			}
		}
		
		/**
		 * sortByUses sorts the workshop by number of uses, going from most to least
		 * @param list the arraylist of tools to sort
		 */
		public static void sortByUses(ArrayList<Tool> list)
		{
			boolean sorted = false;
			int p = 1;
			while(!sorted) 
			{
				sorted = true;
				for(int index = 0; index < list.size() - p; index++)
				{
					if(list.get(index).getNumUses() < list.get(index+1).getNumUses())
					{
						sorted = false;
						Tool temp = list.get(index);
						list.set(index, list.get(index+1));
						list.set(index+1, temp);
					}
				}
				p++;
			}
		}
		
		/**
		 * sortByDate sorts the workshop by the most recent use 
		 * @param list the arraylist of tools to sort
		 */
		public static void sortByDate(ArrayList<Tool> list)
		{
			boolean sorted = false;
			int p = 1;
			while(!sorted) 
			{
				sorted = true;
				for(int index = 0; index < list.size() - p; index++)
				{
					if(list.get(index).getLastIntUse() < list.get(index+1).getLastIntUse())
					{
						sorted = false;
						Tool temp = list.get(index);
						list.set(index, list.get(index+1));
						list.set(index+1, temp);
					}
				}
				p++;
			}
		}
		
		/**
	 	 * getTotalPrice returns the total Price of all Tools in the workshop
	 	 * @return the price of all Tools.
	 	 */
		public double getTotalPrice()
	 	{
			double total = 0;
			for(Tool i : shop)
				total += i.getPrice();
			return total;
		}
		
		/** 
	 	 * removeTool removes a specified Tool from the workshop if it exists, and returns true.
	 	 * If the Tool does not exist in the workshop, returns false.
	 	 * @param thing the tool to search for and remove
	 	 * @return whether the item was removed
	 	 */
		public boolean removeTool(Tool thing)
		{
			int index = shop.indexOf(thing);
			if (index == -1)
				return false;
			else
			{
				shop.remove(index);
				return true;
			}
		}
    	
   		/**
	 	 * Saves the current contents of the workshop to a user specified file.
	 	 */
		public void saveToFile() //throws IOException
		{
			// using try catch is optional
    		String fileName = JOptionPane.showInputDialog("Enter the file name");
    		try
    		{
    			PrintWriter out = new PrintWriter(fileName);
	    		for(Tool i: shop)
	    		{
	    			out.println(i.getName());
	    			out.println(i.getPrice());
	    			out.println(i.getNumUses());
	    			out.println(i.getLastUse());
	    			out.println(i.getType());
	    		}
	    		out.close();
	    		JOptionPane.showMessageDialog(null, fileName + " has been saved.");
    		}
    		catch(IOException exception)
    		{
    			System.out.println("File problem - could not save");
    		}
		}
		
		/**
	 	 * Loads a new workshop from a user specified file.  Uses JFileChooser to allow
	 	 * the user to browse for the file.
	 	 */
		public void loadFromFile() throws IOException
		{
			emptyShop();
    		JFileChooser chooser = new JFileChooser();
    		chooser.requestFocus();
    		File infile = null;
    		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	   	  	{
	   	  		infile = chooser.getSelectedFile();
	   	  	}
	   		Scanner in = new Scanner(infile);
	   		while(in.hasNext())
	   		{
	   			String name = in.nextLine();
	   			double price = in.nextDouble();
	   			int uses = in.nextInt();
	   			in.nextLine();
	   			String last = in.nextLine();
	   			String type = in.nextLine();
	   			boolean t;
	   			if (type.equalsIgnoreCase("power"))
	   				t = true;
	   			else
	   				t = false;
	   			addTool(new Tool(name, price, uses, last, t));
	   			
	   		}
	   		in.close();
		}
}