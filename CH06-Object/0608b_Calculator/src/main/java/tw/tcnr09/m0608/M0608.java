package tw.tcnr09.m0608;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class M0608 extends AppCompatActivity {


    private TextView num1;
    private TextView ans;
    private double n1 = 0.0;
    private String s;
    private double n2 = 0.0;
    private double sum = 0.0;
    private int flag = 0;
    private Button btnClr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0608);
        setComponent();

    }

    private void setComponent() {

        num1 = (TextView) findViewById(R.id.m0608_t001);
        num1.setText("");
        ans = (TextView) findViewById(R.id.m0608_t002);

        //get  Btn ID
        for (int i = 0; i < 16; i++) {
            //compo%0  2 number
            String idName = "m0608_b0" + String.format("%02d", i);
            //public int getIdentifier(String name,
            //                                             String defType,
            //                                             String defPackage)
            int resID = getResources().getIdentifier(idName, "id", getPackageName());

            Button btn = (Button) findViewById(resID);
            if (i < 11) btn.setOnClickListener(numberOnClick);
            if (i > 10 && i < 15) btn.setOnClickListener(calOnClick);
            if (i > 14) btn.setOnClickListener(equalOnClick);
        }


        btnClr = (Button) findViewById(R.id.btn_clr);
        btnClr.setOnClickListener(clearOnClick);


    }

    private Button.OnClickListener numberOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            flag = 1;//按數字鍵
            String keyin = num1.getText().toString();

            switch (v.getId()) {
                case R.id.m0608_b000:
                    num1.setText(keyin + "0");
                    break;
                case R.id.m0608_b001:
                    num1.setText(keyin + "1");
                    break;
                case R.id.m0608_b002:
                    num1.setText(keyin + "2");
                    break;
                case R.id.m0608_b003:
                    num1.setText(keyin + "3");
                    break;
                case R.id.m0608_b004:
                    num1.setText(keyin + "4");
                    break;
                case R.id.m0608_b005:
                    num1.setText(keyin + "5");
                    break;
                case R.id.m0608_b006:
                    num1.setText(keyin + "6");
                    break;
                case R.id.m0608_b007:
                    num1.setText(keyin + "7");
                    break;
                case R.id.m0608_b008:
                    num1.setText(keyin + "8");
                    break;
                case R.id.m0608_b009:
                    num1.setText(keyin + "9");
                    break;
                case R.id.m0608_b010:
                    num1.setText(keyin + ".");
                    break;
            }
        }
    };


    private Button.OnClickListener calOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            flag = 2; //按運算鍵
            //keyin string to number
            try {
                if (num1.getText().length() == 0) n1 = 0.0;
                n1 = Double.parseDouble(num1.getText().toString());
                if (ans.getText().length() == 0) sum = 0.0;
                sum = Double.parseDouble(ans.getText().toString());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }


            switch (v.getId()) {
                case R.id.m0608_b011:
                    s = "P";
                    sum += n1;

                    break;
                case R.id.m0608_b012:
                    s = "M";
                    sum -= n1;
                    break;
                case R.id.m0608_b013:
                    if (sum == 0) sum = 1;
                    s = "X";
                    sum *= n1;
                    break;
                case R.id.m0608_b014:
                    s = "D";
                    if (sum != 0.0 && n1 != 0.0) {
                        sum /= n1;
                    } else if (sum == 0.0 && n1 != 0.0) {
                        sum = n1;
                    }
                    break;

            }

            //click
            ans.setText("" + sum);

            //clear
            num1.setText("");
            sum = 0.0;
            n2 = 0.0;


        }
    };


    private Button.OnClickListener equalOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {


            if (num1.getText().toString().trim().length() != 0) {
                n2 = Double.parseDouble(num1.getText().toString());

                DecimalFormat df = new DecimalFormat("0.0000");

                if (ans.getText().length() != 0) {
                    sum = Double.parseDouble(ans.getText().toString());

                } else {
                    sum = 0.0;
                }

                if(flag == 2){
                    switch (s) {
                        case "P":
                            ans.setText(df.format(sum + n2));
                            break;

                        case "M":
                            ans.setText(df.format(sum - n2));
                            break;
                        case "X":
                            ans.setText(df.format(sum * n2));
                            break;
                        case "D":
                            ans.setText(df.format(sum / n2));
                            break;


                    }

                }
                else if( flag == 1) {
                    ans.setText(num1.getText().toString());


                }

            }
            //clear
            num1.setText("");

            n1 = 0.0;
            n2 = 0.0;

        }
    };

    private Button.OnClickListener clearOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            num1.setText("");
            ans.setText("");

            sum = 0.0;
            n1 = 0.0;
            n2 = 0.0;
        }
    };


}
