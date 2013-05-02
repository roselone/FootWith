package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import edu.thu.cslab.footwith.client.helper.Record_Picture_Adapter;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 4/29/13
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Record_Picture extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_picture);
        Gallery g = (Gallery) findViewById(R.id.record_picture_gallery);
        g.setAdapter(new Record_Picture_Adapter(this));
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //To change body of implemented methods use File | Settings | File Templates.
                ImageView imageView = (ImageView)findViewById(R.id.record_picture_imageView);
                imageView.setImageResource((Integer)adapterView.getAdapter().getItem(i));
            }
        });
        g.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //To change body of implemented methods use File | Settings | File Templates.
                ImageView imageView = (ImageView)findViewById(R.id.record_picture_imageView);
                imageView.setImageResource((Integer)adapterView.getAdapter().getItem(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}