package tw.tcnr21.m0803;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class M0803 extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar proBar;
    private Handler mHandler=new Handler();
    private Button b001;
    private ProgressBar p2,p3,p4;
    private DoLengthyWork work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0803);
        setupViewComponent();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        work=null;
        this.finish();
    }

    private void setupViewComponent() {
        proBar=(ProgressBar)findViewById(R.id.m0803_p001);
        //proBar.getProgressDrawable().setColorFilter(
               //Color.RED,PorterDuff.Mode.SRC_IN );
        p2=(ProgressBar)findViewById(R.id.m0803_p002);
        p3=(ProgressBar)findViewById(R.id.m0803_p003);
        p4=(ProgressBar)findViewById(R.id.m0803_p004);

        b001=(Button)findViewById(R.id.m0603_b001);
        b001.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        p2.setVisibility(View.VISIBLE);
        p3.setVisibility(View.VISIBLE);
        p4.setVisibility(View.VISIBLE);

        DoLengthyWork work=new DoLengthyWork();
        work.setHandler(mHandler);
        work.setProgressBar(proBar,p2,p3,p4);
            work.setFlag(0);
            work.start();
            work.checkAccess();


    }
}
