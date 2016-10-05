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
public class Functions {
    public static ArrayList getList(Scanner in,int type)
    {
        ArrayList<String> userSelect = new ArrayList<String>();
        String contin=" ";
        String check;
        while(!contin.equals("n")){
            System.out.println("Type in the words you want to see");
            check= in.next();
            if((type==1)&&(check.equals("GroupName")||check.equals("HeadWriter")||check.equals("YearFormed")||check.equals("Subject")))
            {
                userSelect.add(check);
                System.out.println("Would you like to continue press 'n' to stop or any key to continue");
                contin=in.nextLine();
            }
            else if((type==2)&&(check.equals("PublisherName")||check.equals("PublisherAddress")||check.equals("PublisherPhone")||check.equals("PublisherEmail")))
            {
                userSelect.add(check);
                System.out.println("Would you like to continue press 'n' to stop or any key to continue");
                contin=in.nextLine();
            }
            else if((type==3)&&(check.equals("BookTitle")||check.equals("YearPublished")||check.equals("YearFormed")||check.equals("NumberPages")))
            {
                userSelect.add(check);
                System.out.println("Would you like to continue press 'n' to stop or any key to continue");
                contin=in.nextLine();
            }
            else{System.out.println("Invalid input");}
            contin=in.nextLine();
        }   
        return userSelect;
    }
    public static void DisplaySelected(Statement stmt, ArrayList<String> inputs,int type)
    {
        String sql;
        sql="SELECT ";
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
        }
        try
        {
            ResultSet rs = stmt.executeQuery(sql);
            for(int i=0; i<inputs.size(); i++){
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
            rs.close();
        }
        catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }
    public static void DisplayBook(Statement stmt)
    {
                
        try{
            String sql;
            sql="SELECT BookTitle,YearPublished,NumberPages FROM Book";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.printf("%-25s%-35s%-35s\n", "BookTitle", "YearPublished", "NumberPages");
            while (rs.next())
            {
                //Retrieve by column name
                String BookTitle  = rs.getString("BookTitle");
                String YearPublished  = rs.getString("YearPublished");
                String NumberPages  = rs.getString("NumberPages");

                //Display values
                System.out.printf("%-20s%-20s%-25s\n", 
                JDBCProject.dispNull(BookTitle), JDBCProject.dispNull(YearPublished), JDBCProject.dispNull(NumberPages));
            }
            rs.close();
        }
        catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }
}
