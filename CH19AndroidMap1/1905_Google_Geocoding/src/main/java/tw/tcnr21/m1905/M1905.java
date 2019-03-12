package tw.tcnr21.m1905;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class M1905 extends AppCompatActivity {
    private final int maxResult = 3;
    private String addressList[] = new String[maxResult];
    private ArrayAdapter<String> adapter;
    private TextView output;
    private Geocoder geocoder;
    private EditText lat, lon, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1905);
        setupViewComponent();
    }

    //---------------------------------------------------
    private void setupViewComponent() {

        // 取得經緯度EditText元件
        lat = (EditText) findViewById(R.id.txtLat);
        lon = (EditText) findViewById(R.id.txtLong);
        // 取得座標輸出元件
        output = (TextView) findViewById(R.id.lblOutput);
        // 取得住址元件
        address = (EditText) findViewById(R.id.txtAddress);
        // 建立Geocoder物件
        geocoder = new Geocoder(this, Locale.TAIWAN);
    }

    //-----------------------------------------------
    // Button元件的事件處理 - 將經緯度座標轉換成地址
    public void button_Click(View view) {
        // 取得經緯度座標
        float latitude = Float.parseFloat(lat.getText().toString());
        float longitude = Float.parseFloat(lon.getText().toString());
        try {
            // 取得地址清單的List物件
            List<Address> listAddress = geocoder.getFromLocation(latitude, longitude, maxResult);
            // 是否有取得地址
            if (listAddress != null){
                Spinner spinner=(Spinner)findViewById(R.id.spinAddress);
                // 指定陣列的初值
                for (int i=0; i<maxResult; i++) addressList[i]="N/A";//清空Spinner
                int index=0;
                for (int i=0; i<maxResult; i++){
                    Address findAddress=listAddress.get(i);
                    // 建立StringBuilder物件
                    StringBuilder strAddress=new StringBuilder();
                    // 取得地址的內容
                    for (int j=0; findAddress.getAddressLine(j)!=null; j++){
                        String str=findAddress.getAddressLine(0);
                        strAddress.append(str).append("\n");
                    }
                    if (strAddress.length()>0){// 指定陣列元素值
                        addressList[index++]=strAddress.toString();
                    }
                }
                // 建立結合器物件
                adapter=new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, addressList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }else{
                output.setText("查無地址!");
            }

        }catch (Exception ex){
            output.setText("錯誤:"+ex.toString());
        }

    }

    //---------------------------------------------
    // 將地址轉換成經緯度座標
    public void button2_Click(View view) {

        String addressName = address.getText().toString();
        try {
            // 取得經緯度座標清單的List物件
            List<Address> listGPSAddress = geocoder.getFromLocationName(addressName, 1);
            // 有找到經緯度座標
            if (listGPSAddress != null) {
                double latitude = listGPSAddress.get(0).getLatitude();
                double longitude = listGPSAddress.get(0).getLongitude();
                output.setText("緯度: " + latitude +
                        "\n經度: " + longitude);
                lat.setText(String.valueOf(latitude)); // 指定值
                lon.setText(String.valueOf(longitude));
            }
        } catch (Exception ex) {
            output.setText("錯誤:" + ex.toString());
        }
    }

    //----------------------------------------------
    // 啟動Google地圖
    public void button3_Click(View view) {
        // 取得經緯度座標
        float latitude = Float.parseFloat(lat.getText().toString());
        float longitude = Float.parseFloat(lon.getText().toString());
        // 建立URI字串
        String uri = String.format("geo:%f,%f?z=18", latitude, longitude);
        // 建立Intent物件
        Intent geoMap = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(geoMap);  // 啟動活動
    }
}

