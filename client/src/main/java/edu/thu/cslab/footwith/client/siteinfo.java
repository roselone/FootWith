package edu.thu.cslab.footwith.client;
import android.app.AlertDialog;
import android.content.ContentProviderOperation;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.KeyStore;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import edu.thu.cslab.footwith.client.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class siteinfo extends Activity{

    /**
     * Called when the activity is first created.
     */
boolean flag=true;
    private List<Map<String, Object>> mData;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siteinfo);

       final ImageButton btn1 = (ImageButton)findViewById(R.id.imageButton);
       final TextView   text1 = (TextView) findViewById(R.id.textView3);


    btn1.setOnClickListener(new OnClickListener() {

        public void onClick(View v)
        {
          if(flag)
                {
                    btn1.setBackgroundResource(R.drawable.heart);
                    text1.setText("  已添加" );

                    flag = false;

                }
                else
                {

                    btn1.setBackgroundResource(R.drawable.heart1);
                    text1.setText(" 快添加到 [我的喜欢]");
                    flag = true;

                }
        }
        });
    }


     public boolean onKeyDown(int keyCode,KeyEvent event) {// 如果是返回键
        if(keyCode == KeyEvent.KEYCODE_BACK){           //want to do
            // setContentView(R.layout.main);
        }
        return super.onKeyDown(keyCode, event);
        //  return true;
    }


}


