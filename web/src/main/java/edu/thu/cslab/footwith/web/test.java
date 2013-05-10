package edu.thu.cslab.footwith.web;

import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.utility.Util;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 4/16/13
 * Time: 10:02 PM
 * To change this template use File | Settings | File Templates.
 */

public class test extends HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        PrintWriter out=response.getWriter();
        String imageUrl= getServletContext().getRealPath("/WEB-INF/classes/images/test.jpg");
        String mime= getServletContext().getMimeType(imageUrl);
        if (mime==null){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.setContentType("application/json;charset=UTF-8");
        File image=new File(imageUrl);
        FileInputStream in =new FileInputStream(image);
        byte[] buf=new byte[(int)image.length()];
        in.read(buf);

        HashMap<String,String> map=new HashMap<String, String>();
        map.put("test.jpg", Util.string2Json(Base64.encodeBase64String(buf)));
        out.write(JSONHelper.getJSONHelperInstance().convertToString(map));
        out.close();
        in.close();

    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ServletOutputStream out=response.getOutputStream();
        String imageUrl= getServletContext().getRealPath("/WEB-INF/classes/images/test.jpg");
        String mime= getServletContext().getMimeType(imageUrl);
        if (mime==null){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.setContentType(mime);
        File image=new File(imageUrl);
        FileInputStream in =new FileInputStream(image);
        byte[] buf=new byte[1024];
        int count=0;
        while ((count=in.read(buf))>=0){
            out.write(buf,0,count);
        }
        out.close();
        in.close();
    }
}
