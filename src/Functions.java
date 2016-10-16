import java.sql.*;
import java.util.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jimmy
 */
public class Functions 
{
    public static ArrayList getList(Scanner in,int type)
    {
        ArrayList<String> userSelect = new ArrayList<String>();
        String contin=" ";
        String check;
        boolean firstinput=true;
        while(!contin.equals("n")){
            System.out.println("Type in the words you want to see");
            check= in.next();
            if((type==1||type==4)&&(check.equals("GroupName")||check.equals("HeadWriter")||check.equals("YearFormed")||check.equals("Subject")))
            {
                userSelect.add(check);
                System.out.println("Would you like to continue press 'n' to stop or any key to continue");
                contin=in.nextLine();
            }
            else if((type==2||type==4)&&(check.equals("PublisherName")||check.equals("PublisherAddress")||check.equals("PublisherPhone")||check.equals("PublisherEmail")))
            {
                userSelect.add(check);
                System.out.println("Would you like to continue press 'n' to stop or any key to continue");
                contin=in.nextLine();
            }
            else if((type==3||type==4)&&(check.equals("BookTitle")||check.equals("YearPublished")||check.equals("YearFormed")||check.equals("NumberPages")||check.equals("PublisherName")||check.equals("GroupName")))
            {
                userSelect.add(check);
                System.out.println("Would you like to continue press 'n' to stop or any key to continue");
                contin=in.nextLine();
            }
            else if((type==4)&&check.equals("*")||check.equals("all")&&firstinput)
            {
                userSelect.add("*");
                System.out.println("Displaying all variables...");
                break;
            }
            else{System.out.println("Invalid input");}
            contin=in.nextLine();
            firstinput=false;
        }   
        return userSelect;
    }
    public static void DisplaySelected(Statement stmt, ArrayList<String> inputs,int type,String book)
    {
        String sql;
        sql="SELECT DISTINCT ";
        for(int i=0; i<inputs.size(); i++)
        {
            sql+= inputs.get(i)+" ";
        }
        switch(type){
            case 1:  
                sql+="FROM WritingGroup";
                break;
            case 2: 
                sql+="FROM Publishers";
                break;
            case 3:
                sql+="FROM Book";
                break;     
            case 4:
                sql ="SELECT * FROM Book NATURAL JOIN PUBLISHERS NATURAL JOIN WRITINGGROUP WHERE BookTitle  = "+ "\'" +book+ "\'";
                break;
        }
        try
        {
            ResultSet rs = stmt.executeQuery(sql);
            for(int i=0; i<inputs.size(); i++)
            {
                System.out.printf("%-30s", inputs.get(i));
            }
            System.out.println("");
            while (rs.next())
            {
                for(int i=0; i<inputs.size(); i++){
                    String currentColumn = rs.getString(inputs.get(i));
                    System.out.printf("%-30s", JDBCProject.dispNull(currentColumn));
                }
                System.out.println();
            }
            rs.close();
        }
        catch (SQLException se) 
        {
             //Handle errors for JDBC
            se.printStackTrace();
        }
        
    
    }
    //cherry pick everything under this comment
    public static void insertBook(Statement stmt)
    {
		//The first part of the query is prepared
        String query = "INSERT INTO Book VALUES(";
        Scanner in = new Scanner(System.in);
            
            try
            {
            //get writingGroup
            System.out.println("Which writing Group wrote this book?");
            String writingGroup = in.nextLine();
            
            //check if writing Group exists
            ResultSet rs = stmt.executeQuery("SELECT GROUPNAME FROM WRITINGGROUP WHERE GROUPNAME = '" + writingGroup + "'");
                if(rs.next())
                {//if writing Group exists, append query
                    query += "'" + writingGroup + "',";
                }
                else
                {
                    System.out.printf("The writing group you have entered does not exist\n "
                                    + "Here is a list of valid groups.\n\n");
                    rs = stmt.executeQuery("SELECT GROUPNAME FROM WRITINGGROUP");
                    
                        while(rs.next())
                            {System.out.println(rs.getObject("GROUPNAME"));}
                            System.out.printf("\n Press Any Key To Continue...\n");
                            in.nextLine();
                        
                    //exit out of function    
                    return;
                }
            
			//get title and append query
            System.out.println("What is the title of the book?");
            query += "'" + in.nextLine() + "',";
            
            
	    //get publisher 
            System.out.println("Who published it?");
            String publisher = in.nextLine();
            
            //check if publisher exists
            rs = stmt.executeQuery("SELECT PUBLISHERNAME FROM PUBLISHERS WHERE PUBLISHERNAME = '" + publisher + "'");
               
                if(rs.next())
                {
                    query += "'" + publisher + "',";
                }
                else
                {
                    System.out.printf("The publisher you have entered does not exist\n "
                                    + "Here is a list of valid publishers.\n\n");
                    rs = stmt.executeQuery("SELECT PUBLISHERNAME FROM PUBLISHERS");
                    
                        while(rs.next())
                            {System.out.println(rs.getObject("PUBLISHERNAME"));}
                            System.out.printf("\n Press Any Key To Continue...\n");
                            in.nextLine();
                        
                    //exit out of function    
                    return;
                }
            
            
			//get date published
            boolean valid = false;
            int day = 0,month = 0, year = 0;
            String date = "";
                while(!valid)
                {
                    try
                    {   //get month,day, and year seperately, check each one for correctness
                        System.out.println("What year was it published in?");
                        year = in.nextInt();

                        System.out.println("On which month? (enter as Integer)");
                        month = in.nextInt();
                            if(month > 12 || month <= 0)
                              {month = 12;}
                                
                        System.out.println("On what day? (enter as Integer)");
                           day = in.nextInt();
                            if(day > 31 || day <= 0)
                              {day = 31;}    
                        
                        valid = true;
                    }
                    
                        //this loop will continue until the user enters a valid month
                    
                        catch(InputMismatchException e)
                         {
                             System.out.println("Input Must be an int\n");
                             in.next();
                         }
                  
                }    
               valid = false; //for later usage
               date = month + "/" + day + "/" + year; 
                
                query += "'" + date + "',";
            
                
                
			//get page number and append query
            System.out.println("How many pages does it have?");
            int pages = 0;
            
            while(!valid)
            {
                try
                {
                    pages = in.nextInt();
                    valid = true;
                }
                
                catch (InputMismatchException e)
                {
                    System.out.println("Input MUST be an Integer");
                    in.next();
                }
            }    
                
                query += pages + ")";

                    //execute query
           stmt.executeUpdate(query);
            
            
            }
            
            catch (SQLException se) 
            {
             //Handle errors for JDBC
            se.printStackTrace();
            }
            
               
        
    }
    
