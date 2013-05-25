package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import  android.util.Log;
import edu.thu.cslab.footwith.client.helper.ServerConnector;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.utility.Util;
import org.json.JSONException;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-5-9
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
public class AddPlan extends Activity {

    private  static final String TAG = "AddPlan";

    private Calendar  my_Calendar;

    private  Button bt_favSite;
    private  Button bt_addPlan;
    private EditText  edit_favSite;
    private EditText  edit_title;
    private EditText  desEditText;
    private EditText  numEditText;
    private Button   bt_start;
    private Button   bt_end;


    private  Date  startTime;
    private  Date  endTime;

    private  int     groupNumMax ;
    private DatePicker start_DP;
    private DatePicker end_DP;



    int my_Year;
    int my_Month;
    int my_Day;
    int my_Hour;
    int my_Minute;

    int year ;
    int month ;
    int day;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        my_Calendar = Calendar.getInstance(Locale.CHINA);
        my_Year = my_Calendar.get(Calendar.YEAR);
        my_Month = my_Calendar.get(Calendar.MONTH)+1;
        my_Day = my_Calendar.get(Calendar.DAY_OF_MONTH);
        my_Hour = my_Calendar.get(Calendar.HOUR_OF_DAY);
        my_Minute = my_Calendar.get(Calendar.MINUTE);

//        startTime = new Date(my_Year,my_Month,my_Day);
//        endTime = new Date(my_Year,my_Month,my_Day);

        setContentView(R.layout.addplan);
        bt_favSite = (Button) findViewById(R.id.bt_favSite);
        bt_addPlan = (Button) findViewById(R.id.bt_addPlan);
        numEditText = (EditText) findViewById(R.id.editText_num);
        desEditText = (EditText) findViewById(R.id.editText_description);
        bt_start = (Button)findViewById(R.id.bt_start);
        bt_end = (Button)findViewById(R.id.bt_end);
        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_favSite = (EditText)findViewById(R.id.edit_favSite);


        bt_start.setText(my_Year+"-"+my_Month+"-"+my_Day);
        bt_end.setText(my_Year+"-"+my_Month+"-"+my_Day);



        bt_start.setText(my_Year+"-"+my_Month+"-"+my_Day);
        bt_end.setText(my_Year+"-"+my_Month+"-"+my_Day);



       // System.out.println(my_Year+"年"+my_Month+"月"+my_Day+"日") ;
       // System.out.println(my_Year+"年"+my_Month+"月"+my_Day+"日");

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                buildDialog(0);
               // bt_start.setText(my_Year+"年"+my_Month+"月"+my_Day+"日");

            }
        });


        bt_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                buildDialog(1);
             //   bt_end.setText(my_Year+"年"+my_Month+"月"+my_Day+"日");
            }
        });

//        start_DP = (DatePicker) findViewById(R.id.datePicker_start);
//        end_DP = (DatePicker) findViewById(R.id.datePicker_end);

        Bundle bundle = getIntent().getExtras();

        if (!(bundle==null || bundle.isEmpty())){
                String data=bundle.getString("myChoice");//读出数据
                String title = bundle.getString("title");
                String number = bundle.getString("groupNumMax");
                String description = bundle.getString("description");

                edit_title.setText(title);
                desEditText.setText(description);
                numEditText.setText(number);


                if (!Util.isEmpty(data)){
//                    data = data.substring(data.indexOf(",")+1,data.length());
                    Vector<String> myChoice = new Vector<String>();

                    try {
                         myChoice = JSONHelper.getJSONHelperInstance().convertToArray2(data);
                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    String dest = myChoice.get(0);
                    for(int i=1;i< myChoice.size();i++)  {
                             dest = dest +","+ myChoice.get(i);
                        }
                   edit_favSite.setText(dest);
                }
        }

      //  edit_favSite.setText();

        //edit_favSite.setText(Favorite_Site.myChoice);
        Log.e(TAG,"start");
        bt_favSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.

                Intent intent = new Intent();
                intent.setClass(AddPlan.this,Favorite_Site.class);
                Bundle bundle = new Bundle();

                if(edit_title.getText().length() == 0 )
                    edit_title.setText("");
                if(numEditText.getText().length() == 0)
                    numEditText.setText("");
                if(desEditText.getText().length() == 0)
                    desEditText.setText("");
                bundle.putString("title",edit_title.getText().toString());
                bundle.putString("groupNumMax",numEditText.getText().toString());
                bundle.putString("description",desEditText.getText().toString());
                intent.putExtras(bundle);
//                startActivityForResult(intent,0);
                startActivity(intent);
//                //AddPlan.this.finish();
                finish();


            }
        });

        bt_addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                AlertDialog.Builder builder = new AlertDialog.Builder(AddPlan.this);
