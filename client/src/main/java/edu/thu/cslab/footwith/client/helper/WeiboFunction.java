package edu.thu.cslab.footwith.client.helper;


import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.weibo.sdk.android.*;
import com.weibo.sdk.android.api.StatusesAPI;
import com.weibo.sdk.android.net.AsyncWeiboRunner;
import com.weibo.sdk.android.net.RequestListener;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by bxl on 5/23/13.
 */
public class WeiboFunction {
    private Context context;
    private Oauth2AccessToken accessToken;
    private String access_token, expires_in;
    static String weiboUpdateResult;
    static String weiboUpLoadResult;
    public WeiboFunction(Context context) {
        this.context = context;


    }
    public String WeiboStatusUpdate(final String content, final RequestListener requestListener) {

        weiboUpdateResult = "success";

        Weibo weibo = Weibo.getInstance(Constant.WeiboAppKey, Constant.WeiboRedirectURL);

        weibo.authorize(context, new WeiboAuthListener() {
            @Override
            public void onComplete(Bundle bundle) {
                access_token = bundle.getString("access_token");
                expires_in = bundle.getString("expires_in");
                accessToken = new Oauth2AccessToken(access_token, expires_in);

                StatusesAPI statusesAPI = new StatusesAPI(accessToken);
                if(requestListener!=null){
                    statusesAPI.update(content,null,null,requestListener);
                }
                else{
                    statusesAPI.update(content,null,null,new RequestListener() {
                        @Override
                        public void onComplete(String s) {
                            weiboUpdateResult = s;
                            weiboUpdateResult = "success";
                            //Toast.makeText(Record_Journal.this, "微博发布成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onIOException(IOException e) {
                            weiboUpdateResult = e.getMessage();
                            weiboUpdateResult = "fail";
                            //Toast.makeText(Record_Journal.this, "微博发布失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(WeiboException e) {
                            weiboUpdateResult = e.getMessage();
                            weiboUpdateResult = "fail";
                            //Toast.makeText(Record_Journal.this, "微博发布失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                /*
                WeiboParameters weiboParameters = new WeiboParameters();
                weiboParameters.add("access_token", access_token);
                weiboParameters.add("status", content);
                AsyncWeiboRunner.request("https://api.weibo.com/2/statuses/update.json", weiboParameters, "POST", new RequestListener() {
                    @Override
                    public void onComplete(String s) {
                        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onIOException(IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onError(WeiboException e) {
                        e.printStackTrace();

                    }
                });
                */


                /*
                SchemeRegistry schemeRegistry = new SchemeRegistry();
                schemeRegistry.register(new Scheme("https",
                        new EasySSLSocketFactory(), 80));
                schemeRegistry.register(new Scheme("https",
                        new EasySSLSocketFactory(), 443));
                schemeRegistry.register(new Scheme("https",
                        new EasySSLSocketFactory(), 8443));

                //HttpParams params = post.getParams();
                HttpParams params = new BasicHttpParams();
                HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
                HttpProtocolParams.setContentCharset(params, "utf-8");
                //params.setBooleanParameter("http.protocol.expect-continue", false);
                ClientConnectionManager connManager = new ThreadSafeClientConnManager(params, schemeRegistry);
                DefaultHttpClient client =  new DefaultHttpClient(connManager, params);
                HttpPost post = new HttpPost("https://api.weibo.com/2/statuses/update.json");
                ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
                param.add(new BasicNameValuePair("access_token", access_token));
                param.add(new BasicNameValuePair("status", content));
                try {
                    UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(param, HTTP.UTF_8);
                    post.setEntity(urlEncodedFormEntity);
                    //HttpHost target = new HttpHost("http://open.weibo.com/");

                    HttpResponse response = client.execute(post);

                    String result = EntityUtils.toString(response.getEntity());
                    System.out.println(result);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ClientProtocolException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                */
                }

            }


            @Override
            public void onWeiboException(WeiboException e) {
                weiboUpdateResult = e.getMessage();
                weiboUpdateResult = "fail";
            }

            @Override
            public void onError(WeiboDialogError weiboDialogError) {
                weiboUpdateResult = weiboDialogError.getMessage();
                weiboUpdateResult = "fail";
            }

            @Override
            public void onCancel() {

            }
        });

        return weiboUpdateResult;
    }
    public String WeiboStatusUpload(final String content, final String pic, final RequestListener requestListener) {

        weiboUpLoadResult = "success";

        Weibo weibo = Weibo.getInstance(Constant.WeiboAppKey, Constant.WeiboRedirectURL);

        weibo.authorize(context, new WeiboAuthListener() {
            @Override
            public void onComplete(Bundle bundle) {
                access_token = bundle.getString("access_token");
                expires_in = bundle.getString("expires_in");
                accessToken = new Oauth2AccessToken(access_token, expires_in);

                StatusesAPI statusesAPI = new StatusesAPI(accessToken);
                if(requestListener!=null){
                    statusesAPI.upload(content,pic,null,null,requestListener);
                }
                else{
                    statusesAPI.upload(content,pic,null,null,new RequestListener() {
                        @Override
                        public void onComplete(String s) {
                            weiboUpLoadResult = s;
                            weiboUpLoadResult = "success";
                            //Toast.makeText(Record_Journal.this, "微博发布成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onIOException(IOException e) {
                            weiboUpLoadResult = e.getMessage();
                            weiboUpLoadResult = "fail";
                            e.printStackTrace();
                            //Toast.makeText(Record_Journal.this, "微博发布失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(WeiboException e) {
                            weiboUpLoadResult = e.getMessage();
                            weiboUpLoadResult = "fail";
                            e.printStackTrace();
                            //Toast.makeText(Record_Journal.this, "微博发布失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            @Override
            public void onWeiboException(WeiboException e) {
                weiboUpLoadResult = e.getMessage();
                weiboUpLoadResult = "fail";
                e.printStackTrace();
            }

            @Override
            public void onError(WeiboDialogError weiboDialogError) {
                weiboUpLoadResult = weiboDialogError.getMessage();
                weiboUpLoadResult = "fail";
                weiboDialogError.printStackTrace();

            }

            @Override
            public void onCancel() {

            }
        });

        return weiboUpLoadResult;
    }

}




