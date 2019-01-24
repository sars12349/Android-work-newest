package tw.tcnr21.m0902;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class M0902 extends AppCompatActivity implements View.OnClickListener {

    final String[] ListStr = {"張三","李四","王五","陳六","呂七","宋八","如果選擇項目太多","Android也會","自動的可以拖曳喔！～"};
    private Button mBtnAlertDlg,mBtnAlertDlgBld;
    private TextView mTxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0902);
        setupViewComponent();
    }

    private void setupViewComponent() {
        mBtnAlertDlg=(Button)findViewById(R.id.m0902_b001);
        mBtnAlertDlgBld=(Button)findViewById(R.id.m0902_b002);
        mTxtResult=(TextView)findViewById(R.id.m0902_t001);

        mBtnAlertDlg.setOnClickListener(this);
        mBtnAlertDlgBld.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.m0902_b001 :
                mTxtResult.setText("");
                MyAlertDialog myAltDlg=new MyAlertDialog(M0902.this);

                myAltDlg.setTitle(getString(R.string.m0902_title));
                myAltDlg.setMessage(getString(R.string.m0902_t001)+getString(R.string.m0902_b001));

                myAltDlg.setIcon(android.R.drawable.ic_dialog_info);
                myAltDlg.setCancelable(false);
                myAltDlg.setButton(DialogInterface.BUTTON_POSITIVE,getString(R.string.m0902_positive),altDlgOnClkPosiBtnLis);
                myAltDlg.setButton(DialogInterface.BUTTON_NEGATIVE,getString(R.string.m0902_negative),altDlgOnClkNegaBtnLis);
                myAltDlg.setButton(DialogInterface.BUTTON_NEUTRAL,getString(R.string.m0902_neutral),altDlgOnClkNeutBtnLis);

                myAltDlg.show();
                break;
            case R.id.m0902_b002 :
                mTxtResult.setText("");

                AlertDialog.Builder altDlgBldr=new AlertDialog.Builder(M0902.this);

                altDlgBldr.setTitle(getString(R.string.m0902_title));

                altDlgBldr.setIcon(android.R.drawable.ic_dialog_info);
                altDlgBldr.setCancelable(false);

                altDlgBldr.setItems(ListStr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTxtResult.setText(getString(R.string.m0902_t001)+
                        getString(R.string.m0902_b002)+
                        getString(R.string.m0902_click)+" "+
                        ListStr[which]+" "+
                        getString(R.string.m0902_button));
                    }
                });

                altDlgBldr.setNegativeButton(getString(R.string.m0902_negative), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTxtResult.setText(getString(R.string.m0902_t001)+
                                getString(R.string.m0902_b002)+
                                getString(R.string.m0902_click)+" "+
                                getString(R.string.m0902_negative)+" "+
                                getString(R.string.m0902_button));
                    }
                });

                altDlgBldr.setNeutralButton(getString(R.string.m0902_neutral), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTxtResult.setText(getString(R.string.m0902_t001)+
                                getString(R.string.m0902_b002)+
                                getString(R.string.m0902_click)+" "+
                                getString(R.string.m0902_neutral)+" "+
                                getString(R.string.m0902_button));
                    }
                });
                altDlgBldr.show();
                break;
        }
    }

    private DialogInterface.OnClickListener altDlgOnClkPosiBtnLis=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mTxtResult.setText(getString(R.string.m0902_t001)+
                    getString(R.string.m0902_b001)+
                    getString(R.string.m0902_click)+" "+
            getString(R.string.m0902_positive)+" "+
            getString(R.string.m0902_button));
        }
    };

    private DialogInterface.OnClickListener altDlgOnClkNegaBtnLis=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mTxtResult.setText(getString(R.string.m0902_t001)+
                    getString(R.string.m0902_b001)+
                    getString(R.string.m0902_click)+" "+
                    getString(R.string.m0902_negative)+" "+
                    getString(R.string.m0902_button));
        }
    };

    private DialogInterface.OnClickListener altDlgOnClkNeutBtnLis=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mTxtResult.setText(getString(R.string.m0902_t001)+
                    getString(R.string.m0902_b001)+
                    getString(R.string.m0902_click)+" "+
                    getString(R.string.m0902_neutral)+" "+
                    getString(R.string.m0902_button));
        }
    };


}


