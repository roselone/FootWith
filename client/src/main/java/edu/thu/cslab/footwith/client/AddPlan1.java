package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;

import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Logger;
import  android.util.Log;
import android.widget.TimePicker;
import edu.thu.cslab.footwith.client.helper.Favorite_Site_Adapter;

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
    private  String  startTime;
    private  String  endTime;
    private  int     groupNumMax ;
    private DatePicker start_DP;
    private DatePicker end_DP;
    private EditText  desEditText;
    private EditText  numEditText;
    private EditText  edit_favSite;
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



        setContentView(R.layout.addplan);
        bt_favSite = (Button) findViewById(R.id.bt_favSite);
        bt_addPlan = (Button) findViewById(R.id.bt_addPlan);
        start_DP = (DatePicker) findViewById(R.id.datePicker_start);
        end_DP = (DatePicker) findViewById(R.id.datePicker_end);
        numEditText = (EditText) findViewById(R.id.editText_num);
        desEditText = (EditText) findViewById(R.id.editText_description);

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
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        start_DP.init(my_Year,my_Month,my_Day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
                my_Year = datePicker.getYear();
                my_Month = datePicker.getMonth();
                my_Day = datePicker.getDayOfMonth();
                desEditText.setText("日期:"+my_Year+my_Month+my_Day);
            }
        });
        end_DP.init(my_Year,my_Month,my_Day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
                my_Year = datePicker.getYear();
                my_Month = datePicker.getMonth();
                my_Day = datePicker.getDayOfMonth();
                desEditText.setText("日期:"+my_Year+my_Month+my_Day);
            }
        });

    }


}