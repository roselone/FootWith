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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/10/13
 * Time: 9:17 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "site")
public class Site extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        HashMap<String,String> resp=new HashMap<String, String>();
        PrintWriter out=response.getWriter();
        request.setCharacterEncoding("UTF-8");
        String context=request.getParameter("location");
        if (!Util.isEmpty(context)){
            try {
                Vector<String> names= Mediator.getSiteNameWithLocation(context);
                System.out.println(context);
                resp.put("id_names",JSONHelper.getJSONHelperInstance().convertToString2(names));
            } catch (SQLException e) {
                resp.put("state", e.getMessage());
                out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
                out.close();
            }
        }
        context = request.getParameter("siteID");
        if (!Util.isEmpty(context)){
            try {
                HashMap<String,String> siteMap=Mediator.getSite(Integer.valueOf(context));
                resp.put("site",JSONHelper.getJSONHelperInstance().convertToString(siteMap));
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
