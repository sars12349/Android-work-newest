package tw.tcnr21.m0601;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class M0601 extends AppCompatActivity {

    private TextView txtComPlay;
    private TextView txtSelect;
    private TextView txtResult;
    private Button btnScissors;
    private Button btnStone;
    private Button btnNet;
    private String user_select;
    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0601);
        setupViewComponent();
    }

    private void setupViewComponent() {
        txtComPlay=(TextView)findViewById(R.id.m0601_c001);//電腦
        txtSelect=(TextView)findViewById(R.id.m0601_s001);//選擇結果
        txtResult=(TextView)findViewById(R.id.m0601_f000);//輸贏判斷
        btnScissors=(Button)findViewById(R.id.m0601_b001);//剪刀
        btnStone=(Button)findViewById(R.id.m0601_b002);//石頭
        btnNet=(Button)findViewById(R.id.m0601_b003);//布

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
                case  R.id.m0601_b001 : //剪刀
                    user_select=getString(R.string.m0601_s001)+" "+getString(R.string.m0601_b001);
                    switch (iComPlay){
                        case 1:
                            txtComPlay.setText(R.string.m0601_b001);
                            answer=getString(R.string.m0601_f000)+getString(R.string.m0601_f003);//平
                            txtResult.setTextColor(getResources().getColor(R.color.Yellow));
                            break;
                        case 2:
                            txtComPlay.setText(R.string.m0601_b002);
                            answer=getString(R.string.m0601_f000)+getString(R.string.m0601_f002);//輸
                            txtResult.setTextColor(getResources().getColor(R.color.Red));
                            break;
                        case 3:
                            txtComPlay.setText(R.string.m0601_b003);
                            answer=getString(R.string.m0601_f000)+getString(R.string.m0601_f001);//贏
                            txtResult.setTextColor(getResources().getColor(R.color.Lime));
                            break;
                    }
                    break;

                case R.id.m0601_b002: //石頭
                    user_select=getString(R.string.m0601_s001)+" "+getString(R.string.m0601_b002);
                    switch (iComPlay){
                        case 1:
                            txtComPlay.setText(R.string.m0601_b001);
                            answer=getString(R.string.m0601_f000)+getString(R.string.m0601_f001);//贏
                            txtResult.setTextColor(getResources().getColor(R.color.Lime));
                            break;
                        case 2:
                            txtComPlay.setText(R.string.m0601_b002);
                            answer=getString(R.string.m0601_f000)+getString(R.string.m0601_f003);//平
                            txtResult.setTextColor(getResources().getColor(R.color.Yellow));
                            break;
                        case 3:
                            txtComPlay.setText(R.string.m0601_b003);
                            answer=getString(R.string.m0601_f000)+getString(R.string.m0601_f002);//輸
                            txtResult.setTextColor(getResources().getColor(R.color.Red));
                            break;

                    }
                    break;

                case  R.id.m0601_b003://布
                    user_select=getString(R.string.m0601_s001)+" "+getString(R.string.m0601_b003);
                    switch (iComPlay){
                        case 1:
                            txtComPlay.setText(R.string.m0601_b001);
                            answer=getString(R.string.m0601_f000)+getString(R.string.m0601_f002);//輸
                            txtResult.setTextColor(getResources().getColor(R.color.Red));
                            break;
                        case 2:
                            txtComPlay.setText(R.string.m0601_b002);
                            answer=getString(R.string.m0601_f000)+getString(R.string.m0601_f001);//贏
                            txtResult.setTextColor(getResources().getColor(R.color.Lime));
                            break;
                        case 3:
                            txtComPlay.setText(R.string.m0601_b003);
                            answer=getString(R.string.m0601_f000)+getString(R.string.m0601_f003);//平手
                            txtResult.setTextColor(getResources().getColor(R.color.Yellow));
                            break;
                    }
                    break;
            }
            txtSelect.setText(user_select);
            txtResult.setText(answer);

        }
    };
}
