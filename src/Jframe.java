import java.awt.Color; //used to change the background color 
import java.awt.Dimension;//used to set the bounds and dimesions 
import java.awt.Desktop; //THIS IS IMPORTANT- used to open a website 
import java.net.URI; //also used in the website opening
import java.awt.Font; //used to change the font 
import java.awt.Image;//used with the images since i brought in images for teh student id 
import java.sql.ResultSet; //the resultset that is returned in the database find method is used through this library
import java.sql.SQLException; //used in the tru catches
import java.awt.BorderLayout;
import java.awt.GridLayout;//used a gridlayout for the display since its easier than the coordinates

import javax.swing.*;//calls everything related to the graphics, even though theres multiple imports above, this is stil needed for teh basic jframe buttons and labels and everyything

//https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html

//https://youtu.be/Kmgo00avvEw?si=YysvMUzI6jSmi_lQ //the main video i used, same as in assignment 1 and 4 for the graphics
//All the info i got about the java swing graphics is here, its a 4 hour video ive been using since assignment 1 
//its the video i sent to naomi, alizah, elias and adam, so if our graphics looks similar it is because we are using the same video tutorial!!
//it also has drag and drop but i didnt use that in this culminating


// https://youtu.be/IumBEpOJtOU?si=_bx8rqi06T12aGqC
//this yt video shows me how to open the browser using a url link, using awt.desktop





//READ- i will be commenting but the code is VERYYY repetitive, setting the text, setting the bounds and setting font and style initalizing the labels n stuff are very repetivive that i may not repeat for all lines
//some of these comments on graphics are the same from my assignments because i was citing the same sources that taught me then, and its the same concepts, JFrame is again not complicated but very repetitive and tedius 

public class Jframe extends JFrame{//creates a new class that has the same properties as a jframe and makes a new window

    //I will be explaining the main parts of teh graphics here so as to not repeat myself multiple times

    //Jlabels = text, can set the text, location and font. 
    //use "setVisibility" to make something appear and dissapear as u set it to true or false
    //majority of the labels are in this format for the aesthetics .setFont(new Font("Segoe Script", Font.PLAIN, 21 or 18));

    //Jframes- there is only one main frame and then panels are added to it
    //Jpanels- areas of space that have dimension and can hold stuff, used for organizing, like evryt label related to the photo id is added to the DigiID panel, teh image, name and stuff,
    //  you can make all that dissapear by simply setting the panel to visibility false 

    //Jbuttons- quote literally buttons 
    //JTextfields- inputs for the editing inforation and username/password 

    //Jmenubar creates a bar with different horizontal menus, which further have vertical memu items that u can use as buttonsish 
    
    //JScroll panels will let u scroll and you add the panel directly to the scroll pane instead of teh other way around. If there is no need to scroll then it wont have a scroller 
    
    //JOptionPanes will give an announcement and can have option to dismis or do other items in the announcement

    //initialised a bunch of the JFrame here so the scope is throughout teh class
    JFrame frame;
    JMenuBar menu;
    JMenu Quantity;
    JMenuItem all;
    JMenuItem Dairy;
    JMenuItem Fruits;
    JMenuItem Vegitables;
    JMenuItem Staples;
    JMenuItem Other;
    JMenu Search;
    JMenu Recipes;
    //JMenu Expiration;
    JLabel intro;
    //JLabel info;

    JPanel quantity;
    JPanel recipes;
    JPanel SearchItem;
    JPanel searchResults;
    JPanel addPanel;
    JPanel deletee;
    JTextField search;
    //JPanel expiry;
    JScrollPane scrollPane;
    Database db = new Database();//The new database

    JTextField Name;
    JTextField quantitY;
    JTextField type;
    //JTextField date;
    JTextField link;
    JTextField category;
    JTextField image;

    JButton add;

