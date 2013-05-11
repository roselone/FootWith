import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.utility.Util;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.DefaultedHttpParams;
import org.apache.http.params.HttpParams;
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
 * Date: 4/26/13
 * Time: 11:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class loginTest {
    private String URL="http://127.0.0.1:12580/web-1.0/login";
    private HttpPost post=new HttpPost(URL);
    @Test
    public void doPost() throws IOException, JSONException {
        DefaultHttpClient client=new DefaultHttpClient();
        HashMap<String,String> userMap=new HashMap<String, String>();
        userMap.put("userName","hel");
        userMap.put("passwd","12345678");
        ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("login", JSONHelper.getJSONHelperInstance().convertToString(userMap)));
        post.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));
        HttpResponse response=client.execute(post);
        HttpEntity entity=response.getEntity();
        String tmp= EntityUtils.toString(entity);
        System.out.println(tmp);
        HashMap<String,String> res=JSONHelper.getJSONHelperInstance().convertToMap(tmp);
        System.out.println(res.get("state"));
        HashMap<String,String> userinfo=JSONHelper.getJSONHelperInstance().convertToMap(res.get("userinfo"));
        System.out.println(userinfo.toString());
        String likes=userinfo.get("like_name");
        if (!Util.isEmpty(likes)){
            Vector<String> id_name=JSONHelper.getJSONHelperInstance().convertToArray2(likes);
            System.out.println(id_name.toString());

            HashMap<Integer,String> userLike=new HashMap<Integer, String>();
            for (int i=0;i<id_name.size();i++){
                String[] tmps=id_name.get(i).split(":");
                userLike.put(Integer.valueOf(tmps[0]), tmps[1]);
            }
            System.out.println(userLike.toString());
        }
    }
}
