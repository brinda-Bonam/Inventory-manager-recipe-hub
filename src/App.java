//Sreebrinda Bonam
//June 7th to June 16th 
//Culminating- Pantry organizer
// A system to organize and store data about your pantry using database and other features!


//Enhancement- GUI- Jframe using java swing
//Enhancement- Opens url using java.awt.desktop for the shopping links7
// Multiple different features with lots of database implementation and product functionability


//This is teh same video i used for assignment 1 and 4 for JFrame graphics
// https://youtu.be/Kmgo00avvEw?si=ysqPq5iRRgMV81id
//polymorphism info, again same as assignment 4
//https://www.w3schools.com/JAVA/java_polymorphism.asp

//there are parts of the code where i made buttons that opened a url stored on chrome or whatever ur default browser is and 3 major websites are used
//some fruits and vegitables are shopped from longos
//the rest of the items are shopped in walmart
//the other opens and searched for recipes in this website called allrecipes.com

//https://www.walmart.ca/en/search?q=
//https://voila.ca/search?q=
//it says viola but the larger known company is longos
//https://www.allrecipes.com/search?q=

//I am not promoting any of them, although they are cool websites and easy to use

//There are more refreneces in the other classes as well!!!
import java.io.File;//used for the cheacking if file exists
import java.util.Scanner;//Just like in the first and 4th assignment i was worried id lose marks for naming teh file wrong, so i made sure to get inputs in console if i cant find teh file

public class App {
    static Scanner scan = new Scanner(System.in);//i had to make them static beacuse it wouldnt run otherwise
    //these objects are used throughout the class so i initialized them at teh starting for higher scope
    static String file = "src\\Inventory.accdb";//this will get updated each time the file is in the wrong place and hopefully prevent eroors
        
    public static void main(String[] args) throws Exception {
       
        checkfile(); //calls the function to check if the file is found or not
        Database db = new Database();//initialized the other class for database
        db.setter(file);//uses a setter to set the variable since its in private- encapsulation and getters and settters
        //there was no need for a getter since there wasnt onjects to get 

        Jframe Frame = new Jframe();//initializes the graphics class
        Frame.MyFrame();//calls the main/major method there to start teh frame
    }
    public static void checkfile(){
        File dbFile = new File(file);//creates a file to check since the file path from before is simply a string
        if (!dbFile.exists()) {//uses recusrion so need if statements or a way out
            System.out.println("Error: Database file not found at " + dbFile.getAbsolutePath());//prints that teh prvios path given is wrong
            System.out.println("Enter the exact relative path to the accdb file!!!\nIt should be in the src folder so please dont move it around!!");//explains and asks to get teh exact file path 
            file = scan.nextLine();//stores the file value
            checkfile();//uses recursion to makee sure that the file is actually found/exists
        }
        else{
            System.out.println("file found!!");//if the file exists and is found, then recursion ends 
            return;
        }


    }
}
