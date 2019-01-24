package tw.tcnr21.m0905;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class M0905 extends AppCompatActivity implements View.OnClickListener {

    private Button b001;
    private TextView ans01;
    private Dialog logindlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0905);
        setupVieComponent();
    }

    private void setupVieComponent() {
        b001=(Button)findViewById(R.id.m0905_b001);
        ans01=(TextView)findViewById(R.id.m0905_ans);

        b001.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ans01.setText("");

        logindlg=new Dialog(M0905.this);
        logindlg.setTitle(getString(R.string.login));
        logindlg.setCancelable(false);
        logindlg.setContentView(R.layout.login_dlg);
        Button loginbtnok=(Button)logindlg.findViewById(R.id.m0905_btnOK);
        Button loginbtncancel=(Button)logindlg.findViewById(R.id.m0905_btnCancel);
        loginbtnok.setOnClickListener(loginbtn);
        loginbtncancel.setOnClickListener(loginbtn);
        logindlg.show();

    }

    private Button.OnClickListener loginbtn= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.m0905_btnOK:
                    EditText edtusername=(EditText)logindlg.findViewById(R.id.edtUserName);
                    EditText edtpassword=(EditText)logindlg.findViewById(R.id.edtPassword);

                    ans01.setText(getString(R.string.m0905_ans)+
                    getString(R.string.m0905_t001)+
                    edtusername.getText().toString()+" "+
                    getString(R.string.m0905_t002)+
                    edtpassword.getText().toString());
                    logindlg.cancel();
                    break;
                case R.id.m0905_btnCancel:
                    ans01.setText(getString(R.string.m0905_ans)+getString(R.string.m0905_btnCancel));
                    logindlg.cancel();
                    break;
            }



        }
    };
}
