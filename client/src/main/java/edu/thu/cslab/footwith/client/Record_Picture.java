package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.*;
import android.widget.*;
import edu.thu.cslab.footwith.client.helper.Menu_Functions;
import edu.thu.cslab.footwith.client.helper.MyPictureNetwork;
import edu.thu.cslab.footwith.client.helper.Record_Picture_Adapter;

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
public class Record_Picture extends Activity {
    ArrayList<HashMap<String, String>> pictureList = new ArrayList<HashMap<String, String>>();
    Record_Picture_Adapter record_picture_adapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_picture);
        Bundle bundle = getIntent().getExtras();
        String recordID = (String) bundle.get("recordID");
        String pictures = (String) bundle.get("pictures");
        MyPictureNetwork myPictureNetwork = new MyPictureNetwork();
        myPictureNetwork.requestList(pictures, recordID);
        pictureList = myPictureNetwork.getList();
        Gallery g = (Gallery) findViewById(R.id.record_picture_gallery);
        record_picture_adapter = new Record_Picture_Adapter(this, pictureList);
        g.setAdapter(record_picture_adapter);
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //To change body of implemented methods use File | Settings | File Templates.
                ImageView imageView = (ImageView)findViewById(R.id.record_picture_imageView);
                //imageView.setImageResource((Integer)adapterView.getAdapter().getItem(i));
                imageView.setImageURI(Uri.parse(pictureList.get(i).get("uri")));
                TextView titleTextView = (TextView) findViewById(R.id.picture_title_textView);
                TextView userTextView = (TextView) findViewById(R.id.picture_user_textView);
                TextView dateTextView = (TextView) findViewById(R.id.picture_date_textView);
            }
        });
        g.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //To change body of implemented methods use File | Settings | File Templates.
                ImageView imageView = (ImageView)findViewById(R.id.record_picture_imageView);
                imageView.setImageURI(Uri.parse(pictureList.get(i).get("uri")));
                //imageView.setImageResource((Integer)adapterView.getAdapter().getItem(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        SubMenu itemAdd = menu.addSubMenu("添加").setIcon(R.drawable.menu_add);
        itemAdd.add("图库");
        itemAdd.add("相机");
        MenuItem itemHelp = menu.add("帮助").setIcon(R.drawable.menu_help);
        MenuItem itemContact = menu.add("联系").setIcon(R.drawable.menu_contact);
        return super.onCreateOptionsMenu(menu);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = (String) item.getTitle();
        if(title.equals("图库")){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "选择图片"), SELECT_IMAGE_ACTIVITY_REQUEST_CODE);

        }else if(title.equals("相机")){

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);


        }else if(title.equals("帮助")){
            Menu_Functions.helpMe(this);
        }else if(title.equals("联系")){
            Menu_Functions.contactMe(this);
        }
        return super.onOptionsItemSelected(item);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                Toast.makeText(this, "Image saved to:\n" +
                        data.toURI(), Toast.LENGTH_LONG).show();
                String uri = String.valueOf(data.getData());
            }else if(requestCode==RESULT_CANCELED){

            }else{

            }
        } else if(requestCode == SELECT_IMAGE_ACTIVITY_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                Toast.makeText(this, "Select image from\n" +
                        data.getDataString(), Toast.LENGTH_LONG).show();
                String result = data.getDataString();
                HashMap<String, String> picture = new HashMap<String, String>();
                picture.put("userID", Login.userID);
                picture.put("time", String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
                picture.put("date", picture.get("time"));
                picture.put("title", picture.get("Untitle"));
                picture.put("uri", result);
                pictureList.add(picture);
                record_picture_adapter.notifyDataSetChanged();

            }else if(requestCode==RESULT_CANCELED){

            }else{

            }

        }
        super.onActivityResult(requestCode, resultCode, data);    //To change body of overridden methods use File | Settings | File Templates.
    }

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int SELECT_IMAGE_ACTIVITY_REQUEST_CODE = 200;

}