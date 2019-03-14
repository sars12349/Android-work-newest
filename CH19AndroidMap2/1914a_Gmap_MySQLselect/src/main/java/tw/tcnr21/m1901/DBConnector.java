package tw.tcnr21.m1901;

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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class DBConnector
{
    //宣告類別變數以方便存取，並判斷是否連線成功
    public static  int httpstate = 0;
    static String result = null;
    static String TAG = "tcnr=>";
    //---------------------------
    static InputStream is = null;
    static String line = null;
    static int code;
    static String mysql_code = null;
    static String connect_ip = "https://tcnr1621.000webhostapp.com/android_mysql_connect/";//Hostinger
//    static String connect_ip = "https://tcnr1609.000webhostapp.com/android_mysql_connect/";//Hostinger
    //static String connect_ip = "http://www.oldpa.tw/android_mysql_connect/";//Hostinger
    //------select MySQL--------------------------------------------------

    public static String executeQuery(String query_string) {

        try {
            HttpClient httpClient = new DefaultHttpClient();
//-----------localhost--------
//            HttpPost httpPost = new HttpPost("http://192.168.60.xx/android/android_connect_db.php");
//-------Hostinger-------
            //HttpPost httpPost = new HttpPost("http://xxxxxxx.esy.es/android/android_connect_db.php");
            //----------000webhost-----------------
            HttpPost httpPost = new HttpPost(connect_ip + "android_connect_db_all.php");

//-------000webhost   組長----------
//            HttpPost httpPost = new HttpPost("https://tcnr1091601.000webhostapp.com/android_mysql_connect/android_connect_db.php");
            //HttpPost httpPost = new HttpPost("https://tcnr1605.000webhostapp.com/android_mysql_connect/android_connect_db.php");
            //HttpPost httpPost = new HttpPost("https://tcnr1608.000webhostapp.com/android_mysql_connect/android_connect_db.php");
//            HttpPost httpPost = new HttpPost("https://tcnr1609.000webhostapp.com/android_mysql_connect/android_connect_db.php");
//-------000webhost   班代----------
            //HttpPost httpPost = new HttpPost("https://tcnr1624.000webhostapp.com/android_mysql_connect/android_connect_db.php");



            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            // selefunc_string -> 給php 使用的參數	query:選擇 insert:新增 update:更新 delete:刪除
            params.add(new BasicNameValuePair("selefunc_string", "query"));

            params.add(new BasicNameValuePair("query_string", query_string));
            // query_string -> 給php 使用的參數
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            // -----------------------------------------------------------------
            // 使用httpResponse的方法取得http 狀態碼設定給httpstate變數
            httpstate = httpResponse.getStatusLine().getStatusCode();
            // -----------------------------------------------------------------
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream inputStream = httpEntity.getContent();
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = bufReader.readLine()) != null) {
                builder.append(line + "\n");
            }
            inputStream.close();
            result = builder.toString();
        } catch (Exception e) {
            Log.d(TAG, "Exception e" + e.toString());
        }
        return result;
    }

    public static String executeInsert(String string, ArrayList<NameValuePair> nameValuePairs) {
        is = null;
        result = null;
        line = null;
        try {
            Thread.sleep(500); //  延遲Thread 睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //----	連結MySQL-------------------
        try {
            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httppost = new HttpPost(connect_ip+"android_insert_db.php");
            HttpPost httpPost = new HttpPost(connect_ip + "android_connect_db_all.php");
//            HttpPost httpPost = new HttpPost("https://tcnr1609.000webhostapp.com/android_mysql_connect/android_insert_db.php");
// selefunc_string -> 給php 使用的參數	query:選擇 insert:新增 update:更新 delete:刪除
            nameValuePairs.add(new BasicNameValuePair("selefunc_string", "insert"));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,
                    HTTP.UTF_8));
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.d(TAG, "pass 1:"+"connection success ");
        } catch (Exception e) {
            Log.d(TAG, "Fail 1"+e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.d(TAG, "pass 2:"+"connection success ");
        } catch (Exception e) {
            Log.d(TAG, "Fail 2:"+e.toString());
        }
        try {
            JSONObject json_data = new JSONObject(result);
            code = (json_data.getInt("code"));

            if (code == 1) {
                Log.d(TAG, "pass 3:"+"Inserted Successfully");
            } else {
                Log.d(TAG, "pass 3:"+"Sorry, Try Again");
            }
        } catch (Exception e) {
            Log.d(TAG, "Fail 3:"+e.toString());
        }
        return result;

    }

    public static String executeDelet(String string, ArrayList<NameValuePair> nameValuePairs)
    {
        is = null;
        result = null;
        line = null;
        mysql_code=null;
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
//-------------------------------------------------------------------------------------------------------
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(connect_ip + "android_connect_db_all.php");

        try {
            // selefunc_string -> 給php 使用的參數	query:選擇 insert:新增 update:更新 delete:刪除
            nameValuePairs.add(new BasicNameValuePair("selefunc_string", "delete"));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,
                    HTTP.UTF_8));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            HttpResponse response;
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            try {
                is = entity.getContent();
            } catch (IllegalStateException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (ClientProtocolException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Log.d(TAG,"pass 1:"+ "connection success");

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, "utf8"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.d(TAG,"pass 2:"+ "connection success");
        } catch (Exception e) {
            Log.d(TAG,"Fail 2:"+ e.toString());
        }
        try {
            JSONObject json_data = new JSONObject(result);
            code = (json_data.getInt("code"));
            if (code == 1) {
                mysql_code= "delete Successfully";
            } else {
                mysql_code=  "Sorry,Try Again";
            }

        } catch (Exception e) {
            Log.d(TAG,"Fail 3:"+ e.toString());
        }

        return mysql_code;
    }

    public static String executeUpdate(String s, ArrayList<NameValuePair> nameValuePairs)
    {
        is = null;
        result = null;
        line = null;
        String update_code=null;
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
//
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(connect_ip+"android_connect_db_all.php");

        try {
            // selefunc_string -> 給php 使用的參數	query:選擇 insert:新增 update:更新 delete:刪除
            nameValuePairs.add(new BasicNameValuePair("selefunc_string", "update"));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,
                    HTTP.UTF_8));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            HttpResponse response;
            response = httpClient.execute(httpPost); //
            HttpEntity entity = response.getEntity();
            try {
                is = entity.getContent(); //	InputStream is = null;

            } catch (IllegalStateException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (ClientProtocolException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Log.d(TAG,"pass 1:"+ "connection success");
//------------------------------------------------------------------------------
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, "utf8"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.d(TAG,"pass 2:"+ "connection success");
        } catch (Exception e) {
            Log.d(TAG,"Fail 2:"+ e.toString());
        }
//----------------------------------------
        try {
            JSONObject json_data = new JSONObject(result);
            code = (json_data.getInt("code"));
            if (code == 1) {
                update_code= "updata Successfully";
            } else {
                update_code=  "Sorry,Try Again";
            }
        } catch (Exception e) {
            Log.d(TAG,"Fail 3:"+ e.toString());
        }
        return update_code;

    }
    //----------------------------------------------------------
}

