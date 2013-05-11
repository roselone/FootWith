package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Logger;
import  android.util.Log;
import edu.thu.cslab.footwith.client.helper.Favorite_Site_Adapter;
import edu.thu.cslab.footwith.client.helper.ServerConnector;
import edu.thu.cslab.footwith.messenger.JSONHelper;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-5-9
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
public class AddPlan1 extends Activity {

    private  static final String TAG = "AddPlan";

    private Calendar  my_Calendar;

    private  Button bt_favSite;
    private  Button bt_addPlan;
    private  Date startTime;
    private  Date endTime;
    private  int     groupNumMax ;
    private DatePicker start_DP;
    private DatePicker end_DP;
    private EditText  desEditText;
    private EditText  numEditText;
    private EditText  edit_favSite;
    private EditText  edit_title;
    int my_Year;
    int my_Month;
    int my_Day;
    int my_Hour;
    int my_Minute;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        my_Calendar = Calendar.getInstance(Locale.CHINA);
        my_Year = my_Calendar.get(Calendar.YEAR);
        my_Month = my_Calendar.get(Calendar.MONTH);
        my_Day = my_Calendar.get(Calendar.DAY_OF_MONTH);
        my_Hour = my_Calendar.get(Calendar.HOUR_OF_DAY);
        my_Minute = my_Calendar.get(Calendar.MINUTE);

        Bundle bundle = getIntent().getExtras();
        String data=bundle.getString("myChoice");//读出数据

        startTime = new Date(my_Year,my_Month,my_Day);
        endTime = new Date(my_Year,my_Month,my_Day);


        setContentView(R.layout.addplan);
        bt_favSite = (Button) findViewById(R.id.bt_favSite);
        bt_addPlan = (Button) findViewById(R.id.bt_addPlan);
        start_DP = (DatePicker) findViewById(R.id.datePicker_start);
        end_DP = (DatePicker) findViewById(R.id.datePicker_end);
        numEditText = (EditText) findViewById(R.id.editText_num);
        desEditText = (EditText) findViewById(R.id.editText_description);
        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_favSite = (EditText)findViewById(R.id.edit_favSite);
        edit_favSite.setText(data);

        //edit_favSite.setText(Favorite_Site.myChoice);
        Log.e(TAG,"start");
        bt_favSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                Intent intent = new Intent();
                intent.setClass(AddPlan1.this,Favorite_Site.class);
                startActivity(intent);

//                finish();

            }
        });

        bt_addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  To change body of implemented methods use File | Settings | File Templates.
                if(edit_favSite.getText().length() == 0 || edit_title.getText().length() == 0  || numEditText.getText().length() == 0){
                    new  AlertDialog.Builder(AddPlan1.this).setTitle("警告").setMessage("不能为空").setPositiveButton("确定",
                            null).show();

                }

                desEditText.setText("go in");

                String SiteNames  = edit_favSite.getText().toString();

                String describe = desEditText.getText().toString();

                int groupNumMax = Integer.valueOf(numEditText.getText().length() == 0 ? "0" :numEditText.getText().toString() );
                String title =  edit_title.getText().toString();

                String userId = Login.userID;

                HashMap<String,String>  addPlan = new HashMap<String, String>();
                addPlan.put("title",title);
                addPlan.put("startTime", new SimpleDateFormat("yyyy-MM-dd").format(startTime));
                addPlan.put("endTime", new SimpleDateFormat("yyyy-MM-dd").format(endTime));
                addPlan.put("describe",describe);
                addPlan.put("userID",userId);
                String[] chooseIds =  Favorite_Site.chooseIds.split(",");
                Vector<String> siteIDs = new Vector<String>();
                for(int i = 1;i<chooseIds.length;i++)
                      siteIDs.add(chooseIds[i]);

                addPlan.put("siteIDs", JSONHelper.JSONHelperInstance.convertToString2(siteIDs));

                String addPlanString = JSONHelper.getJSONHelperInstance().convertToString(addPlan);
                ServerConnector connector=new ServerConnector("planrecord");
                System.out.println(addPlanString);
                String result= null;
                try {
                    result = connector.setRequestParam("addPlan",addPlanString).doPost();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                System.out.println(result);
                if (result!=null && result.equals("successful")){
                    Toast.makeText(AddPlan1.this, "添加成功", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(AddPlan1.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }

        });
        start_DP.init(my_Year,my_Month,my_Day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
                my_Year = datePicker.getYear();
                my_Month = datePicker.getMonth();
                my_Day = datePicker.getDayOfMonth();
                desEditText.setText("日期:"+my_Year+my_Month+my_Day);
                startTime = new Date(my_Year,my_Month,my_Day);
            }
        });
        end_DP.init(my_Year,my_Month,my_Day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
                my_Year = datePicker.getYear();
                my_Month = datePicker.getMonth();
                my_Day = datePicker.getDayOfMonth();

                endTime = new Date(my_Year,my_Month,my_Day);
                desEditText.setText("日期:"+my_Year+my_Month+my_Day);
            }
        });

    }


}