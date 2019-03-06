package tw.tcnr21.m1801;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tw.tcnr21.tcnrlibrary.Tcnrlibrary;

public class M1801 extends AppCompatActivity implements View.OnClickListener {

    private TextView t002,ans01;
    private Button b001;
    private EditText e001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1801);
        setupViewComponent();
    }

    private void setupViewComponent() {
        t002 = (TextView) findViewById(R.id.m1801_t002);
        b001 = (Button) findViewById(R.id.m1801_b001);
        e001 = (EditText) findViewById(R.id.m1801_e001);
        ans01= (TextView)findViewById(R.id.m1801_ans);
        b001.setOnClickListener( this);
    }

    @Override
    public void onClick(View v) {
        Tcnrlibrary u_library=new Tcnrlibrary(); //使用自建library
        String message=u_library.tc_getMessage();
        t002.setText(message);
//
        String ans=u_library.tc_numToCht(e001.getText().toString());
        ans01.setText(ans);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //------------------------------------------
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
