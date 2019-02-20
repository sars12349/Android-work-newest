package tw.tcnr21.m1506;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class M1506 extends AppCompatActivity {
    //   snackbar 簡短的消息
//   出現屏幕上的所有其他元素和只有一個可以一次被顯示。
//    超時後或在屏幕上的其他用戶互動後自動消失，特別是召喚一種新的表面或活動的相互作用後。
    private Toolbar mToolbar;
    private CoordinatorLayout coordinatorLayout;
    private Button btnSimpleSnackbar, btnActionCallback, btnCustomView;
    private FloatingActionButton fab;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1506);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        initFab();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        btnSimpleSnackbar = (Button) findViewById(R.id.btnSimpleSnackbar);
        btnActionCallback = (Button) findViewById(R.id.btnActionCallback);
        btnCustomView = (Button) findViewById(R.id.btnCustomSnackbar);

        btnSimpleSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar = Snackbar
                        .make(coordinatorLayout, "Snackbar ", Snackbar.LENGTH_LONG);

                snackbar.show();
            }
        });

        btnActionCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "顯示訊息刪除", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "顯示訊息重新啟動!", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });

                snackbar.show();
            }
        });

        btnCustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "自定義顏色", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar2 = Snackbar.make(coordinatorLayout, "顯示訊息重新啟動!", Snackbar.LENGTH_SHORT);
                                snackbar2.show();
                            }
                        });

                snackbar.show();
            }
        });

    }

    private void initFab() {
        FloatingActionButton mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "FAB 浮動按鈕啟動快速訊息", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                Toast.makeText(M1506.this, "FAB onClick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; 增加items到action bar.
        getMenuInflater().inflate(R.menu.menu_m1506, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}