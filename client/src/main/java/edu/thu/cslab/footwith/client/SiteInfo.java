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
import android.widget.*;

import java.io.IOException;
import java.security.KeyStore;
import java.util.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import edu.thu.cslab.footwith.client.R;
import edu.thu.cslab.footwith.client.helper.ServerConnector;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import org.json.JSONException;

import java.util.List;
import java.util.Map;
public class SiteInfo extends Activity{

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
        TextView   text2=(TextView) findViewById(R.id.textView);
        TextView  text3=(TextView) findViewById(R.id.textView1);
        RatingBar rate1=(RatingBar) findViewById(R.id.ratingBar);
        ImageView pic1=(ImageView) findViewById(R.id.imageView);

        Bundle bundle = this.getIntent().getExtras();    //获得前一个界面选择的景点名称
        String mingcheng = bundle.getString("siteName");
        String siteID = bundle.getString("siteId");
        System.out.println(mingcheng);

        HashMap<String, String> map1= new HashMap<String, String>();
        HashMap<String, String> map2= new HashMap<String, String>();
        Vector<String> temp=new Vector<String>();
        String result= null;
        String temp2=null;
        ServerConnector conn= new ServerConnector("site");


        try {
            result = conn.setRequestParam("siteID",siteID ).doPost();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println(result.toString());
        map1= JSONHelper.getJSONHelperInstance().convertToMap(result);
       map2 = JSONHelper.getJSONHelperInstance().convertToMap(map1.get("site"));
     //静态测试
        String  name="扬州市瘦西湖风景区" ;
        String  brief="      瘦西湖(Slender West Lake)，位于扬州市北郊，现有游览区面积100公顷右，" +
                "1988年被国务院列为“具有重要历史文化遗产和扬州园林特色的国家重点名胜区”。" +
                "2010年被授予中国旅游界含金量最高荣誉——全国AAAAA级景区，成为扬州 首家国家5A级旅游景区。";

        text2.setText( map2.get("siteName"));
        text3.setText( map2.get("brief"));
        rate1.setRating( Integer.valueOf(map2.get("rate")));


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


