package tw.tcnr14.m0803;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class M0803 extends AppCompatActivity implements View.OnClickListener,DoLengthyWork.IProgressPresenter {

    private ProgressBar p001,p002,p003,p004;
    private Button b001;
    private DoLengthyWork work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0803);
        setupViewComponent();
    }

    private void setupViewComponent() {

        p001 = findViewById(R.id.m0803_p001);
        p002 = findViewById(R.id.m0803_p002);
        p003 = findViewById(R.id.m0803_p003);
        p004 = findViewById(R.id.m0803_p004);
        b001 = findViewById(R.id.m0803_b001);

        b001.setOnClickListener(this);
        p001.setProgress(0);
        p001.setSecondaryProgress(0);
        //設定顏色
//        p001.getProgressDrawable().setColorFilter(
//                Color.RED,PorterDuff.Mode.SRC_IN
//        );
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.m0803_b001) {
            if(work!=null && work.isRunning()) {
                work.end();
            }
            else {
                b001.setText(R.string.m0803_b001_status_progress);
                work = new DoLengthyWork(this);
                work.start();
                work.checkAccess();

                p002.setVisibility(View.VISIBLE);
                p003.setVisibility(View.VISIBLE);
                p004.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void updateProgress(final int progress, final int secondProgress) {
                p001.setProgress(progress);
                p001.setSecondaryProgress(secondProgress);
    }

    @Override
    public void finish() {
                b001.setText(R.string.m0803_b001_status_stop);
                p002.setVisibility(View.INVISIBLE);
                p003.setVisibility(View.INVISIBLE);
                p004.setVisibility(View.INVISIBLE);
    }
}
