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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/11/13
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "picture")
public class Picture extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        HashMap<String,String> resp=new HashMap<String, String>();
        PrintWriter out=response.getWriter();
        request.setCharacterEncoding("UTF-8");
        /**
         * {add : {recordID = 12, picture = {title userID pictrueName  picture time}}}
         */
        String context = request.getParameter("add");
        if (!Util.isEmpty(context)){
            HashMap<String,String> tmpMap= JSONHelper.getJSONHelperInstance().convertToMap(context);
            int recordID=Integer.valueOf(tmpMap.get("recordID"));
            HashMap<String,String> pictureMap=JSONHelper.getJSONHelperInstance().convertToMap(tmpMap.get("picture"));
            try {
                int pictureID= Mediator.addPicture(recordID, pictureMap);
                resp.put("pictureID",String.valueOf(pictureID));
            } catch (SQLException e) {
                resp.put("state", e.getMessage());
                out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
                out.close();
            } catch (TextFormatException e) {
                resp.put("state",e.getMessage());
                out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
                out.close();
            } catch (JSONException e) {
                resp.put("state",e.getMessage());
                out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
                out.close();
            }
        }
        /**
         * {modify : {title = blala , body = balab}}
         */
        //TODO modify time update
//        context=request.getParameter("modify");
//        if (!Util.isEmpty(context)){
//            HashMap<String,String> tmpMap=JSONHelper.getJSONHelperInstance().convertToMap(context);
//            int journalID=Integer.valueOf(tmpMap.get("journalID"));
//            HashMap<String,String> journalMap=JSONHelper.getJSONHelperInstance().convertToMap(tmpMap.get("journal"));
//            try {
//                Mediator.editJournal(journalID,journalMap);
//            } catch (SQLException e) {
//                resp.put("state",e.getMessage());
//                out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
//                out.close();
//            }
//        }
        /**
         * {remove : [12,32,3]}
         */
        context=request.getParameter("remove");
        if (!Util.isEmpty(context)){
            try {
                Vector<Integer> pictureVector=JSONHelper.getJSONHelperInstance().convertToArray(context);
                for (int i=0;i<pictureVector.size();i++){
                    Mediator.deletePicture(pictureVector.get(i));
                }
            } catch (JSONException e) {
                resp.put("state",e.getMessage());
                out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
                out.close();
            } catch (SQLException e) {
                resp.put("state",e.getMessage());
                out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
                out.close();
            }
        }
        context=request.getParameter("query");
        if (!Util.isEmpty(context)){
            try {
                HashMap<String,String> pictureMap=Mediator.getPictures(context);
                resp.put("picture",JSONHelper.getJSONHelperInstance().convertToString(pictureMap));
            } catch (JSONException e) {
                resp.put("state",e.getMessage());
                out.print(JSONHelper.getJSONHelperInstance().convertToString(resp));
                out.close();
            } catch (SQLException e) {
                resp.put("state",e.getMessage());
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
