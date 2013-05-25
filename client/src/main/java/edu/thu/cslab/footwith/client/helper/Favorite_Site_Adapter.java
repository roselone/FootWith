package edu.thu.cslab.footwith.client.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import edu.thu.cslab.footwith.client.Favorite_Site;
import edu.thu.cslab.footwith.client.Login;
import edu.thu.cslab.footwith.client.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-5-8
 * Time: 下午11:52
 * To change this template use File | Settings | File Templates.
 */


public class Favorite_Site_Adapter extends BaseAdapter{
    
    private  ArrayList<String> list;
    private  static HashMap<Integer, Boolean> isSelected;
    private  Context context; 
    private LayoutInflater mInflater = null;

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }


    private class ViewHolder {

        public TextView siteName;
        public CheckBox checkBox;
        public  Integer  siteId;
    }

    public Favorite_Site_Adapter(ArrayList<String> list,Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.list= list;
        isSelected = new HashMap<Integer, Boolean>();
        initData();
    }

    private void initData() {
        for(int i = 0; i<list.size();i++) {
            getIsSelected().put(i,false);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.favsite_item, null);
            final View view = convertView;

            holder.siteName = (TextView) convertView.findViewById(R.id.itemTitle);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.cb);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       // System.out.println("go in");
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Favorite_Site_Adapter.getIsSelected().put(position, true);
                  //  System.out.println("go in");
                    Favorite_Site.myChoice.add((String) getItem(position));
                    Favorite_Site.chooseIds.add(String.valueOf(Favorite_Site.nameIds.get(position)));

//                    System.out.println("dddddddd"+position);
//                    if(Favorite_Site.nameIds.size() == 0) {
//                        Favorite_Site.nameIds.set(0,1111);
//                    }
//                   System.out.println(Favorite_Site.nameIds.get(position));
                } else if (!b) {
                    Favorite_Site_Adapter.getIsSelected().put(position, false);
                }

            }
        });
        holder.siteName.setText(list.get(position));

        holder.checkBox.setChecked(isSelected.get(position));

        holder.siteId = Favorite_Site.nameIds.get(position);

        return convertView;
    }


}
