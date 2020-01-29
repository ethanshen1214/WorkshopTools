/**
 * Ethan Shen
 * Gallatin 5
 * 17/05/08
 * Tester class and interface for the database project
 */

import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.*;


/**
 * Database class that sets up the interface
 */
public class Database {

    public static void main (String[] args) throws IOException
    {
    	DatabaseMenu menu = new DatabaseMenu();
    }
}

/**
 * DabaseMenu class that sets up the user interface
 */
class DatabaseMenu extends JFrame implements ActionListener, MouseListener
{
	private static final int FRAME_WIDTH       = 800; // adjust to have a wider/slimmer window
 	private static final int FRAME_HEIGHT      = 900; // adjust to have a taller/shorter window
 	private static final int FRAME_X_ORIGIN    = 50;
 	private static final int FRAME_Y_ORIGIN    = 50;
 	
 	private Workshop garage;
		
	// declare GUI elements
	private JLabel titleLabel, sort1, sort2, displayAllLabel, show1, show2;
	private JPanel menuPanel;
	private JTextArea display;
	private JMenuBar menuBar;
	private JMenu menu1, menu2;
	private JMenuItem save, load, quit, addTool, delete, changePrice, updateUses, search, totalPrice, help;
	private JScrollPane scroller;
	
		/**
		 * The constructor instantiates all of the gui elements, adds them to the content pane, 
		 * and adds the listeners to the GUI elements
		 */
		public DatabaseMenu()
		{
			garage = new Workshop();
			
			// set up the window
			setTitle("Workshop");
 			setSize(FRAME_WIDTH, FRAME_HEIGHT);
 			setResizable(false);
 			setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
 			
 			// set up the main display area and make it scrollable
 			display = new JTextArea();
 			display.setFont(new Font("Courier",Font.BOLD,12));
 			display.setEditable(false);
 			display.setBorder(BorderFactory.createLineBorder(Color.blue));	
 			scroller = new JScrollPane(display);
 		
			
			// set up the menu bar
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);	
				
			/****************************************************/
			/*File menu*/
			menu1 = new JMenu("File");
			save = new JMenuItem("Save");
			load = new JMenuItem("Load");
			quit = new JMenuItem("Quit");
			help = new JMenuItem("Help");	
			save.addActionListener(this);
			load.addActionListener(this);
			quit.addActionListener(this);
			help.addActionListener(this);	
			menu1.add(save);
			menu1.add(load);
			menu1.add(quit);
			menu1.add(help);	
			menuBar.add(menu1);
			
			/****************************************************/
			/*Inventory menu*/
			menu2 = new JMenu("Inventory");
			addTool = new JMenuItem("Add");
			delete = new JMenuItem("Delete");
			changePrice = new JMenuItem("Change price"); 
			updateUses = new JMenuItem("Update # of uses");
			search = new JMenuItem("Search");
			totalPrice = new JMenuItem("Total Price");
			addTool.addActionListener(this);
			delete.addActionListener(this);
			changePrice.addActionListener(this);
			updateUses.addActionListener(this);
			search.addActionListener(this);
			totalPrice.addActionListener(this);
			menu2.add(addTool);
			menu2.add(delete);
			menu2.add(changePrice);
			menu2.add(updateUses);
			menu2.add(search);
			menu2.add(totalPrice);
			menuBar.add(menu2);
			
			/****************************************************/
			/*Main menu*/
			titleLabel = new JLabel("   DISPLAY:");
 			titleLabel.setForeground(Color.blue);
 			displayAllLabel = new JLabel("  Display all tools");
			sort1 = new JLabel("  Sort by # of uses (High to Low) ");
			sort2 = new JLabel("  Sort by most recent use");
			show1 = new JLabel("  Show tools over a certain number of uses ");
			show2 = new JLabel("  Show tools of a certain type");
			// Make the panel for the menu and add the menu items to it
			menuPanel = new JPanel();	
			menuPanel.add(titleLabel);
			menuPanel.add(sort1);
			menuPanel.add(sort2);
			menuPanel.add(displayAllLabel);
			menuPanel.add(show1);
			menuPanel.add(show2);
			
			
			
			//******************************************************************************************
			// Setting the layout of the window
			// Set the layout of the menuPanel to GridLayout
			menuPanel.setLayout(new GridLayout(6,1)); // the menu has 6 rows and 1 columns
			setLayout(new BorderLayout()); // you can put things in the North, South, East, West or Center
			add(menuPanel, BorderLayout.WEST); // adds the menu to the left.  You could choose a different border direction
			add(scroller, BorderLayout.CENTER); // add the text area to center, which takes up any unused space
			
			//******************************************************************************************
			/*make all of the menu items listen*/
			sort1.addMouseListener(this);
			sort2.addMouseListener(this);
			displayAllLabel.addMouseListener(this);
			show1.addMouseListener(this);
			show2.addMouseListener(this);
	
