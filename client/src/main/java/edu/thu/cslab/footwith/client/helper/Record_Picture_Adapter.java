package edu.thu.cslab.footwith.client.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import edu.thu.cslab.footwith.client.R;
import edu.thu.cslab.footwith.utility.Util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 4/29/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Record_Picture_Adapter extends BaseAdapter {
    ArrayList<Integer> mImages = new ArrayList<Integer>();
    private ArrayList<HashMap<String, String>> pictureList =new ArrayList<HashMap<String,String>>();
    Context mContext;


    public Record_Picture_Adapter(Context mContext, ArrayList<HashMap<String, String>> pictureList) {
        this.mContext = mContext;
        this.pictureList = pictureList;
    }

    public Record_Picture_Adapter(Context mcontext) {
        this.mContext = mcontext;
        mImages.add(R.drawable.gallery_photo_1);
        mImages.add(R.drawable.gallery_photo_2);
        mImages.add(R.drawable.gallery_photo_3);
        mImages.add(R.drawable.gallery_photo_4);
        mImages.add(R.drawable.gallery_photo_5);
        mImages.add(R.drawable.gallery_photo_6);
        mImages.add(R.drawable.gallery_photo_7);
        mImages.add(R.drawable.gallery_photo_8);

    }

    @Override
    public int getCount() {
        return pictureList.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getItem(int i) {
        return pictureList.get(i);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int i) {
        return i;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        ImageView view = new ImageView(mContext);
        HashMap<String, String> picture = pictureList.get(position);
        String uri = picture.get("uri");
        String pic = picture.get("picture");
        if(!Util.isEmpty(uri)){
            view.setImageURI(Uri.parse(uri));
        }else if(!Util.isEmpty(pic)){
            try {
                byte[] pic_byte = net.iharder.Base64.decode(pic.getBytes());
                ByteArrayInputStream imageStream = new ByteArrayInputStream(pic_byte);
                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                view.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setLayoutParams(new Gallery.LayoutParams(160, 88*160/136));
        /*
        try {
            InputStream is = (InputStream) new URL(uri).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");


        } catch (Exception e) {
            return null;
        }
        */
        /*
        view.setImageResource(mImages.get(position));
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setLayoutParams(new Gallery.LayoutParams(160, 88*160/136));
        */

        // The preferred Gallery item background
        view.setBackgroundResource(mContext.obtainStyledAttributes(R.styleable.Gallery1).getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 0));

        return view;

    }

}
