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
import org.json.JSONException;
import org.junit.Test;

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
        param.add(new BasicNameValuePair("location", "beijing"));
        post.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));
        HttpResponse response=client.execute(post);
        HttpEntity entity=response.getEntity();
        String tmp= EntityUtils.toString(entity);
        System.out.println(tmp);
        HashMap<String,String> res= JSONHelper.getJSONHelperInstance().convertToMap(tmp);
        System.out.println(res.get("state"));
        Vector<String> journal=JSONHelper.getJSONHelperInstance().convertToArray2(res.get("id_names"));
        System.out.println(journal.toString());

    }
}
