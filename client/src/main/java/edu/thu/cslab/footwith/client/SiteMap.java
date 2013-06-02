package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/25/13
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class SiteMap extends Activity {
    BMapManager mBMapMan=null;
    MapView mMapView = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mBMapMan=new BMapManager(getApplication());
        mBMapMan.init("7A2F70AEFE7E6E892DF49C152182CC60BF1F2FF7", null);

//注意：请在试用setContentView前初始化BMapManager对象，否则会报错
        setContentView(R.layout.site_map);
        mMapView=(MapView)findViewById(R.id.bmapsView);
        mMapView.setBuiltInZoomControls(true);
//设置启用内置的缩放控件
        MapController mMapController=mMapView.getController();
// 得到mMapView的控制权,可以用它控制和驱动平移和缩放
        Bundle bundle = this.getIntent().getExtras();
        GeoPoint point =new GeoPoint(bundle.getInt("latitude"),bundle.getInt("longitude"));
//用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
        mMapController.setCenter(point);//设置地图中心点
        mMapController.setZoom(14);//设置地图zoom级别
    }
    @Override
    protected void onDestroy(){
        mMapView.destroy();
        if(mBMapMan!=null){
            mBMapMan.destroy();
            mBMapMan=null;
        }
        super.onDestroy();
    }
    @Override
    protected void onPause(){
        mMapView.onPause();
        if(mBMapMan!=null){
            mBMapMan.stop();
        }
        super.onPause();
    }
    @Override
    protected void onResume(){
        mMapView.onResume();
        if(mBMapMan!=null){
            mBMapMan.start();
        }
        super.onResume();
    }
}
