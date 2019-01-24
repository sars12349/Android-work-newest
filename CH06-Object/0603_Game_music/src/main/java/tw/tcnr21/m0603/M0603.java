package tw.tcnr21.m0603;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class M0603 extends AppCompatActivity {

    private ImageView txtComPlay;
    private TextView txtSelect;
    private TextView txtResult;
    private ImageButton btnScissors;
    private ImageButton btnStone;
    private ImageButton btnNet;
    private String user_select;
    private String answer;
    private MediaPlayer startmusic;
    private MediaPlayer mediaWin,mediaLose,mediaDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0603);
        setupViewComponent();
    }

    private void setupViewComponent() {
        txtComPlay=(ImageView)findViewById(R.id.m0603_c001);//電腦
        txtSelect=(TextView)findViewById(R.id.m0603_s001);//選擇結果
        txtResult=(TextView)findViewById(R.id.m0603_f000);//輸贏判斷
        btnScissors=(ImageButton)findViewById(R.id.m0603_b001);//剪刀
        btnStone=(ImageButton)findViewById(R.id.m0603_b002);//石頭
        btnNet=(ImageButton)findViewById(R.id.m0603_b003);//布

        //播放片頭音樂
        startmusic=MediaPlayer.create(getApplication(),R.raw.back);
        startmusic.start();

        mediaWin=MediaPlayer.create(getApplication(),R.raw.happy);
        mediaLose=MediaPlayer.create(getApplication(),R.raw.sad);
        mediaDraw=MediaPlayer.create(getApplication(),R.raw.laugth);


        //監聽
        btnScissors.setOnClickListener(btn01On);
        btnStone.setOnClickListener(btn01On);
        btnNet.setOnClickListener(btn01On);
    }

    private Button.OnClickListener btn01On=new Button.OnClickListener(){


        @Override
        public void onClick(View v) {
           int iComPlay=(int)(Math.random()*3+1);
            //1-scissors,2-stone,3-net
            switch (v.getId()){
                case  R.id.m0603_b001 : //剪刀
                    user_select=getString(R.string.m0603_s001)+" "+getString(R.string.m0603_b001);
                    switch (iComPlay){
                        case 1:
                            //txtComPlay.setText(R.string.m0603_b001);
                            answer=getString(R.string.m0603_f000)+getString(R.string.m0603_f003);//平
                            txtComPlay.setImageResource(R.drawable.scissors);
                            music(2);
                            //txtResult.setTextColor(getResources().getColor(R.color.Yellow));
                            break;
                        case 2:
                            //txtComPlay.setText(R.string.m0603_b002);
                            answer=getString(R.string.m0603_f000)+getString(R.string.m0603_f002);//輸
                            txtComPlay.setImageResource(R.drawable.stone);
                            music(3);
                            //txtResult.setTextColor(getResources().getColor(R.color.Red));
                            break;
                        case 3:
                           // txtComPlay.setText(R.string.m0603_b003);
                            answer=getString(R.string.m0603_f000)+getString(R.string.m0603_f001);//贏
                            txtComPlay.setImageResource(R.drawable.net);
                            music(1);
                            //txtResult.setTextColor(getResources().getColor(R.color.Lime));
                            break;
                    }
                    break;

                case R.id.m0603_b002: //石頭
                    user_select=getString(R.string.m0603_s001)+" "+getString(R.string.m0603_b002);
                    switch (iComPlay){
                        case 1:
                           // txtComPlay.setText(R.string.m0603_b001);
                            answer=getString(R.string.m0603_f000)+getString(R.string.m0603_f001);//贏
                            txtComPlay.setImageResource(R.drawable.scissors);
                            music(1);
                            //txtResult.setTextColor(getResources().getColor(R.color.Lime));
                            break;
                        case 2:
                          //  txtComPlay.setText(R.string.m0603_b002);
                            answer=getString(R.string.m0603_f000)+getString(R.string.m0603_f003);//平
                            txtComPlay.setImageResource(R.drawable.stone);
                            music(2);
                           // txtResult.setTextColor(getResources().getColor(R.color.Yellow));
                            break;
                        case 3:
                           // txtComPlay.setText(R.string.m0603_b003);
                            answer=getString(R.string.m0603_f000)+getString(R.string.m0603_f002);//輸
                            txtComPlay.setImageResource(R.drawable.net);
                            music(3);
                            //txtResult.setTextColor(getResources().getColor(R.color.Red));
                            break;

                    }
                    break;

                case  R.id.m0603_b003://布
                    user_select=getString(R.string.m0603_s001)+" "+getString(R.string.m0603_b003);
                    switch (iComPlay){
                        case 1:
                           //txtComPlay.setText(R.string.m0603_b001);
                            answer=getString(R.string.m0603_f000)+getString(R.string.m0603_f002);//輸
                            txtComPlay.setImageResource(R.drawable.scissors);
                            music(3);
                           // txtResult.setTextColor(getResources().getColor(R.color.Red));
                            break;
                        case 2:
                            //txtComPlay.setText(R.string.m0603_b002);
                            answer=getString(R.string.m0603_f000)+getString(R.string.m0603_f001);//贏
                            txtComPlay.setImageResource(R.drawable.stone);
                            music(1);
                            //txtResult.setTextColor(getResources().getColor(R.color.Lime));
                            break;
                        case 3:
                           // txtComPlay.setText(R.string.m0603_b003);
                            answer=getString(R.string.m0603_f000)+getString(R.string.m0603_f003);//平手
                            txtComPlay.setImageResource(R.drawable.net);
                            music(2);
                           // txtResult.setTextColor(getResources().getColor(R.color.Yellow));
                            break;
                    }
                    break;
            }
            txtSelect.setText(user_select);
            txtResult.setText(answer);

        }
    };

    private void music(int i) {
        //中斷
        if (startmusic.isPlaying()) startmusic.stop();
        if (mediaWin.isPlaying()) mediaWin.pause();
        if (mediaDraw.isPlaying()) mediaDraw.pause();
        if (mediaLose.isPlaying()) mediaLose.pause();

        switch (i)
        {
            case 1:
                mediaWin.start();
            case 2:
                mediaDraw.start();
            case 3:
                mediaLose.start();
        }
    }
}
