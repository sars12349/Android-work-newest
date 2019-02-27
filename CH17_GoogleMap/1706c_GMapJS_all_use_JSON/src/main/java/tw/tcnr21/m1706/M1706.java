package tw.tcnr21.m1706;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class M1706 extends AppCompatActivity {
    private static String[][] locations = {
            { "中區職訓", "24.172127,120.610313" },
            { "東海大學路思義教堂", "24.179051,120.600610" },
            { "台中公園湖心亭", "24.144671,120.683981" },
            { "秋紅谷", "24.1674900,120.6398902" },
            { "台中火車站", "24.136829,120.685011" },
            { "國立科學博物館", "24.1579361,120.6659828" },
            {"我的家", "24.1046944,120.6368772"}};
    private Spinner mSpnLocation;

    private static final String MAP_URL = "file:///android_asset/GoogleMap.html";
    // 自建的html檔名
    private WebView webView;
    private String Lat;
    private String Lon;
    private String jcontent;//地名變數

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1706);
        setupViewComponent();
    }
    private void setupViewComponent() {
        webView = (WebView) findViewById(R.id.webview);
        mSpnLocation = (Spinner) this.findViewById(R.id.spnLocation);
        mSpnLocation.getBackground().setAlpha(150);//0-255

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(M1706.this, "AndroidFunction");
        webView.loadUrl(MAP_URL);

        // ----Location-----------
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_spinner_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_style);
        for (int i = 0; i < locations.length; i++)
            adapter.add(locations[i][0]);

//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.spinner_style);
        mSpnLocation.setAdapter(adapter);
        mSpnLocation.setOnItemSelectedListener(mSpnLocationOnItemSelLis);
    }

    private AdapterView.OnItemSelectedListener mSpnLocationOnItemSelLis = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView parent, View v, int position,
                                   long id) {

            setMapLocation();  //自定義方法
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    };

    private void setMapLocation() {

        int iSelect = mSpnLocation.getSelectedItemPosition();

        String[] sLocation = locations[iSelect][1].split(",");

        Lat = sLocation[0]; // 南北緯
        Lon = sLocation[1]; // 東西經

        jcontent = locations[iSelect][0];  //地名

        webView.getSettings().setJavaScriptEnabled(true);  //
        webView.addJavascriptInterface(M1706.this, "AndroidFunction");//
        webView.loadUrl(MAP_URL);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//		Intent it = new Intent();
        switch (item.getItemId()) {
            case R.id.action_settings:
                // return true;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //-----------------------------

    @JavascriptInterface
    public String GetLat(){
        return Lat;
    }

    @JavascriptInterface
    public String GetLon(){
        return Lon;
    }

    @JavascriptInterface
    public String Getjcontent(){
        return jcontent;
    }

    @JavascriptInterface
    public String GetJsonArry() {
        return ArryToJson();
    }

    private String ArryToJson() {

       JSONArray jArry = new JSONArray();

        for (int i = 0; i < locations.length; i++) {
            JSONObject jObj = new JSONObject();// 一定要放在這裡
            String[] arr = locations[i][1].split(",");

            try {
                jObj.put("title", locations[i][0]);
                jObj.put("jlat", arr[0]);
                jObj.put("jlon", arr[1]);

                jArry.put(jObj);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String string_jArry=jArry.toString();
        return string_jArry;

    }


//-------------------------------



}
