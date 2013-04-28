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
import edu.thu.cslab.footwith.client.helper.Constant;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.utility.Util;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.thu.cslab.footwith.client.helper.ServerConnector;

public class Register extends Activity {
	private Button registerButton;
    private Logger logger= LogManager.getLogger(this.getClass().getName());
	
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

			String values=getTextValue(R.id.register_usermail_edit);
            if (!addParams("userName",values,params)){
                return "Email不得为空";
            }

            values=getTextValue(R.id.register_username_edit);
            if (!addParams("nickName",values,params)){
                return "nickName null error";
            }

			values=getTextValue(R.id.register_password_edit);
            String tmp=getTextValue(R.id.register_repassword_edit);
			if (!values.equals(tmp)){
				return "密码不一致";
			}else if (!addParams("passwd",values,params)) {
                return "密码不得为空";
            }

			RadioButton radioButton=(RadioButton)findViewById(R.id.register_male);
			if (radioButton.isChecked()){
				params.put("sex", "male");
			}else {
				params.put("sex", "female");
			}
			
			return "Valid";
		}
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
            HashMap<String,String> params=new HashMap<String, String>();
			String judge=getParams(params);
			
			if (!judge.equals("Valid")){
				Toast.makeText(Register.this, judge, Toast.LENGTH_SHORT).show();
			}else{
				ServerConnector connector=new ServerConnector("register");
                String result= null;
                try {
                    result = connector.setRequestParam("userinfo", JSONHelper.getJSONHelperInstance().convertToString(params)).doPost();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                if (result!=null && result.equals("successful")){
                    Toast.makeText(Register.this, "Welcome,login please!", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent();
					intent.setClass(Register.this, Login.class);
					startActivity(intent);
					overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
				}else {
					Toast.makeText(Register.this, result, Toast.LENGTH_SHORT).show();
				}
			}
		}
	};
}
