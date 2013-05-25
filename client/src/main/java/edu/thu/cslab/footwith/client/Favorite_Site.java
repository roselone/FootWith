package edu.thu.cslab.footwith.client;

import java.util.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import edu.thu.cslab.footwith.client.helper.Favorite_Site_Adapter;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import edu.thu.cslab.footwith.messenger.JSONHelper;


/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-5-9
 * Time: 上午12:11
 * To change this template use File | Settings | File Templates.
 */
public class Favorite_Site extends Activity {

    private  Bundle bunde;
    private Intent intent;
    private ListView lv;

    private  Favorite_Site_Adapter favorite_site_adapter;
    private  ArrayList<String> list=new ArrayList<String>();
    private int checkNum;
    private TextView tv_show;
    private Button bt_return;
    public static Vector<String> myChoice = new Vector<String>();

    public  static Vector<String> chooseIds = new Vector<String>();

    public  static ArrayList<Integer> nameIds = new ArrayList<Integer>();

  // public  static HashMap<Integer,Boolean> isSelected = new HashMap<Integer, Boolean>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorate_site);
        lv = (ListView) findViewById(R.id.MyListView);
        bt_return = (Button)findViewById(R.id.bt_return);

        Bundle bundle = getIntent().getExtras();          // not intent.getExtras()

        final String title = bundle.getString("title");
        final String number = bundle.getString("groupNumMax");
        final String description = bundle.getString("description");

        initData();
        // 配置适配器
        favorite_site_adapter = new Favorite_Site_Adapter(list,this); // 布局里的控件id
        // 添加并且显示
        lv.setAdapter(favorite_site_adapter);
//        lv.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
//                final ViewHolder holder = (ViewHolder) view.getTag();
//                // 改变CheckBox的状态
//                holder.cb.toggle();
//                // 将CheckBox的选中状况记录下来
//                Favorite_Site_Adapter.getIsSelected().put(i, holder.cb.isChecked());
//
//                holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                        if (b) {
//                            Favorite_Site_Adapter.getIsSelected().put(i, true);
//                        } else if (!b) {
//                            Favorite_Site_Adapter.getIsSelected().put(i, false);
//                        }
//
//                    }
//                });
//                if (holder.cb.isChecked() == true) {
//                    checkNum++;
//                } else {
//                    checkNum--;
//                }
//            }
//
//            class ViewHolder {
//                TextView tv;
//                CheckBox cb;
//            }
//        });

        bt_return.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Favorite_Site.this, AddPlan.class);
                Bundle mBundle = new Bundle();
                if(myChoice.size() == 0) {
                    myChoice.set(0,"");
                }
                mBundle.putString("myChoice", JSONHelper.getJSONHelperInstance().convertToString2(myChoice));

                mBundle.putString("title",title);
                mBundle.putString("groupNumMax",number);
                mBundle.putString("description", description);

                intent.putExtras(mBundle);
                startActivity(intent);
               finish();
            }
        });

    }

    private void initData() {

        myChoice.clear();
        nameIds.clear();
        chooseIds.clear();

//        for (int i = 0; i < 15; i++) {
//
//          //  siteNametoId.put("data"+i,i);
//            list.add("data" + "   " + i);
//            nameIds.add(i);
//        }
        //get the data   from the Login.userLike
        Map<Integer,String> userLike = Login.userLike;
        Iterator iterator = userLike.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Integer key =(Integer) entry.getKey();
            String val = (String) entry.getValue();
            list.add(val);
            nameIds.add(key);

        }
    }


}
