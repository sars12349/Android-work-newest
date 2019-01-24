package tw.tcnr21.m0607;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class M0503 extends AppCompatActivity {

    private CheckBox chb01,chb02,chb03,chb04;
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
        btn01=(Button)findViewById(R.id.m0503_btn01);
        txt01=(TextView)findViewById(R.id.m0503_txt01);
        //設定button案件的事件
        btn01.setOnClickListener(btn01OnClick);

        Intent intent=this.getIntent();
        String mode_title=intent.getStringExtra("class_title");
        this.setTitle(mode_title);

    }

    private Button.OnClickListener btn01OnClick= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            String ans01=getString(R.string.m0503_txt01);

            if (chb01.isChecked())
                ans01+=chb01.getText().toString();
            if (chb02.isChecked())
                ans01+="\r"+chb02.getText().toString();
            if (chb03.isChecked())
                ans01+="\r"+chb03.getText().toString();
            if (chb04.isChecked())
                ans01+="\r"+chb04.getText().toString();
            txt01.setText(ans01);
        }
    };

}
