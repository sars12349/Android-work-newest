package tw.tcnr21.m0602;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class M0602 extends AppCompatActivity {

    private ImageView txtComPlay;
    private TextView txtSelect;
    private TextView txtResult;
    private ImageButton btnScissors;
    private ImageButton btnStone;
    private ImageButton btnNet;
    private String user_select;
    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0602);
        setupViewComponent();
    }

    private void setupViewComponent() {
        txtComPlay=(ImageView)findViewById(R.id.m0602_c001);//電腦
        txtSelect=(TextView)findViewById(R.id.m0602_s001);//選擇結果
        txtResult=(TextView)findViewById(R.id.m0602_f000);//輸贏判斷
        btnScissors=(ImageButton)findViewById(R.id.m0602_b001);//剪刀
        btnStone=(ImageButton)findViewById(R.id.m0602_b002);//石頭
        btnNet=(ImageButton)findViewById(R.id.m0602_b003);//布

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
                case  R.id.m0602_b001 : //剪刀
                    user_select=getString(R.string.m0602_s001)+" "+getString(R.string.m0602_b001);
                    switch (iComPlay){
                        case 1:
                            //txtComPlay.setText(R.string.m0602_b001);
                            answer=getString(R.string.m0602_f000)+getString(R.string.m0602_f003);//平
                            txtComPlay.setImageResource(R.drawable.scissors);
                            //txtResult.setTextColor(getResources().getColor(R.color.Yellow));
                            break;
                        case 2:
                            //txtComPlay.setText(R.string.m0602_b002);
                            answer=getString(R.string.m0602_f000)+getString(R.string.m0602_f002);//輸
                            txtComPlay.setImageResource(R.drawable.stone);
                            //txtResult.setTextColor(getResources().getColor(R.color.Red));
                            break;
                        case 3:
                           // txtComPlay.setText(R.string.m0602_b003);
                            answer=getString(R.string.m0602_f000)+getString(R.string.m0602_f001);//贏
                            txtComPlay.setImageResource(R.drawable.net);
                            //txtResult.setTextColor(getResources().getColor(R.color.Lime));
                            break;
                    }
                    break;

                case R.id.m0602_b002: //石頭
                    user_select=getString(R.string.m0602_s001)+" "+getString(R.string.m0602_b002);
                    switch (iComPlay){
                        case 1:
                           // txtComPlay.setText(R.string.m0602_b001);
                            answer=getString(R.string.m0602_f000)+getString(R.string.m0602_f001);//贏
                            txtComPlay.setImageResource(R.drawable.scissors);
                            //txtResult.setTextColor(getResources().getColor(R.color.Lime));
                            break;
                        case 2:
                          //  txtComPlay.setText(R.string.m0602_b002);
                            answer=getString(R.string.m0602_f000)+getString(R.string.m0602_f003);//平
                            txtComPlay.setImageResource(R.drawable.stone);
                           // txtResult.setTextColor(getResources().getColor(R.color.Yellow));
                            break;
                        case 3:
                           // txtComPlay.setText(R.string.m0602_b003);
                            answer=getString(R.string.m0602_f000)+getString(R.string.m0602_f002);//輸
                            txtComPlay.setImageResource(R.drawable.net);
                            //txtResult.setTextColor(getResources().getColor(R.color.Red));
                            break;

                    }
                    break;

                case  R.id.m0602_b003://布
                    user_select=getString(R.string.m0602_s001)+" "+getString(R.string.m0602_b003);
                    switch (iComPlay){
                        case 1:
                           //txtComPlay.setText(R.string.m0602_b001);
                            answer=getString(R.string.m0602_f000)+getString(R.string.m0602_f002);//輸
                            txtComPlay.setImageResource(R.drawable.scissors);
                           // txtResult.setTextColor(getResources().getColor(R.color.Red));
                            break;
                        case 2:
                            //txtComPlay.setText(R.string.m0602_b002);
                            answer=getString(R.string.m0602_f000)+getString(R.string.m0602_f001);//贏
                            txtComPlay.setImageResource(R.drawable.stone);
                            //txtResult.setTextColor(getResources().getColor(R.color.Lime));
                            break;
                        case 3:
                           // txtComPlay.setText(R.string.m0602_b003);
                            answer=getString(R.string.m0602_f000)+getString(R.string.m0602_f003);//平手
                            txtComPlay.setImageResource(R.drawable.net);
                           // txtResult.setTextColor(getResources().getColor(R.color.Yellow));
                            break;
                    }
                    break;
            }
            txtSelect.setText(user_select);
            txtResult.setText(answer);

        }
    };
}
