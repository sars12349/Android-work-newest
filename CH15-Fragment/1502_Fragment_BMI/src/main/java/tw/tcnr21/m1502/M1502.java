package tw.tcnr21.m1502;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class M1502 extends AppCompatActivity implements BMIFragment.BMIListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1502);
    }
    // 實作BMIListener介面方法
    public void onButtonClick(double bmi) {
        FragmentManager fm = getSupportFragmentManager();
        TextFragment tf = (TextFragment) fm.findFragmentById(R.id.fragment2);
        //  呼叫TextFragment物件的方法
        tf.changeBMIValue(bmi);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
