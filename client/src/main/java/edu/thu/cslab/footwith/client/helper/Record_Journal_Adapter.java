package edu.thu.cslab.footwith.client.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import edu.thu.cslab.footwith.client.R;
import edu.thu.cslab.footwith.client.Record_Journal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 4/29/13
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Record_Journal_Adapter extends BaseAdapter {
    Context mContext;
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    Vector<Integer> journalIDVector;
    private LayoutInflater mLayoutInflater;

    public Record_Journal_Adapter(Context mContext, Vector<Integer> journalIDVector) {
        this.mContext = mContext;
        this.journalIDVector = journalIDVector;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    public Record_Journal_Adapter(Context mContext, ArrayList<HashMap<String, String>> list) {
        this.mContext = mContext;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public Record_Journal_Adapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        //list_Init();
    }
    private void list_Init(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("type", "journal");
        map.put("title", "hello");
        map.put("content", "大饭店洒发达热吻乞丐发达色燃气特人高飞的烧热为他人请问恶气额外她");
        map.put("date", "1970-01-01");
        list.add(map);

    }
    @Override
    public int getCount() {
        return list.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int i) {
        return i;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {

        View view = null;
        HashMap<String, String> map = list.get(position);
        //String type = (String) map.get("type");
        //if(type.equals("journal")){
        view = mLayoutInflater.inflate(R.layout.journal_listitem, null);

        final TextView titleTextView = (TextView) view.findViewById(R.id.journal_title_textView);
        titleTextView.setText((String) map.get("title"));

        final TextView contentTextView = (TextView) view.findViewById(R.id.journal_content_textView);
        contentTextView.setText((String) map.get("body"));

        TextView dateTextView = (TextView) view.findViewById(R.id.journal_date_textView);
        dateTextView.setText((String) map.get("time"));

        TextView userTextView = (TextView) view.findViewById(R.id.journal_user_textView);
        userTextView.setText((String) map.get("userName"));
        //}
        ImageView weiboImageView = (ImageView) view.findViewById(R.id.weibo_imageView);
        weiboImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder_weibo = new AlertDialog.Builder(mContext);
                final String weiboContent = String.valueOf(titleTextView.getText())+"!"
                        +String.valueOf(contentTextView.getText())
                        +"。##来自我的Footwith";
                builder_weibo.setMessage(weiboContent);
                builder_weibo.setTitle("同步到新浪微博？");
                builder_weibo.setPositiveButton("发布", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //WeiboFunction weiboFunction = WeiboFunction.getInstance(mContext);
                        WeiboFunction.authorize(mContext);
                        String weiboUpdateResult = WeiboFunction.WeiboStatusUpdate(mContext, weiboContent, null);
                        if(weiboUpdateResult.equals("success")){
                            Toast.makeText(mContext, "微博发布成功", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(mContext, "微博发布失败", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder_weibo.setNegativeButton("算了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder_weibo.show();
            }
        });


        return view;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