    public void MyFrame(){//This is the main method that is called, this starts every other method and everything else
        frame = new JFrame();//Creates a frame
        frame.setTitle("Pantry Organizer");//Creates a title for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//The frame will close when u press X
        frame.setLayout(null);//this layout helps keep the panels in order when you expand the window/frame
        frame.setSize(1000,700);//size  of the frame
        frame.getContentPane().setBackground(Color.PINK);//background colour of the frame 
        
        //I will not be commenting every single label since there will be a LOTT of labels, but they are again in the same format, initialize, set text, set coordinates, set font style, ALLL this for one label
        intro= new JLabel("Choose one of the tabs above");
        intro.setBounds(100,180, 600,200);
        intro.setFont(new Font("Segoe Script", Font.BOLD, 21));
            
        //Menubar shows up in all panels and will be at teh top, you have to set it not add it to frame
        menu = new JMenuBar();
        Quantity = new JMenu("Quantity");//first item- teh quanity displays 
        Quantity.setFont(new Font("Segoe Script",Font.BOLD, 18));//sets font style for teh menu 
        //search for whatever food you have in ur pantry
        Search = new JMenu("Search");
        Search.setFont(new Font("Segoe Script",Font.BOLD, 18));//sets font style for teh menu 
        //make a list based on quantity
        Recipes = new JMenu("Recipes");
        Recipes.setFont(new Font("Segoe Script",Font.BOLD, 18));//sets font style for teh menu 
        
        //a menu will show you the different options but they dont behave like buttons,  menuitems do behave like buttons so you must add menu items for the menus to be usefull

        //a category for all the items available under quantity
        all = new JMenuItem("All Items");
        all.setFont(new Font("Segoe Script",Font.BOLD, 18));//font style
        Quantity.add(all); //adds this menuitem to quantity
        all.addActionListener(e -> ShowItems("All Items"));// when pressed, this method is called and the parametr is all items 
        
        //same as above, lines 96-100, will be copy pasted and variables changed down below since its all teh same making menu items- 
        // From lines 103 to 141 is all new menu items, thei font style, what menu its added to and the respecive methods called with the respective parameters
        Dairy = new JMenuItem("Dairy");
        Dairy.setFont(new Font("Segoe Script",Font.BOLD, 18));
        Quantity.add(Dairy);
        Dairy.addActionListener(e -> ShowItems("Dairy"));
        
        Fruits = new JMenuItem("Fruits");
        Fruits.setFont(new Font("Segoe Script",Font.BOLD, 18));
        Quantity.add(Fruits);
        Fruits.addActionListener(e -> ShowItems("Fruits"));
        
        Vegitables = new JMenuItem("Vegitables");
        Vegitables.setFont(new Font("Segoe Script",Font.BOLD, 18));
        Quantity.add(Vegitables);
        Vegitables.addActionListener(e -> ShowItems("Vegitables"));
        
        Staples = new JMenuItem("Staples"); 
        Staples.setFont(new Font("Segoe Script",Font.BOLD, 18));
        Quantity.add(Staples);
        Staples.addActionListener(e -> ShowItems("Staples"));

        Other = new JMenuItem("Other");
        Other.setFont(new Font("Segoe Script",Font.BOLD, 18));
        Quantity.add(Other);
        Other.addActionListener(e -> ShowItems("Other"));

        JMenuItem search = new JMenuItem("Search"); 
        search.setFont(new Font("Segoe Script",Font.BOLD, 18));
        Search.add(search);
        search.addActionListener(e -> ShowItems());

        JMenuItem recipe = new JMenuItem("Recipes");
        recipe.setFont(new Font("Segoe Script",Font.BOLD, 18));
        Recipes.add(recipe);
        recipe.addActionListener(e -> recipePage());
        
        //all of the menus are added to the menu bar and the menu items are already added to teh menus in earlier code
        menu.add(Quantity);
        menu.add(Search);
        menu.add(Recipes);
        frame.setJMenuBar(menu);//menu bar must be set not added
        frame.add(intro);//the intro label is also added here,it dissaprears once u press some menu item
        frame.setVisible(true);// this makes sure the frame is visible
    }
    public void ShowItems(String category){//This is the method called for the menu items, it displays the items based on category selected

        intro.setVisible(false);// the intro label is set to false so that it doesnt overlap
        String sql = "SELECT * FROM Inventorytbl WHERE Category = '" + category + "'";// the string for sql statement used to make queries later 
        //the imfo is selected based on the category provided

        if (category.equalsIgnoreCase("All Items") || category.equalsIgnoreCase("All"))sql = "SELECT * FROM Inventorytbl";//if the category is all, there is nothing like all in the database so we get all using teh star, the sql statement must be chanbged if its all items
        if(quantity!=null)frame.remove(quantity);//Whatever is on screem before must be removed but it will cause a null pointer exception is you remove it while its empty so have to make sure its not null using an if statement
        if(scrollPane != null)frame.remove(scrollPane);//same as above, if not null, remove it so it doesnt overlap
        if(SearchItem!=null)frame.remove(SearchItem);//same as above, if not empty, remove it
        if (addPanel != null) frame.remove(addPanel);
        if (recipes != null) frame.remove(recipes);

        //new panel for the quantity- the background colour and layout are set
        quantity = new JPanel(); 
        quantity.setBackground(Color.PINK); //background colour
        quantity.setLayout(new GridLayout(0, 3, 20, 20));//a grid layout will not need specific coordinates and every label and button added will be right by each other neatly
        //the rows are set to 0, but there will be more added as you have more info to add, they will be added based on how many items you have and displayed in 3s, 3 columns
        //the 20 is just the horizontal and vertical gaps 

        
        try {//since working with a resultset, using a try catch is safer 
        ResultSet rs = db.find(sql);//stores the resultset from the method into a new variable to use

        while (rs != null && rs.next()) {//uses a while loop to fill in the grid layout 
            String name = rs.getString("ItemName");//stores the name from the database
            int qty = rs.getInt("Quantity");//quantity from the database
            String imgPath = rs.getString("ImagePath");//and image path of the item from the database
            
            JPanel itemRow = new JPanel(); // A panel for one food item
            itemRow.setLayout(new BoxLayout(itemRow, BoxLayout.Y_AXIS));//sets the layout  of the panel to box layout, which stacks them on top of each other, whatever is initialized first, goes first 
            itemRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //creates a border so different items can be noticed 
            itemRow.setBackground(Color.PINK);//background colour to match the rest of the panels

            ImageIcon icon = new ImageIcon(imgPath); //The image is found using the path stored in the database
            Image scaledImg = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); //the images are of different sizes so a scaled version is used, that causes them to be blurry but its okay 
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImg));//the image is then set as a label and since it was made first, it shows uo at teh top before the name, buttons and quantity

