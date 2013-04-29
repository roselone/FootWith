package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import edu.thu.cslab.footwith.client.helper.Record_Journal_Adapter;


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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_journal);
        ListView listView = (ListView) findViewById(R.id.record_journal_listView);
        listView.setAdapter(new Record_Journal_Adapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //To change body of implemented methods use File | Settings | File Templates.
                //LayoutInflater inflater = (LayoutInflater) Record_Journal.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LayoutInflater inflater = LayoutInflater.from(adapterView.getContext());
                LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.journal_clickitem, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(Record_Journal.this);

                String type = (String) ((HashMap<String, Object>)adapterView.getAdapter().getItem(i)).get("type");

                builder.setPositiveButton("保 存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }
                });
                builder.setNegativeButton("取 消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }
                });

                if(type.equals("journal")){
                    TextView titleTextView = (TextView) view.findViewById(R.id.journal_title_textView);
                    String title = (String) titleTextView.getText();

                    TextView contentTextView = (TextView) view.findViewById(R.id.journal_content_textView);
                    String content = (String) contentTextView.getText();

                    String date = String.valueOf(new Date());

                    EditText titleEditText = (EditText) linearLayout.findViewById(R.id.journal_title_editText);
                    titleEditText.setText(title);

                    EditText contentEditText = (EditText) linearLayout.findViewById(R.id.journal_content_editText);
                    contentEditText.setText(content);

                    titleEditText.setEnabled(true);
                    contentEditText.setEnabled(true);


                }


                builder.setTitle("编辑日志").setView(linearLayout);
                builder.show();
            }
        });
    }
}