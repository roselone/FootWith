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
        HashMap<String,String> resp=new HashMap<String, String>();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String context=request.getParameter("getPlanRecord");
        if (!Util.isEmpty(context)){
            try {
                HashMap<String,String> map= JSONHelper.getJSONHelperInstance().convertToMap(context);
                String planList=map.get("planList");
                String recordList=map.get("recordList");
                HashMap<String,String> result=new HashMap<String, String>();
                if (!Util.isEmpty(planList)){
                    result.putAll(Mediator.getUserPlans(planList));
                }
                if (!Util.isEmpty(recordList)){
                    result.putAll(Mediator.getUserRecords(recordList));
                }

                resp.put("planrecord",JSONHelper.getJSONHelperInstance().convertToString(result));

            } catch (TextFormatException e) {
                resp.put("state",e.getMessage());
                out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
                out.close();
            } catch (SQLException e) {
                resp.put("state",e.getMessage());
                out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
                out.close();
            } catch (JSONException e) {
                resp.put("state",e.getMessage());
                out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
                out.close();
            }
        }
        context=request.getParameter("addPlan");
        if (!Util.isEmpty(context)){

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