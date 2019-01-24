package tw.tcnr21.m0500e;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.Format;

public class M0500 extends AppCompatActivity {

    private EditText e001;
    private Button b001;
    private TextView t003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0500);
        setupViewComponent();    //設定手機螢幕內容
    }

    private void setupViewComponent() {
    //自定義方法
        e001=(EditText)findViewById(R.id.m0500_e001); //美金
        b001=(Button)findViewById(R.id.m0500_b001); //按鈕
        t003=(TextView)findViewById(R.id.m0500_t003); //台幣
        //--宣告傾聽--
        b001.setOnClickListener(b001ON);
    }

    private Button.OnClickListener b001ON=new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            DecimalFormat pondsformat = new DecimalFormat("0.0000");
            String outcome =pondsformat.format(Float.parseFloat(e001.getText().toString())*30.92);
            t003.setText(outcome.toString());

        }
    };
}
