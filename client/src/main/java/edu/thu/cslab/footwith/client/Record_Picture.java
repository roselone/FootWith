package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.*;
import android.widget.*;
import edu.thu.cslab.footwith.client.helper.Menu_Functions;
import edu.thu.cslab.footwith.client.helper.MyPictureNetwork;
import edu.thu.cslab.footwith.client.helper.Record_Picture_Adapter;
import edu.thu.cslab.footwith.client.helper.WeiboFunction;
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
        int position = bundle.getInt("position");
        myPictureNetwork = new MyPictureNetwork();

        myPictureNetwork.requestList(pictures, recordID, position);
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

            //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            //picFileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
            //picFileUri = Uri.fromFile(new File("file:///sdcard/Pictures/FootWith/IMG_"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())+".jpg"));
            //intent.putExtra(MediaStore.EXTRA_OUTPUT, picFileUri); // set the image file name
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);


        }else if(title.equals("帮助")){
            Menu_Functions.helpMe(this);
        }else if(title.equals("联系")){
            Menu_Functions.contactMe(this);
        }
        //return super.onOptionsItemSelected(item);    //To change body of overridden methods use File | Settings | File Templates.
        return true;
    }

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "FootWith");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("FootWith", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    private Uri picFileUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
            //Bundle
            if(resultCode==RESULT_OK){
                Toast.makeText(this, "Image saved\n", Toast.LENGTH_LONG).show();
                final Bitmap pictureBMP = (Bitmap) data.getExtras().get("data");
                if(pictureBMP!=null){
                    HashMap<String, String> picture = new HashMap<String, String>();
                    picture.put("userID", Login.userID);
                    picture.put("userName", Login.userName);
                    picture.put("time", String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
                    picture.put("date", picture.get("time"));
                    picture.put("title", "Untitle");
                    picture.put("uri", "null");
                    picture.put("pictureName", "IMG_"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())+".jpg");
                    ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
                    pictureBMP.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayBitmapStream);
                    byte[] buf = byteArrayBitmapStream.toByteArray();
                    String picCode  = net.iharder.Base64.encodeBytes(buf);
                    picture.put("picture", picCode);

                    AlertDialog.Builder builder_weibo = new AlertDialog.Builder(Record_Picture.this);
                    final String weiboContent = String.valueOf(picture.get("title")) +"。##来自我的Footwith";
                    builder_weibo.setMessage(weiboContent);
                    builder_weibo.setTitle("同步到新浪微博？");
                    builder_weibo.setPositiveButton("发布", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //WeiboFunction weiboFunction = WeiboFunction.getInstance();
                            WeiboFunction.authorize(Record_Picture.this);
                            picFileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                            String filePath = picFileUri.getPath();
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                                pictureBMP.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                                String weiboUpdateResult = WeiboFunction.WeiboStatusUpload(Record_Picture.this, weiboContent, filePath, null);
                                if(weiboUpdateResult.equals("success")){
                                    Toast.makeText(Record_Picture.this, "微博发布成功", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(Record_Picture.this, "微博发布失败", Toast.LENGTH_LONG).show();
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }


                        }
                    });
                    builder_weibo.setNegativeButton("算了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder_weibo.show();

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
                    picture.put("title", "Untitle");
                    picture.put("uri", uri);
                    String[] proj = { MediaStore.Images.Media.DATA };
                    Cursor cursor = managedQuery(Uri.parse(uri), proj, null, null, null);
                    int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    final String filePath = cursor.getString(index);
                    File picFile = new File(filePath);
                    picture.put("pictureName", picFile.getName());
                    try {
                        FileInputStream fileInputStream = new FileInputStream(picFile);
                        final byte []buf = new byte[(int) picFile.length()];
                        fileInputStream.read(buf);
                        final String picCode  = net.iharder.Base64.encodeBytes(buf);
                        picture.put("picture", picCode);

                        AlertDialog.Builder builder_weibo = new AlertDialog.Builder(Record_Picture.this);
                        final String weiboContent = String.valueOf(picture.get("title")) +"。##来自我的Footwith";
                        builder_weibo.setMessage(weiboContent);
                        builder_weibo.setTitle("同步到新浪微博？");
                        builder_weibo.setPositiveButton("发布", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //WeiboFunction weiboFunction = WeiboFunction.getInstance(Record_Picture.this);
                                WeiboFunction.authorize(Record_Picture.this);
                                String weiboUpdateResult = WeiboFunction.WeiboStatusUpload(Record_Picture.this, weiboContent, filePath, null);
                                if(weiboUpdateResult.equals("success")){
                                    Toast.makeText(Record_Picture.this, "微博发布成功", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(Record_Picture.this, "微博发布失败", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        builder_weibo.setNegativeButton("算了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder_weibo.show();

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
        //super.onActivityResult(requestCode, resultCode, data);    //To change body of overridden methods use File | Settings | File Templates.
        return;
    }

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int SELECT_IMAGE_ACTIVITY_REQUEST_CODE = 200;

}