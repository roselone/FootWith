package edu.thu.cslab.footwith.web;

import edu.thu.cslab.footwith.mediator.Mediator;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.utility.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int userID=Integer.valueOf(request.getParameter("userID"));
        PrintWriter out=response.getWriter();
        HashMap<String,String> resp=new HashMap<String, String>();
        String context=request.getParameter("like");
        if (!Util.isEmpty(context))  {
            try {
                Mediator.updateUserLike(userID, context);
            } catch (SQLException e) {
                resp.put("state", e.getMessage());
                out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
                out.close();
            }
        }
        context=request.getParameter("sinaToken");
        if (!Util.isEmpty(context)){
            HashMap<String,String> tokenMap=JSONHelper.getJSONHelperInstance().convertToMap(context);
            String access_token=tokenMap.get("access_token");
            String expires_in=tokenMap.get("expires_in");
            try {
                Mediator.updateUserSinaToken(userID,access_token,expires_in);
            } catch (SQLException e) {
                resp.put("state", e.getMessage());
                out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
                out.close();
            }
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
