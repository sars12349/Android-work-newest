package tw.tcnr21.m1908;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Settings;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;

public class M1908 extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap map;

    static LatLng VGPS = new LatLng(24.172127, 120.610313);
    int currentZoom = 12;
    private static String[][] locations = {
            {"中區職訓", "24.172127,120.610313"},
            {"東海大學路思義教堂", "24.179051,120.600610"},
            {"台中公園湖心亭", "24.144671,120.683981"},
            {"秋紅谷", "24.1674900,120.6398902"},
            {"台中火車站", "24.136829,120.685011"},
            {"國立科學博物館", "24.1579361,120.6659828"}};

    private static String[] mapType = {
            "街道圖",
            "衛星圖",
            "地形圖",
            "混合圖",
            "開啟路況",
            "關閉路況"};

    private Spinner mSpnLocation, mSpnMapType;
    double dLat, dLon;
    double latitude;
    double longitude;
    private BitmapDescriptor image_des;// 圖標顯示
    private int icosel; //圖示旗標
    //----------------

    /** GPS**/
    private TextView txtOutput;
    private TextView tmsg;
    private Marker markerMe;

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 101;
    private LocationManager locationManager;
    private Location currentLocation;
    private String provider; // 提供資料
    long minTime = 5000;// ms
    float minDist = 5.0f;// meter

    private final String TAG = "tcnr21=>";

    //----------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1908);
        //------------設定MapFragment-----------------------------------
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //-------------------------------------------------------
        u_checkgps();  //檢查GPS是否開啟
        setupViewComponent();

    }


    //--------------------
    private void setupViewComponent() {
        mSpnLocation = (Spinner) this.findViewById(R.id.spnLocation);
        mSpnMapType = (Spinner) this.findViewById(R.id.spnMapType);
        // -----------------------------------------------------------------
        txtOutput = (TextView) findViewById(R.id.txtOutput);
        tmsg = (TextView) findViewById(R.id.msg);
        // ---------------
        icosel = 1;  //設定圖示初始值
        // ---------------
        /** 設定景點下拉資料,由陣列傳入spinner下拉 */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item);

        for (int i = 0; i < locations.length; i++)
            adapter.add(locations[i][0]);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnLocation.setAdapter(adapter);
        // 指定事件處理物件
        mSpnLocation.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                setMapLocation();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // ---------------
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        for (int i = 0; i < mapType.length; i++)
            adapter.add(mapType[i]);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnMapType.setAdapter(adapter);
        //-----------設定ARGB 透明度----
//        mSpnMapType.setPopupBackgroundDrawable(new ColorDrawable(0x00FFFFFF)); //全透明
        mSpnMapType.setPopupBackgroundDrawable(new ColorDrawable(0x80FFFFFF)); //50%透明
        mSpnLocation.setPopupBackgroundDrawable(new ColorDrawable(0x80FFFFFF)); //50%透明
