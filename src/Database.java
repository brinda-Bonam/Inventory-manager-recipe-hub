//Same as in assignment 4, these are the same websites used, nothing different, i got teh ucanaccess files from the same website as assignment 4 but cited them anyways 
//https://www.geeksforgeeks.org/java/introduction-to-jdbc/
//https://www.w3schools.com/sql/

//https://sourceforge.net/projects/ucanaccess/
//this is where i downloaded the ucanaccess files and uploaded them into the libraray/ lib folder, since i will be submitting this entire folder, hopefully you will have acesss to it too

//I had to research what api means and how i can use it and the types, so i used this website 
//https://blog.postman.com/different-types-of-apis/


//For the actual api connections, i didnt make a new api but used an existing rest api
//https://github.com/public-apis/public-apis#food--drink
//I found this link on reddit to a bunchhh of free apis, and the one i am using is called recipeapi, it needed me to sign in and get an api key but it was free

//IMPORTANt READ- the api i used is free but it only has 500 requests per monthh, so while correcting the culminating, please try not to spam it!!

//sk_live_B6TvrRtEpr3TUcyeyhHeanh9zAsI0juKaLRvG4t834386f67
//the api key used 

//https://www.geeksforgeeks.org/java/java-net-httpurlconnection-class-java/
//https://www.baeldung.com/java-http-request

//https://www.youtube.com/watch?v=zZoboXqsCNw
//https://www.youtube.com/watch?v=b59d5_NcbfY

//The second one is veryyy robotic, but it gives direct code and has good explanations 
//The first one is what i mainly used and my code follows that video




//below are the imports that i used for the jdbc
//import java.io.File;
import java.sql.Connection;//the connection to the specific database, used to make statemenst 
import java.sql.DriverManager;//the connection  to the databse using the url 
import java.sql.ResultSet;//a table of data from teh database based on specific instructions 
import java.sql.SQLException;//handles exceptions 
import java.sql.Statement;// used for the actual selcts and updates

//These next libraries are all used for the web connections
import java.io.BufferedReader; //
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



//i also used a try catch and made a connection to the databse everytime so i will not be commenting it as much after the first time 

public class Database {
    private String file = "src\\Inventory.accdb";//the variable that has teh path to the database
    private String url = "jdbc:ucanaccess://" + file;//the actual url taht is used by the driver manager to connect to the accdb file

