/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souq.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aya
 */
public class CheckProfile extends HttpServlet {

    Connection c = null;
    Statement ps;
    String URL = "jdbc:postgresql://localhost:5432/e_commerce";
    String uname;
    String password;
    ResultSet result = null;
    int flag;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, "postgres", "ayaabdelkader");
            ps = c.createStatement();
            result = ps.executeQuery("select * from customer ");

            response.setContentType("text/html");
            out.println("<table  border =\"1\" cellpadding=\"5\" solid black align=center width=500px height=500px  margin-left=2px");

            RequestDispatcher disp = request.getRequestDispatcher("guestHeader.jsp");
            disp.include(request, response);

            RequestDispatcher side = request.getRequestDispatcher("SideBar.jsp");
            side.include(request, response);
            while (result.next()) {

                out.println("<h1 style=\"position: relative;left: 250px;top: 2px  \">Your Profile</h1><br>\n"
                        + "        <form action=\"UpdateProfile\" method=\"get\">\n"
                        + "        <div style=\"position: relative;left: 230px;top: 2px;font-size:20px  \">\n"
                        + "            Date Of Birth &emsp;: &emsp;" + result.getString(2) + " \n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 230px;top: 2px;font-size:20px  \">\n"
                        + "            Job &emsp; &emsp;&emsp; &emsp; &ensp;:   &emsp; " + result.getString(3) + "\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 230px;top: 2px;font-size:20px  \">\n"
                        + "            First Name &emsp;  &ensp; :   &emsp;   " + result.getString(4) + "\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 230px;top: 2px ;font-size:20px \">\n"
                        + "            Last Name &ensp; &ensp;   &ensp;:  &emsp; " + result.getString(5) + "\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 530px;top: 2px ;font-size:20px \">\n"
                        + "            User Name &ensp; &emsp; : &emsp;" + result.getString(6) + "\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 530px;top: 2px ;font-size:20px \">\n"
                        + "            Email  &emsp;&emsp;&emsp;&ensp;&ensp;&ensp; : &emsp;" + result.getString(8) + "\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 530px;top: 2px ;font-size:20px \">\n"
                        + "            Credit Limit  &emsp; &ensp; : &emsp;" + result.getString(9) + "\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 530px;top: 2px ;font-size:20px \">\n"
                        + "            Credit Number &ensp;   : &emsp;" + result.getString(10) + "\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 530px;top: 2px ;font-size:20px \">\n"
                        + "            Address &emsp; &emsp;&emsp;  : &emsp;" + result.getString(11) + "\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 530px;top: 2px ;font-size:20px \">\n"
                        + "            Password &ensp;  &ensp; &emsp; : &emsp;" + result.getString(12) + "\n"
                        + "        </div><br>\n"
                        + "        <div style=\"position: relative;left: 530px;top: 2px;font-size:20px  \">\n"
                        + "            Interests &emsp; &ensp;&ensp;  &ensp;  : &emsp;  " + result.getString(13) + "\n"
                        + "        </div> <br>\n"
                        + "        </form>"
                        + "");

            }

            ps.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
