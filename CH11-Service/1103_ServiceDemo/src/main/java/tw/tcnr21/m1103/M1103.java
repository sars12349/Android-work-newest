package tw.tcnr21.m1103;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class M1103 extends AppCompatActivity implements View.OnClickListener {

    private Button btnstartservice,btnstopmyservice,btnbindservice,btnunbindservice,btncallservice;
    private Intent it=new Intent();
    private MyService mMyServ=null;
    private String TAG="tcnr21=>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1103);
        setupViewComponent();
    }

    private void setupViewComponent() {
        btnstartservice=(Button)findViewById(R.id.m1103_btnStartMyService);
        btnstopmyservice=(Button)findViewById(R.id.m1103_btnStopMyService);
        btnbindservice=(Button)findViewById(R.id.m1103_btnBindMyService);
        btnunbindservice=(Button)findViewById(R.id.m1103_btnUnbindMyService);
        btncallservice=(Button)findViewById(R.id.m1103_btnCallMyServiceMethod);

        btnstartservice.setOnClickListener(this);
        btnstopmyservice.setOnClickListener(this);
        btnbindservice.setOnClickListener(this);
        btnunbindservice.setOnClickListener(this);
        btncallservice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.m1103_btnStartMyService:
                mMyServ=null;
                it=new Intent(M1103.this,MyService.class);
                startService(it);
                break;
            case R.id.m1103_btnStopMyService:
                mMyServ=null;
                it=new Intent(M1103.this,MyService.class);
                stopService(it);
                break;
            case R.id.m1103_btnBindMyService:
                mMyServ=null;
                it=new Intent(M1103.this,MyService.class);
                bindService(it,mServConn,BIND_AUTO_CREATE);
                break;
            case R.id.m1103_btnUnbindMyService:
                mMyServ=null;
                unbindService(mServConn);
                break;
            case R.id.m1103_btnCallMyServiceMethod:
                if(mMyServ!=null)
                    mMyServ.myMethod();
                break;
        }

    }
    private ServiceConnection mServConn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG,"onServiceConnected()"+name.getClassName());
            mMyServ=((MyService.LocalBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG,"onServiceDisconnected()"+name.getClassName());
        }
    };


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