    public void setter(String path){//uses a setter since the objects are private
        file = path;//sets the path with the parameter
        url = "jdbc:ucanaccess://" + file;//updates the url as well
        System.out.println("file path updated to " + file);//prints to show that it has been properly updated and u can see the path 
    }
    public String getter(){//a getter to return the file info
        return file;
    }
    //a resultset is like a table of data, more than one column of information is present
    public ResultSet find(String sql){//returns the entire resultset of data based on teh sql statement
        try {//try catch to prevent the code from crashing
            Connection conn = DriverManager.getConnection(url);//creates a connection to the database first 
            Statement st = conn.createStatement();//creates a statement 
            return st.executeQuery(sql);//makes a table of data from the query and returns it so that it can be used in the other classes
        } catch (SQLException e) {//if theres an exception it returns nothing and shows what went wrong
            e.printStackTrace();//shows went wrong
            return null;//returns nothing
        }
    }
    //polymorphism, the find methods in teh database, like in assignment 4 
    //thr first find method is used to return full resultsets while thsi one returns specific values only from columns
    public String find(String sql, String column){//this method simple finds the sql query and returns that specific string
        try{//same as above methods, connects to teh database lines 44-47 are repeated throughout the code
            Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                String info = rs.getString(column);//gets the string info for the column asked
                return info;//returns that info                          
            }
            return "NA";//if there is no value for the sql injection it returns NA
        }
         catch(SQLException e){//same as above catches the exceptions
            e.printStackTrace();
            return "SQL issue!";//returns sql issue if something wnet wrong to error catch 
        }
    }
    public void update(String itemName, int newQuantity){//uses teh update sql function to update quantity after button clicks
        String sql = "UPDATE Inventorytbl SET Quantity = " + newQuantity + " WHERE ItemName = '" + itemName + "'";//updates the quantity based on the itemname given 
    try (Connection conn = DriverManager.getConnection(url);//same as above methods, creates a connection to the database
        Statement st = conn.createStatement()) {//creates a statement
        st.executeUpdate(sql);//updates the database
        //System.out.println("Database updated!"); //can be used to check and see when its updated but i just commented it out for now
    } catch (SQLException e) {//catches any sql exceptions 
        e.printStackTrace();
    }
    }
    public void insert(String name, int qty, String type, String link, String cat, String imgPath){
        // A new method that adds the info to the databse
        String sql = "INSERT INTO Inventorytbl (ItemName, Quantity, QuantityType, ShopLink, Category, ImagePath) " +
                 "VALUES ('" + name + "', " + qty + ", '" + type + "', '" + link + "', '" + cat + "', '" + imgPath + "')";
        //the sql statement that inserts the items into the inventory 
         try{//same as above methods, connects to teh database lines 44-47 are repeated throughout the code
            Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            st.executeUpdate(sql);//updates the database
            st.close();//closes the statement
        } catch (Exception e) {//try catch for
            e.printStackTrace();
        }
    }
    public String[][] APIrecipes() {
        String[][] recipeResults = new String[5][2];
        //The recipe results array is a 2d array and has the first column as name of recipe and a link of recipe in the second column, 
        // since this is based on teh ingredients you have at that moment, we use an array that holds the info for that run and not long term using the database
        String Ingredients = "";//empty containger for now

        String sql = "SELECT ItemName FROM Inventorytbl WHERE Quantity > 0";//the sql statement that can bring all items in stock
        try {
            ResultSet rs = find(sql);//uses an existing method to get a resultset of data with the item names where quantity is above 0
            while (rs != null && rs.next()) {//makes sure the resultset is not empty
                String itemName = rs.getString("ItemName").trim();//stores the resultset item into a string, it goes based on whats first, which is why it is in a while loop
                if (!itemName.isEmpty()) {//makes sure the iten name recieved from the resultset is not empty
                    if (!Ingredients.isEmpty()) {//makes sure to not add a comma for the first item, where its empty at first
                        Ingredients = Ingredients + ","; //adds a comma for the request from the first ingredient onwards
                    }
                    Ingredients = Ingredients + itemName;//adds the itemname into the string, so its a long string of "milk,cheese,flour" etc etc 
                }
            }
        } catch (Exception e) {//try catch that just prints what went wrong, since we are using a resultset, its safe to put it in a try catch
            e.printStackTrace();
        }

        if (Ingredients.isEmpty()) {//this just makes sure we are not sending an empty ingredients list to the api, if empty, return the empty array
            return recipeResults;
        }

        try {//a try catch is just safe to use since we are dealing with url connections
            String safeIngredients = URLEncoder.encode(Ingredients, "UTF-8");//This specifically encodes the ingredients string to match what needs to be sent to the api, i had to encode it or it would crash
            //like "pasta, eggs, chicken" gets translated to "pasta%2C+eggs%2C+chicken"
            //the UTF_8 is just the charset commonly used by java
        
            String webAddress = "https://recipeapi.io/api/v1/recipes?lang=en&ingredients=" + safeIngredients;
            //webaddress now has the encoded ingredients added to it, the first part of the url is from teh api website, i cited teh website at teh top its called recipeapi

            URL webUrl = java.net.URI.create(webAddress).toURL();//turns the string into an actual url that can be used to create a connection

            HttpURLConnection connection = (HttpURLConnection) webUrl.openConnection();//creates a connection, i got this from the yt video, it essentially just makes a connection to the website and gets the info asked
            connection.setRequestMethod("GET");
            //the requestmethod just returns whats asked, whic is the method GET, there are multiple request types, this one just works best 
            connection.setRequestProperty("Authorization", "Bearer sk_live_B6TvrRtEpr3TUcyeyhHeanh9zAsI0juKaLRvG4t834386f67");
            //this is the api key used, we need to register using our gmail on teh api website to have access, and the free version only gives you 500 queries per month
            //pleease be careful with the amount of queries you make, i dont think its even possible to get more than 500 a month

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //the buffered reader is what is used to read the info given from the api
            String jsonResponse = "";//the empty strings are used to take the inputs and actually store them into a string
            //the api website also gave out json responses without using a code based connection
            String textLine = "";//this updates each loop

            while ((textLine = reader.readLine()) != null) {//uses a while loop to check each line and make sure its not empty
                jsonResponse = jsonResponse + textLine;//stores each line into the json response string
            }
            reader.close();
            connection.disconnect();
            //once the information is stored into a string, the reader and the connection is closes/disconnected since leaving then open would cause issues 

            int count = 0;//count is the amount of recipes we are getting from the api
            int position = 0;//position is stores as the starting index of 

            //FOR CONTEXT!!!, this below is a SMALL part of the string that the api returns based on the ingredients you have, while we only need the recipe name and the link since i am not displaying alll that 
            //this entire string is ONEE of 5 recepies and is all in the same string, so i had to use string functions to get specifically the dishname or recepie name

            //"name":"Tres Leches Cake (American)","description":"American style Tres Leches Cake is a moist sponge cake soaked in a rich mixture of three kinds of milk, creating a decadent and creamy dessert popular in Latin American cuisine.","difficulty":"medium",
            // "meal_type":"dessert","cuisine":"american","dietary_tags":["nut_free","vegetarian"],"servings":12,"prep_time":20,"cook_time":35,"calories_per_serving":340,"protein":7,"instructions"
            // :["Preheat the oven to 175\u00b0C (350\u00b0F). Grease and flour a 9x13 inch baking pan.","In a large bowl, beat eggs for 5 minutes until fluffy and lemon-colored.","Gradually add 1 cup sugar while continuing to beat until well combined.",
            // "Sift together flour and baking powder, then gently fold into the egg mixture until just combined.","Pour batter into the prepared baking pan and bake for 30 to 35 minutes or until a toothpick inserted in the center comes out clean."
            // ,"Allow the cake to cool completely in the pan.","In a separate bowl, whisk together sweetened condensed milk, evaporated milk, and whole milk.","Using a fork or skewer, poke holes all over the cooled cake, then slowly pour the milk mixture evenly over the cake, allowing it to soak in.",
            // "Refrigerate the cake for at least 4 hours or overnight to absorb the milk mixture.","To prepare the topping, beat heavy cream with powdered sugar and vanilla extract until stiff peaks form.","Spread the whipped cream evenly over the soaked cake.",
            // "Optionally decorate with fresh strawberries or a sprinkle of cinnamon before serving."],"ingredients":[{"id":26,"name":"Flour","category":"grain","quantity":1,"unit":"cup","optional":false},{"id":29,
            // "name":"Egg","category":"egg","quantity":5,"unit":"piece","optional":false},{"id":64,"name":"Milk","category":"dairy","quantity":120,"unit":"ml","optional":false},{"id":128,
            // "name":"Sugar","category":"sweetener","quantity":1,"unit":"cup","optional":false},{"id":190,"name":"Heavy cream","category":"dairy","quantity":240,
            // "unit":"ml","optional":false},{"id":258,"name":"Baking powder","category":"other","quantity":1,"unit":"tsp","optional":false},{"id":312,"name":"Vanilla extract","category":"spice","quantity":1,"unit":"tsp","optional":false},{"id":332,
            // "name":"Sweetened condensed milk","category":"dairy","quantity":400,"unit":"ml","optional":false},{"id":335,"name":"Cinnamon","category":"spice","quantity":1,"unit":"pinch","optional":true},{"id":540,
            // "name":"Evaporated milk","category":"dairy","quantity":370,"unit":"ml","optional":false},{"id":541,"name":"Confectioner sugar","category":"sweetener","quantity":2,"unit":"tbsp","optional":false},{"id":542,"name":"Strawberry","category":"fruit","quantity":100,"unit":"g","optional":true}]}]
            
            //System.out.println(jsonResponse);
            
            while (count < 5) {//we only take 5 recipes to display 
                //Finds the word "name" using index of
                position = jsonResponse.indexOf("\"name\":\"", position);//the backward slashes are used to indicate that the quotations are literallly there and are not part of the syntax
                //the position has to change since there will be multiple recipes in the same string, so at first it starts from 0, but then the starting point has to change so we get new recipes

                if (position == -1) {//if u cant find anyy instance of "name", then we have no recipes and the loop must break and the 
                    break; 
                }
                //the recipe name actually starts 8 letters after the index since it goes like "name":"Tres Leches Cake (American)","description"
                //4 letters, 3 quotations and a colon- 8 characters after index of
                int startName = position + 8;
            
                //theres a bunch of quotations in the string, so to be careful, it goes from the Tres part, like the actual name and as soon as u find the next quotation mark, it ends 
                int endName = jsonResponse.indexOf("\"", startName);

                //now that we have the starting and ending index, we just make a new string of the name
                String newName = jsonResponse.substring(startName, endName);

           
                // i had to check if the characters immediately following the quote are ,"description", since even ingredient names start with "name";
                //ingredients cant be a recipe so i had to edit that
                if (jsonResponse.startsWith(",\"description\"", endName + 1)) {
                    //if the string extracted is actually a recipe, it goes into the array and the counter goes up, or the while loop continues
                    String searchName = newName.replace(" ", "+");//the spaces of the names had to replaced with + since that is how urls work
                    String recipeLink = "https://www.allrecipes.com/search?q=" + searchName + "+recipe";
                    //this is a random website that seems to have all recipes so this could take you to a full recipe

                    recipeResults[count][0] = newName;//the name and link are then stored into an array and returned 
                    recipeResults[count][1] = recipeLink;
                
                    count++; // Only increase the count if we actually saved a recipe
                }
                position = endName;//we change the position so that a neww recipe name can be made instead of repeats 
            }

        } catch (Exception e) {//a try catch just in case 
            System.err.println("Someee issue " + e.getMessage());
        }
        return recipeResults;// if it causes an error , it goes here and whatevers in the array at that point gets returned
    }   
}
