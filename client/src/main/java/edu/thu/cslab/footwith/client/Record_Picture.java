package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.*;
import android.widget.*;
import edu.thu.cslab.footwith.client.helper.Menu_Functions;
import edu.thu.cslab.footwith.client.helper.MyPictureNetwork;
import edu.thu.cslab.footwith.client.helper.Record_Picture_Adapter;
import edu.thu.cslab.footwith.utility.Util;

import java.io.*;
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
    MyPictureNetwork myPictureNetwork;
    Record_Picture_Adapter record_picture_adapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_picture);
        Bundle bundle = getIntent().getExtras();
        String recordID = (String) bundle.get("recordID");
        String pictures = (String) bundle.get("pictures");
        myPictureNetwork = new MyPictureNetwork();
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
                HashMap<String, String> picture = pictureList.get(i);
                String uri = picture.get("uri");
                String pic = picture.get("picture");
                if(!Util.isEmpty(uri)){
                    imageView.setImageURI(Uri.parse(uri));
                }else if(!Util.isEmpty(pic)){
                    try {
                        byte[] pic_byte = net.iharder.Base64.decode(pic.getBytes());
                        ByteArrayInputStream imageStream = new ByteArrayInputStream(pic_byte);
                        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                TextView titleTextView = (TextView) findViewById(R.id.picture_title_textView);
                TextView userTextView = (TextView) findViewById(R.id.picture_user_textView);
                TextView dateTextView = (TextView) findViewById(R.id.picture_date_textView);
                titleTextView.setText(pictureList.get(i).get("title"));
                userTextView.setText(pictureList.get(i).get("userName"));
                dateTextView.setText(pictureList.get(i).get("date"));
            }
        });
        g.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //To change body of implemented methods use File | Settings | File Templates.
                ImageView imageView = (ImageView)findViewById(R.id.record_picture_imageView);
                HashMap<String, String> picture = pictureList.get(i);
                String uri = picture.get("uri");
                String pic = picture.get("picture");
                if(!Util.isEmpty(uri)){
                    imageView.setImageURI(Uri.parse(uri));
                }else if(!Util.isEmpty(pic)){
                    try {
                        byte[] pic_byte = net.iharder.Base64.decode(pic.getBytes());
                        ByteArrayInputStream imageStream = new ByteArrayInputStream(pic_byte);
                        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                TextView titleTextView = (TextView) findViewById(R.id.picture_title_textView);
                TextView userTextView = (TextView) findViewById(R.id.picture_user_textView);
                TextView dateTextView = (TextView) findViewById(R.id.picture_date_textView);
                titleTextView.setText(pictureList.get(i).get("title"));
                userTextView.setText(pictureList.get(i).get("userName"));
                dateTextView.setText(pictureList.get(i).get("date"));
                /*
                if(!Util.isEmpty(pictureList.get(i).get("uri"))){
                    imageView.setImageURI(Uri.parse(pictureList.get(i).get("uri")));
                }else{

                }
                */
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
                        data.getDataString(), Toast.LENGTH_LONG).show();
                String uri = data.getDataString();
                if(!Util.isEmpty(uri)){
                    HashMap<String, String> picture = new HashMap<String, String>();
                    picture.put("userID", Login.userID);
                    picture.put("userName", Login.userName);
                    picture.put("time", String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
                    picture.put("date", picture.get("time"));
                    picture.put("title", picture.get("Untitle"));
                    picture.put("uri", uri);
                    File picFile = new File(uri);
                    picture.put("pictureName", picFile.getName());
                    try {
                        FileInputStream fileInputStream = new FileInputStream(picFile);
                        byte []buf = new byte[(int) picFile.length()];
                        fileInputStream.read(buf);
                        String picCode  = net.iharder.Base64.encodeBytes(buf);
                        picture.put("picture", picCode);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }


                    //pictureList.add(picture);
                    myPictureNetwork.add(picture);
                    record_picture_adapter.notifyDataSetChanged();
                }
            }else if(requestCode==RESULT_CANCELED){

            }else{

            }
        } else if(requestCode == SELECT_IMAGE_ACTIVITY_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                Toast.makeText(this, "Select image from\n" +
                        data.getDataString(), Toast.LENGTH_LONG).show();
                String uri = data.getDataString();
                if(!Util.isEmpty(uri)){
                    HashMap<String, String> picture = new HashMap<String, String>();
                    picture.put("userID", Login.userID);
                    picture.put("userName", Login.userName);
                    picture.put("time", String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
                    picture.put("date", picture.get("time"));
                    picture.put("title", picture.get("Untitle"));
                    picture.put("uri", uri);
                    File picFile = new File(uri);
                    picture.put("pictureName", picFile.getName());
                    try {
                        FileInputStream fileInputStream = new FileInputStream(picFile);
                        byte []buf = new byte[(int) picFile.length()];
                        fileInputStream.read(buf);
                        String picCode  = net.iharder.Base64.encodeBytes(buf);
                        picture.put("picture", picCode);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    //pictureList.add(picture);
                    myPictureNetwork.add(picture);
                    record_picture_adapter.notifyDataSetChanged();
                }

            }else if(requestCode==RESULT_CANCELED){

            }else{

            }

        }
        super.onActivityResult(requestCode, resultCode, data);    //To change body of overridden methods use File | Settings | File Templates.
    }

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int SELECT_IMAGE_ACTIVITY_REQUEST_CODE = 200;

}