			// when the user clicks the X in the top right corner, the program will exit
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true); // make it visible LAST
		}
		
		
		/**
		 * required by the MouseListener interface.  Invoked when the mouse is clicked.
		 * @param e the MouseEvent that triggered the method
		 */
		public void mouseClicked(MouseEvent e)
		{
			// nothing needed, but method required by interface
		}
		/**
		 * required by the MouseListener interface.  Invoked when the mouse is released.
		 * @param e the MouseEvent that triggered the method
		 */
		public void mouseReleased(MouseEvent e)
		{
			// nothing needed, but method required by interface
		}
		/**
		 * required by the MouseListener interface.  Invoked when no mouse buttons are clicked and 
		 * the mouse pointer enters a particular gui element.  In this implementation, if the mouse
		 * pointer enters one of the menu labels, the label is set to red.
		 * @param e the MouseEvent that triggered the method
		 */
		public void mouseEntered(MouseEvent e)
		{
			JLabel label = (JLabel) e.getSource();
			label.setForeground(Color.red);
		}
		
		/**
		 * required by the MouseListener interface.  Invoked when no mouse buttons are clicked and 
		 * the mouse pointer leaves a particular gui element.  In this implementation, if the mouse
		 * pointer leaves one of the menu labels, the label is set to back to black.
		 * @param e the MouseEvent that triggered the method
		 */
		public void mouseExited(MouseEvent e)
		{
			JLabel label = (JLabel) e.getSource();
			label.setForeground(Color.black);
		}
		
		public void mousePressed(MouseEvent e) 
		{
	    	String toolName;
			double toolPrice;
			int toolUses;
			int lastUse;
			boolean toolType = false;
			JLabel label = (JLabel) e.getSource();
			
			if (label.equals(displayAllLabel))
			{
				showShop(garage);	
			}
			
			else if (label.equals(show1))
			{
				int temp;
				Workshop shop1 = new Workshop();
				temp = Integer.parseInt(JOptionPane.showInputDialog("Enter the minimum number of uses for the tools you wish to display."));
				String a = "";
				int count = 0;
				for (Tool thing : garage.getShop())
				{
					if (thing.getNumUses() > temp)
					{
						shop1.addTool(thing);
						count++;
					}
				}
				if (count == 0)
					JOptionPane.showMessageDialog(null, String.format("There are no tools over %d uses in the workshop", temp));
				else
					showShop(shop1);
			}
			
			else if (label.equals(show2))
			{
				String type = JOptionPane.showInputDialog(String.format("Enter the name of the type of tool you wish to see.\nEnter either \"power\" or \"hand\""));
				Workshop shop2 = new Workshop();
				int count = 0;
				for (Tool thing : garage.getShop())
				{
					if (thing.getType().equalsIgnoreCase(type))
					{	
						shop2.addTool(thing);
						count++;
					}
				}
				if (count == 0)
					JOptionPane.showMessageDialog(null, String.format("There are no %s tools in the workshop", type));
				else
					showShop(shop2);
			}
			
			else if (label.equals(sort1))
			{
				Workshop.sortByUses(garage.getShop());
				showShop(garage);
			}
			
			else if (label.equals(sort2))
			{
				Workshop.sortByDate(garage.getShop());
				showShop(garage);
			}
    
		}
		
		
		/**
		 * actionPerformed is required by the ActionListener interface.  
		 * It is invoked whenever one of the menu items is selected.
		 * The possible menu items that can be activated are quit, save, load, help, add, delete, total price, search, update #uses, and change price.
		 * @param e the ActionEvent that triggered the method
		 */
		public void actionPerformed(ActionEvent e)
		{
			String menuName = e.getActionCommand();
			String toolName;
			double toolPrice;
			int toolUses;
			String lastUse = "";
			boolean toolType = false;
			
			if (menuName.equals("Quit"))
				System.exit(0);
				
			else if (menuName.equals("Help"))
			{
				JOptionPane.showMessageDialog(null,String.format("\tThis program allows the user to maintain a database pertaining to the inventory of workshop\ntools in use within the workshop. It keeps track of how many times each tool has been used and \nrecommended safety equipment. This allows for the owner to understand wear and tear of each tool \nand allow for the owner to make decisions on buying and selling tools.\n\n\tRecords in the system track every tool in the workshop. Each record contains the following\ndata: name, price, number of uses, date of last use (yy/mm/dd), tool type (hand tool or power tool), \nand list of recommended safety equipment for proper use.\n\nThe user is responsible for updating the database after every tool use and entering newly \nacquired tools to the database. This new inventory is then added to the database.\n\nIn the menu bar at the top, the user will find options for File and Inventory. The file \noption will allow for the user to load and save different databases. The inventory option will allow for \nthe user to access the editing tools for the database. On the left hand side of the \nwindow, the user will see multiple options for displaying the database."));
			}
			
			else if (menuName.equals("Add"))
			{
					toolName = JOptionPane.showInputDialog("Enter the name of the Tool");
					toolPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter the price. Do not include the dollar sign. \nYou may use a decimal if needed."));
					toolUses = Integer.parseInt(JOptionPane.showInputDialog(String.format("Enter the current number of uses.\nThis can be updated at a later time.")));
					lastUse += JOptionPane.showInputDialog(String.format("Enter the date of the latest use.\nFormat: yy/mm/dd \nFor example: 17/05/09"));
					boolean valid = false;
					do
					{	String type = JOptionPane.showInputDialog(String.format("Enter the type of the tool.\nIf it is a power tool, enter \"power\"\nIf it is a hand tool, enter \"hand\""));
						if (type.equalsIgnoreCase("power"))
						{
							toolType = true;
							valid = true;
						}
						else if (type.equalsIgnoreCase("hand"))
						{
							toolType = false;
							valid = true;
						}
						else
						{
							JOptionPane.showMessageDialog(null,"You entered an invalid option for the type of tool");
							valid = false;
						}
							
					}	
					while(!valid);
					garage.addTool(new Tool(toolName, toolPrice, toolUses, lastUse, toolType));
					showShop(garage);	
			}
			
			else if (menuName.equals("Delete"))
			{
				String name = JOptionPane.showInputDialog("Enter the name of the Tool you wish to delete");

				boolean exists = garage.removeTool(new Tool(name));
				showShop(garage);
				if (exists)
					JOptionPane.showMessageDialog(null, name + " has been removed from the workshop.");
				else
					JOptionPane.showMessageDialog(null, name + " does not exist in the workshop.");
			}
			
			else if (menuName.equals("Total Price"))
			{
				display.setText("The total price of all the tools in this workshop is: $" + String.format("%.2f", garage.getTotalPrice()));
			}
			
			else if (menuName.equals("Search"))
			{
				toolName = JOptionPane.showInputDialog("Enter the name of the Tool you are looking for");
				Tool i = garage.findTool(new Tool(toolName));
				if(i == null)
				{
					showShop(garage);
					JOptionPane.showMessageDialog(null,"There is no tool by the name of " + toolName + " in your workshop.");
				}
						
				else
					showTool(i);
			}
			
			else if (menuName.equals("Update # of uses"))
			{
				String name = JOptionPane.showInputDialog("Enter the name of the Tool you are looking for");
				int temp = 0;
				boolean exists = garage.updateUses(new Tool(name), temp);
				
				if (exists)
				{
					int addUse = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of uses you wish to add to the tool"));
					garage.updateUses(new Tool(name), addUse);
					showShop(garage);
					JOptionPane.showMessageDialog(null, "" + addUse + " uses have been added to " + name);
				}
				else
					JOptionPane.showMessageDialog(null, name + " does not exist in the workshop.");
			}
			
			else if (menuName.equals("Change price"))
			{
				String name = JOptionPane.showInputDialog("Enter the name of the Tool you are looking for");
				int temp = 0;
				boolean exists = garage.updatePrice(new Tool(name), temp);
				
				if (exists)
				{
					double newPrice = Integer.parseInt(JOptionPane.showInputDialog("Enter the new price of the tool. Do not include a dollar sign."));
					garage.updatePrice(new Tool(name), newPrice);
					showShop(garage);
					JOptionPane.showMessageDialog(null, "The price of " + name + " has been changed to $" + newPrice);
				}
				else
					JOptionPane.showMessageDialog(null, name + " does not exist in the workshop.");
			}
			
			else if (menuName.equals("Save"))
			{
				garage.saveToFile();
			}
			
			else if (menuName.equals("Load"))
			{
				try
				{
						garage.loadFromFile();
				}
				catch(IOException a){}
				showShop(garage);
			}
			
		}
		/**
		 * Shows the entire contents of a given workshop
		 * @param a the workshop to show
		 */
		private void showShop(Workshop a)
		{
				display.setText("Workshop Tools:\n" + 
					String.format("%-20s%-10s%-20s%-10s%10s\n","Name", "#Uses", "Most Recent Use", "Type", "Price")+
    				String.format("=============================================================================\n")+a.toString());
		}
		
		/**
		 * Shows the data for a single tool
		 * @param the tool to show
		 */
		private void showTool(Tool i)
		{
			display.setText("Found Tool:\n" + 
					String.format("%-20s%-10s%-20s%-10s%10s\n","Name", "#Uses", "Most Recent Use", "Type", "Price")+
    				String.format("=============================================================================\n")+
    				i.toString());
		}
}