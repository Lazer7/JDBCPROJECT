
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
    public static void DISPLAY(Statement stmt)
    {
                
                try{
                    String sql;
                    sql="SELECT * FROM WritingGroup";
                    ResultSet rs = stmt.executeQuery(sql);
                    System.out.printf(JDBCProject.displayFormat, "Group Name", "HeadWriter", "YearFormed", "Subject");
                    while (rs.next()) {
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
                 catch (SQLException se) {
                //Handle errors for JDBC
                   se.printStackTrace();
                }
    }
}