//        # ARGB依次代表透明度（alpha）、紅色(red)、綠色(green)、藍色(blue)
//        100% — FF       95% — F2        90% — E6        85% — D9
//        80% — CC        75% — BF        70% — B3        65% — A6
//        60% — 99        55% — 8C        50% — 80        45% — 73
//        40% — 66        35% — 59        30% — 4D        25% — 40
//        20% — 33        15% — 26        10% — 1A         5% — 0D         0% — 00
        //---------------
        mSpnMapType.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        map.setMapType(GoogleMap.MAP_TYPE_NORMAL); // 道路地圖。
                        break;
                    case 1:
                        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE); // 衛星空照圖
                        break;
                    case 2:
                        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN); // 地形圖
                        break;
                    case 3:
                        map.setMapType(GoogleMap.MAP_TYPE_HYBRID); // 道路地圖混合空照圖
                        break;
                    case 4:
                        map.setTrafficEnabled(true); //開啟路況
                        break;
                    case 5:
                        map.setTrafficEnabled(false); //關閉路況
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setMapLocation() {
        showloc(); //刷新所有景點

        int iSelect = mSpnLocation.getSelectedItemPosition();
        String[] sLocation = locations[iSelect][1].split(",");
        double dLat = Double.parseDouble(sLocation[0]);    // 南北緯
        double dLon = Double.parseDouble(sLocation[1]);    // 東西經
        String vtitle = locations[iSelect][0];
        //--- 設定所選位置之當地圖示 ---//
        image_des = BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE); //使用系統水滴

        VGPS = new LatLng(dLat, dLon);

        map.addMarker(new MarkerOptions()
                .position(VGPS)
                .title(vtitle)
                .snippet("座標:" + dLat + "," + dLon)
                .icon(image_des));// 顯示圖標文字


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(VGPS, currentZoom));
    }

    private void showloc() {
        // 將所有景點位置顯示
        for (int i = 0; i < locations.length; i++) {
            String[] sLocation = locations[i][1].split(",");
            dLat = Double.parseDouble(sLocation[0]); // 南北緯
            dLon = Double.parseDouble(sLocation[1]); // 東西經
            String vtitle = locations[i][0];
            // --- 設定所選位置之當地圖片 ---//
            switch (icosel) {
                case 0:
                    image_des = BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_BLUE); // 使用橘色系統水滴
                    break;
                case 1:
                    // 運用巨集
                    String idName = "t" + String.format("%02d", i + 1);
                    int resID = getResources().getIdentifier(idName, "drawable",
                            getPackageName());
                    image_des = BitmapDescriptorFactory.fromResource(resID);// 使用照片
                    break;
            }
            VGPS = new LatLng(dLat, dLon);// 更新成欲顯示的地圖座標

            map.addMarker(new MarkerOptions()
                    .position(VGPS)
                    .title(vtitle)
                    .snippet("座標:" + dLat + "," + dLon)
                    .icon(image_des));// 顯示圖標文字
        }
    }

    //--------------------------------------------------------------
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
//        mUiSettings = map.getUiSettings();//
//        開啟 Google Map 拖曳功能
        map.getUiSettings().setScrollGesturesEnabled(true);
//        右下角的導覽及開啟 Google Map功能
        map.getUiSettings().setMapToolbarEnabled(true);
//        左上角顯示指北針，要兩指旋轉才會出現
        map.getUiSettings().setCompassEnabled(true);
//        右下角顯示縮放按鈕的放大縮小功能
        map.getUiSettings().setZoomControlsEnabled(true);
        // --------------------------------
        map.addMarker(new MarkerOptions().position(VGPS).title("中區職訓"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(VGPS, currentZoom));
        //----------取得定位許可-----------------------
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //----顯示我的位置ICO-------
            map.setMyLocationEnabled(true);
        } else {
            Toast.makeText(getApplicationContext(), "GPS定位權限未允許", Toast.LENGTH_LONG).show();
        }
        //---------------------------------------------
    }


    //-------提示使用者開啟GPS定位------------------------------------
    private void u_checkgps() {
        // 取得系統服務的LocationManager物件
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // 檢查是否有啟用GPS
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // 顯示對話方塊啟用GPS
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("定位管理")
                    .setMessage("GPS目前狀態是尚未啟用.\n"
                            + "請問你是否現在就設定啟用GPS?")
                    .setPositiveButton("啟用", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 使用Intent物件啟動設定程式來更改GPS設定
                            Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(i);
                        }
                    })
                    .setNegativeButton("不啟用", null).create().show();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }


    }
// ============ GPS =================
    /** onMyLocationButtonClick */
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getApplicationContext(), "返回GPS目前位置", Toast.LENGTH_LONG).show();
        return true;
    }
    //***********************************************/
    /* 檢查GPS 是否開啟 */
    private boolean initLocationProvider() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
            return true;
        }
        return false;
    }

//------------------------------------
    /**
     * 位置變更狀態監視
     */
    LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
            Log.d(TAG, "locationListener->onLocationChanged:" + map.getCameraPosition().zoom + " currentZoom:"
                    + currentZoom);
            tmsg.setText("目前Zoom:" + map.getCameraPosition().zoom);
        }

        @Override
        public void onProviderDisabled(String provider) {
            updateWithNewLocation(null);
            Log.d(TAG, "onProviderDisabled");
        }

        @Override
        public void onProviderEnabled(String provider) {
            tmsg.setText("onProviderEnabled");
            Log.d(TAG, "onProviderEnabled");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.OUT_OF_SERVICE:
                    Log.v(TAG, "Status Changed: Out of Service");
                    tmsg.setText("Out of Service");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.v(TAG, "Status Changed: Temporarily Unavailable");
                    tmsg.setText("Temporarily Unavailable");
                    break;
                case LocationProvider.AVAILABLE:
                    Log.v(TAG, "Status Changed: Available");
                    tmsg.setText("Available");
                    break;
            }
        }

    };

    //-------------------
    private void nowaddress() {
// 取得上次已知的位置
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Location location = locationManager.getLastKnownLocation(provider);
            updateWithNewLocation(location);
            return;
        }