            JLabel Name = new JLabel("Item: " + name);//the name of the item is set 
            Name.setFont(new Font("Segoe Script",Font.BOLD, 16));//font style 
        
            JPanel controls = new JPanel();//this is a new panel to control the plus and minus- this way the pkus and minus show beside each other in the box layout instead of below each other 
            controls.setBackground(Color.WHITE);//set background to white purely for the aesthetics and to differentiate between the other panels
            JButton minus = new JButton("-");//new button for teh minus
            
            JButton plus = new JButton("+");//new button for the plus 
            
            JLabel amount = new JLabel("Amount available: " + qty);//the amount available is also set using 
            amount.setFont(new Font("Segoe Script",Font.BOLD, 16));//font style is set like all tthe other labels 
        
            minus.addActionListener(e -> PlusMinus("minus", name, amount));// teh action listeners for the buttons are set, so tha the name and amount can be updated in the database when pressed that button
            plus.addActionListener(e -> PlusMinus("plus", name, amount));

            controls.add(minus);//teh minus and plus are added to the control panel which is then added to the itemrow panel and shows up below the name label in a box layout 
            controls.add(plus);
            //the image, name, the control panels, amount available are all added to the itemrow panel
            itemRow.add(imageLabel);
            itemRow.add(Name);
            itemRow.add(controls);
            itemRow.add(amount);     
            //and the item rows are all added to the quantity       
            quantity.add(itemRow);
        }
        } catch (SQLException e) {//catches any exceptions since we were dealing with resultsets
            quantity.add(new JLabel("Database Error!"));//shows a label instead of printing a statement and it is already added to the panel
        }

        
        scrollPane = new JScrollPane(quantity);// this allows it to scroll and make sure all items are visible 
        scrollPane.setBounds(50, 50, 900, 550); // Set bounds on the scroll pane, NOT the quantity panel
        //frame.add(quantity); - do not need to add the quantity panel again since the scrol pane is set to the quantity, so add scrol pane and its all good
        frame.add(scrollPane);
        add = new JButton("Add");//new button for the plus 
        add.addActionListener(e -> addItem());
        add.setBounds(800,10, 100,40);
        frame.add(add);
        
        //everything is repainted and revalidated just to make sure it works, new componnents and everything 
        quantity.setVisible(true);
        quantity.revalidate();
        quantity.repaint();
        frame.revalidate(); // Tells the frame it has new components
        frame.repaint();   // Tells the frame to redraw the screen
    }
    //again uses polymorphism here with the methods, this method is similar to the rest, but is used by the search function
    public void ShowItems(){
        add.setVisible(false);// the add button is set to false so that it doesnt overlap
        //if the other panels are not null, they are removed to prevent overlap
        intro.setVisible(false);// the intro label is set to false so that it doesnt overlap
        
        if(quantity!=null)frame.remove(quantity);
        if(scrollPane != null)frame.remove(scrollPane);
        if(SearchItem != null) frame.remove(SearchItem);
        if (addPanel != null) frame.remove(addPanel);
        if (recipes != null) frame.remove(recipes);
    
        SearchItem = new JPanel();//creates a new panel specifically for the grades
        SearchItem.setBounds(0,0,985,700); //sets it to around the same size as teh frame
        SearchItem.setBackground(Color.PINK);//background colour of the frame 
        SearchItem.setLayout(null);//this is so that we can control the layout, if we dont mention this, the panel will be placed anywhere, we need to set layout to null and then use set bounds

        JLabel Search = new JLabel();//same as above, a label with dimentions, coordinates and font style
        Search.setText("Search for the item you are looking for");
        Search.setBounds(200, 40, 500, 50);
        Search.setFont(new Font("Segoe Script",Font.PLAIN, 21));

        search = new JTextField();//creates a text field to get the item name
        search.setPreferredSize(new Dimension(400,50));//sets the size of the actual textbox
        search.setBounds(200, 100, 400,50);//sets the boundaries and coordinates of where the text field is, even if the size is declared above, this helps the location
        search.setFont(new Font("Times New Roman", Font.PLAIN, 20));//font style

        JButton enter = new JButton("Enter");//the enter button with the text set
        enter.setBounds(600,100, 100, 50);//sets the boundaries and coordinates of where the button is 
        enter.setFont(new Font("Segoe Script",Font.PLAIN, 21)); //sets the font style of the button 
        enter.addActionListener(e -> enter());//uses an actionlistenor to check when the button is clicked, when clicked that method is called 


        SearchItem.add(Search);//adds all items to the panel and adds the panel tp the frame
        SearchItem.add(search);
        SearchItem.add(enter);
        frame.add(SearchItem);
        SearchItem.setVisible(true);//sets the visibility to true
        frame.revalidate();
        frame.repaint();

    }
    public void enter(){//the enter button for the search panel
        intro.setVisible(false);// the intro label is set to false so that it doesnt overlap
        if (searchResults != null) {//if the search results panel is not empty it makes it empty
            SearchItem.remove(searchResults);
        }
        //if(quantity!=null)frame.remove(quantity);//Whatever is on screem before must be removed but it will cause a null pointer exception is you remove it while its empty so have to make sure its not null using an if statement
        //if(scrollPane != null)frame.remove(scrollPane);//same as above, if not null, remove it so it doesnt overlap
        //if(SearchItem!=null)frame.remove(SearchItem);//same as above, if not empty, remove it
        

        String Searchitem = search.getText();// gets the text from the textfield
        String sql = "SELECT * FROM Inventorytbl WHERE ItemName = '" + Searchitem + "'"; //the sql statement is made here to get the search results later
        String qty = db.find(sql, "Quantity");//gets the quantity available
        
        
        searchResults = new JPanel();//Makes new panel for the search results 
        searchResults.setBounds(200, 180, 850, 400); // Placed below the search bar
        searchResults.setBackground(Color.PINK);//background colour to pink
        searchResults.setLayout(null); //sets layout to null and sets bounds instead

        JLabel Search = new JLabel();//same as above, a label with dimentions, coordinates and font style
        Search.setText("You have searched for '"+ Searchitem+ "'");
        Search.setBounds(0, 20, 700, 50);
        Search.setFont(new Font("Segoe Script",Font.PLAIN, 21));

        JLabel item = new JLabel();//same as above, a label with dimentions, coordinates and font style
        item.setText(" -> You have "+ qty+ " items left");
        item.setBounds(0, 80, 500, 50);
        item.setFont(new Font("Segoe Script",Font.PLAIN, 21));

        
        if(qty== "NA"){
            Search.setText("The item you have searched for is not found, TRY AGAIN!");
            searchResults.add(Search);
            SearchItem.add(searchResults);
            SearchItem.repaint();
            SearchItem.revalidate();
            frame.repaint();//this just makes sure no label is missing or misprint by reprinting it
            //any labels added later will also be refreshed with these lines up and below 
            frame.revalidate();
            return;

            
        }
        //all the labels are added to the panel 
        searchResults.add(Search);
        searchResults.add(item);
        //the panel is then added to the original search panel  
        SearchItem.add(searchResults);
        SearchItem.repaint();
        SearchItem.revalidate();
        frame.repaint();//this just makes sure no label is missing or misprint by reprinting it
        //any labels added later will also be refreshed with these lines up and below 
        frame.revalidate();
        
    }
    public void PlusMinus(String choice, String Itemname, JLabel labelToUpdate){//this is the method used to update the quantities available using plus and minus
        String text  = labelToUpdate.getText();//the Jlabel that is brought in as a parameter is teh amount available, used to get that current quantity
        int currentQty = Integer.parseInt(text.substring(text.lastIndexOf(" ") + 1)); //the JLabel text is written as "Amount available: #", so to get the last number, we get the last index of a space and use that as the starting index of the substring to get the number
        //then we use parseint to convert to an integer
        
        int newQty = currentQty;//stores that value here and then adds or minus 1 based on what is pressed
        if (choice.equals("plus")) {// this method is used for both plus and minus simultaniously, so if statements are used to differentiate 
            newQty++;//adds one if its plus
        } else if (choice.equals("minus") && currentQty > 0) {
            newQty--;//and subtracts 1 if its minus, The quantity also cannot be in the negatives so its restricted in the if statement 
        }
        db.update(Itemname, newQty);//the new quantity is updated 
        labelToUpdate.setText("Amount available: " + newQty);//the text is set again

        if (choice.equals("minus")) {//the shop pop up only matters if minus is pressed and your quantity is reduced
            String sql = "SELECT QuantityType FROM Inventorytbl WHERE ItemName = '" + Itemname + "'"; //sql statement to get the quantity tyep, individuals cannot go lesser than 3 and packs cant go lesser than 0-
            // one coffee powder lasts weeks while one tomato may not, so pack vs individual was required 
            String unitType = db.find(sql, "QuantityType"); //stores the quantity tpe 
            if (unitType.equalsIgnoreCase("Pack") && newQty == 0) {
            //if the packs are 0, then the shop pop up is called
                showStock(Itemname);
            } 
            else if (unitType.equalsIgnoreCase("Individual") && newQty < 3) {
            //if the individuals are less than 3, then the shop pop is called 
                showStock(Itemname);
            }
        }
    }
    public void showStock(String itemName){// the shop pop up 
        Object[] options = {"Shop", "Dismiss"};//The 2 options are stored into an array 
        //I only used an array for the sake of it since we had an entire unit on it, it felt like it was neccesary
        int choice = JOptionPane.showOptionDialog( //the announcement pop up and the button/option clicked is stored 
        frame, //the primary window
        "You are running low on " + itemName + "!", //the message 
        "Low Stock Warning", //the title of pop up
         JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, //the yess or no option is self explanotory, the warning message makes the announcement treated as a warning and not choosing an option will cause a buzz 
        null,//the icon is empty
         options, options[0]); //the choices 

        if (choice == JOptionPane.YES_OPTION) {//when pressed shop, it will go through this if statement 
            try {            
                String sql = "SELECT ShopLink FROM Inventorytbl WHERE ItemName = '" + itemName + "'";
                //The shopping link is individually stored in teh database, so we use an sql statement to find it
                String link = db.find(sql, "ShopLink");//teh specific shop link for that item is stored into this variable

                if (link != null && !link.isEmpty() && Desktop.isDesktopSupported()) {//makes sure theere arent errors like the link being null or empty or the desktop used has a browser before running 
                    Desktop.getDesktop().browse(new URI(link));//if all works, the link is opened on your defaault browser
                } else {//if something is missing it goes through this if statement to show so 
                    JOptionPane.showMessageDialog(frame, "Link unavailable. Opening default shop");
                    link = "https://www.walmart.ca/en";
                    Desktop.getDesktop().browse(new URI(link));
                }
            } catch (Exception e) {//catches any exceptions and prints the error 
                JOptionPane.showMessageDialog(frame, "Something went wrong with the shop link!");
                System.out.println("Error \n " + e.getMessage());
            }
        }
    }
    public void addItem(){
        if (add != null) add.setVisible(false);// the add button is set to false so that it doesnt overlap
        
        //if the other panels are not null, they are removed to prevent overlap
        //same as above methods
        //Everything is again repetivive, this method starts with making a panel and having display labels and textfields to collect the information
        intro.setVisible(false);
        
        if (quantity != null) frame.remove(quantity);
        if (scrollPane != null) frame.remove(scrollPane);
        if (recipes != null) frame.remove(recipes); 
        if (addPanel != null) frame.remove(addPanel);
        if (recipes != null) frame.remove(recipes);


        // a panel like all others to hold the items for add
        addPanel = new JPanel();
        addPanel.setBounds(0, 0, 950, 700);
        addPanel.setBackground(Color.PINK);
        addPanel.setLayout(null);

        //A label with dimentions, coordinates and font style
        JLabel newItem = new JLabel("Add New Pantry Item");
        newItem.setBounds(100, 20, 500, 50);
        newItem.setFont(new Font("Segoe Script", Font.BOLD, 21));
        addPanel.add(newItem);

        
        //same as above, a label with dimentions, coordinates and font style
        JLabel name = new JLabel("Item Name:");
        name.setBounds(100, 80, 500, 50);
        name.setFont(new Font("Segoe Script", Font.PLAIN, 18));
        addPanel.add(name);
        //Collects the name of the item
        Name = new JTextField();//creates a text field to get the item name
        Name.setBounds(400, 80, 500,50);//sets the boundaries and coordinates of where the text field is, even if the size is declared above, this helps the location
        addPanel.add(Name);
        
        //same as above, a label with dimentions, coordinates and font style
        JLabel Quantity = new JLabel("Quantity: ");
        Quantity.setBounds(100, 130, 500, 50);
        Quantity.setFont(new Font("Segoe Script", Font.PLAIN, 18));
        addPanel.add(Quantity);
        //collects the quantity of item in ur pantry
        quantitY = new JTextField();
        quantitY.setBounds(400,130,500,50);
        addPanel.add(quantitY);

        //same as above, a label with dimentions, coordinates and font style
        JLabel Type = new JLabel("Quantity Type: ");
        Type.setBounds(100, 180, 500, 50);
        Type.setFont(new Font("Segoe Script", Font.PLAIN, 18));
        addPanel.add(Type);
        //Collects the quantity type
        type = new JTextField("Individual/Pack");
        type.setBounds(400,180,500,50);
        addPanel.add(type);
        
        //same as above, a label with dimentions, coordinates and font style
        JLabel Link = new JLabel("Store Link url: ");
        Link.setBounds(100, 230, 500, 50);
        Link.setFont(new Font("Segoe Script", Font.PLAIN, 18));
        addPanel.add(Link);
        //collects the link to the shop
        link = new JTextField();
        link.setBounds(400,230,500,50);
        addPanel.add(link);
        
        //same as above, a label with dimentions, coordinates and font style
        JLabel Category = new JLabel("Category: ");
        Category.setBounds(100, 280, 500, 50);
        Category.setFont(new Font("Segoe Script", Font.PLAIN, 18));
        addPanel.add(Category);
        //collects the category type
        category = new JTextField("Dairy/Vegitables/Fruits/Staples/Other");
        category.setBounds(400,280,500,50);
        addPanel.add(category);

        //same as above, a label with dimentions, coordinates and font style
        JLabel Image = new JLabel("Image Path: ");
        Image.setBounds(100, 330, 500, 50);
        Image.setFont(new Font("Segoe Script", Font.PLAIN, 18));
        addPanel.add(Image);
        //collects the image path 
        image = new JTextField();
        image.setBounds(400,330,500,50);
        addPanel.add(image);

        JButton saveButton = new JButton("Save Item"); 
        //The save button takes you to a seperate method 
        saveButton.setBounds(400, 450, 150, 40);
        saveButton.addActionListener(e -> save());

        //the back button just taken you back to the quanity menu and shows u all items present
        JButton back = new JButton("Back");
        back.setBounds(200, 450, 150, 40);
        back.addActionListener(e -> ShowItems("All Items"));
        addPanel.add(back);
        //the buttons are added to the panel and the panel is added to the frame
        addPanel.add(saveButton);
        frame.add(addPanel);
        
        addPanel.setVisible(true);
        frame.revalidate();//The revalidate and repaint just make sure everythiings working as it should be
        frame.repaint();
    }
    public void save(){
        //the back button is made here as well, and it does the same thing as above, i copy pasted it 
        //making it again here just makes ure its on top of teh panel n not removed.
        JButton back = new JButton("Back");
        back.setBounds(200, 450, 150, 40);
        back.addActionListener(e -> ShowItems("All Items"));
        addPanel.add(back);
        
        //add.setVisible(true);// the add button is set to false so that it doesnt overlap
        
        
        String a = Name.getText();
        String b = quantitY.getText();
        String c = type.getText();
        //String d = date.getText();
        String d = link.getText();
        String e = category.getText();
        String f = image.getText();
        //The text is taken from the textfields and stored into strings

        if (a.isEmpty() || b.isEmpty() || c.isEmpty()|| d.isEmpty() || e.isEmpty() || f.isEmpty()) {//this makes sure u cant submit without the name and quantity
            JOptionPane.showMessageDialog(frame, "Nothing can be left blank!");
            return;
        }
        //if the user inputs some other category, it just gets saved to other
        if(e!="Dairy"|| e!= "Vegitables"||e!="Fruits"||e!="Staples"){
            e= "Other";
        }

        int qty;//the quantity needs to be converted into a number
        try{//a try catch is used to make sure the number inputed is a number 
            qty = Integer.parseInt(b);
        }
        catch(Exception ex){//if the quantity is not a number, then it is set to default 0
            JOptionPane.showMessageDialog(frame, "Quantity must be a number! So it will be a 0");// a pop up to inform the user what happened 
            quantitY.setText(""); 
            qty = 0;        
        }

        //since we are using file paths, the user must save the file into a specific location and that location is stored into teh database
        java.io.File fileCheck = new java.io.File(f);
        if (f.isEmpty() || !fileCheck.exists()) {
            //if the file is empty or doesn't exist, the default image is used and teh user is informed 
            f = "src\\images\\default.jpg";
            JOptionPane.showMessageDialog(frame, "Image path not found! Using standard system default image.");
        }
        db.insert(a, qty, c, d, e, f);// this adds all this info into the database as a new item 
        JOptionPane.showMessageDialog(frame, "Successfully saved!! Press back or continue adding items!");//another pop up to tell the user whats happening
        
        //the textfields are all reset to empty so that it can be saved
        Name.setText("");
        quantitY.setText("");
        type.setText("");
        //date.setText("");
        link.setText("");
        category.setText("");
        image.setText("");
    }   
    public void recipePage(){
        add.setVisible(false);
        //like the other methods, if the other panels are not empty, it is removed and the into and info labels are set to not visible
        intro.setVisible(false);
        if(SearchItem != null) frame.remove(SearchItem);
        if (quantity != null) frame.remove(quantity);
        if (scrollPane != null) frame.remove(scrollPane);
        if (addPanel != null) frame.remove(addPanel);
        if (recipes != null) frame.remove(recipes);
        

        //the recipe panel is created to hold everything related to the recipes
        recipes = new JPanel();
        recipes.setBounds(0, 0, 985, 700);
        recipes.setBackground(Color.PINK);
        recipes.setLayout(null);

        //the title label to just tell the user what it is
        JLabel title = new JLabel("Recipes based on what you have in your pantry!!");
        title.setBounds(50, 30, 700, 40);//the boundaries and location like all labels 
        title.setFont(new Font("Segoe Script", Font.BOLD, 21)); //the default font style i use
        recipes.add(title); //the label is added to panel

        // a new grid layout is used on top of the recepe one, this makes formatting easier when you use a forloop, since u wont have to manually set the coordinates
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(0,2,0,15));//there are no default number of rows but it gets added as you go in the forloop, there will always be 2 columns tho
        grid.setBackground(Color.PINK);

        String[][] arr = db.APIrecipes();//the array of recipe data from the database class is stored 

        for(int i = 0; i<arr.length; i++){//goes through teh array and sets the label and buttons
            String Rtitle = arr[i][0];//the name of recipe
            String Rlink = arr[i][1];//the link to recipe, its just a search on a website

            if(Rtitle != null && !Rlink.isEmpty()){//makes sure neither variable is empty or null
                JPanel Recepi = new JPanel(); //makes a new panel for the individual recipe and its link
                Recepi.setLayout(new BorderLayout(10, 0));//a border layout makes it easier to center or left the labels and buttons properly
                Recepi.setBackground(Color.PINK);//the background in pink as usual

                JLabel name = new JLabel(Rtitle);//recipe title is displayes 
                name.setFont(new Font("Segoe Script", Font.BOLD, 18));//with teh usual font style
                Recepi.add(name,BorderLayout.CENTER); //the recipe name is centered 

                JButton fullR = new JButton("Full Recipe");//the button for teh full recipe link is added
                //fullR.setBounds(50,50,100,50);
                fullR.addActionListener(e -> {//instead of making another new method, i just added the method instructions here since its just simply launch opening the link
                    try{
                        java.awt.Desktop.getDesktop().browse(new java.net.URI(Rlink));//opens the link given
                    } catch(Exception ex){
                        JOptionPane.showMessageDialog(frame, "Web link did not work :( " + ex.getMessage());//if something went wrong, a simple announcement pop up is made
                    }
                });

                Recepi.add(fullR, BorderLayout.AFTER_LAST_LINE);//the button is set to the east, so it wont overlap with the label
                grid.add(Recepi);//the label is already added to the grid, and now the button tpp
            }
        }
        JScrollPane ScrollRecipe = new JScrollPane(grid);//since there could be multiple recipes, i added a scroll pane, but its not neccesary 
        //it only shows up is the text inside is squished, if theres extra space there is no need for a scroll pane
        ScrollRecipe.setBounds(150, 100, 680, 450);
        recipes.add(ScrollRecipe);

        //the recipe panel is added to the frame and everything is revalidated
        frame.add(recipes);
        recipes.setVisible(true);
        frame.revalidate();
        frame.repaint();

    }

}
