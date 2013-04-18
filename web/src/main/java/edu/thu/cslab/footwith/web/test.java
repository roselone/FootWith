package edu.thu.cslab.footwith.web;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 4/16/13
 * Time: 10:02 PM
 * To change this template use File | Settings | File Templates.
 */

public class test extends HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response){

    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("successful!!");
        out.close();
    }
}
