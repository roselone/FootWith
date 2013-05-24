package edu.thu.cslab.footwith.client.helper;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 4/26/13
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerConnector {
    private String dest;
    private DefaultHttpClient client = new DefaultHttpClient();
    private HttpPost post;
    private ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
    public ServerConnector(String dest){
        this.dest=dest;
        post=new HttpPost(Constant.URL+dest);
    }
    public ServerConnector(String weibo, String dest){
        this.dest=dest;
        post=new HttpPost(weibo+dest);
    }
    public ServerConnector setRequestParam(String key,String value){
        param.add(new BasicNameValuePair(key,value));
        return this;
    }

    public String doPost() throws IOException {
        post.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));
        HttpResponse response=client.execute(post);
        return EntityUtils.toString(response.getEntity());
    }
}
