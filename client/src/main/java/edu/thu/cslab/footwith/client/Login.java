package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.baidu.mapapi.BMapManager;
import edu.thu.cslab.footwith.client.helper.Constant;
import edu.thu.cslab.footwith.client.helper.ServerConnector;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.utility.Util;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

//import edu.xdu.RL.FootWith.helper.ServerConnector;

public class Login extends Activity{
    /** Called when the activity is first created. */
	
	private Button loginButton;
	private Button registerButton;
	static public String userID="TODO";
    static public String userName="me";
    static public String userNickName;
    static public String userSex;
    static public String userPlans;
    static public String userRecords;
    static public HashMap<Integer,String> userLike=new HashMap<Integer, String>();
    static public String userMarks;
    static public String sinaWeiboToken;
    static public String sinaExpiresIN;
    static public BMapManager mBMapMan = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        
        bindButton();
    }

	private void bindButton() {
		// TODO Auto-generated     method stub
		loginButton=(Button)findViewById(R.id.button_login);
		loginButton.setOnClickListener(loginListener);
		
		registerButton=(Button)findViewById(R.id.button_register);
		registerButton.setOnClickListener(registerListener);
		
	}
	
	private OnClickListener loginListener=new OnClickListener() {
        private String getTextValue(int id){
            EditText editText=(EditText)findViewById(id);
            String value=editText.getText().toString().trim();
            return value;
        }

        private boolean addParams(String key,String value,HashMap<String,String> map){
            if (!Util.isEmpty(value)){
                map.put(key,value);
                return true;
            }
            return false;
        }

        private String getParams(HashMap<String,String> params){

            String values=getTextValue(R.id.username_edit);
            if (!addParams("userName",values,params)){
                return "userName不得为空";
            }

            values=getTextValue(R.id.password_edit);
            if (!addParams("passwd",values,params)){
                return "passwd null error";
            }

            return "Valid";
        }
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
            HashMap<String,String> params=new HashMap<String, String>();
            String judge=getParams(params);

            if (!judge.equals("Valid")){
                Toast.makeText(Login.this, judge, Toast.LENGTH_SHORT).show();
            }else{
                ServerConnector connector=new ServerConnector("login");
                String result= null;
                try {
                    result = connector.setRequestParam("login", JSONHelper.getJSONHelperInstance().convertToString(params)).doPost();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                if (!Util.isEmpty(result)){
                    HashMap<String,String> res=JSONHelper.getJSONHelperInstance().convertToMap(result);
                    if (res.get("state").equals("successful")){
                        HashMap<String,String> userinfo=JSONHelper.getJSONHelperInstance().convertToMap(res.get("userinfo"));
                        //TODO
                        userNickName=userinfo.get("nickName");
                        userID = userinfo.get("userID");
                        userPlans= userinfo.get("plans");
                        userRecords = userinfo.get("records");
                        userMarks = userinfo.get("marks");
                        userSex = userinfo.get("sex");
                        sinaWeiboToken=userinfo.get("sinaWeiboToken");
                        sinaExpiresIN=userinfo.get("sinaExpiresIN");

                        String like=userinfo.get("like_name");
                        if (!Util.isEmpty(like)){
                            try {
                                Vector<String> id_name=JSONHelper.getJSONHelperInstance().convertToArray2(like);
                                for (int i=0;i<id_name.size();i++){
                                    String[] tmp=id_name.get(i).split(":");
                                    userLike.put(Integer.valueOf(tmp[0]),tmp[1]);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                        }


                        Intent intent=new Intent();
                        intent.setClass(Login.this, FootWithActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                    }else{
                        Toast.makeText(Login.this, res.get("state"), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Login.this, "connection failure", Toast.LENGTH_SHORT).show();
                }
            }
		}
	};
	
	private OnClickListener registerListener=new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
            Intent intent=new Intent();
            intent.setClass(Login.this, Register.class);
            startActivity(intent);
		}
	};
	
}
