package tw.tcnr21.m1908;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class DBConnector {
    // 宣告類別變數以方便存取，並判斷是否連線成功
    public static int httpstate = 0;
    static String result = null;
    static String TAG = "tcnr21=>";
    // ---------------------------
    static InputStream is = null;
    static String line = null;
    static int code;
    static String mysql_code = null;

    //=========================================================================================
//    static String connect_ip ="http://192.168.0.88/android_mysql_connect/"; //localhost
    static String connect_ip ="http://www.oldpa.tw/android_mysql_connect/"; //智邦
//    static String connect_ip = "http://oldpa88.esy.es/android_mysql_connect/";//Hostinger
//=========================================================================================
//-------000webhost-------
//    static String connect_ip ="http://www.oldpa.tw/android_mysql_connect/";//老爹
//    static String connect_ip = "https://tcnr1091601.000webhostapp.com/android_mysql_connect/";//000webhost
//    static String connect_ip = "https://tcnr1621.000webhostapp.com/android_mysql_connect/";
    // ----------01---------------
//    static String connect_ip = "http://tcnr27.000webhostapp.com/android_connect_db/";//000webhost18tcnr24 林家賢
    // ------select MySQL--------------------------------------------------
    public static String executeQuery(String query_string) {
        String result = "";
//--創建URL對象，並將上述服務器地址字符串轉化成URL地址
        HttpURLConnection urlConnection = null;
        InputStream is = null;
//--------------------
//--------------------
        try {
            URL url = new URL(connect_ip + "android_connect_db_all.php");//php的位置
//--創建HttpURLConnection對象，通過URL對象的openConnection打開網址，注意此時只是打開網址
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");// 设置请求方法为post
            urlConnection.setReadTimeout(5000);// 设置读取超时为5秒
            urlConnection.setConnectTimeout(10000);// 设置连接网络超时为10秒
            urlConnection.setDoOutput(true);// 设置此方法,允许向服务器输出内容
            urlConnection.connect();//接通資料庫
// selefunc_string -> 給php 使用的參數	query:選擇 insert:新增 update:更新 delete:刪除
            // post请求的参数
            String data = "selefunc_string=query&query_string="+query_string;
            // 获得一个输出流,向服务器写数据,默认情况下,系统不允许向服务器输出内容
            OutputStream out = urlConnection.getOutputStream();// 获得一个输出流,向服务器写数据
            out.write(data.getBytes());
            out.flush();
            out.close();

//================================================================
            is = urlConnection.getInputStream();//從database 開啟 stream
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = bufReader.readLine()) != null) {
                builder.append(line + "\n");
            }
            is.close();
            result = builder.toString();
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

        return result;
    }

    // ---新增資料--------------------------------------------------------------
    public static String executeInsert(String string, ArrayList<NameValuePair> nameValuePairs) {
        is = null;
        result = null;
        line = null;
        try {
            Thread.sleep(500); // 延遲Thread 睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ---- 連結MySQL-------------------
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(connect_ip + "android_connect_db_all.php");
            //-----------------------------------
            // selefunc_string -> 給php 使用的參數	query:選擇 insert:新增 update:更新 delete:刪除
            nameValuePairs.add(new BasicNameValuePair("selefunc_string", "insert"));
            //------------------------------------
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log.d(TAG, "insert:新增錯誤1" + e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.d(TAG, "insert:新增錯誤2:" + e.toString());
        }
        try {
            JSONObject json_data = new JSONObject(result);
            code = (json_data.getInt("code"));
            if (code != 1) Log.d(TAG, "insert:新增錯誤3:" + "..重試..");
        } catch (Exception e) {
            Log.d(TAG, "insert:新增錯誤4:" + e.toString());
        }
        return result;
    }
    //---更新資料--------------------------------------------------------------
    public static String executeUpdate(String string, ArrayList<NameValuePair> nameValuePairs) {
        is = null;
        result = null;
        line = null;
        String update_code = null;
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(connect_ip + "android_connect_db_all.php");
        try {
            //----------------------------
            // selefunc_string -> 給php 使用的參數	query:選擇 insert:新增 update:更新 delete:刪除
            nameValuePairs.add(new BasicNameValuePair("selefunc_string", "update"));
            //-----------------------------
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            HttpResponse response;
            response = httpClient.execute(httpPost); //
            HttpEntity entity = response.getEntity();
            try {
                is = entity.getContent(); // InputStream is = null;
            } catch (IllegalStateException e1) {
                e1.printStackTrace();
            } catch (ClientProtocolException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf8"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.d(TAG, "update:更新錯誤2:" + e.toString());
        }
        try {
            JSONObject json_data = new JSONObject(result);
            code = (json_data.getInt("code"));
            if (code == 1) {
                update_code = "更新成功";
            } else {
                update_code = "更新失敗";
            }
        } catch (Exception e) {
            Log.d(TAG, "update:更新錯誤3:" + e.toString());
        }
        return update_code;
    }
    //---刪除資料--------------------------------------------------------------
    public static String executeDelet(String string, ArrayList<NameValuePair> nameValuePairs) {
        is = null;
        result = null;
        line = null;
        mysql_code = null;
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // --------------------------------------------------------------------------------------
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(connect_ip + "android_connect_db_all.php");
        try {
            // selefunc_string -> 給php 使用的參數	query:選擇 insert:新增 update:更新 delete:刪除
            nameValuePairs.add(new BasicNameValuePair("selefunc_string", "delete"));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            HttpResponse response;
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            try {
                is = entity.getContent();
            } catch (IllegalStateException e1) {
                e1.printStackTrace();
            } catch (ClientProtocolException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf8"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.d(TAG, "delete:刪除錯誤2:" + e.toString());
        }
        try {
            JSONObject json_data = new JSONObject(result);
            code = (json_data.getInt("code"));
            if (code == 1) {
                mysql_code = "刪除成功";
            } else {
                mysql_code = "刪除失敗";
            }
        } catch (Exception e) {
            Log.d(TAG, "delete:刪除錯誤3:" + e.toString());
        }
        return mysql_code;
    }
    // ----------------------------------------------------------

}
