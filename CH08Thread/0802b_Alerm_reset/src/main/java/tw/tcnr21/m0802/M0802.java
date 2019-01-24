package tw.tcnr21.m0802;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Calendar;

public class M0802 extends AppCompatActivity implements View.OnClickListener {

    private DatePicker mDatePik;
    private TextView mTxtResult;
    private Button mBtnOK;
    private TimePicker mTimePik;
    private long endTime;
    private Handler handler=new Handler();
    private long spentTime,hours,minius,seconds;
    private TextView time;
    private TextView text001;
    private MediaPlayer startmusic;
    private Button timeCancel;
    private int years02,month02,days02,hours02,min02;
    private DatePicker date01;
    private TimePicker time01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0802);
        setupViewComponent();
    }

    private void setupViewComponent() {
        time=(TextView)findViewById(R.id.m0802_timer);
        mDatePik = (DatePicker) findViewById(R.id.m0802_date01);
        mTxtResult = (TextView)findViewById(R.id.m0802_ans01);
        mBtnOK = (Button)findViewById(R.id.m0802_b001);
        mTimePik=(TimePicker)findViewById(R.id.m0802_time01);
        text001 = (TextView) findViewById(R.id.m0802_t001);
        startmusic = MediaPlayer.create(M0802.this, R.raw.s01);// 抓取歌曲
        timeCancel=(Button)findViewById(R.id.m0802_b002);
        mBtnOK.setOnClickListener(this);
        timeCancel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.m0802_b001:
                mBtnOK.setVisibility(View.GONE);
                timeCancel.setVisibility(View.VISIBLE);
                String s = getString(R.string.result);
                mTxtResult.setText(s + mDatePik.getYear() + getString(R.string.n_yy) +
                        (mDatePik.getMonth()+1) + getString(R.string.n_mm) +
                        mDatePik.getDayOfMonth() +getString(R.string.m_dd) +
                        mTimePik.getCurrentHour() + getString(R.string.d_hh) +
                        mTimePik.getCurrentMinute() + getString(R.string.d_mm)
                );
                Calendar cg=Calendar.getInstance();//設定日曆新物件
                cg.set(mDatePik.getYear(),mDatePik.getMonth(),mDatePik.getDayOfMonth(),mTimePik.getCurrentHour(),mTimePik.getCurrentMinute());
                endTime=cg.getTimeInMillis();
                //設定開始delay的時間
                handler.postDelayed(updateTimer,1000);
                break;
            case R.id.m0802_b002:
                mBtnOK.setVisibility(View.VISIBLE);
                timeCancel.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),getString(R.string.m0802_b002),Toast.LENGTH_SHORT).show();
                music_reset();
                time.setText(getString(R.string.m0802_timer));
                text001.setText(getString(R.string.m0802_play));
                setnowtime();
                mTxtResult.setText(getString(R.string.m0802_ans01));
                handler.removeCallbacks(updateTimer);
                break;
        }

    }

    private void setnowtime() {
        Calendar c=Calendar.getInstance();
        years02=c.get(Calendar.YEAR);
        month02=c.get(Calendar.MONTH);
        days02=c.get(Calendar.DAY_OF_MONTH);
        hours02=c.get(Calendar.HOUR_OF_DAY);
        min02=c.get(Calendar.MINUTE);
        mDatePik.updateDate(years02,month02,days02);
        mTimePik.setCurrentHour(hours02);
        mTimePik.setCurrentMinute(min02);
    }

    private void music_reset() {
        if (startmusic.isPlaying()){
            startmusic.stop();
            try{
                startmusic.prepare();
            }catch (IllegalStateException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Runnable updateTimer=new Runnable() {
        @Override
        public void run() {
            spentTime=endTime-System.currentTimeMillis();
            hours=(spentTime/1000)/60/60;
            minius=(spentTime/1000)/60%60;
            seconds=(spentTime/1000)%60;

            if (spentTime<0||hours>999){
                Toast.makeText(getApplicationContext(),getString(R.string.msg),Toast.LENGTH_LONG).show();
                time.setText(getString(R.string.m0802_timer));
                text001.setText("");
                handler.removeCallbacks(updateTimer);
                //time.setText(String.format("%02d",hours)+":"+String.format("%02d",minius)+":"+String.format("%02d",seconds));
            }else{
                text001.setText(getString(R.string.m0802_alerm));
                music_set();

                time.setText(String.format("%02d",hours)+":"+String.format("%02d",minius)+":"+String.format("%02d",seconds));
                handler.postDelayed(this,1000);
                if (hours==0&&minius==0&&seconds==0){
                    startmusic.start();
                    text001.setText(getString(R.string.m0802_play));
                    handler.removeCallbacks(updateTimer);
                }

            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateTimer);
        this.finish();
    }

    private void music_set() {
        if(startmusic.isPlaying()){
            startmusic.stop();
            try{
                startmusic.prepare();
            }catch (IllegalStateException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