// 監聽 GPS Listener----------------------------------
// long minTime = 5000;// ms
// float minDist = 5.0f;// meter
//---網路和GPS來取得定位，因為GPS精準度比網路來的更好，所以先使用網路定位、
// 後續再用GPS定位，如果兩者皆無開啟，則跳無法定位的錯誤訊息
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Location location = null;
        if (!(isGPSEnabled || isNetworkEnabled))
            tmsg.setText("GPS 未開啟");
        else {
            if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        minTime, minDist, locationListener);
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                tmsg.setText("使用網路GPS");
            }

            if (isGPSEnabled) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        minTime, minDist, locationListener);
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                tmsg.setText("使用精確GPS");
            }
        }

    }

    private void updateWithNewLocation(Location location) {

        String where = "";
        if (location != null) {

            double lng = location.getLongitude();// 經度
            double lat = location.getLatitude();// 緯度
            float speed = location.getSpeed();// 速度
            long time = location.getTime();// 時間
            String timeString = getTimeString(time);

            where = "經度: " + lng + "\n緯度: " + lat + "\n速度: " + speed + "\n時間: " + timeString + "\nProvider: "
                    + provider;

            // 標記"我的位置"
            showMarkerMe(lat, lng);
            cameraFocusOnMe(lat, lng);

        } else {
            where = "*位置訊號消失*";
        }

        // 位置改變顯示
        txtOutput.setText(where);

    }

    // ------------------------------------------

    /**
     * cameraFocusOnMe
     */
    private void cameraFocusOnMe(double lat, double lng) {
        CameraPosition camPosition = new CameraPosition.Builder().target(new LatLng(lat, lng)).zoom(map.getCameraPosition().zoom).build();
        /* 移動地圖鏡頭 */
        map.animateCamera(CameraUpdateFactory.newCameraPosition(camPosition));
        Log.d(TAG, "cameraFocusOnMe");
        tmsg.setText("目前Zoom:" + map.getCameraPosition().zoom);

    }

    /*** 顯示目前位置 */
    private void showMarkerMe(double lat, double lng) {
        Log.d(TAG, "showMarkerMe");
        if (markerMe != null) {
            markerMe.remove();
        }
//------------------
        int resID = getResources().getIdentifier("q00", "drawable", getPackageName());
//-------------------------
        dLat = lat; // 南北緯
        dLon = lng; // 東西經
        String vtitle = "GPS位置:";
        String vsnippet = "座標:" + String.valueOf(dLat) + "," + String.valueOf(dLon);
        VGPS = new LatLng(lat, lng);// 更新成欲顯示的地圖座標
        MarkerOptions markerOpt = new MarkerOptions();
        markerOpt.position(new LatLng(lat, lng));
        markerOpt.title(vtitle);
        markerOpt.snippet(vsnippet);
        markerOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        markerMe = map.addMarker(markerOpt);
//----------------------------
        locations[0][1] = lat + "," + lng;
    }

    /***********************************************
     * timeInMilliseconds
     ***********************************************/
    private String getTimeString(long timeInMilliseconds) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timeInMilliseconds);
    }
    //----生命週期--------------------------------------
    @Override
    protected void onStart() {
        super.onStart();
        if (initLocationProvider()) {
            nowaddress();
        } else {
            txtOutput.setText("GPS未開啟,請先開啟定位！");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }
    //-----------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.item1:
                map.clear();  //歸零
                if (icosel < 1) {
                    icosel = 1; //用照片顯示
                    showloc();
                } else
                    icosel = 0; //用水滴顯示
                showloc();
                break;
            case R.id.action_settings:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

//--------------------end class-------------------------------------
}
