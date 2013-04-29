import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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


/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 4/26/13
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class registerTest {
    private String dest;
    private DefaultHttpClient client = new DefaultHttpClient();
    private String URL="http://127.0.0.1:8080/web-1.0/register";
    private HttpPost post;
    private ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();

    @Test
    public void doPost() throws IOException {
//        HashMap<String,String> userinfo=new HashMap<String, String>();
//        userinfo.put("userName","test@test.com");
//        userinfo.put("nickName","test");
//        userinfo.put("passwd","123");
//        post=new HttpPost(URL);
//        param.add(new BasicNameValuePair("userinfo", JSONHelper.getJSONHelperInstance().convertToString(userinfo)));
//        post.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));
//        HttpResponse response=client.execute(post);
//        HttpEntity entity=response.getEntity();
//        System.out.println(EntityUtils.toString(entity));
//        return ;
    }
}
