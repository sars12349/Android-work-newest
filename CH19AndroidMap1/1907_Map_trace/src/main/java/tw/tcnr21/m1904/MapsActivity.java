package tw.tcnr21.m1904;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private double[] Lats, Lngs;
    private int max_index;
    private GoogleMap mMap;
    //---------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        // 取得Intent物件附加的陣列
        Lats = getIntent().getDoubleArrayExtra("GPSLATITUDE");
        Lngs = getIntent().getDoubleArrayExtra("GPSLONGITUDE");

        max_index = getIntent().getIntExtra("MAX_INDEX", 10);
        // 取得SupportMapFragment片段
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);   // 通知地圖已經可以使用
    }
    //--------------------------------------------------------------------
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mMap != null) {
            setUpMap(Lats, Lngs, max_index);
        }
    }

    // 顯示行蹤的標記
    private void setUpMap(double[] Lats, double[] Lngs, int max_index) {
        LatLng first_pos = new LatLng(Lats[0], Lngs[0]); // 建立第1個LatLng物件的座標
        for (int i = 0; i < max_index; i++) {
            // 新增Marker標記
            mMap.addMarker(new MarkerOptions().position(new LatLng(Lats[i], Lngs[i]))
                    .title(Lats[i] + "/" + Lngs[i]));
        }
        // 顯示目前位址的附近地圖
        CameraPosition cp = new CameraPosition.Builder()
                .target(first_pos)
                .zoom(16)
                .bearing(70)
                .tilt(25)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
    }
//--------------------------------------------------------------
}