//                builder.setMessage("确认增加计划吗？");
//
//                builder.setTitle("提示");
//                Dialog dialog = builder.create();
//                dialog.show();
//                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//
//
//
//
//                    }
//                });
//
//                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//
//                    }
//                });
////                builder.create().show();
//
//
//                //  To change body of implemented methods use File | Settings | File Templates.
//                if(edit_favSite.getText().length() == 0 || edit_title.getText().length() == 0  || numEditText.getText().length() == 0){
//                    new  AlertDialog.Builder(AddPlan.this).setTitle("警告").setMessage("不能为空").setPositiveButton("确定",
//                            null).show();
//
//                }

                //  desEditText.setText("go in");

                String SiteNames  = edit_favSite.getText().toString();

                String describe = desEditText.getText().toString();

                int groupNumMax = Integer.valueOf(numEditText.getText().length() == 0 ? "0" :numEditText.getText().toString() );
                String title =  edit_title.getText().toString();

                String userId = Login.userID;

                HashMap<String,String> addPlan = new HashMap<String, String>();
                addPlan.put("title",title);

             //   addPlan.put("startTime", new SimpleDateFormat("yyyy年MM月dd日").format(startTime);
                //  addPlan.put("endTime", new SimpleDateFormat("yyyy年MM月dd日").format(endTime);
                   addPlan.put("startTime", bt_start.getText().toString());
                  addPlan.put("endTime", bt_end.getText().toString());
                addPlan.put("describe",describe);
                addPlan.put("organizer",userId);
                addPlan.put("groupNumMax",String.valueOf(groupNumMax));


//                String[] chooseIds =  Favorite_Site.chooseIds.split(",");
              //  Vector<String> chooseIds = Favorite_Site.chooseIds;            // can't derived from the vector<string>
                System.out.println(Favorite_Site.chooseIds.get(0));
                Vector<Integer> siteIDs = new Vector<Integer>();
                for(int i = 0;i< Favorite_Site.chooseIds.size();i++)   {
                    siteIDs.add(Integer.valueOf(Favorite_Site.chooseIds.get(i)));
                    System.out.println(siteIDs.get(i));
                }


                addPlan.put("siteIDs", JSONHelper.JSONHelperInstance.convertToString(siteIDs));
                System.out.println(JSONHelper.JSONHelperInstance.convertToString(siteIDs));

                String addPlanString = JSONHelper.getJSONHelperInstance().convertToString(addPlan);
                ServerConnector connector=new ServerConnector("planrecord");
                System.out.println(addPlanString);
                String result= null;
                try {
                    result = connector.setRequestParam("addPlan",addPlanString).doPost();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                HashMap<String,String> resultInfo = new HashMap<String, String>();
                resultInfo = JSONHelper.getJSONHelperInstance().convertToMap(result);

                System.out.println(result);
                if (result!=null &&  resultInfo.get("state").equals("successful")){
                    Toast.makeText(AddPlan.this, "添加成功", Toast.LENGTH_SHORT).show();
                    addPlan.put("planID",resultInfo.get("planID"));
                    addPlan.put("itemType", "plan");
                    addPlan.put("participants", "");
                    addPlan.put("isDone", "false");

                    try {
                        Vector<Integer> oldSiteIDs = JSONHelper.getJSONHelperInstance().convertToArray(addPlan.get("siteIDs"));
                        Vector<String> newSiteIDsVector = new Vector<String>();
                        for(int i=0; i<oldSiteIDs.size(); i++){
                            newSiteIDsVector.add(Login.userLike.get(oldSiteIDs.get(i)));
                        }
                        addPlan.put("siteIDs", JSONHelper.getJSONHelperInstance().convertToString2(newSiteIDsVector));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    AboutMe.self.add(addPlan);
                    AboutMe.selfAdapter.notifyDataSetChanged();
                    finish();

                }else {
                    Toast.makeText(AddPlan.this, "添加失败", Toast.LENGTH_SHORT).show();
                }


            }

        });
