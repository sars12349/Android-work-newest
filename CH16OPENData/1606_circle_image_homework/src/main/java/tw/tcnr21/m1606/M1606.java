package tw.tcnr21.m1606;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tw.tcnr21.tcnrlibrary.Tcnrlibrary;

public class M1606 extends AppCompatActivity implements View.OnClickListener {

    private Button b001;
    private EditText e001;
    private TextView ans01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1606);
        setupViewComponent();
    }

    private void setupViewComponent() {
        b001 = (Button) findViewById(R.id.m1606_b001);
        e001 = (EditText) findViewById(R.id.m1606_e001);
        ans01= (TextView)findViewById(R.id.m1606_ans);
        b001.setOnClickListener( this);
    }

    @Override
    public void onClick(View v) {
        Tcnrlibrary u_library = new Tcnrlibrary();
        String ans ="";

//        int val = Integer.valueOf(e001.getText().toString());
        String stmp = e001.getText().toString();
        char[] num_arr = stmp.toCharArray();
        for(int i = 0 ; i<num_arr.length; i++){

            int val =  num_arr[i] - '0';
            ans += u_library.tc_chinayear(val);

        }


        ans01.setText(ans);
    }
}
