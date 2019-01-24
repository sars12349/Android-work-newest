package tw.tcnr21.m1103;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {
    private final String TAG = "tcnr21=>";

    public class LocalBinder extends Binder {
        MyService getService() {
            return  MyService.this;
        }
    }

    private LocalBinder mLocBin = new LocalBinder();

    public void myMethod() {
        Log.d(TAG, "myMethod()");
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStart()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Log.d(TAG, "onBind()");

        // 一定要傳回一個Binder物件才會執行ServiceConnection物件的
        // onServiceConnected()方法
        return mLocBin;
    }

}

