package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import edu.thu.cslab.footwith.client.helper.Record_Basic_Sites_Adapter;
import edu.thu.cslab.footwith.client.helper.Record_Basic_Users_Adapter;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import org.json.JSONException;

import java.util.Vector;

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
        Bundle bundle = getIntent().getExtras();
        String sites = (String) bundle.get("sites");
        String users = (String) bundle.get("users");
        Vector<String> sites_vector = null;
        Vector<String> users_vector = null;
        try {
            sites_vector = JSONHelper.getJSONHelperInstance().convertToArray2(sites);
            users_vector = JSONHelper.getJSONHelperInstance().convertToArray2(users);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        ListView sites_lv = (ListView) findViewById(R.id.basic_sites_listView);
        ListView users_lv = (ListView) findViewById(R.id.basic_users_listView);
        sites_lv.setAdapter(new Record_Basic_Sites_Adapter(this, sites_vector));
        users_lv.setAdapter(new Record_Basic_Users_Adapter(this, users_vector));
    }
}