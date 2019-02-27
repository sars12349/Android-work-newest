package tw.tcnr21.m1603;

import android.app.Activity;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class M1603 extends Activity {
    private ListView list001;
    private TextView time;
    private String TAG="tcnr21=>";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1603);
        setupViewComponent();
    }

    private void setupViewComponent() {
        // 動態調整高度 抓取使用裝置尺寸
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int newscrollheight = displayMetrics.heightPixels * 80 / 100; // 設定ScrollView使用尺寸的4/5
        //
        list001 = (ListView) findViewById(R.id.listView1);
        list001.getLayoutParams().height = newscrollheight;
        list001.setLayoutParams(list001.getLayoutParams()); // 重定ScrollView大小
        time = (TextView) findViewById(R.id.time);
        // 解析 json
        try {
            String a = new TransTask().execute("https://quality.data.gov.tw/dq_download_json.php?nid=6037&md5_url=a155732e5592d56f8d69165cc59f59e5").get();
            Log.d(TAG,a);

            List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
//            List<String> list = new ArrayList<String>();
            // 解析 json
            JSONArray jsonArray = new JSONArray(a);
//            JSONObject jsonData = new JSONObject(a);
//            Log.d(TAG,jsonData.toString());
//            String aaData = jsonData.getString("aaData");
//            JSONArray jsonArray2 = new JSONArray(aaData);
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject jsonData = jsonArray.getJSONObject(j);
//                JSONObject jsonData2 = jsonArray2.getJSONObject(j);
                String Town = jsonData.getString("Town");//前日買進報價
                String Name = jsonData.getString("Name");//今日買進報價
                String Address = jsonData.getString("Address");//當日最高
                String Tel = jsonData.getString("Tel");//當日最低
                String City = jsonData.getString("City");//最近一筆成交價
                String ID = jsonData.getString("ID");

                Map<String, Object> item = new HashMap<String, Object>();

                item.put("t001",
                        "\n編號：" + ID +
                                "\n名子：" + Name +
                                "\n地址：" + Address +
                                "\n電話：" + Tel +
                                "\n市：" + City +
                                "\n鎮：" + Town);
                mList.add(item);
            }
//            String datetime = jsonData.getString("Coordinate");
//            time.setText("經緯度：" + datetime);
            SimpleAdapter adapter1 = new SimpleAdapter(this, mList, R.layout.list, new String[]{"t001"}, new int[]{R.id.t001});
            list001.setAdapter(adapter1);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
