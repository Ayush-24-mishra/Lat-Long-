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
import java.sql.ResultSet;
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
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
   
    Connection conn;
    PreparedStatement ps;
//    Map <String,String> hashmap = new HashMap<>();
    public void init()
    {
        try
                        Class.forName("oracle.jdbc.driver.OracleDriver");

            conn= DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","javabackend","java");
            ps = conn.prepareStatement("select username from newuser where username=? and password=?");
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
                String userid=request.getParameter("username");
                String userpwd=request.getParameter("password");
                PrintWriter pw=response.getWriter();
                response.setContentType("text/html");
                pw.println("<html><head><title>Login Response</title></head>");
                pw.println("<body>");

                try{
                    ps.setString(1,userid);
                    ps.setString(2,userpwd);
                    ResultSet rs=ps.executeQuery();
                    if(rs.next())
                    {
                        String username=rs.getString(1);
                        response.sendRedirect("success.html");
                        pw.println("<p>Hello <strong>"+username+"</strong><br>Welcome to our site!</p>");
                    }
                    else{
                        response.sendRedirect("Failure.html");
                        pw.println("<p>Sorry! You cannot login<br>Invalid credential<br>");
                        pw.println("<a href='signin.html'>Try again</a><br>");
                        pw.println("<a href='signup.html'>New User ?</a><br></p>");

                    }
                }catch(SQLException ex){
                    pw.println("<p>Server is experiencing heavy traffic.Please try later</p>");
                    System.out.println("Exception in doPost:"+ex);
                 }
    }
    }

    
    

