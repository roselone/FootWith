package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 4/24/13
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Record extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
        ListView recordList = (ListView) findViewById(R.id.listView_Record);

    }
}