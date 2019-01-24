package tw.tcnr21.m0500t;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class M0500 extends AppCompatActivity {

    private EditText e001;
    private Button b001;
    private TextView t003;
    private TextView t005;
    private TextView t007;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0500);
        setupViewComponent();    //設定手機螢幕內容
    }

    private void setupViewComponent() {
        //自定義方法
        e001 = (EditText) findViewById(R.id.m0500_e001); //坪數
        b001 = (Button) findViewById(R.id.m0500_b001); //按鈕
        t003 = (TextView) findViewById(R.id.m0500_t003); //甲
        t005 = (TextView) findViewById(R.id.m0500_t005); //平方公尺
        t007 = (TextView) findViewById(R.id.m0500_t007); //畝
        //--宣告傾聽--
        b001.setOnClickListener(b001ON);
    }

    private Button.OnClickListener b001ON = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            DecimalFormat pondsformat = new DecimalFormat("0.0000");
            String a = pondsformat.format(Float.parseFloat(e001.getText().toString()) * 0.000341);
            String b = pondsformat.format(Float.parseFloat(e001.getText().toString()) * 3.3058);
            String c = pondsformat.format(Float.parseFloat(e001.getText().toString()) * 0.0331);
            t003.setText(a.toString());
            t005.setText(b.toString());
            t007.setText(c.toString());

        }
    };
}
