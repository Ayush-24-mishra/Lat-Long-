/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lenovo
 */
@WebServlet(urlPatterns = {"/Register22"})
public class Register22 extends HttpServlet {

   Connection conn;
    PreparedStatement ps;
//    Map <String,String> hashmap = new HashMap<>();
    public void init()
    {
        try
                        Class.forName("oracle.jdbc.driver.OracleDriver");

            conn= DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","javabackend","java");
            ps = conn.prepareStatement("insert into newuser values(?,?,?)");
        }
        catch(ClassNotFoundException cnf)
        {
            System.out.println(cnf.getMessage());
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    } 
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try{
        ps.setString(1, username);
        ps.setString(2, email);
        ps.setString(3, password);
        int count = ps.executeUpdate();
        if(count>0)
        {
            response.sendRedirect("successregister.html");
        }
        else
            response.sendRedirect("failureregister.html");
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
