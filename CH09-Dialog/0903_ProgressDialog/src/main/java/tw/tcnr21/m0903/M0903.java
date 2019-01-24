package tw.tcnr21.m0903;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class M0903 extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnProgDlg;
    private Handler mHandler =new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0903);
        setupViewComponent();
    }

    private void setupViewComponent() {
        mBtnProgDlg=(Button)findViewById(R.id.m0903_b001);
        mBtnProgDlg.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        final ProgressDialog progDlg =new ProgressDialog(M0903.this);
        progDlg.setTitle(getString(R.string.m0903_title));
        progDlg.setMessage(getString(R.string.m0903_title));
        progDlg.setIcon(android.R.drawable.ic_dialog_alert);
        progDlg.setCancelable(false);
        progDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progDlg.setMax(100);
        progDlg.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Calendar begin=Calendar.getInstance();

                do{
                    Calendar now=Calendar.getInstance();

                    final int iDiffSec=60*(now.get(Calendar.MINUTE)-begin.get(Calendar.MINUTE))+
                            now.get(Calendar.SECOND)-begin.get(Calendar.SECOND);
                    int lastSec = 0;
                    if (lastSec != iDiffSec) {
                        lastSec = iDiffSec;

                        if (iDiffSec * 10 > 100) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progDlg.setProgress(100);
                                }
                            });
                            break;
                        }

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                progDlg.setProgress(iDiffSec * 10);
                            }
                        });
                        if (iDiffSec * 10 + 11 < 100)
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progDlg.setSecondaryProgress(iDiffSec * 10 + 11);
                                }
                            });
                        else
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progDlg.setSecondaryProgress(100);
                                }
                            });
                    }
                }while (true);
                progDlg.cancel();
            }
        }).start();
    }
}
