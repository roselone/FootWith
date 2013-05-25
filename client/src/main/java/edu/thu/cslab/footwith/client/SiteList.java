package edu.thu.cslab.footwith.client;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.*;

import android.widget.*;
import java.lang.String;
import android.view.View;
import edu.thu.cslab.footwith.client.helper.ServerConnector;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import org.json.JSONException;

public class SiteList extends Activity{
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
        System.out.println(province);

        HashMap<String, String> map1= new HashMap<String, String>();

        Vector<String> temp=new Vector<String>();
        String temp2 =null;
        String result= null;
        ServerConnector conn= new ServerConnector("site");
        try {
            result = conn.setRequestParam("location", province).doPost();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println(result.toString());
        map1= JSONHelper.getJSONHelperInstance().convertToMap(result);

        // String[] 北京 ={"故宫博物院","颐和园","八达岭-慕田峪长城","明十三陵","圆明园","北京香山公园","清华大学","天坛","地坛","奥体中心","恭王府","香山公园","景山公园"};

        Vector<String> vector2 = new Vector<String>();

        try {
            vector2 = JSONHelper.getJSONHelperInstance().convertToArray2(map1.get("id_names"));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        final HashMap<Integer,String> map2 = new HashMap<Integer, String>();
        final HashMap<String,Integer> siteToId = new HashMap<String, Integer>();

        Vector<Integer> keySet = new Vector<Integer>();
        for(int i=0;i<vector2.size();i++)
        {
            String[] tmp = vector2.get(i).split(":");
            map2.put(Integer.valueOf(tmp[0]),tmp[1]);
            keySet.add(Integer.valueOf(tmp[0]));
            siteToId.put(tmp[1],Integer.valueOf(tmp[0]));
        }

        System.out.println("ok3");
        for(int i=0;i<map2.size();i++)

        {
            Map<String, Object> map3 = new HashMap<String, Object>();
            map3.put("ItemTitle", map2.get(keySet.get(i)));
            listItem.add(map3);
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

                String siteName=(String) mData.get(arg2).get("ItemTitle");  //获得景点

                Intent intent = new Intent();
                intent=new Intent(SiteList.this,SiteInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("siteName", siteName);
                bundle.putString("siteId", String.valueOf(siteToId.get(siteName)));

                intent.putExtras(bundle);
                SiteList.this.startActivity(intent);
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


