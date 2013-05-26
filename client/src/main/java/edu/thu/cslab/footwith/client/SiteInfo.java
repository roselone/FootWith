package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import edu.thu.cslab.footwith.client.helper.ServerConnector;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import net.iharder.Base64;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

public class SiteInfo extends Activity{

    /**
     * Called when the activity is first created.
     */
    boolean flag=false;
    private List<Map<String, Object>> mData;
    private int latitude;
    private int longitude;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siteinfo);

        final ImageButton btn1 = (ImageButton)findViewById(R.id.imageButton);
        ImageButton mapButton=(ImageButton)findViewById(R.id.mapButton);
        TextView   text2=(TextView) findViewById(R.id.textView);
        TextView  text3=(TextView) findViewById(R.id.textView1);
        RatingBar rate1=(RatingBar) findViewById(R.id.ratingBar);
        ImageView pic1=(ImageView) findViewById(R.id.imageView);

        Bundle bundle = this.getIntent().getExtras();    //获得前一个界面选择的景点名称景点ID
        final String siteName = bundle.getString("siteName");
        final String siteID = bundle.getString("siteId");
        System.out.println(siteName);

        //查看景点是否已经被在userlike中

        if(Login.userLike.keySet().contains(Integer.valueOf(siteID)))
        {
            flag = true;
        }  else {
            flag = false;
        }
//        //查看景点是否已经被在userlike中
//         Iterator it = Login.userLike.keySet().iterator();
//
//         while (it.hasNext())
//         {
//            Integer key;
//            key=(Integer)it.next();
//             if( key==Integer.valueOf(siteID))
//             {
//                 flag = true;
//             }
//         }

        if(flag)
        {

            btn1.setBackgroundResource(R.drawable.heart);

        }
        else
        {
            btn1.setBackgroundResource(R.drawable.heart1);
        }


        HashMap<String, String> map1= new HashMap<String, String>();
        HashMap<String, String> map2= new HashMap<String, String>();
        Vector<String> temp=new Vector<String>();
        String result= null;
        ServerConnector conn= new ServerConnector("site");

        try {
            result = conn.setRequestParam("siteID",siteID ).doPost();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println(result.toString());
        map1= JSONHelper.getJSONHelperInstance().convertToMap(result);
        map2 = JSONHelper.getJSONHelperInstance().convertToMap(map1.get("site"));
        Bitmap img=null;
        Bitmap img2=null;

        //静态测试
        String  name="扬州市瘦西湖风景区" ;
        String  brief="      瘦西湖(Slender West Lake)，位于扬州市北郊，现有游览区面积100公顷右，" +
                "1988年被国务院列为“具有重要历史文化遗产和扬州园林特色的国家重点名胜区”。" +
                "2010年被授予中国旅游界含金量最高荣誉——全国AAAAA级景区，成为扬州 首家国家5A级旅游景区。";

        try {
            byte[] p1 = Base64.decode(map2.get("picture"));
            img = BitmapFactory.decodeByteArray(p1, 0, p1.length);
            img2=  Bitmap.createScaledBitmap(img,380, 340, true);




        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        text2.setText( map2.get("siteName"));
        text3.setText( map2.get("brief"));
        latitude=Integer.valueOf(map2.get("latitude"));
        longitude=Integer.valueOf(map2.get("longitude"));
        rate1.setRating( Integer.valueOf(map2.get("rate")));  //显示获取的图片
        pic1.setImageBitmap(img2);

        btn1.setOnClickListener(new OnClickListener() {

            public void onClick(View v)
            {
                if(!flag)
                {

                    btn1.setBackgroundResource(R.drawable.heart);

                    if(!Login.userLike.keySet().contains(Integer.valueOf(siteID))){
                        Login.userLike.put( Integer.valueOf(siteID),siteName);
                        try {
                            String result = sendLike();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    flag = true;
                }
                else
                {

                    btn1.setBackgroundResource(R.drawable.heart1);
                    Login.userLike.remove(Integer.valueOf(siteID));
                    try {
                        String result = sendLike();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    flag = false;

                }
            }
        });
        mapButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(SiteInfo.this, SiteMap.class);
                Bundle bundle = new Bundle();
                bundle.putInt("latitude", latitude);
                bundle.putInt("longitude", longitude);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }



    public boolean onKeyDown(int keyCode,KeyEvent event) {// 如果是返回键
        if(keyCode == KeyEvent.KEYCODE_BACK){           //want to do
            // setContentView(R.layout.main);
        }
        return super.onKeyDown(keyCode, event);
        //  return true;
    }

    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts)
    {

        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }
    private String sendLike() throws IOException {
        ServerConnector sc = new ServerConnector("user");
        sc.setRequestParam("userID", Login.userID);
        //HashMap<String, String> userLikeStringMap = new HashMap<String, String>();
        //Integer[] keys = (Integer[]) Login.userLike.keySet().toArray();
        Vector<Integer> siteIDKeys = new Vector<Integer>(Login.userLike.keySet());
        /*
        for(Integer siteIDKey: Login.userLike.keySet()){
            userLikeStringMap.put(String.valueOf(siteIDKey), Login.userLike.get(siteIDKey));
        }
        */
        sc.setRequestParam("like", JSONHelper.getJSONHelperInstance().convertToString(siteIDKeys));
        String result = sc.doPost();
        return result;
    }

}