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
    private double n1,n3,e1;
    private String s;
    private int cnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0608);
        setupViewcomponent();

    }

    private void setupViewcomponent() {
        num1=(TextView)findViewById(R.id.m0607_e001);
        ans=(TextView)findViewById(R.id.m0607_t001);
        //----------------------------------------------------
        btn0=(Button)findViewById(R.id.m0608_b000);
        btn1=(Button)findViewById(R.id.m0608_b001);
        btn2=(Button)findViewById(R.id.m0608_b002);
        btn3=(Button)findViewById(R.id.m0608_b003);
        btn4=(Button)findViewById(R.id.m0608_b004);
        btn5=(Button)findViewById(R.id.m0608_b005);
        btn6=(Button)findViewById(R.id.m0608_b006);
        btn7=(Button)findViewById(R.id.m0608_b007);
        btn8=(Button)findViewById(R.id.m0608_b008);
        btn9=(Button)findViewById(R.id.m0608_b009);
        btn10=(Button)findViewById(R.id.m0608_b010);// .
        btn11=(Button)findViewById(R.id.m0608_b011);//+
        btn12=(Button)findViewById(R.id.m0608_b012);//-
        btn13=(Button)findViewById(R.id.m0608_b013);//  *
        btn14=(Button)findViewById(R.id.m0608_b014);// /
        btn15=(Button)findViewById(R.id.m0608_b015);//=


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
            try {
                n1=Double.parseDouble(num1.getText().toString());
            }catch (Exception e){

           }
            //-----------------
            switch (v.getId()){
                case R.id.m0608_b011:
                    s="P";
                    break;
                case  R.id.m0608_b012:
                    s="M";
                    break;
                case R.id.m0608_b013:
                    s="X";
                    break;
                case  R.id.m0608_b014:
                    s="D";
                    break;
            }
            num1.setText("");
/*            cnt++;
            n3=Double.parseDouble(num1.getText().toString());
            if (cnt>0)
            {
                switch (v.getId()) {
                    case R.id.m0608_b011:
                        n1 += n3;
                        s = "P";
                        break;
                    case R.id.m0608_b012:
                        n1 -= n3;
                        s = "M";
                        break;
                    case R.id.m0608_b013:
                        n1 *= n3;
                        s = "X";
                        break;
                    case R.id.m0608_b014:
                        n1 /= n3;
                        s = "D";
                        break;
                }
            }*/

            }
    };

    private  Button.OnClickListener equalOnClick=new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            if (num1.getText().toString().trim().length()!=0){
                double n2=Double.parseDouble(num1.getText().toString());
                DecimalFormat nf =new DecimalFormat("0.0000");


                if (s.equals("P"))
                    ans.setText(nf.format(n1 + n2));
                if (s.equals("M"))
                    ans.setText(nf.format(n1 - n2));
                if (s.equals("X"))
                    ans.setText(nf.format(n1 * n2));
                if (s.equals("D"))
                    ans.setText(nf.format(n1 / n2));

                num1.setText("");


            }

        }
    };




}
