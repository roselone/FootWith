package edu.thu.cslab.footwith.client.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.thu.cslab.footwith.client.R;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 5/2/13
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Record_Basic_Sites_Adapter extends BaseAdapter {
    private Vector<Integer> sites = new Vector<Integer>();
    private Context context;
    private LayoutInflater mInflater;
    @Override
    public int getCount() {
        return sites.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getItem(int i) {
        return sites.get(i);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int i) {
        return i;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        View view = null;
        view=mInflater.inflate(R.layout.basic_sites_listitem, null);
        TextView textView = (TextView) view.findViewById(R.id.basic_siteName_textView);
        textView.setText(sites.get(position).toString());
        return view;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public Record_Basic_Sites_Adapter(Context context) {
        this.context = context;
        this.mInflater=LayoutInflater.from(context);
    }

    public Record_Basic_Sites_Adapter(Vector<Integer> sites, Context context) {
        this.sites = sites;
        this.context = context;
        this.mInflater=LayoutInflater.from(context);
    }
}
