package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import edu.thu.cslab.footwith.client.helper.Record_Basic_Sites_Adapter;
import edu.thu.cslab.footwith.client.helper.Record_Basic_Users_Female_Adapter;
import edu.thu.cslab.footwith.client.helper.Record_Basic_Users_Male_Adapter;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 4/29/13
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Record_Basic extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_basic);
        ListView sites_lv = (ListView) findViewById(R.id.basic_sites_listView);
        ListView users_m_lv = (ListView) findViewById(R.id.basic_users_male_listView);
        ListView users_f_lv = (ListView) findViewById(R.id.basic_users_female_listView);
        sites_lv.setAdapter(new Record_Basic_Sites_Adapter(this));
        users_m_lv.setAdapter(new Record_Basic_Users_Male_Adapter(this));
        users_f_lv.setAdapter(new Record_Basic_Users_Female_Adapter(this));
    }
}