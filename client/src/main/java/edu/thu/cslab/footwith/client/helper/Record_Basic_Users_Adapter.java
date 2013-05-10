package edu.thu.cslab.footwith.client.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.thu.cslab.footwith.client.R;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 5/2/13
 * Time: 10:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Record_Basic_Users_Adapter extends BaseAdapter {
    @Override
    public int getCount() {
        return users.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int i) {
        return i;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        View view = null;
        view = mInflater.inflate(R.layout.basic_users_listitem, null);
        TextView textView = (TextView) view.findViewById(R.id.basic_username_textView);
        textView.setText(users.get(position).toString());
        return view;  //To change body of implemented methods use File | Settings | File Templates.
    }
    private Context context;
    private LayoutInflater mInflater;
    private Vector<String> users;

    public Record_Basic_Users_Adapter(Context context, Vector<String> users) {
        this.context = context;
        this.users = users;
        this.mInflater=LayoutInflater.from(context);
    }

    public Record_Basic_Users_Adapter(Context context) {
        this.context = context;
        this.mInflater=LayoutInflater.from(context);
    }
}
