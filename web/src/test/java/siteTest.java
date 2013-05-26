import edu.thu.cslab.footwith.messenger.JSONHelper;
import net.iharder.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/10/13
 * Time: 9:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class siteTest {
    private String URL="http://127.0.0.1:12580/web-1.0/site";
    private HttpPost post=new HttpPost(URL);
    @Test
    public void doPost() throws IOException, JSONException {
        DefaultHttpClient client=new DefaultHttpClient();
        ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("location", "北京"));
        post.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));
        HttpResponse response=client.execute(post);
        HttpEntity entity=response.getEntity();
        String tmp= EntityUtils.toString(entity);
        System.out.println(tmp);
        HashMap<String,String> res= JSONHelper.getJSONHelperInstance().convertToMap(tmp);
        System.out.println(res.get("state"));
        Vector<String> id_names=JSONHelper.getJSONHelperInstance().convertToArray2(res.get("id_names"));
        System.out.println(id_names.toString());

    }
    @Test
    public void getSiteTest() throws IOException, JSONException {
        DefaultHttpClient client=new DefaultHttpClient();
        ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("siteID", "2"));
        post.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));
        HttpResponse response=client.execute(post);
        HttpEntity entity=response.getEntity();
        String tmp= EntityUtils.toString(entity);
        System.out.println(tmp);
        HashMap<String,String> res= JSONHelper.getJSONHelperInstance().convertToMap(tmp);
        System.out.println(res.get("state"));
        HashMap<String,String> siteMap=JSONHelper.getJSONHelperInstance().convertToMap(res.get("site"));
        System.out.println(siteMap.toString());
//        String picture=siteMap.get("picture");
//        System.out.println(picture);
//        byte[] image= Base64.decode(siteMap.get("picture"));

//        FileOutputStream outputStream=new FileOutputStream("/home/roselone/test8.jpg");
//        outputStream.write(image);
//        outputStream.close();
    }
}
