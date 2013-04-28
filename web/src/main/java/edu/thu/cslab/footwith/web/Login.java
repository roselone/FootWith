package edu.thu.cslab.footwith.web;

import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.mediator.Mediator;
import edu.thu.cslab.footwith.messenger.JSONHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 4/26/13
 * Time: 11:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text");
        String login=request.getParameter("login");
        HashMap<String,String> info= JSONHelper.getJSONHelperInstance().convertToMap(login);
        PrintWriter out=response.getWriter();
        try {
            if (Mediator.isValid(info.get("userName"),info.get("passwd"))){
                out.print("successful");
            }else{
                out.println("wrong username or password!");
            }
        } catch (TextFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        out.close();
    }
}
