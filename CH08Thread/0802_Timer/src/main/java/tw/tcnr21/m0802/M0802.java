package tw.tcnr21.m0802;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class M0802 extends AppCompatActivity {

    private TextView time;
    private long startTime;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0802);
        setupViewComponent();
    }

    private void setupViewComponent() {
        time=(TextView)findViewById(R.id.m0802_timer);
        //取得現在時間(毫秒)
        startTime=System.currentTimeMillis();
        //設定delay時間
        handler.postDelayed(updateTimer,0);
    }

    private Runnable updateTimer=new Runnable() {
        @Override
        public void run() {
            long spentTime=System.currentTimeMillis()-startTime;

            long minus=(spentTime/1000)/60;

            long seconds=(spentTime/1000)%60;

            time.setText(String.format("%02d",minus)+":"+String.format("%02d",seconds));
            handler.postDelayed(this,5000);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateTimer);
        this.finish();
    }
}
