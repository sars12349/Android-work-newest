package tw.tcnr21.m1104;

import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

public class MyService extends Service {
    Handler handler = new Handler();
    String action = "";
    int counter;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    //--------------------------------------------------------
    @Override
    public void onDestroy() {
        // 移除Runnable
        handler.removeCallbacks(timer);

        // 準備傳回資料到Receiver，準備關閉Notification
        Intent intent = new Intent(action);
        Bundle bundle = new Bundle();
        bundle.putString("action", "stop");
        intent.putExtras(bundle);
        sendBroadcast(intent);

        super.onDestroy();
    }
    //----------------------------------------------------------
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 取得動作
        action = intent.getExtras().getString("action");
        // 每隔一秒執行 timer 的動作
        handler.postDelayed(timer, 1000);
        return START_NOT_STICKY;
    }
    //----------------------------------------------------------
    Runnable timer = new Runnable() {
        @Override
        public void run() {
            counter++;
            sendToReceiver(new Date().toString(), counter);
            // 每隔一秒重覆執行
            handler.postDelayed(this, 1000);
        }
    };
    //--------------------------------------------------------

    private void sendToReceiver(String time, int number) {
        // 預備將資料傳回給Receiver，由其來做UI的改變
        Intent intent = new Intent(action);
        Bundle bundle = new Bundle();
        bundle.putString("time", time);
        bundle.putInt("counter", number);
        bundle.putString("action", "start");
        intent.putExtras(bundle);
        sendBroadcast(intent);
    }
}

