package edu.thu.cslab.footwith.client.helper;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.weibo.sdk.android.*;
import com.weibo.sdk.android.api.AccountAPI;
import com.weibo.sdk.android.api.StatusesAPI;
import com.weibo.sdk.android.net.AsyncWeiboRunner;
import com.weibo.sdk.android.net.RequestListener;
import com.weibo.sdk.android.util.*;
import edu.thu.cslab.footwith.client.Login;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.utility.Util;
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
import java.util.HashMap;

/**
 * Created by bxl on 5/23/13.
 */
public class WeiboFunction {
    static private Oauth2AccessToken accessToken=null;
    static private String access_token=null, expires_in=null;
    static private String weiboUpdateResult;
    static private String weiboUpLoadResult;
    static private Weibo weibo = Weibo.getInstance(Constant.WeiboAppKey, Constant.WeiboRedirectURL);;
    //static private WeiboFunction weiboFunction = null;
    /*
    static public WeiboFunction getInstance(){
        if(weiboFunction==null){
            weiboFunction = new WeiboFunction();
        }
        return weiboFunction;
    }
    */
    static public boolean isBound(){
        if(!Util.isEmpty(Login.sinaWeiboToken) && !Util.isEmpty(Login.sinaExpiresIN)){
            access_token = Login.sinaWeiboToken;
            expires_in = Login.sinaExpiresIN;
            accessToken = new Oauth2AccessToken(access_token, expires_in);
            if(!accessToken.isSessionValid()){
                accessToken = null;
            }
        }
        return accessToken!=null;
    }
    static public boolean unBound(){

        if(isBound()){

            ServerConnector sc = new ServerConnector("https://api.weibo.com/oauth2", "/revokeoauth2");
            sc.setRequestParam("access_token", access_token);
            try {
                String result = sc.doPost();
                Log.d("revoke", "result");
            } catch (IOException e) {
                //accessToken = null;
                Log.d("revoke exception", e.getMessage());
                e.printStackTrace();
            }

            AccountAPI accountAPI =  new AccountAPI(accessToken);
            accountAPI.endSession(new RequestListener() {
                @Override
                public void onComplete(String s) {
                    clearToken();
                }

                @Override
                public void onIOException(IOException e) {
                    clearToken();
                }

                @Override
                public void onError(WeiboException e) {
                    clearToken();
                }
            });
            clearToken();
        }

        clearToken();
        return true;
    }
    static private void clearToken(){
        access_token = null;
        expires_in = null;
        accessToken = null;
        try {
            SendToken();
            Login.sinaWeiboToken = null;
            Login.sinaExpiresIN = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static public void authorize(Context context){
        if(accessToken!=null)
            return;
        if(!Util.isEmpty(Login.sinaWeiboToken) && !Util.isEmpty(Login.sinaExpiresIN)){
            access_token = Login.sinaWeiboToken;
            expires_in = Login.sinaExpiresIN;
            accessToken = new Oauth2AccessToken(access_token, expires_in);
            if(!accessToken.isSessionValid()){
                accessToken = null;
            }
        }

        WeiboParameters parameters = new WeiboParameters();
        parameters.add("forcelogin", "true");
        com.weibo.sdk.android.util.Utility.isWifi(context);
        weibo.startDialog(context, parameters, new WeiboAuthListener() {

        //weibo.authorize(context, new WeiboAuthListener() {
            @Override
            public void onComplete(Bundle bundle) {
                access_token = bundle.getString("access_token");
                expires_in = bundle.getString("expires_in");
                accessToken = new Oauth2AccessToken(access_token, expires_in);
                try {
                    SendToken();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onWeiboException(WeiboException e) {
                clearToken();
            }

            @Override
            public void onError(WeiboDialogError weiboDialogError) {
                clearToken();
            }

            @Override
            public void onCancel() {
                clearToken();
            }
        });
    }

    private static String SendToken() throws IOException {
        ServerConnector sc = new ServerConnector("user");
        HashMap<String,String> tokenMap= new HashMap<String, String>();
        tokenMap.put("access_token", access_token);
        tokenMap.put("expires_in", expires_in);
        sc.setRequestParam("userID", Login.userID);
        sc.setRequestParam("sinaToken", JSONHelper.getJSONHelperInstance().convertToString(tokenMap));
        String result = sc.doPost();
        return result;
    }

    private WeiboFunction() {
        weibo = Weibo.getInstance(Constant.WeiboAppKey, Constant.WeiboRedirectURL);

    }
    static public String WeiboStatusUpdate(Context context, final String content, final RequestListener requestListener) {

        weiboUpdateResult = "success";
        if(accessToken==null){
            WeiboParameters parameters = new WeiboParameters();
            parameters.add("forcelogin", "true");
            com.weibo.sdk.android.util.Utility.isWifi(context);
            weibo.startDialog(context, parameters, new WeiboAuthListener() {
            //weibo.authorize(context, new WeiboAuthListener() {
                @Override
                public void onComplete(Bundle bundle) {
                    access_token = bundle.getString("access_token");
                    expires_in = bundle.getString("expires_in");
                    accessToken = new Oauth2AccessToken(access_token, expires_in);
                    try {
                        SendToken();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    StatusesAPI statusesAPI = new StatusesAPI(accessToken);
                    if (requestListener != null) {
                        statusesAPI.update(content, null, null, requestListener);
                    } else {
                        statusesAPI.update(content, null, null, new RequestListener() {
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


                    }

                }

                @Override
                public void onWeiboException(WeiboException e) {
                    weiboUpdateResult = e.getMessage();
                    weiboUpdateResult = "fail";

                    clearToken();
                }

                @Override
                public void onError(WeiboDialogError weiboDialogError) {
                    weiboUpdateResult = weiboDialogError.getMessage();
                    weiboUpdateResult = "fail";

                    clearToken();
                }

                @Override
                public void onCancel() {
                    clearToken();

                }
            });
        }else{
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


            }
        }

        return weiboUpdateResult;
    }
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

    static public String WeiboStatusUpload(Context context, final String content, final String pic, final RequestListener requestListener) {

        weiboUpLoadResult = "success";
        if(accessToken==null){
            WeiboParameters parameters = new WeiboParameters();
            parameters.add("forcelogin", "true");
            com.weibo.sdk.android.util.Utility.isWifi(context);
            weibo.startDialog(context, parameters, new WeiboAuthListener() {
            //weibo.authorize(context, new WeiboAuthListener() {
                @Override
                public void onComplete(Bundle bundle) {
                    access_token = bundle.getString("access_token");
                    expires_in = bundle.getString("expires_in");
                    accessToken = new Oauth2AccessToken(access_token, expires_in);
                    try {
                        SendToken();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    StatusesAPI statusesAPI = new StatusesAPI(accessToken);
                    if (requestListener != null) {
                        statusesAPI.upload(content, pic, null, null, requestListener);
                    } else {
                        statusesAPI.upload(content, pic, null, null, new RequestListener() {
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
                    clearToken();
                }

                @Override
                public void onError(WeiboDialogError weiboDialogError) {
                    weiboUpLoadResult = weiboDialogError.getMessage();
                    weiboUpLoadResult = "fail";
                    weiboDialogError.printStackTrace();
                    clearToken();

                }

                @Override
                public void onCancel() {
                    clearToken();

                }
            });
        }else{
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

        return weiboUpLoadResult;
    }

}




