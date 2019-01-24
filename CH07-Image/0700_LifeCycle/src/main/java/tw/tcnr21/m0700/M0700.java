package tw.tcnr21.m0700;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class M0700 extends AppCompatActivity {

    private String TAG="tcnr21=>";

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate()執行");
        setContentView(R.layout.m0700);
        setupViewComponent();
    }

    private void setupViewComponent() {
        Log.d(TAG,"setuoViewComponent()");
    }
}
