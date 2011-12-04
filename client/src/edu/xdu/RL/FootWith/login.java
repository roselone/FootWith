package edu.xdu.RL.FootWith;

import edu.xdu.RL.FootWith.helper.intentHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class login extends Activity{
    /** Called when the activity is first created. */
	
	private Button loginButton;
	
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
	}
	
	private OnClickListener loginListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(login.this, FootWithActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
		}
	};
	
}
