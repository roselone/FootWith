package edu.xdu.RL.FootWith.helper;

import android.app.Activity;
import edu.xdu.RL.FootWith.R;
import android.content.Context;
import android.content.Intent;

public class intentHelper {
	private Context packageContext;
	private Class<?> cls;
	private Activity activity;
	public intentHelper(Context from,Class<?> to,Activity act) {
		// TODO Auto-generated constructor stub
		packageContext=from;
		cls=to;
		activity=act;
	}
	public void launth(){
		Intent intent=new Intent();
		intent.setClass(packageContext, cls);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
	}
}
