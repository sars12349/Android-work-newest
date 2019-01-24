package tw.tcnr21.m1405;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class M1405query extends AppCompatActivity implements View.OnClickListener {
    private TextView count_t;
    private Button b001, b002, b003, b004;
    private EditText e001, e002, e003, e004;

    private FriendDbHelper dbHper;
    private static final String DB_FILE = "friends.db";
    private static final String DB_TABLE = "member";
    private static final int DBversion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1405_query);
        setupViewComponent();
        initDB();
        count_t.setText("共計:" + Integer.toString(dbHper.RecCount()) + "筆");
    }


    private void setupViewComponent() {
        e001 = (EditText) findViewById(R.id.edtName);
        e002 = (EditText) findViewById(R.id.edtGrp);
        e003 = (EditText) findViewById(R.id.edtAddr);
        count_t = (TextView) findViewById(R.id.count_t);
        b002 = (Button) findViewById(R.id.btnquery);
        b002.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String result = null;
        // 查詢name跟在e001上打得是否有有此筆資料
        String tname = e001.getText().toString().trim();
        if (tname.length() != 0)
        {
            String rec = dbHper.FindRec(tname);
            if (rec != null)
            {
                result = "找到的資料為 ：\n" + rec;
            } else
            {
                result = "找不到指定的編號：" + tname;
            }
            Toast.makeText(M1405query.this, result, Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void initDB() {
        if (dbHper == null)
            dbHper = new FriendDbHelper(this, DB_FILE, null, DBversion);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dbHper != null)
        {
            dbHper.close();
            dbHper = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbHper == null)
            dbHper = new FriendDbHelper(this, DB_FILE, null, DBversion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.m1405sub, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.m_return) finish();
        return true;
    }
}
