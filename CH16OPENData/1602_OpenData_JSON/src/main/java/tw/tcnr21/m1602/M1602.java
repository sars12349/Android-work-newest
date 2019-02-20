package tw.tcnr21.m1602;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableRow;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class M1602 extends AppCompatActivity {

    private ListView list001;
    private TableRow tab01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1602);
        setupViewComponent();
    }

    private void setupViewComponent() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int newscrollheight = displayMetrics.heightPixels * 75 / 100; // 設定ScrollView使用尺寸的4/5
        //--設定listview 抬頭--
        //---
        list001 = (ListView) findViewById(R.id.listView1);
        list001.getLayoutParams().height = newscrollheight;
        list001.setLayoutParams(list001.getLayoutParams()); // 重定ScrollView大小
        tab01 = (TableRow) findViewById(R.id.tab01);

        list001 = (ListView) findViewById(R.id.listView1);
        list001.getLayoutParams().height = newscrollheight;
        list001.setLayoutParams(list001.getLayoutParams()); // 重定ScrollView大小

        try {
            String Task_opendata = new TransTask().execute("http://opendata.epa.gov.tw/ws/Data/AQI/?$format=json").get();
            //===== 設定 opendata 網址===============
//                        [{"AQI":"19",
//                        "CO":"0.17",
//                        "CO_8hr":"0.7",
//                        "County":"基隆市",
//                        "NO":"2.3",
//                        "NO2":"3.4",
//                        "NOx":"5.6",
//                        "O3":"43",
//                        "O3_8hr":"20",
//                        "PM10":"13",
//                        "PM10_AVG":"13",
//                        "PM2.5":"5",
//                        "PM2.5_AVG":"6",
//                        "Pollutant":"",
//                        "PublishTime":"2018-01-15 13:00",
//                        "SiteName":"基隆",
//                        "SO2":"1.7",
//                        "Status":"良好",
//                        "WindDirec":"78",
//                        "WindSpeed":"3.5"},.....
            List<Map<String, Object>> mList;
            mList = new ArrayList<Map<String, Object>>();
            // 解析 json
            JSONArray jsonArray = new JSONArray(Task_opendata);
            //------JSON 排序-
            jsonArray=sortJsonArray( jsonArray);
            //-----開始逐筆轉換-----
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonData = jsonArray.getJSONObject(i);
                Map<String, Object> item = new HashMap<String, Object>();
                String County = jsonData.getString("County");
                String SiteName = jsonData.getString("SiteName");
                String Pm25 = jsonData.getString("PM2.5_AVG");
                String Status = jsonData.getString("Status");
                String PublishTime = jsonData.getString("PublishTime");

                item.put("County", County);
                item.put("SiteName", SiteName);
                item.put("Pm25", Pm25);
                item.put("Status", Status);
                item.put("PublishTime", PublishTime);
                mList.add(item);
            }
            //=========設定listview========
            SimpleAdapter adapter = new SimpleAdapter(
                    this,
                    mList,
                    R.layout.list,
                    new String[]{"County","SiteName", "Pm25","Status","PublishTime"},
                    new int[]{R.id.t001, R.id.t002,R.id.t003,R.id.t004,R.id.t005}
            );
            list001.setAdapter(adapter);

            //設定list內的PM2.5的文字顏色和背景顏色
            adapter.setViewBinder(new SimpleAdapter.ViewBinder() {

                @Override
                public boolean setViewValue(View view, Object data, String textRepresentation) {
                    if (view.getId() == R.id.t003) {
                        //PM2.5
                        TextView PM25 = (TextView) view;
                        PM25.setText(data.toString());
                        if (checkIfDataIsInt(data.toString())) {
                            int PM25num = Integer.parseInt(data.toString());
                            //-判斷細懸浮微粒(PM2.5)指標對照
                            if (PM25num < 11) {
                                PM25.setTextColor(getResources().getColor(R.color.black));
                                PM25.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                            } else if(PM25num >= 11 &&PM25num < 23) {
                                PM25.setTextColor(getResources().getColor(R.color.black));
                                PM25.setBackgroundColor(getResources().getColor(R.color.lime));
                            } else if (PM25num >= 23 &&PM25num < 35) {
                                PM25.setTextColor(getResources().getColor(R.color.black));
                                PM25.setBackgroundColor(getResources().getColor(R.color.olivedrab));
                            } else if (PM25num >= 36 && PM25num <= 41) {
                                PM25.setTextColor(getResources().getColor(R.color.black));
                                PM25.setBackgroundColor(getResources().getColor(R.color.yellow));
                            } else if (PM25num >= 42 && PM25num <= 47) {
                                PM25.setTextColor(getResources().getColor(R.color.blue));
                                PM25.setBackgroundColor(getResources().getColor(R.color.yellow));
                            } else if (PM25num >= 48 && PM25num <= 53) {
                                PM25.setTextColor(getResources().getColor(R.color.black));
                                PM25.setBackgroundColor(getResources().getColor(R.color.maroon));
                            } else if (PM25num >= 54 && PM25num <= 70) {
                                PM25.setTextColor(getResources().getColor(R.color.black));
                                PM25.setBackgroundColor(getResources().getColor(R.color.red));
                            } else if (PM25num > 70) {
                                PM25.setTextColor(getResources().getColor(R.color.red));
                                PM25.setBackgroundColor(getResources().getColor(R.color.fuchsia));
                            } else {
                                PM25.setTextColor(getResources().getColor(R.color.black));
                                PM25.setBackgroundColor(getResources().getColor(R.color.white));
                            }
                        }
////--Title------------------------
//                        if (view.getId() == R.id.t001) {
//                            TextView County_t = (TextView) view;
//                            County_t.setText(data.toString());
//                            if (County_t.getText().toString().equals("縣市")) {
//                                County_t.setBackgroundColor(getResources().getColor(R.color.Silver));
//                            }
//                        }
//                        if (view.getId() == R.id.t002) {
//                            TextView SiteName_t = (TextView) view;
//                            SiteName_t.setText(data.toString());
//                            if (SiteName_t.getText().toString().equals("觀測點")) {
//                                SiteName_t.setBackgroundColor(getResources().getColor(R.color.Silver));
//                            }
//                        }
//                        if (view.getId() == R.id.t003) {
//                            TextView Pm25_t = (TextView) view;
//                            Pm25_t.setText(data.toString());
//                            if (data.toString().equals("PM25")) {
//                                Pm25_t.setBackgroundColor(getResources().getColor(R.color.Silver));
//                            }
//                        }
//                        if (view.getId() == R.id.t004) {
//                            TextView Status_t = (TextView) view;
//                            Status_t.setText(data.toString());
//                            if (data.toString().equals("狀態")) {
//                                Status_t.setBackgroundColor(getResources().getColor(R.color.Silver));
//                            }
//                        }
//                        if (view.getId() == R.id.t005) {
//                            TextView PublishTime_t = (TextView) view;
//                            PublishTime_t.setText(data.toString());
//                            if (data.toString().equals("更新時間")) {
//                                PublishTime_t.setBackgroundColor(getResources().getColor(R.color.Silver));
//                            }
//                        }

////--------------------------
                    }
                    return false;
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private boolean checkIfDataIsInt(String data) {
        if (data.equals("") || data.isEmpty()) {
            return false;
        } else {
            try {
                int i = Integer.parseInt(data);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public static JSONArray sortJsonArray(JSONArray array) {
        ArrayList<JSONObject> jsons = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                jsons.add(array.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(jsons, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject t1, JSONObject t2) {
                String lid = "";
                String rid = "";
                try {
                    lid = t1.getString("County");
                    rid = t2.getString("County");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return lid.compareTo(rid);
            }
        });
        return new JSONArray(jsons);
    }

    //====================================================
    private class TransTask extends AsyncTask<String, Void, String> {
        String ans;

        @Override
        protected String doInBackground(String... params) {
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(params[0]);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(url.openStream()));
                String line = in.readLine();
                while (line != null) {
                    Log.d("HTTP", line);
                    sb.append(line);
                    line = in.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ans = sb.toString();
            //------------
            return ans;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("s", "s:" + s);
            parseJson(s);
        }

        private void parseJson(String s) {

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
