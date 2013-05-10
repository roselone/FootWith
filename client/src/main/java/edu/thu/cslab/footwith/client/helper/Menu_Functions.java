package edu.thu.cslab.footwith.client.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 5/10/13
 * Time: 7:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class Menu_Functions {
    public static void contactMe(Context context){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:08618201606720"));
        context.startActivity(callIntent);
    }
    public static void helpMe(Context context){

    }

}
