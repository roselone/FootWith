package edu.thu.cslab.footwith.client.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import edu.thu.cslab.footwith.client.R;

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
    Vector<Integer> pictureIDVector;
    private ArrayList<HashMap<String, String>> pictureList =new ArrayList<HashMap<String,String>>();
    Context mContext;

    public Record_Picture_Adapter(Context mContext, Vector<Integer> pictureIDVector) {
        this.mContext = mContext;
        this.pictureIDVector = pictureIDVector;
    }

    public Record_Picture_Adapter(Context mcontext,ArrayList<Integer> mImages) {
        this.mImages = mImages;
        this.mContext = mcontext;
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
        return mImages.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getItem(int i) {
        return mImages.get(i);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int i) {
        return i;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        ImageView view = new ImageView(mContext);

        view.setImageResource(mImages.get(position));
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setLayoutParams(new Gallery.LayoutParams(160, 88*160/136));

        // The preferred Gallery item background
        view.setBackgroundResource(mContext.obtainStyledAttributes(R.styleable.Gallery1).getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 0));

        return view;

    }

}
