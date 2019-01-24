package tw.tcnr21.m1003;

import android.content.Intent;
import android.media.MediaPlayer;
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
import android.widget.ViewSwitcher;

public class M1003 extends AppCompatActivity implements
        ViewSwitcher.ViewFactory, View.OnClickListener{

    // ----宣告變數----
    private TextView txtSelect, txtResult;
    private ImageButton btnScissors, btnStone, btnNet;
    private String user_select;
    private String answer;
    private MediaPlayer startmusic; //宣告媒體物件 片頭音樂
    private MediaPlayer mediaWin; // 宣告媒體物件 贏
    private MediaPlayer mediaLose; // 宣告媒體物件 輸
    private MediaPlayer mediaDraw; // 宣告媒體物件 平
    private RelativeLayout r_layout;
    private MediaPlayer mediagoodnight;
    private Button btnShowResult;

    private int miCountSet = 0,
            miCountPlayerWin = 0,
            miCountComWin = 0,
            miCountDraw = 0;


    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.anim_alpha_in,R.anim.anim_alpha_out);
    }

    private ImageSwitcher txtComPlay;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startmusic.stop();
        mediagoodnight.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1003);
        setupViewComponent();
    }

    private void setupViewComponent() {
        // ---取得R.java 配置碼---
        txtComPlay = (ImageSwitcher) findViewById(R.id.m1003_c001); // 電腦選擇
        txtSelect = (TextView) findViewById(R.id.m1003_s001); // 選擇結果
        txtResult = (TextView) findViewById(R.id.m1003_f000); // 輸贏判斷
        btnScissors = (ImageButton) findViewById(R.id.m1003_b001); // 剪刀
        btnStone = (ImageButton) findViewById(R.id.m1003_b002); // 石頭
        btnNet = (ImageButton) findViewById(R.id.m1003_b003); // 布
        btnShowResult=(Button)findViewById(R.id.btnShowResult);


        //----電腦出拳-----
        txtComPlay.setFactory(this);
        // --開啟片頭音樂-----
        startmusic = MediaPlayer.create(getApplication(), R.raw.guess);
        startmusic.start();

        //--設定開機動畫--
        r_layout=(RelativeLayout)findViewById(R.id.m1003_r001);
        r_layout.setBackgroundResource(R.drawable.back01);
        r_layout.setAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_scale_rotate_out));
        r_layout.setAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_scale_rotate_in));
        r_layout.setBackgroundResource(R.drawable.a1);

        //--設定音樂連結--
        mediaWin = MediaPlayer.create(getApplication(), R.raw.vctory);
        mediaLose = MediaPlayer.create(getApplication(), R.raw.lose);
        mediaDraw = MediaPlayer.create(getApplication(), R.raw.haha);
        mediagoodnight=MediaPlayer.create(getApplication(),R.raw.a88);
        // ---啟動監聽事件----
        btnScissors.setOnClickListener(this);
        btnStone.setOnClickListener(this);
        btnNet.setOnClickListener(this);
        btnShowResult.setOnClickListener(btnShowResultLis);
    }


    private Button.OnClickListener btnShowResultLis= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it=new Intent();
            it.setClass(M1003.this,GameResult.class);

            Bundle bundle=new Bundle();
            bundle.putInt("KEY_COUNT_SET", miCountSet);
            bundle.putInt("KEY_COUNT_PLAYER_WIN", miCountPlayerWin);
            bundle.putInt("KEY_COUNT_COM_WIN", miCountComWin);
            bundle.putInt("KEY_COUNT_DRAW", miCountDraw);
            it.putExtras(bundle);

            startActivity(it);

        }
    };

        public void onClick(View v) {
            int iComPlay = (int) (Math.random() * 3 + 1);
            miCountSet++;
            // 1 - scissors, 2 - stone, 3 - net.
            switch (iComPlay) {
                case 1: //電腦:剪刀scissors
                    user_select = getString(R.string.m1003_s002) + getString(R.string.m1003_b001) + " ";
                    txtComPlay.setImageResource(R.drawable.scissors); // 轉換ImageView剪刀
                    break;
                case 2: //電腦:石頭stone
                    user_select = getString(R.string.m1003_s002) + getString(R.string.m1003_b002) + " ";
                    txtComPlay.setImageResource(R.drawable.stone); // 轉換ImageView石頭
                    break;
                case 3: // 電腦:布net
                    user_select = getString(R.string.m1003_s002) + getString(R.string.m1003_b003) + " ";
                    txtComPlay.setImageResource(R.drawable.net); // 轉換ImageView布
                    break;
            }
            switch (v.getId()) {

                case R.id.m1003_b001:
                    // 選擇 剪刀scissors
                    user_select += getString(R.string.m1003_s001) + getString(R.string.m1003_b001);
                    u_setalpha();
                    btnScissors.getBackground().setAlpha(150);
                    switch (iComPlay) {
                        case 1:
                            answer = getString(R.string.m1003_f000) + getString(R.string.m1003_f003); // 平
                            txtResult.setTextColor(getResources().getColor(R.color.Yellow)); // 平用黃顯示
                            miCountDraw++;
                            music(2);
                            break;
                        case 2:
                             answer = getString(R.string.m1003_f000) + getString(R.string.m1003_f002); // 輸
                            txtResult.setTextColor(getResources().getColor(R.color.Red)); // 輸用紅顯示
                            miCountComWin++;
                            music(3);
                            break;
                        case 3:
                            answer = getString(R.string.m1003_f000) + getString(R.string.m1003_f001); // 贏
                            txtResult.setTextColor(getResources().getColor(R.color.Lime)); // 贏用綠顯示
                            miCountPlayerWin++;
                            music(1);
                            break;
                    }

                    break;
                //----------------------------------------------
                case R.id.m1003_b002:
                    // 選擇 石頭stone
                    user_select += getString(R.string.m1003_s001)  + getString(R.string.m1003_b002);
                    u_setalpha();
                    btnStone.getBackground().setAlpha(250);
                    switch (iComPlay) {
                        case 1:
                            answer = getString(R.string.m1003_f000) + getString(R.string.m1003_f001); // 贏
                            txtResult.setTextColor(getResources().getColor(R.color.Lime)); // 贏用綠顯示
                            miCountPlayerWin++;
                            music(1);
                            break;
                        case 2:
                            answer = getString(R.string.m1003_f000) + getString(R.string.m1003_f003); // 平
                            txtResult.setTextColor(getResources().getColor(R.color.Yellow)); // 平用黃顯示
                            miCountDraw++;
                            music(2);
                            break;
                        case 3:
                            answer = getString(R.string.m1003_f000) + getString(R.string.m1003_f002); // 輸
                            txtResult.setTextColor(getResources().getColor(R.color.Red)); // 輸用紅顯示
                            miCountComWin++;
                            music(3);
                            break;
                    }
                    break;
                //---------------------------------------------
                case R.id.m1003_b003:
                    // 選擇 布net
                    user_select += getString(R.string.m1003_s001) + getString(R.string.m1003_b003);
                    u_setalpha();
                    btnNet.getBackground().setAlpha(200);
                    switch (iComPlay) {
                        case 1:
                            answer = getString(R.string.m1003_f000) + getString(R.string.m1003_f002); // 輸
                            txtResult.setTextColor(getResources().getColor(R.color.Red)); // 輸用紅顯示
                            miCountComWin++;
                            music(3);
                            break;
                        case 2:
                            answer = getString(R.string.m1003_f000) + getString(R.string.m1003_f001); // 贏
                            txtResult.setTextColor(getResources().getColor(R.color.Lime)); // 贏用綠顯示
                            miCountPlayerWin++;
                            music(1);
                            break;
                        case 3:
                            answer = getString(R.string.m1003_f000) + getString(R.string.m1003_f003); // 平
                            txtResult.setTextColor(getResources().getColor(R.color.Yellow)); // 平用黃顯示
                            miCountDraw++;
                            music(2);
                            break;
                    }
                    break;
            }
            //------電腦出拳動畫----
            txtComPlay.clearAnimation();
            Animation anim=AnimationUtils.loadAnimation(this,R.anim.anim_trans_in);
            anim.setInterpolator(new BounceInterpolator());
            txtComPlay.setAnimation(anim);


            txtSelect.setText(user_select);
            txtResult.setText(answer);
        }

    private void u_setalpha() {
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
                break;
            case 2: //平
                mediaDraw.start();
                break;
            case 3: //輸
                mediaLose.start();
                break;
        }
    }


    @Override
    public View makeView() {
        ImageView v =new ImageView(this);
        v.setBackgroundColor(0xFF000000);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT ));
        return v;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_m1003,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startmusic.stop();
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
