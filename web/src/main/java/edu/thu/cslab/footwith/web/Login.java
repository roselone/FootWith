package edu.thu.cslab.footwith.web;

import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.mediator.Mediator;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import org.json.JSONException;

import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String login=request.getParameter("login");
        HashMap<String,String> info= JSONHelper.getJSONHelperInstance().convertToMap(login);
        PrintWriter out=response.getWriter();
        HashMap<String,String> resp=new HashMap<String, String>();
        try {
            if (Mediator.isValid(info.get("userName"),info.get("passwd"))){
                resp.put("userinfo",Mediator.selectUser(info.get("userName")));
            }else{
                out.println("wrong username or password!");
            }
        } catch (TextFormatException e) {
            resp.put("state",e.getMessage());
            out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
            out.close();
        } catch (SQLException e) {
            resp.put("state",e.getMessage());
            out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
            out.close();
        } catch (NoSuchAlgorithmException e) {
            resp.put("state",e.getMessage());
            out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
            out.close();
        } catch (JSONException e) {
            resp.put("state",e.getMessage());
            out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
            out.close();
        }
        if (!resp.containsKey("state")){
            resp.put("state","successful");
            out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
            out.close();
        }
    }
}
