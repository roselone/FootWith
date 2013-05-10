package edu.thu.cslab.footwith.client;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.widget.*;
import java.lang.String;
import android.view.View;


public class sitelist extends Activity{
    /**
     * Called when the activity is first created.
     */
    private List<Map<String, Object>> mData;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sitelist);
            //绑定Layout里面的ListView
            ListView list = (ListView) findViewById(R.id.ListView02);
            //生成动态数组，加入数据
            List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();

        Intent intent = this.getIntent();
        Bundle bundle = this.getIntent().getExtras();
        String province=bundle.getString("province");

        String[] 北京 ={"故宫博物院","颐和园","八达岭-慕田峪长城","明十三陵","圆明园","北京大学","清华大学","天坛","地坛","奥体中心","恭王府","香山公园","景山公园"};

         /*
        String baseurl="http://192.168.0.106:8080/javaweb001/loadMessage";
        String url=baseurl+"?didian="+province;
        String result = sitelist_HttpUtil.queryStringForPost(url);
        System.out.println(result);
        */
      for(int i=0;i<13;i++)

            {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("ItemTitle",北京[i]);
                listItem.add(map);
            }
            mData=listItem;

            //生成适配器的Item和动态数组对应的元素
            SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,R.layout.sitelist1,//ListItem的XML实现
                    new String[] {"ItemTitle"},
                    new int[] {R.id.ItemTitle}
            );
            //添加并且显示
            list.setAdapter(listItemAdapter);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3)
            {

                String sitename=(String) mData.get(arg2).get("ItemTitle");  //获得景点

                Intent intent = new Intent();
                intent=new Intent(sitelist.this,siteinfo.class);
                Bundle bundle = new Bundle();
                //  bundle.putString("sinename", sitename);
                //   intent.putExtras(bundle);
                 sitelist.this.startActivity(intent);
  }
 });
 }
 public boolean onKeyDown(int keyCode,KeyEvent event) {        // 如果是返回键
        if(keyCode == KeyEvent.KEYCODE_BACK){           //want to do
            // setContentView(R.layout.main);
        }
        return super.onKeyDown(keyCode, event);
        //  return true;
    }

 }


