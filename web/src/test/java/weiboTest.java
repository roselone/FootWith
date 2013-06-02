import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 5/23/13
 * Time: 10:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class weiboTest {
/*
 *    @Test
 *    public void weibo()  {
 *        DefaultHttpClient client = new DefaultHttpClient();
 *        HttpPost post = new HttpPost("https://api.weibo.com/2/statuses/update.json");
 *        ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
 *        param.add(new BasicNameValuePair("access_token", "2.00sQ5khD7hVYGE1d5ad3bdfea1EAkC"));
 *        param.add(new BasicNameValuePair("status", "HelloTest"));
 *        try {
 *            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(param, HTTP.UTF_8);
 *            post.setEntity(urlEncodedFormEntity);
 *            HttpResponse response = client.execute(post);
 *
 *
 *            String result = EntityUtils.toString(response.getEntity());
 *            System.out.println(result);
 *
 *        } catch (UnsupportedEncodingException e) {
 *            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
 *        } catch (ClientProtocolException e) {
 *            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
 *        } catch (IOException e) {
 *            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
 *        }
 *
 *    }
 */
}
