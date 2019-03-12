package tw.tcnr21.m1904;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class M1904 extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 101;
    private final int MAX_RECORDS = 10;
    private LocationManager manager;
    private Location currentLocation;
    private int index = 0, count = 0;
    private double[] Lats = new double[MAX_RECORDS];
    private double[] Lngs = new double[MAX_RECORDS];
    //--------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1904);
        // 取得系統服務的LocationManager物件
        manager = (LocationManager)getSystemService(LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }
    //-----------------------------------
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 已經取得權限
                Toast.makeText(this, "取得權限取得GPS資訊",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "直到取得權限, 否則無法取得GPS資訊",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    //-----------------------------------
    // 啟動Google地圖
    public void button_Click(View view) {
        // 取得經緯度座標
        float latitude = (float) currentLocation.getLatitude();
        float longitude = (float) currentLocation.getLongitude();
        // 建立URI字串
        String uri = String.format("geo:%f,%f?z=18", latitude, longitude);
        // 建立Intent物件
        Intent geoMap = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(geoMap);  // 啟動活動
    }
    // 在MapView元件顯示個人行蹤
    public void button2_Click(View view) {
        // 建立Intent物件
        Intent mapView = new Intent(this, MapsActivity.class);
        mapView.putExtra("GPSLATITUDE", Lats);
        mapView.putExtra("GPSLONGITUDE", Lngs);
        mapView.putExtra("MAX_INDEX", count);
        startActivity(mapView);  // 啟動活動
    }

    //-----------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        // 取得最佳的定位提供者
        Criteria criteria = new Criteria();
        String best = manager.getBestProvider(criteria, true);
        // 更新位置頻率的條件
        int minTime = 5000; // 毫秒
        float minDistance = 15; // 公尺
        try {
            if (best != null) { // 取得快取的最後位置,如果有的話
                currentLocation = manager.getLastKnownLocation(best);
                manager.requestLocationUpdates(best, minTime,
                        minDistance, listener);
            } else { // 取得快取的最後位置,如果有的話
                currentLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        minTime, minDistance, listener);
            }
        }
        catch(SecurityException e) {
            Log.e("M1907", "GPS權限失敗..." + e.getMessage());
        }
        updatePosition(); // 更新位置
    }
    //-----------------------------------
    @Override
    protected void onPause() {
        super.onPause();
        try {
            manager.removeUpdates(listener);
        }
        catch(SecurityException e) {
            Log.e("M1907", "GPS權限失敗..." + e.getMessage());
        }
    }
    //-----------------------------------
    // 更新現在的位置
    private void updatePosition() {
        TextView output, list;
        String str = "最近個人行蹤的座標清單:\n";
        output = (TextView) findViewById(R.id.lblOutput);
        list = (TextView) findViewById(R.id.lblList);
        if(currentLocation == null) {
            output.setText("取得定位資訊中...");
        } else {
            // 顯示目前經緯度座標資訊
            output.setText(getLocationInfo(currentLocation));
            // 顯示個人行蹤的座標清單
            for (int i = 0; i < MAX_RECORDS; i++) {
                if (Lats[i] != 0.0)
                    str += Lats[i] + "/" + Lngs[i] +"\n";
            }
            list.setText(str);
        }
    }
    //-----------------------------------------------------------
    // 建立定位服務的傾聽者物件
    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            currentLocation = location;
            updatePosition();
        }
        @Override
        public void onProviderDisabled(String provider) { }
        @Override
        public void onProviderEnabled(String provider) { }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { }
    };
    // 取得定位資訊
    public String getLocationInfo(Location location) {
        boolean isSave = true;
        double lat, lng;
        lat = location.getLatitude();
        lng = location.getLongitude();
        StringBuffer str = new StringBuffer();
        str.append("定位提供者(Provider): "+location.getProvider());
        str.append("\n緯度(Latitude): " + Double.toString(lat));
        str.append("\n經度(Longitude): " + Double.toString(lng));
        if (count >= 1) { // 檢查是否需要儲存座標
            // 建立目地GPS座標的Location物件
            Location dest = new Location(location);
            // 前一個座標的陣列索引
            int preIndex = index - 1;
            // 檢查前一座標是否是陣列最後一個元素
            if (preIndex < 0 ) preIndex = Lats.length - 1;
            // 指定目的Location物件的座標
            dest.setLatitude(Lats[preIndex]);
            dest.setLongitude(Lngs[preIndex]);
            Log.d("M1907", count + " index/preIndex: " + index + "/" + preIndex);
            Log.d("M1907", "dlat: " + Lats[preIndex]);
            Log.d("M1907", "dlng: " + Lngs[preIndex]);
            // 計算與目地座標的距離
            float distance = location.distanceTo(dest);
            Toast.makeText(this, "距離: " + distance + "公尺",
                    Toast.LENGTH_SHORT).show();
            Log.d("M1907", "distance: " + distance);
            // 檢查距離是否小於20公尺, 小於不用存
            if (distance < 20.0) isSave = false;
        }
        if (isSave) { // 記錄座標
            Lats[index] = lat;
            Lngs[index] = lng;
            count++;
            if (count >= MAX_RECORDS) count = MAX_RECORDS;
            index++; // 陣列索引加一
            // 如果索引大於最大索引, 重設為0
            if (index >= MAX_RECORDS) index = 0;
        }
        return str.toString();
    }


}
