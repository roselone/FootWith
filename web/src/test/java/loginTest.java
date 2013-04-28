import edu.thu.cslab.footwith.messenger.JSONHelper;
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
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 4/26/13
 * Time: 11:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class loginTest {
    private String URL="http://127.0.0.1:8080/web-1.0/login";
    private HttpPost post=new HttpPost(URL);
    @Test
    public void doPost() throws IOException {
        DefaultHttpClient client=new DefaultHttpClient();
        HashMap<String,String> userMap=new HashMap<String, String>();
        userMap.put("userName","roselonelh@gmail.com");
        userMap.put("passwd","00371989");
        ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("login", JSONHelper.getJSONHelperInstance().convertToString(userMap)));
        post.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));
        HttpResponse response=client.execute(post);
        HttpEntity entity=response.getEntity();
        System.out.println(EntityUtils.toString(entity));
    }
}
