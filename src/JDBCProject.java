import java.sql.*;
import java.util.Scanner;
import java.util.*;
/**
 *
 * @author Jimmy Chao/Alex Tol
 */
public class JDBCProject {
    //  Database credentials
    static String USER;
    static String PASS;
    static String DBNAME;
    //This is the specification for the printout that I'm doing:
    //each % denotes the start of a new field.
    //The - denotes left justification.
    //The number indicates how wide to make the field.
    //The "s" denotes that it's a string.  All of our output in this test are 
    //strings, but that won't always be the case.
    static final String displayFormat="%-20s%-25s%-25s%-25s\n";
// JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";
//            + "testdb;user=";
/**
 * Takes the input string and outputs "N/A" if the string is empty or null.
 * @param input The string to be mapped.
 * @return  Either the input string or "N/A" as appropriate.
 */
    public static String dispNull (String input) {
        //because of short circuiting, if it's null, it never checks the length.
        if (input == null || input.length() == 0)
            return "N/A";
        else
            return input;
    }
/**
 * This displays The Menu and get the User's desired option
 * @param in is the Scanner to collect the user input
 * @return the user input 1-10
 */
    public static int displayMenu(Scanner in) 
    {   
        int UserInput=0;
        System.out.print( "Please type in the number corresponding to your choice \n"+
        "1) List all writing groups \n"+
        "2) List all the data for a group specified by the user \n"+
        "3) List all publishers \n"+
        "4) List all the data for a publisher specified by the user \n"+
        "5) List all book titles \n"+
        "6) List all the data for a book specified by the user \n"+
        "7) Insert a new book \n"+
        "8) Insert a new publisher and update all book published by one publisher to be published by the new publisher \n"+
        "9) Remove a book specified by the user \n"+
        "10) Exit \n"+
        "--------------------------------------------------------------------------------------------------------------------\n");
        do{
            try
            {
                UserInput= in.nextInt();
                if(UserInput<=0 || UserInput>10)
                {
                    throw new NumberFormatException();
                }
            }
            catch(NumberFormatException el){
                    System.out.println("Your input needs to be a number from 1 to 8");
                   
            }
            catch(InputMismatchException el){
                    System.out.println("Your input needs to be a number from 1 to 8");
                   
            }
            
        }while(UserInput<=0 || UserInput>10);
        return UserInput;
    }
    public static void main(String[] args) {
        //Prompt the user for the database name, and the credentials.
        //If your database has no credentials, you can update this code to 
        //remove that from the connection string.
        int userInput;
        Scanner in = new Scanner(System.in);
        System.out.print("Name of the database (not the user account): ");
        DBNAME = in.nextLine();
        System.out.print("Database user name: ");
        USER = in.nextLine();
        System.out.print("Database password: ");
        PASS = in.nextLine();
        //Constructing the database URL connection string
        DB_URL = DB_URL + DBNAME + ";user="+ USER + ";password=" + PASS;
        Connection conn = null; //initialize the connection
        Statement stmt = null;  //initialize the statement that we're using
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);       
            //DISPLAY THE MENU AND 9 OPTIONS FOR THE USER 
           
            do{
                //Display the Menu after each option choice
                userInput=displayMenu(in);
                //creates a connection to the database
                stmt = conn.createStatement();
                //A String ArrayList to hold all the User's Column Selection from the database
                ArrayList<String> userSelect = new ArrayList<String>();
                //Do the User's Selected Option
                switch(userInput)
                {
                    case 1:
                        //Display all information of the Writing Group
                        Functions.DisplayWritingGroup(stmt);
                        break;
                    case 2:
                        //Gets User's desired column selection
                         userSelect=Functions.getList(in, 1);
                         //Display the columns for the Writing Group
                         Functions.DisplaySelected(stmt, userSelect, 1,"");
                        break;
                    case 3: 
                        //Display all information of Publishers
                          Functions.DisplayPublishers(stmt);
                        break;
                    case 4: 
                        //Gets User's desired column selection
                         userSelect=Functions.getList(in, 2);
                         //Display the columns for the Publishers
                         Functions.DisplaySelected(stmt, userSelect, 2,"");
                        break;
                    case 5: 
                        //Display all Book Titles
                        Functions.DisplayBook(stmt,false);
                        break;
                    case 6: 
                        //Display a certain Book Information
                        Functions.DisplayBookInformation(stmt);
                        break;
                    case 7: 
                        //Insert a New Book
                            Functions.insertBook(stmt); 
                        break;
                    case 8:
                        //Updates a publisher or insert a new publisher
                            Functions.updatePublisher(stmt);
                        break;
                    case 9: 
                        //removes a book from the database
                            Functions.removeBook(stmt);
                        break;
                    
                }
                //empties the user's selection of columns from the array list
                userSelect.clear();
            }while(userInput != 10);
            
            
            //STEP 6: Clean-up environment
            //rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end FirstExample}
