package edu.thu.cslab.footwith.web;

import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.mediator.Mediator;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.utility.Util;
import org.json.JSONException;

import javax.servlet.ServletException;
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
 * Date: 5/2/13
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "planrecord")
public class PlanRecord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planRecord=request.getParameter("getPlanRecord");
        HashMap<String,String> req=new HashMap<String, String>();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        if (!Util.isEmpty(planRecord)){
            try {
                HashMap<String,String> map= JSONHelper.getJSONHelperInstance().convertToMap(planRecord);
                String planList=map.get("planList");
                String recordList=map.get("recordList");
                HashMap<String,String> result=new HashMap<String, String>();
                if (!Util.isEmpty(planList)){
                    result.putAll(Mediator.getUserPlans(planList));
                }
                if (!Util.isEmpty(recordList)){
                    result.putAll(Mediator.getUserRecords(recordList));
                }

                req.put("state","successful");
                req.put("planrecord",JSONHelper.getJSONHelperInstance().convertToString(result));
                out.print(JSONHelper.getJSONHelperInstance().convertToString(req));
                out.close();

            } catch (TextFormatException e) {
                req.put("state",e.getMessage()); //To change body of catch statement use File | Settings | File Templates.
                out.print(JSONHelper.getJSONHelperInstance().convertToString(req));
                out.close();
            } catch (SQLException e) {
                req.put("state",e.getMessage()); //To change body of catch statement use File | Settings | File Templates.
                out.print(JSONHelper.getJSONHelperInstance().convertToString(req));
                out.close();
            } catch (JSONException e) {
                req.put("state",e.getMessage()); //To change body of catch statement use File | Settings | File Templates.
                out.print(JSONHelper.getJSONHelperInstance().convertToString(req));
                out.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
