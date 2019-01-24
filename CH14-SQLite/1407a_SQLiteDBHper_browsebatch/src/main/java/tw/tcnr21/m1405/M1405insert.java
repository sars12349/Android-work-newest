package tw.tcnr21.m1405;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class M1405insert extends AppCompatActivity implements View.OnClickListener{

    private TextView count_t;
    private Button b001, b002, b003, b004;
    private EditText e001, e002, e003, e004;

    private FriendDbHelper dbHper;
    private static final String DB_FILE = "friends.db";
    private static final String DB_TABLE = "member";
    private static final int DBversion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1405_insert);
        setupViewComponent();
        initDB();
    }

    private void setupViewComponent(){
        e001 = (EditText) findViewById(R.id.edtName);
        e002 = (EditText) findViewById(R.id.edtGrp);
        e003 = (EditText) findViewById(R.id.edtAddr);
        count_t = (TextView) findViewById(R.id.count_t);
        b001 = (Button) findViewById(R.id.btnAdd);
        b001.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnAdd:
                String tname = e001.getText().toString().trim();
                String tgrp = e002.getText().toString().trim();
                String taddress = e003.getText().toString().trim();


                if(tname.equals("")||tgrp.equals("")||taddress.equals("")){
                   Toast.makeText(M1405insert.this,"資料空白無新增!",Toast.LENGTH_SHORT).show();
                    return;
                }
                String msg=null;
                long rowID=dbHper.insertRec(tname,tgrp,taddress);
                if (rowID != -1) {
                    e001.setHint("請繼續輸入");
                    e001.setText("");
                    e002.setText("");
                    e003.setText("");

                    msg = "新增記錄  成功 ! \n" + "目前資料表共有 " + dbHper.RecCount() + " 筆記錄 !";
                } else {
                    msg = "新增記錄  失敗 !";
                }
                Toast.makeText(M1405insert.this, msg, Toast.LENGTH_SHORT).show();
                count_t.setText("共計:" + Integer.toString(dbHper.RecCount()) + "筆");
                // 獲取輸入法打開的狀態-------------------------------
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                boolean isOpen = imm.isActive();
                // isOpen若返回true，則表示輸入法打開
                if (isOpen == true) {// 如果輸入法打開則關閉，如果沒打開則打開
                    // InputMethodManager m=(InputMethodManager)
                    // getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }

                break;
        }
    }


    private void initDB() {
        if(dbHper==null)
            dbHper=new FriendDbHelper(this, DB_FILE, null, DBversion);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(dbHper!=null){
            dbHper.close();
            dbHper=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dbHper==null)
            dbHper=new FriendDbHelper(this,DB_FILE,null,DBversion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.m1405sub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.m_return) finish();
        return super.onOptionsItemSelected(item);
    }


}
