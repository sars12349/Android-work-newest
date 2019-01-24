package tw.tcnr21.m0608;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class M0608 extends AppCompatActivity {

    private TextView num1,ans;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    private Button btn10,btn11,btn12,btn13,btn14,btn15;
    private double n1,sum;
    private String s;
    private int cnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0608);
        setupViewcomponent();

    }

    private void setupViewcomponent() {
        num1=(TextView)findViewById(R.id.m0608_e001);
        for(int i=0;i<18;i++){
            String idName="m0608_b0"+String.format("%02d",i);
            int resID=getResources().getIdentifier(idName,"id",getPackageName());

            Button btn=((Button)findViewById(resID));
            if(i<11)btn.setOnClickListener(numberOnClick);
            if(i>10&&i<15)btn.setOnClickListener(calOnClick);
            if (i==15)btn.setOnClickListener(equalOnClick);
            if(i==16)btn.setOnClickListener(clearOnClick);
            if(i==17)btn.setOnClickListener(aclearOnClick);
        }

    }
    private Button.OnClickListener numberOnClick=new Button.OnClickListener(){


        @Override
        public void onClick(View v) {
            String keyin=num1.getText().toString();

             switch(v.getId()){
                 case R.id.m0608_b000:
                     num1.setText(keyin +"0");
                     break;
                 case R.id.m0608_b001:
                     num1.setText(keyin +"1");
                     break;
                 case R.id.m0608_b002:
                     num1.setText(keyin +"2");
                     break;
                 case R.id.m0608_b003:
                     num1.setText(keyin +"3");
                     break;
                 case R.id.m0608_b004:
                     num1.setText(keyin +"4");
                     break;
                 case R.id.m0608_b005:
                     num1.setText(keyin +"5");
                     break;
                 case R.id.m0608_b006:
                     num1.setText(keyin +"6");
                     break;
                 case R.id.m0608_b007:
                     num1.setText(keyin +"7");
                     break;
                 case R.id.m0608_b008:
                     num1.setText(keyin +"8");
                     break;
                 case R.id.m0608_b009:
                     num1.setText(keyin +"9");
                     break;
                 case R.id.m0608_b010:
                     num1.setText(keyin +".");
                     break;

             }


        }
    };

    private Button.OnClickListener calOnClick=new Button.OnClickListener(){


        @Override
        public void onClick(View v) {

            if(cnt==0) {
                try {
                    n1 = Double.parseDouble(num1.getText().toString());
                } catch (Exception e) {

                }

                switch (v.getId()) {
                    case R.id.m0608_b011:
                        s = "P";
                        break;
                    case R.id.m0608_b012:
                        s = "M";
                        break;
                    case R.id.m0608_b013:
                        s = "X";
                        break;
                    case R.id.m0608_b014:
                        s = "D";
                        break;
                }
            }

            if(cnt>0){
                sum = Double.parseDouble(num1.getText().toString());
                //-------------------------------
                switch (v.getId()) {
                    case R.id.m0608_b011:
                        n1+=sum;
                        s = "P";
                        break;
                    case R.id.m0608_b012:
                        n1 -= sum;
                        s = "M";
                        break;
                    case R.id.m0608_b013:
                        n1 *= sum;
                        s = "X";
                        break;
                    case R.id.m0608_b014:
                        n1 /= sum;
                        s = "D";
                        break;
                }
            }
            num1.setText("");
            cnt++;
            }
    };

    private  Button.OnClickListener equalOnClick=new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            if (num1.getText().toString().trim().length()!=0){
                double n2=Double.parseDouble(num1.getText().toString());
                DecimalFormat nf =new DecimalFormat("0.0000");

                if (s.equals("P"))
                    num1.setText(nf.format(n1+ n2));
                if (s.equals("M"))
                    num1.setText(nf.format(n1 - n2));
                if (s.equals("X"))
                    num1.setText(nf.format(n1 * n2));
                if (s.equals("D"))
                    num1.setText(nf.format(n1 / n2));
                num1.setText("");
            }
        }
    };
    private Button.OnClickListener clearOnClick=new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            num1.setText("");

        }
    };

    private Button.OnClickListener aclearOnClick=new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            num1.setText("");
            n1=0;
            sum=0;
            cnt=0;
        }
    };

}
