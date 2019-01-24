package tw.tcnr21.m0901;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class M0901 extends AppCompatActivity {

    private Button btntimepicdlg,btndatepicdlg;
    private TextView txtresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0901);
        setupViewComponent();
    }

    private void setupViewComponent() {
        btntimepicdlg=(Button)findViewById(R.id.m0901_b001);
        btndatepicdlg=(Button)findViewById(R.id.m0901_b002);
        txtresult=(TextView)findViewById(R.id.m0901_t001);

        btndatepicdlg.setOnClickListener(btnonclklis);
        btntimepicdlg.setOnClickListener(btnonclklis);

    }

    private Button.OnClickListener btnonclklis= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            txtresult.setText("");
            Calendar now=Calendar.getInstance();

            switch (v.getId()){
                case R.id.m0901_b001:
                    DatePickerDialog datePicDlg =new DatePickerDialog(
                            M0901.this,
                            datePicDlgOnDateSelLis,
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH),
                            now.get(Calendar.DAY_OF_MONTH)
                    );
                    datePicDlg.setTitle(getString(R.string.m0901_datetitle));
                    datePicDlg.setMessage(getString(R.string.m0901_datemessage));
                    datePicDlg.setIcon(android.R.drawable.ic_dialog_info);
                    datePicDlg.setCancelable(false);  //禁止按螢幕跳出
                    datePicDlg.show();
                    break;
                case R.id.m0901_b002:
                    TimePickerDialog timePicDlg=new TimePickerDialog(M0901.this,
                            timePicDlgOnTimeSelLis,
                            now.get(Calendar.HOUR_OF_DAY),
                            now.get(Calendar.MINUTE),true);
                    timePicDlg.setTitle(getString(R.string.m0901_timetitle));
                    timePicDlg.setMessage(getString(R.string.m0901_timemessage));
                    timePicDlg.setIcon(android.R.drawable.ic_dialog_info);
                    timePicDlg.setCancelable(false);
                    timePicDlg.show();
                    break;
            }

        }
    };

    private DatePickerDialog.OnDateSetListener datePicDlgOnDateSelLis=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            txtresult.setText(getString(R.string.m0901_datetitle)+
                    Integer.toString(year) + getString(R.string.n_yy)+
                    Integer.toString(month + 1) + getString(R.string.n_mm) +
                    Integer.toString(dayOfMonth) + getString(R.string.n_dd));

        }
    };

    private TimePickerDialog.OnTimeSetListener timePicDlgOnTimeSelLis =new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            txtresult.setText(getString(R.string.m0901_timetitle)+Integer.toString(hourOfDay)
            +getString(R.string.d_hh)+Integer.toString(minute)+getString(R.string.d_mm));

        }
    };
}
