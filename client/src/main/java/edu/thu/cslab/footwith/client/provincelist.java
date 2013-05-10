package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pengpeng
 * Date: 13-5-7
 * Time: 下午10:30
 * To change this template use File | Settings | File Templates.
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.util.Log;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import edu.thu.cslab.footwith.client.R;


public class provincelist extends Activity{

    /**
     * Called when the activity is first created.
     */
    private List<Map<String, Object>> mData;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provincelist);

        //绑定Layout里面的ListView
        ListView list = (ListView) findViewById(R.id.ListView01);

        //生成动态数组，加入数据
        // final  ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();



        String[] provinces = { "北京","安徽" ,"重庆","福建" ,"甘肃","广东" ,"广西","贵州" ,"海南","河北" ,"黑龙江","河南" ,
                "香港","湖北" ,
                "湖南","内蒙古" ,"江苏","江西" ,"吉林","辽宁" ,"澳门","宁夏" ,"青海","陕西" ,"山东","上海" ,
                "山西","四川","天津","西藏" ,"新疆","云南","浙江","台湾"
        };

        List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();

        int[] imgs = {
                R.drawable.s01, R.drawable.s02, R.drawable.s03,R.drawable.s04,R.drawable.s05,R.drawable.s06,R.drawable.s07,
                R.drawable.s08,R.drawable.s09,R.drawable.s10, R.drawable.s11,R.drawable.s12,R.drawable.s13,R.drawable.s14,
                R.drawable.s15,R.drawable.s16,R.drawable.s17,R.drawable.s18,R.drawable.s19,R.drawable.s20,R.drawable.s21,
                R.drawable.s22,R.drawable.s23,R.drawable.s24,R.drawable.s25,R.drawable.s26,R.drawable.s27,R.drawable.s28,
                R.drawable.s29,R.drawable.s30,R.drawable.s31,R.drawable.s32,R.drawable.s33,R.drawable.s34,
        };
        for(int i=0;i<34;i++)
        {
            // HashMap<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle",provinces[i]);
            map.put("ItemImage", imgs[i]);//图像资源的ID
            listItem.add(map);
        }
        mData=listItem;

        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,R.layout.procincelist1,//ListItem的XML实现
                //动态数组与ImageItem对应的子项

                new String[] {"ItemImage","ItemTitle"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.ItemImage,R.id.ItemTitle}
        );

        //添加并且显示
        list.setAdapter(listItemAdapter);
        //添加点击
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3)
            {

                String province=(String) mData.get(arg2).get("ItemTitle");  //获得省份

                Intent intent = new Intent();
                intent=new Intent(provincelist.this,sitelist.class);

                Bundle bundle = new Bundle();

                bundle.putString("province", province);
                intent.putExtras(bundle);

                provincelist.this.startActivity(intent);

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