//        start_DP.init(my_Year,my_Month,my_Day,new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
//                my_Year = datePicker.getYear();
//                my_Month = datePicker.getMonth();
//                my_Day = datePicker.getDayOfMonth();
//                desEditText.setText("日期:"+my_Year+my_Month+my_Day);
//                startTime = new Date(my_Year,my_Month,my_Day);
//            }
//        });
//        end_DP.init(my_Year,my_Month,my_Day,new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
//                my_Year = datePicker.getYear();
//                my_Month = datePicker.getMonth();
//                my_Day = datePicker.getDayOfMonth();
//
//                endTime = new Date(my_Year,my_Month,my_Day);
//                desEditText.setText("日期:"+my_Year+my_Month+my_Day);
//            }
//        });

    }
//    protected void onActivityResult(int requestCode,int resultCode,Intent data){
//        switch(resultCode) {
//            case RESULT_OK:
//                Bundle bunde = data.getExtras();
//                String desString = bunde.getString("myChoice");
//
//                edit_favSite.setText(desString);
//                break;
//            default:
//                break;
//
//        }
//    }

    public void buildDialog(final int type){
        // 从资源创建视图
        LayoutInflater factory = LayoutInflater.from(this);
        View view = factory.inflate(R.layout.my_datepicker, null); //自定义视图
        year = my_Year;
        month = my_Month;
        day = my_Day;
        DatePicker date = (DatePicker) view.findViewById(R.id.datePicker);



        date.init(year,month-1,day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
                year = datePicker.getYear();
                month = datePicker.getMonth()+1;
                day = datePicker.getDayOfMonth();
            //    desEditText.setText("日期:"+my_Year+my_Month+my_Day);
            //    startTime = new Date(my_Year,my_Month,my_Day);
            }
        });

        // 创建对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        // OK按钮消息处理
        DialogInterface.OnClickListener  okClickListener = new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                AlertDialog alertDialog = (AlertDialog)dialog;

                //假设colorpeek.xml里面定义了文本框txtColor，现取出放到主界面txtInput里面
                //   bt_start.setText(my_Year+"-");
                //    txtInput.setText(((EditText)alertDialog.findViewById(R.id.txtColor)).getText().toString());
                my_Day = day;
                my_Month = month;
                my_Year = year;
               if(type == 0) {
                   bt_start.setText(my_Year+"-"+my_Month+"-"+my_Day);
                   startTime = new Date(my_Year,my_Month,my_Day);
               } else {

                   bt_end.setText(my_Year+"-"+my_Month+"-"+my_Day);
                   endTime = new Date(my_Year,my_Month,my_Day);
                   if(endTime.before(startTime)) {
                       new  AlertDialog.Builder(AddPlan.this).setTitle("警告").setMessage("结束时间不能小于开始时间").setPositiveButton("确定",
                               null).show();

                    bt_end.setText("9999-12-30");
                       endTime = new Date(9999,12,30);
                   }
               }
            }
        };

        builder.setPositiveButton("确定", okClickListener);
        builder.setTitle("请选择时间");
        AlertDialog dialog = builder.create();
        dialog.show();


    }



}