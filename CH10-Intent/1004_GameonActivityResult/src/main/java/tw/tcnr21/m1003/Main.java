package tw.tcnr21.m1003;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    private  final int LAUNCH_GAME =0 ;
    private Button mBtnLaunchAct;
    private TextView mTxtResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupViewComponent();
    }

    private void setupViewComponent() {
        mBtnLaunchAct = (Button)findViewById(R.id.btnLaunchAct);
        mTxtResult = (TextView)findViewById(R.id.txtResult);
        mBtnLaunchAct.setOnClickListener(btnLaunchActOnClickLis);
    }

    private Button.OnClickListener btnLaunchActOnClickLis= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = new Intent();
            it.setClass(Main.this, M1003.class);
            startActivityForResult(it,LAUNCH_GAME);
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != LAUNCH_GAME) return;
        switch (resultCode) {
            case RESULT_OK:
                Bundle bundle = data.getExtras();
                int iCountSet = bundle.getInt("KEY_COUNT_SET");
                int iCountPlayerWin = bundle.getInt("KEY_COUNT_PLAYER_WIN");
                int iCountComWin = bundle.getInt("KEY_COUNT_COM_WIN");
                int iCountDraw = bundle.getInt("KEY_COUNT_DRAW");

                String s =getString(R.string.m1004_result) + iCountSet +getString(R.string.m1004_table)+" "+
                        getString(R.string.m1004_PlayerWin) + iCountPlayerWin +getString(R.string.m1004_table)+" "+
                        getString(R.string.m1004_comWin)+ iCountComWin +getString(R.string.m1004_table)+" "+
                        getString(R.string.m1004_draw)+ iCountDraw +getString(R.string.m1004_table);
                mTxtResult.setText(s);
                break;
            case RESULT_CANCELED:
                mTxtResult.setText("你選擇取消遊戲。");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menumain,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mainexit :
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
