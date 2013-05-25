package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.weibo.sdk.android.*;
import com.weibo.sdk.android.net.RequestListener;
import com.weibo.sdk.android.sso.SsoHandler;
import edu.thu.cslab.footwith.client.helper.*;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 4/29/13
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Record_Journal extends Activity {
    final MyJournalNetwork myJournalNetwork = new MyJournalNetwork();
    Record_Journal_Adapter record_journal_adapter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.record_journal);
        Bundle bundle = getIntent().getExtras();
        String journalIDs = (String) bundle.get("journals");
        String recordID = (String) bundle.get("recordID");
        int position = bundle.getInt("position");
        myJournalNetwork.requestList(journalIDs, recordID, position);
        final ArrayList<HashMap<String, String>> journalList=myJournalNetwork.getList();

        ListView listView = (ListView) findViewById(R.id.record_journal_listView);
        record_journal_adapter = new Record_Journal_Adapter(this, journalList);
        listView.setAdapter(record_journal_adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //To change body of implemented methods use File | Settings | File Templates.
                //LayoutInflater inflater = (LayoutInflater) Record_Journal.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final int position = i;
                final HashMap<String, String> modified_journal = journalList.get(i);
                LayoutInflater inflater = LayoutInflater.from(adapterView.getContext());
                LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.journal_clickitem, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(Record_Journal.this);

                //String type = (String) ((HashMap<String, Object>)adapterView.getAdapter().getItem(i)).get("type");
                TextView titleTextView = (TextView) view.findViewById(R.id.journal_title_textView);
                String title = (String) titleTextView.getText();

                TextView contentTextView = (TextView) view.findViewById(R.id.journal_content_textView);
                String content = (String) contentTextView.getText();

                final EditText titleEditText = (EditText) linearLayout.findViewById(R.id.journal_title_editText);
                titleEditText.setText(title);

                final EditText contentEditText = (EditText) linearLayout.findViewById(R.id.journal_content_editText);
                contentEditText.setText(content);

                titleEditText.setEnabled(true);
                contentEditText.setEnabled(true);

                builder.setPositiveButton("保 存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //To change body of implemented methods use File | Settings | File Templates.
                        modified_journal.put("title", String.valueOf(titleEditText.getText()));
                        modified_journal.put("body", String.valueOf(contentEditText.getText()));
                        String date = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                        modified_journal.put("time", date);
                        modified_journal.put("userID", Login.userID);
                        modified_journal.put("userName", Login.userName);
                        if(myJournalNetwork.modify(position, modified_journal)){
                            AlertDialog.Builder builder_weibo = new AlertDialog.Builder(Record_Journal.this);
                            final String weiboContent = String.valueOf(titleEditText.getText())+"!"
                                    +String.valueOf(contentEditText.getText())
                                    +"。##来自我的Footwith";
                            builder_weibo.setMessage(weiboContent);
                            builder_weibo.setTitle("同步到新浪微博？");
                            builder_weibo.setPositiveButton("发布", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //WeiboFunction weiboFunction = WeiboFunction.getInstance(Record_Journal.this);
                                    WeiboFunction.authorize(Record_Journal.this);
                                    String weiboUpdateResult = WeiboFunction.WeiboStatusUpdate(Record_Journal.this, weiboContent, null);
                                    if(weiboUpdateResult.equals("success")){
                                        Toast.makeText(Record_Journal.this, "微博发布成功", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(Record_Journal.this, "微博发布失败", Toast.LENGTH_LONG).show();
                                    }
                                    Toast.makeText(Record_Journal.this, "修改成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder_weibo.setNegativeButton("算了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(Record_Journal.this, "修改成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder_weibo.show();




                            /*
                            ServerConnector sc = new ServerConnector("https://api.weibo.com/oauth2", "/authorize");
                            sc.setRequestParam("client_id", Constant.WeiboAppKey);
                            sc.setRequestParam("redirect_uri", Constant.WeiboRedirectURL);
                            try {
                                String result = sc.doPost();
                                Toast.makeText(Record_Journal.this, result, Toast.LENGTH_LONG).show();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            */



                        }else{
                            Toast.makeText(Record_Journal.this, "修改失败", Toast.LENGTH_SHORT).show();
                        }
                        record_journal_adapter.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("取 消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }
                });

                builder.setTitle("编辑日志").setView(linearLayout);
                builder.show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO delete journal
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        menu.add("添加").setIcon(R.drawable.menu_add);
        menu.add("帮助").setIcon(R.drawable.menu_help);
        menu.add("联系").setIcon(R.drawable.menu_contact);
        return super.onCreateOptionsMenu(menu);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = (String) item.getTitle();
        if(title.equals("添加")){
            final HashMap<String, String> add_journal = new HashMap<String, String>();
            LayoutInflater inflater = LayoutInflater.from(this);
            LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.journal_clickitem, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(Record_Journal.this);
            final EditText titleEditText = (EditText) linearLayout.findViewById(R.id.journal_title_editText);
            final EditText contentEditText = (EditText) linearLayout.findViewById(R.id.journal_content_editText);
            titleEditText.setEnabled(true);
            contentEditText.setEnabled(true);

            builder.setPositiveButton("保 存", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //To change body of implemented methods use File | Settings | File Templates.
                    add_journal.put("title", String.valueOf(titleEditText.getText()));
                    add_journal.put("body", String.valueOf(contentEditText.getText()));
                    String date = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    add_journal.put("time", date);
                    add_journal.put("userID", Login.userID);
                    add_journal.put("userName", Login.userName);
                    if(myJournalNetwork.add(add_journal)){
                        AlertDialog.Builder builder_weibo = new AlertDialog.Builder(Record_Journal.this);
                        final String weiboContent = String.valueOf(titleEditText.getText())+"!"
                                +String.valueOf(contentEditText.getText())
                                +"。##来自我的Footwith";
                        builder_weibo.setMessage(weiboContent);
                        builder_weibo.setTitle("同步到新浪微博？");
                        builder_weibo.setPositiveButton("发布", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //WeiboFunction weiboFunction = WeiboFunction.getInstance(Record_Journal.this);
                                WeiboFunction.authorize(Record_Journal.this);
                                String weiboUpdateResult = WeiboFunction.WeiboStatusUpdate(Record_Journal.this, weiboContent, null);
                                if(weiboUpdateResult.equals("success")){
                                    Toast.makeText(Record_Journal.this, "微博发布成功", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(Record_Journal.this, "微博发布失败", Toast.LENGTH_LONG).show();
                                }
                                Toast.makeText(Record_Journal.this, "添加成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder_weibo.setNegativeButton("算了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Record_Journal.this, "添加成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder_weibo.show();

                    }else{
                        Toast.makeText(Record_Journal.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                    record_journal_adapter.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("取 消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //To change body of implemented methods use File | Settings | File Templates.
                }
            });

            builder.setTitle("添加日志").setView(linearLayout);
            builder.show();

        }else if(title.equals("帮助")){
            Menu_Functions.helpMe(this);
        }else if(title.equals("联系")){
            Menu_Functions.contactMe(this);
        }
        return super.onOptionsItemSelected(item);    //To change body of overridden methods use File | Settings | File Templates.

    }


}