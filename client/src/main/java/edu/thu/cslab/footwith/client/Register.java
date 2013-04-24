package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

//import edu.xdu.RL.FootWith.helper.ServerConnector;

public class Register extends Activity {
	private Button registerButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register);
        
        bindButton();
    }
    
	private void bindButton() {
		// TODO Auto-generated method stub
		
		registerButton=(Button)findViewById(R.id.register_button_register);
		registerButton.setOnClickListener(registerListener);
		
	}
	private OnClickListener registerListener=new OnClickListener() {
		
		private ArrayList<NameValuePair> getParams(){
			ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
			
			EditText editText=(EditText)findViewById(R.id.register_username_edit);
			String values=editText.getText().toString();
			if (values.trim().length()!=0){
				params.add(new BasicNameValuePair("ID", values));
				Login.userID=values;
			}else {
				params.add(new BasicNameValuePair("judge", "用户名不得为空"));
				return params;
			}
			editText=(EditText)findViewById(R.id.register_password_edit);
			values=editText.getText().toString();
			editText=(EditText)findViewById(R.id.register_repassword_edit);
			if (!values.equals(editText.getText().toString())){
				params.add(new BasicNameValuePair("judge", "密码不一致"));
				return params;
			}else if (values.trim().length()!=0) {
				params.add(new BasicNameValuePair("PASSWORD", values));
			}else {
				params.add(new BasicNameValuePair("judge", "密码不得为空"));
				return params;
			}
			RadioButton radioButton=(RadioButton)findViewById(R.id.register_male);
			if (radioButton.isChecked()){
				params.add(new BasicNameValuePair("SEX", "male"));
			}else {
				params.add(new BasicNameValuePair("SEX", "female"));
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
				Toast.makeText(Register.this, judge, Toast.LENGTH_SHORT);
			}else{
//				params.add(new BasicNameValuePair("REQUEST", "REGISTER"));
//				ServerConnector connector=new ServerConnector();
//				String result=connector.userRequest(params);
//				if (result.equals("Successful")){
//					Intent intent=new Intent();
//					intent.setClass(Register.this, FootWithActivity.class);
//					startActivity(intent);
//					overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
//				}else {
//					Toast.makeText(Register.this, result, Toast.LENGTH_SHORT).show();
//				}
			}
		}
	};
}
