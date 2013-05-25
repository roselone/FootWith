package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 4/29/13
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Record_Tab extends ActivityGroup {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        Bundle extras = getIntent().getExtras();
        HashMap<String, String> map=null;
        int position = -1;
        if(extras!=null){
            map = (HashMap<String, String>) extras.getSerializable("map");
            position = extras.getInt("position");
        }

        Intent intent;

        TabHost recordTabHost = (TabHost)findViewById(R.id.tabhost);
        recordTabHost.setup(this.getLocalActivityManager());

        TabHost.TabSpec spec = recordTabHost.newTabSpec("basic");
        intent = new Intent(this, Record_Basic.class);
        intent.putExtra("sites", map.get("siteIDs"));
        intent.putExtra("users", map.get("userIDs"));
        intent.putExtra("siteGPS", map.get("siteGPS"));
        intent.putExtra("position", position);
        spec.setContent(intent);
        spec.setIndicator("基本", getResources().getDrawable(R.drawable.basic));
        recordTabHost.addTab(spec);


        spec = recordTabHost.newTabSpec("journal");
        intent = new Intent(this, Record_Journal.class);
        intent.putExtra("journals", map.get("journals"));
        intent.putExtra("recordID", map.get("recordID"));
        intent.putExtra("position", position);
        spec.setContent(intent);
        spec.setIndicator("日志", getResources().getDrawable(R.drawable.journal));
        recordTabHost.addTab(spec);

        spec = recordTabHost.newTabSpec("picture");
        intent = new Intent(this, Record_Picture.class);
        intent.putExtra("pictures", map.get("pictures"));
        intent.putExtra("recordID", map.get("recordID"));
        intent.putExtra("position", position);
        spec.setContent(intent);
        spec.setIndicator("图片", getResources().getDrawable(R.drawable.picture));
        recordTabHost.addTab(spec);

        TabWidget tabWidget = recordTabHost.getTabWidget();
        for (int i=0;i<tabWidget.getChildCount();i++){
            ImageView iv = (ImageView)tabWidget.getChildTabViewAt(i).findViewById(android.R.id.icon);
            iv.setPadding(10, 0, 10, 25);
            TextView tv=(TextView)tabWidget.getChildTabViewAt(i).findViewById(android.R.id.title);
            tv.setPadding(0, 0, 0, 5);
        }
        recordTabHost.setCurrentTab(0);


    }
}