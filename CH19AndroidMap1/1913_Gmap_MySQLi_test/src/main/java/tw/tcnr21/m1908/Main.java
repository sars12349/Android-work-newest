package tw.tcnr21.m1908;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Main extends AppCompatActivity {
    private String TAG="tcnr21=>";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }

    private void setupViewComponent() {
    }

    //-----------------
    // Button元件的事件處理
    public void btn_start_Click(View view) {
        Intent it = new Intent();
        it.setClass(Main.this, M1908.class);
        startActivity(it);
    }
    // Button元件的事件處理
    public void btn_sql_Click(View view) {
        Intent it = new Intent();
        it.setClass(Main.this, M1901select.class);
        startActivity(it);
    }
    //====================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_finish:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
