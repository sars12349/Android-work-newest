package tw.tcnr21.m0605;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import tw.oldpa.m0605.R;

public class M0605 extends AppCompatActivity {

    // ----宣告變數----
    private ImageView txtComPlay;
    private TextView txtSelect, txtResult;
    private ImageButton btnScissors, btnStone, btnNet;
    private String user_select;
    private String answer;
    private MediaPlayer startmusic; //宣告媒體物件 片頭音樂
    private MediaPlayer mediaWin; // 宣告媒體物件 贏
    private MediaPlayer mediaLose; // 宣告媒體物件 輸
    private MediaPlayer mediaDraw; // 宣告媒體物件 平

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0605);
        setupViewComponent();
    }

    private void setupViewComponent() {
        // ---取得R.java 配置碼---
        txtComPlay = (ImageView) findViewById(R.id.m0605_c001); // 電腦選擇
        txtSelect = (TextView) findViewById(R.id.m0605_s001); // 選擇結果
        txtResult = (TextView) findViewById(R.id.m0605_f000); // 輸贏判斷
        btnScissors = (ImageButton) findViewById(R.id.m0605_b001); // 剪刀
        btnStone = (ImageButton) findViewById(R.id.m0605_b002); // 石頭
        btnNet = (ImageButton) findViewById(R.id.m0605_b003); // 布

        // --開啟片頭音樂-----
        startmusic = MediaPlayer.create(getApplication(), R.raw.guess);
        startmusic.start();

        //--設定音樂連結--
        mediaWin = MediaPlayer.create(getApplication(), R.raw.vctory);
        mediaLose = MediaPlayer.create(getApplication(), R.raw.lose);
        mediaDraw = MediaPlayer.create(getApplication(), R.raw.haha);
        // ---啟動監聽事件----
        btnScissors.setOnClickListener(btn01On);
        btnStone.setOnClickListener(btn01On);
        btnNet.setOnClickListener(btn01On);
    }


    private Button.OnClickListener btn01On = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            int iComPlay = (int) (Math.random() * 3 + 1);
            // 1 - scissors, 2 - stone, 3 - net.
            switch (iComPlay) {
                case 1: //電腦:剪刀scissors
                    user_select = getString(R.string.m0605_s002) + getString(R.string.m0605_b001) + " ";
                    txtComPlay.setImageResource(R.drawable.scissors); // 轉換ImageView剪刀
                    break;
                case 2: //電腦:石頭stone
                    user_select = getString(R.string.m0605_s002) + getString(R.string.m0605_b002) + " ";
                    txtComPlay.setImageResource(R.drawable.stone); // 轉換ImageView石頭
                    break;
                case 3: // 電腦:布net
                    user_select = getString(R.string.m0605_s002) + getString(R.string.m0605_b003) + " ";
                    txtComPlay.setImageResource(R.drawable.net); // 轉換ImageView布
                    break;
            }
            switch (v.getId()) {
                
                case R.id.m0605_b001:
                    // 選擇 剪刀scissors
                    user_select = getString(R.string.m0605_s001) + " " + getString(R.string.m0605_b001);
                    user_select += getString(R.string.m0605_s001) + getString(R.string.m0605_b001);
                    switch (iComPlay) {
                        case 1:
                            answer = getString(R.string.m0605_f000) + getString(R.string.m0605_f003); // 平
                            txtResult.setTextColor(getResources().getColor(R.color.Yellow)); // 平用黃顯示
                            music(2);
                            break;
                        case 2:
                             answer = getString(R.string.m0605_f000) + getString(R.string.m0605_f002); // 輸
                            txtResult.setTextColor(getResources().getColor(R.color.Red)); // 輸用紅顯示
                            music(3);
                            break;
                        case 3:
                            answer = getString(R.string.m0605_f000) + getString(R.string.m0605_f001); // 贏
                            txtResult.setTextColor(getResources().getColor(R.color.Lime)); // 贏用綠顯示
                            music(1);
                            break;
                    }

                    break;
                //----------------------------------------------
                case R.id.m0605_b002:
                    // 選擇 石頭stone
                    user_select += getString(R.string.m0605_s001)  + getString(R.string.m0605_b002);
                    switch (iComPlay) {
                        case 1:
                            answer = getString(R.string.m0605_f000) + getString(R.string.m0605_f001); // 贏
                            txtResult.setTextColor(getResources().getColor(R.color.Lime)); // 贏用綠顯示
                            music(1);
                            break;
                        case 2:
                            answer = getString(R.string.m0605_f000) + getString(R.string.m0605_f003); // 平
                            txtResult.setTextColor(getResources().getColor(R.color.Yellow)); // 平用黃顯示
                            music(2);
                            break;
                        case 3:
                            answer = getString(R.string.m0605_f000) + getString(R.string.m0605_f002); // 輸
                            txtResult.setTextColor(getResources().getColor(R.color.Red)); // 輸用紅顯示
                            music(3);
                            break;
                    }
                    break;
                //---------------------------------------------
                case R.id.m0605_b003:
                    // 選擇 布net
                    user_select += getString(R.string.m0605_s001) + getString(R.string.m0605_b003);
                    switch (iComPlay) {
                        case 1:
                            answer = getString(R.string.m0605_f000) + getString(R.string.m0605_f002); // 輸
                            txtResult.setTextColor(getResources().getColor(R.color.Red)); // 輸用紅顯示
                            music(3);
                            break;
                        case 2:
                            answer = getString(R.string.m0605_f000) + getString(R.string.m0605_f001); // 贏
                            txtResult.setTextColor(getResources().getColor(R.color.Lime)); // 贏用綠顯示
                            music(1);
                            break;
                        case 3:
                            answer = getString(R.string.m0605_f000) + getString(R.string.m0605_f003); // 平
                            txtResult.setTextColor(getResources().getColor(R.color.Yellow)); // 平用黃顯示
                            music(2);
                            break;
                    }
                    break;
            }
            txtSelect.setText(user_select);
            txtResult.setText(answer);
        }
    };

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
                Toast.makeText(getApplication(),R.string.m0605_f001,Toast.LENGTH_LONG).show();
                break;
            case 2: //平
                mediaDraw.start();
                Toast.makeText(getApplicationContext(),R.string.m0605_f003, Toast.LENGTH_LONG).show();
                break;
            case 3: //輸
                mediaLose.start();
                Toast.makeText(getApplicationContext(),R.string.m0605_f002, Toast.LENGTH_LONG).show();
                break;
        }
    }


}
