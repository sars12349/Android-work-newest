package tw.tcnr20.m1401;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class M1004 extends AppCompatActivity implements ViewSwitcher.ViewFactory, View.OnClickListener
{

    // ----宣告變數----
    private ImageSwitcher txtComPlay;
    private TextView txtSelect, txtResult;
    private ImageButton btnScissors, btnStone, btnNet;
    private String user_select;
    private String answer;
    private MediaPlayer startmusic; //宣告媒體物件 片頭音樂
    private MediaPlayer mediaWin; // 宣告媒體物件 贏
    private MediaPlayer mediaLose; // 宣告媒體物件 輸
    private MediaPlayer mediaDraw; // 宣告媒體物件 平
    private RelativeLayout r_layout;
    private MediaPlayer stopmusic;//結尾音樂

    private int miCountSet = 0,
            miCountPlayerWin = 0,
            miCountComWin = 0,
            miCountDraw = 0;
    private Button mBtnShowResult,mBtnOK,mBtnCancel;
    private static int NOTI_ID=100; //給訊息一個id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1004);
        setupViewComponent();
    }

    private void setupViewComponent() {
        // ---取得R.java 配置碼---
        txtComPlay = (ImageSwitcher) findViewById(R.id.m1004_c001); // 電腦選擇
        txtComPlay.setFactory(this);
        txtSelect = (TextView) findViewById(R.id.m1004_s001); // 選擇結果
        txtResult = (TextView) findViewById(R.id.m1004_f000); // 輸贏判斷
        btnScissors = (ImageButton) findViewById(R.id.m1004_b001); // 剪刀
        btnStone = (ImageButton) findViewById(R.id.m1004_b002); // 石頭
        btnNet = (ImageButton) findViewById(R.id.m1004_b003); // 布

        mBtnShowResult = (Button)findViewById(R.id.btnShowResult);
        // --開啟片頭音樂-----
        startmusic = MediaPlayer.create(getApplication(), R.raw.gem);
        startmusic.start();
       //設定開機動畫
        r_layout=(RelativeLayout)findViewById(R.id.m1004_r001);
        r_layout.setBackgroundResource(R.drawable.food001);
        r_layout.setAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_scale_rotate_out));
        r_layout.setAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_scale_rotate_in));
        r_layout.setBackgroundResource(R.drawable.food001);
        //--設定音樂連結--
        mediaWin = MediaPlayer.create(getApplication(), R.raw.vctory);
        mediaLose = MediaPlayer.create(getApplication(), R.raw.lose);
        mediaDraw = MediaPlayer.create(getApplication(), R.raw.haha);
        //--設定結束音樂--
        stopmusic=MediaPlayer.create(getApplication(),R.raw.alice);
        // ---啟動監聽事件----
        btnScissors.setOnClickListener(this);
        btnStone.setOnClickListener(this);
        btnNet.setOnClickListener(this);

        mBtnShowResult.setOnClickListener(btnShowResultLis);
        //-----------
        mBtnOK = (Button)findViewById(R.id.m1004_btnOK);
        mBtnCancel = (Button)findViewById(R.id.m1004_btnCance);
        mBtnOK.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

        u_loaddata(); // 開機自動載入

    }

    private void u_setAlpha()//imageButton 背景為銀色且為全透明
    {
        btnScissors.setBackgroundColor(ContextCompat.getColor(this,R.color.Silver));
        btnScissors.getBackground().setAlpha(0);
        btnStone.setBackgroundColor(ContextCompat.getColor(this,R.color.Silver));
        btnStone.getBackground().setAlpha(0);
        btnNet.setBackgroundColor(ContextCompat.getColor(this,R.color.Silver));
        btnNet.getBackground().setAlpha(0);
    }

    private void music(int i) {
        //--中斷播放中音樂--
        if (startmusic.isPlaying()) startmusic.stop();
        if (mediaWin.isPlaying()) mediaWin.pause();
        if (mediaDraw.isPlaying()) mediaDraw.pause();
        if (mediaLose.isPlaying()) mediaLose.pause();
        //--
        switch (i) {
            case 1: //贏
                mediaWin.start();
//             Toast.makeText(getApplicationContext(), R.string.m1004_f001, Toast.LENGTH_LONG).show();
                break;
            case 2: //平
                mediaDraw.start();
//               Toast.makeText(getApplicationContext(), R.string.m1004_f002, Toast.LENGTH_LONG).show();
                break;
            case 3: //輸
                mediaLose.start();
//               Toast.makeText(getApplicationContext(), R.string.m1004_f003, Toast.LENGTH_LONG).show();
                break;
            case 4:
                stopmusic.start();
                break;
        }
    }


    @Override
    public View makeView()
    {
        ImageView v = new ImageView(this);
      //  v.setBackgroundColor(0xFF000000);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        return v;
    }
    private Button.OnClickListener btnShowResultLis = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            intentsel(1);
        }
    };


    @Override
    public void finish()
    {
        super.finish();
        //關機動畫
        overridePendingTransition(R.anim.anim_alpha_out,R.anim.anim_alpha_out);
    }

    @Override
    protected void onDestroy()
    {
        u_savedata();
        super.onDestroy();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        stopmusic.start();
      //  music(4);  //結束時關閉音樂
    }

    @Override
    public void onClick(View v)
    {
        int iComPlay = (int) (Math.random() * 3 + 1);
        miCountSet++;
        // 1 - scissors, 2 - stone, 3 - net.
        switch (iComPlay) {
            case 1: //電腦:剪刀scissors
                user_select = getString(R.string.m1004_s002) + getString(R.string.m1004_b001) + " ";
                txtComPlay.setImageResource(R.drawable.scissors); // 轉換ImageView剪刀
                break;
            case 2: //電腦:石頭stone
                user_select = getString(R.string.m1004_s002) + getString(R.string.m1004_b002) + " ";
                txtComPlay.setImageResource(R.drawable.stone); // 轉換ImageView石頭
                break;
            case 3: // 電腦:布net
                user_select = getString(R.string.m1004_s002) + getString(R.string.m1004_b003) + " ";
                txtComPlay.setImageResource(R.drawable.net); // 轉換ImageView布
                break;
        }
        switch (v.getId()) {

            case R.id.m1004_b001:
                // 選擇 剪刀scissor
                //  (刪OK )     user_select = getString(R.string.m1004_s001) + " " + getString(R.string.m1004_b001);
                user_select += getString(R.string.m1004_s001) + getString(R.string.m1004_b001);
                u_setAlpha();
                btnScissors.getBackground().setAlpha(150);
                switch (iComPlay) {
                    case 1:
                        answer = getString(R.string.m1004_f000) + getString(R.string.m1004_f003); // 平
                        txtResult.setTextColor(getResources().getColor(R.color.Yellow)); // 平用黃顯示
                        miCountDraw++;
                        music(2);
                        showNotification(getString(R.string.m1004_f003)+Integer.toString(miCountDraw)+getString(R.string.m1004_table));
                        break;
                    case 2:
                        answer = getString(R.string.m1004_f000) + getString(R.string.m1004_f002); // 輸
                        txtResult.setTextColor(getResources().getColor(R.color.Red)); // 輸用紅顯示
                        miCountComWin++;
                        music(3);
                        showNotification(getString(R.string.m1004_f002)+Integer.toString(miCountComWin)+getString(R.string.m1004_table));
                        break;
                    case 3:
                        answer = getString(R.string.m1004_f000) + getString(R.string.m1004_f001); // 贏
                        txtResult.setTextColor(getResources().getColor(R.color.Lime)); // 贏用綠顯示
                        miCountPlayerWin++;
                        music(1);
                        showNotification(getString(R.string.m1004_f001)+Integer.toString(miCountPlayerWin)+getString(R.string.m1004_table));
                        break;
                }

                break;
            //----------------------------------------------
            case R.id.m1004_b002:
                // 選擇 石頭stone
                user_select += getString(R.string.m1004_s001)  + getString(R.string.m1004_b002);
                u_setAlpha();
                btnStone.getBackground().setAlpha(150);
                switch (iComPlay) {
                    case 1:
                        answer = getString(R.string.m1004_f000) + getString(R.string.m1004_f001); // 贏
                        txtResult.setTextColor(getResources().getColor(R.color.Lime)); // 贏用綠顯示
                        miCountPlayerWin++;
                        music(1);
                        showNotification(getString(R.string.m1004_f001)+Integer.toString(miCountPlayerWin)+getString(R.string.m1004_table));
                        break;
                    case 2:
                        answer = getString(R.string.m1004_f000) + getString(R.string.m1004_f003); // 平
                        txtResult.setTextColor(getResources().getColor(R.color.Yellow)); // 平用黃顯示
                        miCountDraw++;
                        music(2);
                        showNotification(getString(R.string.m1004_f003)+Integer.toString(miCountDraw)+getString(R.string.m1004_table));
                        break;
                    case 3:
                        answer = getString(R.string.m1004_f000) + getString(R.string.m1004_f002); // 輸
                        txtResult.setTextColor(getResources().getColor(R.color.Red)); // 輸用紅顯示
                        miCountComWin++;
                        music(3);
                        showNotification(getString(R.string.m1004_f002)+Integer.toString(miCountComWin)+getString(R.string.m1004_table));
                        break;
                }
                break;
            //---------------------------------------------
            case R.id.m1004_b003:
                // 選擇 布net
                user_select += getString(R.string.m1004_s001) + getString(R.string.m1004_b003);
                u_setAlpha();
                btnNet.getBackground().setAlpha(150);
                switch (iComPlay) {
                    case 1:
                        answer = getString(R.string.m1004_f000) + getString(R.string.m1004_f002); // 輸
                        txtResult.setTextColor(getResources().getColor(R.color.Red)); // 輸用紅顯示
                        miCountComWin++;
                        music(3);
                        showNotification(getString(R.string.m1004_f002)+Integer.toString(miCountComWin)+getString(R.string.m1004_table));
                        break;
                    case 2:
                        answer = getString(R.string.m1004_f000) + getString(R.string.m1004_f001); // 贏
                        txtResult.setTextColor(getResources().getColor(R.color.Lime)); // 贏用綠顯示
                        miCountPlayerWin++;
                        music(1);
                        showNotification(getString(R.string.m1004_f001)+Integer.toString(miCountPlayerWin)+getString(R.string.m1004_table));
                        break;
                    case 3:
                        answer = getString(R.string.m1004_f000) + getString(R.string.m1004_f003); // 平
                        txtResult.setTextColor(getResources().getColor(R.color.Yellow)); // 平用黃顯示
                        miCountDraw++;
                        music(2);
                        showNotification(getString(R.string.m1004_f003)+Integer.toString(miCountDraw)+getString(R.string.m1004_table));
                        break;
                }
                break;
            case R.id.m1004_btnCance:  //選擇取消
                intentsel(3);
                break;

            case R.id.m1004_btnOK: //選擇完成遊戲
               intentsel(2);
                break;
        }
        //--------電腦出拳增加動畫---------------
        txtComPlay.clearAnimation();
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_trans_in);
        anim.setInterpolator(new BounceInterpolator());
        txtComPlay.setAnimation(anim);
        //----------------------------------------
        txtSelect.setText(user_select);
        txtResult.setText(answer);
    }

    private void showNotification(String s) {
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100); // 100=max
        toneG.startTone(ToneGenerator.TONE_CDMA_DIAL_TONE_LITE, 200);
        toneG.stopTone();

        Intent it = new Intent(getApplicationContext(), GameResult.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putInt("KEY_COUNT_SET", miCountSet);
        bundle.putInt("KEY_COUNT_PLAYER_WIN", miCountPlayerWin);
        bundle.putInt("KEY_COUNT_COM_WIN", miCountComWin);
        bundle.putInt("KEY_COUNT_DRAW", miCountDraw);
        it.putExtras(bundle);
        PendingIntent penIt = PendingIntent.getActivity(getApplicationContext(), 0, it,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Notification noti = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.btn_star_big_on)
                .setTicker(s)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(s)
                .setContentIntent(penIt).build();

        NotificationManager notiMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notiMgr.notify(NOTI_ID, noti);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.m1004,menu);
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.item1:
                intentsel(1);
                break;
            case R.id.item2:
                intentsel(2);
                break;
            case R.id.item3:
                intentsel(3);
                break;
            case R.id.btnShowResult:// 統計
                mBtnShowResult.performClick();
                break;

            case R.id.btnSaveResult: // 儲存
                u_savedata();

                break;

            case R.id.btnLoadResult: // 載入
                u_loaddata();
                break;

            case R.id.btnClearResult: // 清除
                u_cleardata();
                break;

            case R.id.btnaboutme:
                new AlertDialog.Builder(M1004.this)
                        .setTitle("Preferences範例程式")
                        .setMessage("TCNR雲端製作")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton("確定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                    }
                                })
                        .show();
                break;

            case R.id.action_settings:
                u_savedata();
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void u_cleardata() {
        // 清除SharedPreferences資料
        SharedPreferences gameResultData =
                getSharedPreferences("GAME_RESULT", 0);

        gameResultData
                .edit()
                .clear()
                .commit();

        Toast.makeText(M1004.this, "清除完成", Toast.LENGTH_LONG).show();

    }

    private void u_loaddata() {
        // 載入SharedPreferences資料
        SharedPreferences gameResultData =
                getSharedPreferences("GAME_RESULT", 0);

        miCountSet = gameResultData.getInt("COUNT_SET", 0);
        miCountPlayerWin = gameResultData.getInt("COUNT_PLAYER_WIN", 0);
        miCountComWin = gameResultData.getInt("COUNT_COM_WIN", 0);
        miCountDraw = gameResultData.getInt("COUNT_DRAW", 0);

        Toast.makeText(M1004.this, "載入完成", Toast.LENGTH_LONG).show();

    }

    private void u_savedata() {
        // 儲存SharedPreferences資料
        SharedPreferences gameResultData =
                getSharedPreferences("GAME_RESULT", 0);

        gameResultData
                .edit()
                .putInt("COUNT_SET", miCountSet)
                .putInt("COUNT_PLAYER_WIN", miCountPlayerWin)
                .putInt("COUNT_COM_WIN", miCountComWin)
                .putInt("COUNT_DRAW", miCountDraw)
                .commit();

        Toast.makeText(M1004.this, "儲存完成", Toast.LENGTH_LONG).show();

    }

    private void intentsel(int i)
    {
        switch (i){
            case 1:
                Intent it1 = new Intent();
                it1.setClass(M1004.this, GameResult.class);

                Bundle bundle1 = new Bundle();
                bundle1.putInt("KEY_COUNT_SET", miCountSet);
                bundle1.putInt("KEY_COUNT_PLAYER_WIN", miCountPlayerWin);
                bundle1.putInt("KEY_COUNT_COM_WIN", miCountComWin);
                bundle1.putInt("KEY_COUNT_DRAW", miCountDraw);
                it1.putExtras(bundle1);

                startActivity(it1);
                break;
            case 2:
                Intent it = new Intent();

                Bundle bundle = new Bundle();
                bundle.putInt("KEY_COUNT_SET", miCountSet -1);
                bundle.putInt("KEY_COUNT_PLAYER_WIN", miCountPlayerWin);
                bundle.putInt("KEY_COUNT_COM_WIN", miCountComWin);
                bundle.putInt("KEY_COUNT_DRAW", miCountDraw);
                it.putExtras(bundle);

                setResult(RESULT_OK, it);
                finish();
                break;
            case 3:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
