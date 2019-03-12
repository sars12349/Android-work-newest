package tw.tcnr21.m1906;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class M1906 extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 101;
    private EditText lat, lon;
    private TextView output, t004;
    private LocationManager manager;
    private Handler handler = new Handler();
    String b_lat="24.172127";
    String b_lon="120.610313";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1906);
        u_checkgps();
        setupViewComponent();
    }

    private void u_checkgps() {
        // 取得系統服務的LocationManager物件
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // 檢查是否有啟用GPS
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // 顯示對話方塊啟用GPS
            AlertDialog.Builder openGPS = new AlertDialog.Builder(this);
            openGPS.setTitle("定位管理")
                    .setMessage("GPS尚未啟用.\n" + "請問現在是否設定啟用GPS?")
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

    }


    private void setupViewComponent() {
        // 取得EditText元件
        lat = (EditText) findViewById(R.id.txtLat);
        lon = (EditText) findViewById(R.id.txtLong);
        lat.setText(b_lat);
        lon.setText(b_lon);
        // 取得TextView元件
        output = (TextView) findViewById(R.id.lblOutput);
        t004 = (TextView) findViewById(R.id.t004);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

    }

    public void btnStart_Click(View view) {//開始警告
        // 取得經緯度座標
        float latitude = Float.parseFloat(lat.getText().toString());
        float longitude = Float.parseFloat(lon.getText().toString());
        // 建立Intent物件
        Intent intent = new Intent(this, GPSService.class);
        // 加上傳遞資料
        intent.putExtra("LATITUDE", latitude);
        intent.putExtra("LONGITUDE", longitude);
        startService(intent); // 啟動服務
        output.setText("服務啟動中...");

        final String Action = "FilterString";
       IntentFilter filter = new IntentFilter(Action);
        // 將 BroadcastReceiver 在 Activity 掛起來。
        registerReceiver(receiver, filter);
    }
    BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String mdistance = intent.getStringExtra("Distance");
            if (mdistance != null) {
                t004.setText("距離:" + mdistance + "公尺");
            } else {
                t004.setText("距離:尚未抓取");
            }
        }
    };



    public void btnStop_Click(View view) {//停止警告
        Intent intent = new Intent(this, GPSService.class);
        stopService(intent); // 停止服務
        handler.removeCallbacks(updatime);//停止序
        output.setText("服務停止中...");
    }

    public void btnFinish_Click(View view) {//結束
        //        finish();
        // 取得經緯度座標
        float latitude = Float.parseFloat(lat.getText().toString());
        float longitude = Float.parseFloat(lon.getText().toString());
        // 建立Intent物件
        Intent intent = new Intent(this, GPSService.class);
        // 加上傳遞資料
        intent.putExtra("LATITUDE", latitude);
        intent.putExtra("LONGITUDE", longitude);
        startService(intent); // 啟動服務
        output.setText("服務啟動中...");
        handler.postDelayed(updatime, 1000);
    }
    private Runnable updatime = new Runnable() {
        @Override
        public void run() {
            String dis = GPSService.t_distance();
            t004.setText("距離:" + dis + "公尺");
            handler.postDelayed(this, 1000);
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
