package edu.xdu.RL.FootWith;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

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
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(Register.this, FootWithActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
		}
	};
}
