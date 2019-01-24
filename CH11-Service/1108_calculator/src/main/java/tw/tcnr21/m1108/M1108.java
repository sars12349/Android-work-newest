package tw.tcnr21.m1108;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class M1108 extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5;
    private Button btn6, btn7, btn8, btn9, btn0;
    private Button btn10, btn11, btn12, btn13, btn14, btn15;
    private TextView num1;
    private double n1;
    private TextView ans;
    private String s = ""; //用來判斷+-*/
//    private String TAG = "oldpa=>";
ToneGenerator toneG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1108);
        setupViewCompoent();

    }

    private void setupViewCompoent() {
        num1 = (TextView) findViewById(R.id.m1108_e001);
        num1.setText("");
        ans = (TextView) findViewById(R.id.m1108_t001);
//--------------------------------------------------------
        btn0 = (Button) findViewById(R.id.m1108_b000);
        btn1 = (Button) findViewById(R.id.m1108_b001);
        btn2 = (Button) findViewById(R.id.m1108_b002);
        btn3 = (Button) findViewById(R.id.m1108_b003);
        btn4 = (Button) findViewById(R.id.m1108_b004);
        btn5 = (Button) findViewById(R.id.m1108_b005);
        btn6 = (Button) findViewById(R.id.m1108_b006);
        btn7 = (Button) findViewById(R.id.m1108_b007);
        btn8 = (Button) findViewById(R.id.m1108_b008);
        btn9 = (Button) findViewById(R.id.m1108_b009);
        btn10 = (Button) findViewById(R.id.m1108_b010); //.

        btn11 = (Button) findViewById(R.id.m1108_b011);// +
        btn12 = (Button) findViewById(R.id.m1108_b012);// -
        btn13 = (Button) findViewById(R.id.m1108_b013);// *
        btn14 = (Button) findViewById(R.id.m1108_b014);// /
        btn15 = (Button) findViewById(R.id.m1108_b015); // =

        btn0.setOnClickListener(numberOnClick);
        btn1.setOnClickListener(numberOnClick);
        btn2.setOnClickListener(numberOnClick);
        btn3.setOnClickListener(numberOnClick);
        btn4.setOnClickListener(numberOnClick);
        btn5.setOnClickListener(numberOnClick);
        btn6.setOnClickListener(numberOnClick);
        btn7.setOnClickListener(numberOnClick);
        btn8.setOnClickListener(numberOnClick);
        btn9.setOnClickListener(numberOnClick);
        btn10.setOnClickListener(numberOnClick);

        btn11.setOnClickListener(calOnClick);
        btn12.setOnClickListener(calOnClick);
        btn13.setOnClickListener(calOnClick);
        btn14.setOnClickListener(calOnClick);

        btn15.setOnClickListener(equalOnClick);
        // 宣告鈴聲
        toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100); // 100=max
    }


    private Button.OnClickListener numberOnClick = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            String keyin = num1.getText().toString();
            if (toneG!=null)        toneG.stopTone();
            switch (v.getId()) {
                case R.id.m1108_b000:
                    num1.setText(keyin + "0");
                    toneG.startTone(ToneGenerator.TONE_DTMF_0, 500);//
                    break;
                case R.id.m1108_b001:
                    num1.setText(keyin + "1");
                    toneG.startTone(ToneGenerator.TONE_DTMF_1, 500);//
                    break;
                case R.id.m1108_b002:
                    num1.setText(keyin + "2");
                    toneG.startTone(ToneGenerator.TONE_DTMF_2, 500);//
                    break;
                case R.id.m1108_b003:
                    num1.setText(keyin + "3");
                    toneG.startTone(ToneGenerator.TONE_DTMF_3, +100);//
                    break;
                case R.id.m1108_b004:
                    num1.setText(keyin + "4");
                    toneG.startTone(ToneGenerator.TONE_DTMF_4, 500);//

                    break;
                case R.id.m1108_b005:
                    num1.setText(keyin + "5");
                    toneG.startTone(ToneGenerator.TONE_DTMF_5, 500);//
                    break;
                case R.id.m1108_b006:
                    num1.setText(keyin + "6");
                    toneG.startTone(ToneGenerator.TONE_DTMF_6, 500);//
                    break;
                case R.id.m1108_b007:
                    num1.setText(keyin + "7");
                    toneG.startTone(ToneGenerator.TONE_DTMF_7, 500);//
                    break;
                case R.id.m1108_b008:
                    num1.setText(keyin + "8");
                    toneG.startTone(ToneGenerator.TONE_DTMF_8, 500);//
                    break;
                case R.id.m1108_b009:
                    num1.setText(keyin + "9");
                    toneG.startTone(ToneGenerator.TONE_DTMF_9, 500);//
                    break;
                case R.id.m1108_b010:
                    num1.setText(keyin + ".");
                    toneG.startTone(ToneGenerator.TONE_DTMF_P, 500);//#
                    break;
            }
        }
    };


    private Button.OnClickListener calOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (toneG!=null)        toneG.stopTone();
            try {
                n1 = Double.parseDouble(num1.getText().toString());
            } catch (Exception e) {
//                Log.e(TAG, "error:");
            }
            num1.setText("");
            switch (v.getId()) {
                case R.id.m1108_b011:
                    s = "P";
                    toneG.startTone(ToneGenerator.TONE_DTMF_A, 500);//
                    break;
                case R.id.m1108_b012:
                    s = "M";
                    toneG.startTone(ToneGenerator.TONE_DTMF_B, 500);//
                    break;
                case R.id.m1108_b013:
                    toneG.startTone(ToneGenerator.TONE_DTMF_C, 500);//
                    s = "X";
                    break;
                case R.id.m1108_b014:
                    s = "D";
                    toneG.startTone(ToneGenerator.TONE_DTMF_D, 500);//
                    break;
            }
        }
    };
    private Button.OnClickListener equalOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            toneG.startTone(ToneGenerator.TONE_DTMF_S, 500);//  *
            if(num1.getText().toString().trim().length() != 0) {
                double n2 = Double.parseDouble((num1.getText().toString()));
                DecimalFormat nf = new DecimalFormat("0.0000");
                if (s.equals("P")) {
                    ans.setText(nf.format(n1 + n2));
                } else if (s.equals("M")) {
                    ans.setText(nf.format(n1 - n2));
                } else if (s.equals("X")) {
                    ans.setText(nf.format(n1 * n2));
                } else if (s.equals("D")) {
                    ans.setText(nf.format(n1 / n2));
                }
                num1.setText("");
            }
        }
    };
}
