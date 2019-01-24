package tw.tcnr21.m0806;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.navdrawer.SimpleSideDrawer;

public class M0806 extends AppCompatActivity {

    private long startTime;
    private SimpleSideDrawer mNav;
    private long spenttime;
    private TextView lt001,lt002,lt003,rt001,rt002,rt003;
    private Button lmb001,rmb001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0806);

        //取得目前手機時間
        startTime=System.currentTimeMillis();

        mNav=new SimpleSideDrawer(this);
        //設定開啟左選單
        findViewById(R.id.b001).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNav.toggleLeftDrawer();
            }
        });

        //設定開啟左側選單  點兩下
        mNav.setLeftBehindContentView(R.layout.leftmenu);
        findViewById(R.id.rl01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spenttime=System.currentTimeMillis()-startTime;

                if (spenttime<1000){
                    mNav.toggleLeftDrawer();
                }else {
                    startTime=System.currentTimeMillis();
                }
            }
        });


        //設定開啟右選單
        findViewById(R.id.b002).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNav.toggleRightDrawer();
            }
        });

        //螢幕長按開右邊選單
        mNav.setRightBehindContentView(R.layout.rightmenu);
        findViewById(R.id.rl01).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mNav.toggleRightDrawer();
                return false;
            }
        });

        setupViewComponent();

    }

    private void setupViewComponent() {
        lt001 = (TextView) findViewById(R.id.lt001);
        lt002 = (TextView) findViewById(R.id.lt002);
        lt003 = (TextView) findViewById(R.id.lt003);
        lmb001 = (Button) findViewById(R.id.lmb001);

        rt001 = (TextView) findViewById(R.id.rt001);
        rt002 = (TextView) findViewById(R.id.rt002);
        rt003 = (TextView) findViewById(R.id.rt003);
        rmb001 = (Button) findViewById(R.id.rmb001);
        // 設定 button 按鍵的事件
        lt001.setOnClickListener(OnClick);
        lt002.setOnClickListener(OnClick);
        lt003.setOnClickListener(OnClick);
        lmb001.setOnClickListener(OnClick); // 左側邊欄按鈕監聽
        rt001.setOnClickListener(OnClick);
        rt002.setOnClickListener(OnClick);
        rt003.setOnClickListener(OnClick);
        rmb001.setOnClickListener(OnClick); // 右側邊欄按鈕監聽
    }

private Button.OnClickListener OnClick=new Button.OnClickListener(){

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lt001:
                mNav.closeLeftSide();
                Toast.makeText(getApplicationContext(),getString(R.string.lt001),Toast.LENGTH_LONG).show();
                break;
            case R.id.lt002:
                mNav.closeLeftSide();
                Toast.makeText(getApplicationContext(),getString(R.string.lt002),Toast.LENGTH_LONG).show();
                break;
            case R.id.lt003:
                mNav.closeLeftSide();
                Toast.makeText(getApplicationContext(),getString(R.string.lt003),Toast.LENGTH_LONG).show();
                break;
            case R.id.lmb001:
                Uri uri=Uri.parse("http://developer.android.com/");
                Intent it=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(it);
                mNav.closeLeftSide();
                Toast.makeText(getApplicationContext(),getString(R.string.lb001),Toast.LENGTH_LONG).show();
                break;
            case R.id.rt001:
                mNav.closeRightSide();
                Toast.makeText(getApplicationContext(),getString(R.string.rt001),Toast.LENGTH_LONG).show();
                break;
            case R.id.rt002:
                mNav.closeRightSide();
                Toast.makeText(getApplicationContext(),getString(R.string.rt002),Toast.LENGTH_LONG).show();
                break;
            case R.id.rt003:
                mNav.closeRightSide();
                Toast.makeText(getApplicationContext(),getString(R.string.rt003),Toast.LENGTH_LONG).show();
                break;
            case R.id.rmb001:
                mNav.closeLeftSide();
                Toast.makeText(getApplicationContext(), getString(R.string.rb001), Toast.LENGTH_LONG).show();
                break;

            default:
                break;



        }

    }
} ;
}
