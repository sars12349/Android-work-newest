package tw.tcnr21.m1101;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class M1101 extends AppCompatActivity implements View.OnClickListener {

    private Button b001,b002,b003,b004;
    private MyBroadcastReceiver1 mMyReceiver1;
    private Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1101);
        setupViewComponent();
    }

    private void setupViewComponent() {
        b001=(Button)findViewById(R.id.m1101_b001);
        b002=(Button)findViewById(R.id.m1101_b002);
        b003=(Button)findViewById(R.id.m1101_b003);
        b004=(Button)findViewById(R.id.m1101_b004);

        b001.setOnClickListener(this);
        b002.setOnClickListener(this);
        b003.setOnClickListener(this);
        b004.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.m1101_b001:
                IntentFilter itfilter=new IntentFilter("MY_BROADCAST1");
                mMyReceiver1=new MyBroadcastReceiver1();
                registerReceiver(mMyReceiver1,itfilter);
                break;
            case R.id.m1101_b002:
                try{
                    unregisterReceiver(mMyReceiver1);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),getString(R.string.m1101_msg02),
                            Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.m1101_b003:
                it=new Intent("MY_BROADCAST1");
                it.putExtra("sender_name",getString(R.string.m1101_msg03));
                sendBroadcast(it);
            break;
            case R.id.m1101_b004:
                it=new Intent("MY_BROADCAST2");
                it.putExtra("sender_name",getString(R.string.m1101_msg04));
                sendBroadcast(it);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
