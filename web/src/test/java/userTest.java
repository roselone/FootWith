import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.utility.Util;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/14/13
 * Time: 11:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class userTest {
    private String URL="http://127.0.0.1:12580/web-1.0/user";
    private HttpPost post=new HttpPost(URL);
//    @Test
//    public void doPost() throws IOException, JSONException {
//        DefaultHttpClient client=new DefaultHttpClient();
//        ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
//        param.add(new BasicNameValuePair("userID", "4"));
//        //param.add(new BasicNameValuePair("like", "[1,2]"));
//        HashMap<String,String> tokenMap=new HashMap<String, String>();
//        tokenMap.put("access_token","12345678901234567890123456789012");
//        tokenMap.put("expires_in","jsdafj");
//        param.add(new BasicNameValuePair("sinaToken",JSONHelper.getJSONHelperInstance().convertToString(tokenMap)));
//        post.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));
//        HttpResponse response=client.execute(post);
//        HttpEntity entity=response.getEntity();
//        String tmp= EntityUtils.toString(entity);
//        System.out.println(tmp);
//        HashMap<String,String> res=JSONHelper.getJSONHelperInstance().convertToMap(tmp);
//        System.out.println(res.get("state"));
//    }

}
