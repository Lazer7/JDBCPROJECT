import java.sql.*;
import java.util.Scanner;
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
            sql="SELECT BookTitle,YearPublished,NumberPages FROM Publishers";
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
