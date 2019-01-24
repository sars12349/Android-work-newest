package tw.tcnr21.m0503;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class M0503 extends AppCompatActivity {

    private CheckBox chb01,chb02,chb03,chb04,chb05,chb06,chb07,chb08,chb09,chb10,chb11,chb12,chb13,chb14,chb15,chb16,chb17,chb18,chb19,chb20;
    private Button btn01;
    private TextView txt01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0503);
        setupViewcomponent();
    }

    private void setupViewcomponent() {
        chb01=(CheckBox)findViewById(R.id.m0503_chb01);
        chb02=(CheckBox)findViewById(R.id.m0503_chb02);
        chb03=(CheckBox)findViewById(R.id.m0503_chb03);
        chb04=(CheckBox)findViewById(R.id.m0503_chb04);
        chb05=(CheckBox)findViewById(R.id.m0503_chb05);
        chb06=(CheckBox)findViewById(R.id.m0503_chb06);
        chb07=(CheckBox)findViewById(R.id.m0503_chb07);
        chb08=(CheckBox)findViewById(R.id.m0503_chb08);
        chb09=(CheckBox)findViewById(R.id.m0503_chb09);
        chb10=(CheckBox)findViewById(R.id.m0503_chb10);
        chb11=(CheckBox)findViewById(R.id.m0503_chb11);
        chb12=(CheckBox)findViewById(R.id.m0503_chb12);
        chb13=(CheckBox)findViewById(R.id.m0503_chb13);
        chb14=(CheckBox)findViewById(R.id.m0503_chb14);
        chb15=(CheckBox)findViewById(R.id.m0503_chb15);
        chb16=(CheckBox)findViewById(R.id.m0503_chb16);
        chb17=(CheckBox)findViewById(R.id.m0503_chb17);
        chb18=(CheckBox)findViewById(R.id.m0503_chb18);
        chb19=(CheckBox)findViewById(R.id.m0503_chb19);
        chb20=(CheckBox)findViewById(R.id.m0503_chb20);
        btn01=(Button)findViewById(R.id.m0503_btn01);
        txt01=(TextView)findViewById(R.id.m0503_txt01);
        //設定button案件的事件
        btn01.setOnClickListener(btn01OnClick);

    }

    private Button.OnClickListener btn01OnClick= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            String ans01=getString(R.string.m0503_txt01);

            if (chb01.isChecked())
                ans01+="\r"+chb01.getText().toString();
            if (chb02.isChecked())
                ans01+="\r"+chb02.getText().toString();
            if (chb03.isChecked())
                ans01+="\r"+chb03.getText().toString();
            if (chb04.isChecked())
                ans01+="\r"+chb04.getText().toString();
            if (chb05.isChecked())
                ans01+="\r"+chb05.getText().toString();
            if (chb06.isChecked())
                ans01+="\r"+chb06.getText().toString();
            if (chb07.isChecked())
                ans01+="\r"+chb07.getText().toString();
            if (chb08.isChecked())
                ans01+="\r"+chb08.getText().toString();
            if (chb09.isChecked())
                ans01+="\r"+chb09.getText().toString();
            if (chb10.isChecked())
                ans01+="\r"+chb10.getText().toString();
            if (chb11.isChecked())
                ans01+="\r"+chb11.getText().toString();
            if (chb12.isChecked())
                ans01+="\r"+chb12.getText().toString();
            if (chb13.isChecked())
                ans01+="\r"+chb13.getText().toString();
            if (chb14.isChecked())
                ans01+="\r"+chb14.getText().toString();
            if (chb15.isChecked())
                ans01+="\r"+chb15.getText().toString();
            if (chb16.isChecked())
                ans01+="\r"+chb16.getText().toString();
            if (chb17.isChecked())
                ans01+="\r"+chb17.getText().toString();
            if (chb18.isChecked())
                ans01+="\r"+chb18.getText().toString();
            if (chb19.isChecked())
                ans01+="\r"+chb19.getText().toString();
            if (chb20.isChecked())
                ans01+="\r"+chb20.getText().toString();
            txt01.setText(ans01);
        }
    };

}
