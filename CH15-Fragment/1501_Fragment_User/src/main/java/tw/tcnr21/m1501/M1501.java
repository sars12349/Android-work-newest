package tw.tcnr21.m1501;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;

public class M1501 extends AppCompatActivity implements View.OnClickListener {

    SecondFragment sf;
    private Button btn01,btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1501);
        // 在FrameLayout新增Fragment片段
        FirstFragment ff = new FirstFragment(); // 建立片段物件
        // 取得FragmentManager物件
        FragmentManager fm = getSupportFragmentManager();
        // 開始執行片段管理的交易
        FragmentTransaction trans = fm.beginTransaction();
        trans.add(R.id.frame, ff);   // 新增片段
        trans.commit();              // 確認交易

        btn01=(Button)findViewById(R.id.button);
        btn02=(Button)findViewById(R.id.button2);

        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:
                // 取代成第2個片段
                sf = SecondFragment.newInstance("改成第二個Fragment片段");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, sf)
                        .commit();
                break;
            case R.id.button2:
                // 刪除第2個片段
                getSupportFragmentManager().beginTransaction()
                        .remove(sf)
                        .commit();
                break;
        }
    }
}
