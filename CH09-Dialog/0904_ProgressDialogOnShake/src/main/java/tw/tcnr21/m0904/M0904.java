package tw.tcnr21.m0904;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class M0904 extends AppCompatActivity implements View.OnClickListener,SensorEventListener {

    private Button B001;
    private Handler mHandler=new Handler();
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private M0904 mShakeDetector;
    private Vibrator mVibrator;
    private OnShakeListener mListener;
    private static final float ShakeThresholdGravity = 2.0f;//重力閾值  1次要甩多大力
    private long firstclick  , secondclick = 0;;
    private int t;
    private int mShakeCount = 0;//紀錄搖動次數的計數器
    private long mShakeTimestamp;//開始搖動的系統時間
    private static final int ShakeSlopTimeMs = 500; //每甩1次要在0.5秒內完成才算1次 要快甩
    private static final int ShakeCountResetTimeMs = 5000;//3秒沒甩 -多久沒甩 計數器會重製
    private int n=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0904);
        setupViewCompoent();
    }

    private void setupViewCompoent() {
        B001=(Button)findViewById(R.id.m0904_b001);
        B001.setOnClickListener(this);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//開啟感測服務
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//將加速度計設定給mAccelerometer使用
        mShakeDetector = new M0904(); //震動

        mShakeDetector.setOnShakeListener(new OnShakeListener() {
            @Override
            public void onShake(int count) {
                mVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);//取得系統Vibrator服務控制
                mVibrator.vibrate(500);//震動一秒
                Toast.makeText(getApplicationContext(), "Shake:" + count, Toast.LENGTH_SHORT).show();
                mShakeCount=count;
                if (count  == 1) {
                    domywork();
                }

            }
        });
    }

    private void domywork() {//設定監聽震動的處理進度
        final ProgressDialog progDlg=new ProgressDialog(M0904.this);
        progDlg.setTitle(getString(R.string.m0904_title));
        progDlg.setMessage(getString(R.string.m0904_message));
        progDlg.setIcon(android.R.drawable.star_big_on);
        progDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progDlg.setMax(100);
        progDlg.setCancelable(true);//  false亂點其他視窗會跳掉 是固定的   ,  true 可以亂點其他視窗跳掉
        progDlg.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Calendar begin=Calendar.getInstance();
                int lastSec = 0;
//                if (ishake!=mShakeCount)
//                {
//                    ishake=mShakeCount;
                    do {

                        Calendar now = Calendar.getInstance();
                        final int iDiffSec = 60 * (now.get(Calendar.MINUTE) - begin.get(Calendar.MINUTE)) +
                                now.get(Calendar.SECOND) - begin.get(Calendar.SECOND);

                        if (lastSec == iDiffSec)  continue;
                        lastSec = iDiffSec;

                            if (iDiffSec * 10 > 100) {
                            mHandler.post(new Runnable() {
                                public void run() {
                                    progDlg.setProgress(100);
                                }
                            });
                            break;
                        }
                        mHandler.post(new Runnable() {
                            int a=0;
                            public void run() {
                                if(n!=mShakeCount){
                                    progDlg.setProgress(iDiffSec * 10);
                                    n=mShakeCount;
                                }else if(n==mShakeCount){
                                    progDlg.cancel();//跑完了對話盒縮起
                                    firstclick = 0;
                                    secondclick = 0;
                                    t = 1;
                                    mShakeCount = 0;

                                }

                            }
                        });
                        if (iDiffSec * 15< 100)
                            mHandler.post(new Runnable() {
                                public void run() {
                                    progDlg.setSecondaryProgress(iDiffSec * 15);
                                }
                            });
                        else
                            mHandler.post(new Runnable() {
                                public void run() {
                                    progDlg.setSecondaryProgress(100);
                                }
                            });
                    }while (true);

//                }else if(ishake==mShakeCount)
//                {
//                    progDlg.cancel();//跑完了對話盒縮起
//                    firstclick = 0;
//                    secondclick = 0;
//                    t = 1;
//                    mShakeCount = 0;
//                }
            }
        }).start();
    }



    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - firstclick < 2000) {
            if (SystemClock.elapsedRealtime() - secondclick < 2000) {
                domywork();
                return;
            }
            secondclick = SystemClock.elapsedRealtime();
            return;
        }
        firstclick = SystemClock.elapsedRealtime();
    }
    public void setOnShakeListener(OnShakeListener listener) {  //宣告設定搖動監聽器的method
        this.mListener = listener;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在resume週期的時候持續監聽
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);

    }


    @Override
    protected void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
//在pause週期的時候停止監聽
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (mListener != null) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float gX = x / SensorManager.GRAVITY_EARTH;//
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement.

//            float gForce = FloatMath.sqrt(gX * gX + gY * gY + gZ * gZ);
            double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);
            if (gForce > ShakeThresholdGravity) { //使出的力量大於設定的力量
                final long now = System.currentTimeMillis();
                // ignore shake events too close to each other (500ms)
                if (mShakeTimestamp + ShakeSlopTimeMs > now) {
                    return;//  return從來一次
                }
                // reset the shake count after 3 seconds of no shakes
                if (mShakeTimestamp + ShakeCountResetTimeMs < now) {
                    mShakeCount = 0; //超過歸零
                }

                mShakeTimestamp = now;
                mShakeCount++;

                mListener.onShake(mShakeCount);
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    //<!---------------自建立搖動監聽器----------------------->
    private interface OnShakeListener {
        //宣告一個發生搖動時候的監聽器
        public void onShake(int count);
    }
}