    public static void removeBook(Statement stmt)
    {
		//Prepare the first part of the query
        String query = "DELETE FROM BOOK WHERE BOOKTITLE = ";
        Scanner in = new Scanner(System.in);
        
        try
        {
        System.out.println("What book do you want to remove?");
		//get title, append query
        query += "'" + in.nextLine() + "'";
		
		//execute query
        stmt.executeUpdate(query);
        }
        
        catch (SQLException se) 
            {
             //Handle errors for JDBC
            System.out.println("The book you specified does not exist");
            }
        
    }
    public static ArrayList<String> DisplayBook(Statement stmt,Boolean getList)
    {
        ArrayList<String> bookList= new ArrayList<String>();
        try{
            String sql;
            sql="SELECT BookTitle FROM Book";
            ResultSet rs = stmt.executeQuery(sql);
            if(!getList){System.out.printf("%-20s\n","Book Titles");}
            while (rs.next())
            {
                if(!getList){
                    //Retrieve by column name
                    String BookTitle  = rs.getString("BookTitle");
                    //Display values
                    System.out.printf("%-20s\n",JDBCProject.dispNull(BookTitle));
                }
                else
                {
                    bookList.add(rs.getString("BookTitle"));
                }
            }
            if(!getList){System.out.println("-------------------------------------------------------------------------");}
            rs.close();
        }
        catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        
        return bookList;
    }
    public static void DisplayBookInformation(Statement stmt)
    {
        Scanner in=new Scanner(System.in);
        System.out.println("Please enter a book title");
        String input= in.nextLine();
        ArrayList<String> bookList=DisplayBook(stmt,true);
        boolean bookfound=false;
        for(int i=0; i<bookList.size(); i++)
        {
            String x=bookList.get(i);
            if((input.trim().equals(x.trim()))){bookfound=true;}
        }
        if(bookfound)
        {
            ArrayList<String> Select=getList(in,4);
            if((Select.get(0).equals("*"))){
                try{
                    String sql ="SELECT * FROM Book NATURAL JOIN PUBLISHERS NATURAL JOIN WRITINGGROUP WHERE BookTitle  = "+ "\'" +input+ "\'";
                    ResultSet rs = stmt.executeQuery(sql);
                    System.out.printf("%-30s%-20s%-20s%-20s%-20s%-20s%-20s%-30s%-30s%-30s%-20s\n","BookTitle","YearPublished","NumberPages","GroupName","HeadWriter","YearFormed","Subject","PublisherName","PublisherAddress","PublisherPhone","PublisherEmail");
                    while(rs.next())
                    {
                        String BookTitle=rs.getString("BookTitle");               
                        String YearPublished=rs.getString("YearPublished");         
                        String NumberPages=rs.getString("NumberPages"); 
                        String groupName = rs.getString("GroupName");
                        String HeadWriter = rs.getString("HeadWriter");
                        String YearFormed = rs.getString("YearFormed");
                        String Subject = rs.getString("Subject");
                        String PublisherName  = rs.getString("PublisherName");
                        String PublisherAddress  = rs.getString("PublisherAddress");
                        String PublisherPhone  = rs.getString("PublisherPhone");
                        String PublisherEmail = rs.getString("PublisherEmail");
                        System.out.printf("%-30s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-2s%-5s%-5s\n",JDBCProject.dispNull(BookTitle),JDBCProject.dispNull(YearPublished),JDBCProject.dispNull(NumberPages)
                                ,JDBCProject.dispNull(groupName),JDBCProject.dispNull(HeadWriter),JDBCProject.dispNull(YearFormed),JDBCProject.dispNull(Subject)
                                ,JDBCProject.dispNull(PublisherName),JDBCProject.dispNull(PublisherAddress),JDBCProject.dispNull(PublisherPhone),JDBCProject.dispNull(PublisherEmail));
                    }
                    System.out.println("-------------------------------------------------------------------------");
                    rs.close();
                }
                catch (SQLException se) {
                    //Handle errors for JDBC
                    se.printStackTrace();
                }
            }
            else{DisplaySelected(stmt, Select,4,input);}
        }
        else{System.out.println("Book not Found");}
    }
    public static void updatePublisher(Statement stmt)
    {
		//prepare first part of query1
        String query1 = "SELECT PUBLISHERNAME FROM PUBLISHERS WHERE PUBLISHERNAME = ";
        String target;
        ResultSet rs;
        Scanner in = new Scanner(System.in);
        
        try
        {
			//get publisher, append query 1
            System.out.println("What Publisher would you like to update?");
            target = in.nextLine();
            query1 += "'" + target + "'";
            
            rs = stmt.executeQuery(query1);
            
			//check if publisher exists, if yes execute below
            if(rs.next())
            {
				//prepare first part of updates to and PUBLISHERS and BOOK tables
                String update1 = "UPDATE PUBLISHERS SET PUBLISHERNAME = ";
                String update2 = "UPDATE BOOK SET PUBLISHERNAME = ";
				//get new publisher 
                System.out.println("What publisher would you like to replace them with?");
                String target2 = in.nextLine();
				
				//append updates
                update1 += "'" + target2 + "' WHERE PUBLISHERNAME = " + "'" + target + "'";
                update2 += "'" + target2 + "' WHERE PUBLISHERNAME = " + "'" + target + "'";
                
                //execute updates
                stmt.executeUpdate(update1);
                stmt.executeUpdate(update2);
            }
            
			//if publisher does not exist, user is informed
            else
            {
                System.out.println("This publisher does not exist");
            }
        }
        
        catch (SQLException se) 
            {
             //Handle errors for JDBC
            se.printStackTrace();
            }
        
        
    }
        public static void DisplayWritingGroup(Statement stmt)
    {
                
        try
        {
            String sql;
            sql="SELECT * FROM WritingGroup";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.printf(JDBCProject.displayFormat, "Group Name", "HeadWriter", "YearFormed", "Subject");
            while (rs.next())
            {
                //Retrieve by column name
                String groupName = rs.getString("GroupName");
                String HeadWriter = rs.getString("HeadWriter");
                String YearFormed = rs.getString("YearFormed");
                String Subject = rs.getString("Subject");
                //Display values
                System.out.printf(JDBCProject.displayFormat, 
                JDBCProject.dispNull(groupName), JDBCProject.dispNull(HeadWriter), JDBCProject.dispNull(YearFormed), JDBCProject.dispNull(Subject));
            }
            System.out.println("-------------------------------------------------------------------------");
            rs.close();
        }
        catch (SQLException se) 
        {
             //Handle errors for JDBC
            se.printStackTrace();
        }
    }
	
	
    public static void DisplayPublishers(Statement stmt)
    {
                
        try{
            String sql;
            sql="SELECT * FROM Publishers";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.printf("%-25s%-35s%-35s%-25s\n", "Publisher Name", "PublisherAddress", "PublisherPhone", "PublisherEmail");
            while (rs.next())
            {
                //Retrieve by column name
                String PublisherName  = rs.getString("PublisherName");
                String PublisherAddress  = rs.getString("PublisherAddress");
                String PublisherPhone  = rs.getString("PublisherPhone");
                String PublisherEmail = rs.getString("PublisherEmail");
                //Display values
                System.out.printf("%-20s%-20s%-25s%-35s\n", 
                JDBCProject.dispNull(PublisherName), JDBCProject.dispNull(PublisherAddress), JDBCProject.dispNull(PublisherPhone), JDBCProject.dispNull(PublisherEmail));
            }
            System.out.println("-------------------------------------------------------------------------");
            rs.close();
        }
        catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }
}
