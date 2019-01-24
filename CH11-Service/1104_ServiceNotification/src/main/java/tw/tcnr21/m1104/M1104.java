package tw.tcnr21.m1104;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class M1104 extends AppCompatActivity implements View.OnClickListener {

    private TextView show,counter;
    private Button start,stop;
    Context mContext;
    private myReceiver mr;
    NotificationManager NM;
    static final String ActionFlag="showtime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1104);
        setupViewComponent();
    }

    private void setupViewComponent() {
        start=(Button)this.findViewById(R.id.m1104_btnstart);
        stop=(Button)this.findViewById(R.id.m1104_btnstop);
        show=(TextView)this.findViewById(R.id.m1104_tvtimer);
        counter=(TextView)this.findViewById(R.id.m1104_tvcounter);
        mContext=this;

        // 取得NotificationManager
        NM=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        // 生成 BroadcastReceiver instance
        mr=new myReceiver();
        // 設定自定動作給 Receiver 用
        IntentFilter intentFilter=new IntentFilter(ActionFlag);
        registerReceiver(mr,intentFilter);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getBaseContext(),MyService.class);
        Bundle bundle=new Bundle();
        switch (v.getId())
        {
            case R.id.m1104_btnstart:
                // 將動作傳給預備回傳資料的 Service
                bundle.putString("action",ActionFlag);
                intent.putExtras(bundle);
                startService(intent);
                break;
            case R.id.m1104_btnstop:
                // 將動作傳給預備回傳資料的 Service
                bundle.putString("action",ActionFlag);
                intent.putExtras(bundle);
                stopService(intent);
                counter.setText(getString(R.string.m1104_tvcounter)+"0");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mr);// 在Activity 消滅時才unregister
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class myReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            // 判斷是否為自訂動作 showtime
            if (intent.getAction()==ActionFlag){
                // 將傳回值塞入View 及 變數中
                Bundle bundle=intent.getExtras();
                show.setText(getString(R.string.m1104_tvtimer)+bundle.getString("time"));
                counter.setText(getString(R.string.m1104_tvcounter)+bundle.getInt("counter")+"");
                // 取得預備要執行的動作
                String action =bundle.getString("action");

                if (action.equals("start")){
                    Intent backToMain=new Intent(mContext,M1104.class);
                    PendingIntent pendingIntent=PendingIntent.getActivity(mContext,0,backToMain,0);
                    //宣告通知欄物件
                    Notification NF = new Notification.Builder(mContext)
                            .setAutoCancel(true) // 設定Notification 是否點擊後就消除
                            .setContentTitle(getString(R.string.m1104_title)) // 抬頭
                            .setContentText(getString(R.string.m1104_Content)) // 顯示內容
                            .setContentIntent(pendingIntent) // 設定Notification點擊返回的Activity
                            .setSmallIcon(android.R.drawable.stat_notify_chat) //設定Notification的Icon
                            .setContentInfo(getString(R.string.m1104_ContentInfo)) // 設定Notification 右方訊息
                            .setTicker(getString(R.string.m1104_Ticker)) // 設定Notification 一開始顯是文字
                            .setWhen(System.currentTimeMillis())
                            .build();
                    //--------------------------------------------
                    // 生成Notification 並給定識別號碼 1
                    NM.notify(1,NF);
                }else{
                    // 取消Notificaiton
                    NM.cancel(1);
                    show.setText("");
                }

            }

        }
    }
}
