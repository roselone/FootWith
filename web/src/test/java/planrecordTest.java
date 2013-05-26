import edu.thu.cslab.footwith.messenger.JSONHelper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/2/13
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class planrecordTest {
    private String URL="http://127.0.0.1:12580/web-1.0/planrecord";
    private HttpPost post=new HttpPost(URL);
//    @Test
//    public void doPost() throws IOException {
//        DefaultHttpClient client=new DefaultHttpClient();
//        HashMap<String,String> userMap=new HashMap<String, String>();
//        //userMap.put("planList","[58,59,61]");
//        userMap.put("recordList","[13,14]");
//        ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
//        param.add(new BasicNameValuePair("getPlanRecord", JSONHelper.getJSONHelperInstance().convertToString(userMap)));
//        post.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));
//        HttpResponse response=client.execute(post);
//        HttpEntity entity=response.getEntity();
//        String tmp= EntityUtils.toString(entity);
//        System.out.println(tmp);
//        HashMap<String,String> res=JSONHelper.getJSONHelperInstance().convertToMap(tmp);
//        System.out.println(res.get("state"));
//        HashMap<String,String> planrecord=JSONHelper.getJSONHelperInstance().convertToMap(res.get("planrecord"));
//        System.out.println(planrecord.toString());
//
//    }
//    @Test
//    public void startPlanTest() throws IOException {
//        DefaultHttpClient client=new DefaultHttpClient();
//        HashMap<String,String> userMap=new HashMap<String, String>();
//        userMap.put("planList","[15,16,25]");
//        userMap.put("recordList","[13,14,15]");
//        ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
//        param.add(new BasicNameValuePair("start", "25"));
//        post.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));
//        HttpResponse response=client.execute(post);
//        HttpEntity entity=response.getEntity();
//        String tmp= EntityUtils.toString(entity);
//        System.out.println(tmp);
//        HashMap<String,String> res=JSONHelper.getJSONHelperInstance().convertToMap(tmp);
//        System.out.println(res.get("state"));
//        HashMap<String,String> planrecord=JSONHelper.getJSONHelperInstance().convertToMap(res.get("recordID"));
//        System.out.println(planrecord.toString());
//
//    }
}
