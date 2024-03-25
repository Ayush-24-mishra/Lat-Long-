
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
public class DBConn {
   

    private static ResultSet rs=null;
    private static Connection conn=null;
            static{
    try{
    conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","javabackend","java");
    Statement st = conn.createStatement();
    rs = st.executeQuery("select * from personaldata");
    
     
     
     
     
    }
    catch(SQLException ex)
    {
        System.out.println("error"+ex);
            
    }
            }
            
            
            
            
            
       public static List<String> showNames() throws SQLException 
       {
           String username;
            List<String> data=new ArrayList<>();
     while(rs.next())
            {
                 username = rs.getString(0);
                data.add(0, username);
                
            }
     return data;
       }
       
            
           
}
            
            
    
    
   
    

