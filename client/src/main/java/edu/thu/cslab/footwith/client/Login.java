package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

//import edu.xdu.RL.FootWith.helper.ServerConnector;

public class Login extends Activity{
    /** Called when the activity is first created. */
	
	private Button loginButton;
	private Button registerButton;
	static public String userID="我";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        
        bindButton();
    }

	private void bindButton() {
		// TODO Auto-generated method stub
		loginButton=(Button)findViewById(R.id.button_login);
		loginButton.setOnClickListener(loginListener);
		
		registerButton=(Button)findViewById(R.id.button_register);
		registerButton.setOnClickListener(registerListener);
		
	}
	
	private OnClickListener loginListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(Login.this, FootWithActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
		}
	};
	
	private OnClickListener registerListener=new OnClickListener() {
		
		private ArrayList<NameValuePair> getParams(){
			ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
			EditText editText=(EditText)findViewById(R.id.username_edit);
			String values=editText.getText().toString();
			if (values.trim().length()!=0){
				Login.userID=values;
				params.add(new BasicNameValuePair("ID", values));
			}else{
				params.add(new BasicNameValuePair("judge", "用户名不得为空"));
				return params;
			}
			editText=(EditText)findViewById(R.id.password_edit);
			values=editText.getText().toString();
			if (values.trim().length()!=0){
				params.add(new BasicNameValuePair("PASSWORD", values));
			}else {
				params.add(new BasicNameValuePair("judge", "密码不得为空"));
				return params;
			}
			params.add(new BasicNameValuePair("judge", "true"));
			return params;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
			params=getParams();
			String judge=params.get(params.size()-1).getValue().toString();
			
			if (!judge.equals("true")){
				Toast.makeText(Login.this, judge, Toast.LENGTH_SHORT);
			}else{
//				params.add(new BasicNameValuePair("REQUEST", "CONFIRM"));
//				ServerConnector connector=new ServerConnector();
//				String result=connector.userRequest(params);
//				if (result.equals("Yes")){
//					Intent intent=new Intent();
//					intent.setClass(login.this, FootWithActivity.class);
//					startActivity(intent);
//					overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
//				}else {
//					Toast.makeText(login.this, result, Toast.LENGTH_SHORT).show();
//				}
			}
		}
	};
	
}
