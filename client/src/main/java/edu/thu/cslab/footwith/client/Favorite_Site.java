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
    public static String myChoice;
    public static HashMap<String,Integer> siteNametoId = new HashMap< String,Integer>();
    public  static String chooseIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorate_site);
        lv = (ListView) findViewById(R.id.MyListView);
        bt_return = (Button)findViewById(R.id.bt_return);

//      intent = this.getIntent();
//      bunde = intent.getExtras();
//      myChoice = bunde.getString("myChoice");

        initData();
        // 配置适配器
        favorite_site_adapter = new Favorite_Site_Adapter(list,this); // 布局里的控件id
        // 添加并且显示
        lv.setAdapter(favorite_site_adapter);
        // 全选按钮的回调接口
       // myChoice = "nihao";

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                //To change body of implemented methods use File | Settings | File Templates.
                final ViewHolder holder = (ViewHolder) view.getTag();
                // 改变CheckBox的状态

                holder.cb.toggle();
                // 将CheckBox的选中状况记录下来
                Favorite_Site_Adapter.getIsSelected().put(i, holder.cb.isChecked());

                holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            Favorite_Site_Adapter.getIsSelected().put(i, true);
                            //System.out.println("add checked=" + position);
                            // Login.userChooseLike.add((String) getItem(position));
                          //  myChoice = myChoice+holder.tv.getText();

                        } else if (!b) {
                            Favorite_Site_Adapter.getIsSelected().put(i, false);
                            //System.out.println("remove checked=" + position);
                            //   Login.userChooseLike.remove(Login.userChooseLike.indexOf((String)getItem(position)));
                        }

                    }
                });
                // 调整选定条目
                if (holder.cb.isChecked() == true) {
                    checkNum++;
                  //  myChoice = myChoice+holder.tv.getText();
                } else {
                    checkNum--;
                }
            }

            class ViewHolder {
                TextView tv;
                CheckBox cb;
            }
        });

        bt_return.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Favorite_Site.this, AddPlan.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("myChoice",myChoice);
                intent.putExtras(mBundle);
                startActivity(intent);
               // Favorite_Site.this.setResult(RESULT_OK,intent);


               finish();
            }
        });

    }

    private void initData() {
        for (int i = 0; i < 15; i++) {
            siteNametoId.put("data"+i,i);
            list.add("data" + "   " + i);
        }
    }

    // 刷新listview和TextView的显示
    private void dataChanged() {
        // 通知listView刷新
        favorite_site_adapter.notifyDataSetChanged();
        // TextView显示最新的选中数目
       // tv_show.setText("已选中" + checkNum + "项");
    }




}
