package tw.tcnr21.m0801;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class M0801 extends AppCompatActivity implements View.OnClickListener {

    private DatePicker mDatePik;
    private TextView mTxtResult;
    private Button mBtnOK;
    private TimePicker mTimePik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0801);
        setupViewComponent();
    }

    private void setupViewComponent() {
        mDatePik = (DatePicker) findViewById(R.id.m0801_date01);
        mTxtResult = (TextView)findViewById(R.id.m0801_t001);
        mBtnOK = (Button)findViewById(R.id.m0801_b001);
        mBtnOK.setOnClickListener(this);
        mTimePik=(TimePicker)findViewById(R.id.m0801_time01);

    }


    @Override
    public void onClick(View v) {
        String s = getString(R.string.result);
        mTxtResult.setText(s + mDatePik.getYear() + getString(R.string.n_yy) +
                (mDatePik.getMonth()+1) + getString(R.string.n_mm) +
                mDatePik.getDayOfMonth() +getString(R.string.m_dd) + mTimePik.getCurrentHour() +
                mTimePik.getCurrentHour() + getString(R.string.d_hh) +
                mTimePik.getCurrentMinute() + getString(R.string.d_mm)
        );
    }
}
