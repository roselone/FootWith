package edu.thu.cslab.footwith.web;

import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.mediator.Mediator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 4/26/13
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String userinfo=request.getParameter("userinfo");
        System.out.println(userinfo);
        try {
            int userID=Mediator.addUser(userinfo);
            if (userID!=-1){
                response.setContentType("text");
                PrintWriter out=response.getWriter();
                out.print("successful");
                out.close();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TextFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("successful!!");
        out.close();
    }
}
