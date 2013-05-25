package edu.thu.cslab.footwith.web;

import edu.thu.cslab.footwith.mediator.Mediator;
import edu.thu.cslab.footwith.messenger.JSONHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/14/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "user")
public class User extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String likes=request.getParameter("like");
        int userID=Integer.valueOf(request.getParameter("userID"));
        PrintWriter out=response.getWriter();
        HashMap<String,String> resp=new HashMap<String, String>();
        try {
            Mediator.updateUserLike(userID, likes);
        } catch (SQLException e) {
            resp.put("state", e.getMessage());
            out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
            out.close();
        }
        if (!resp.containsKey("state")){
            resp.put("state","successful");
            out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
