package tw.tcnr21.m1106;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class M1106 extends AppCompatActivity implements View.OnTouchListener {

    private Spinner s001;
    private TextView txv;
    private ImageButton imgb001;
    private Vibrator myVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1106);
        setupViewComponent();
    }

    private void setupViewComponent() {
        s001 = (Spinner) findViewById(R.id.m1106_s001);
        txv = (TextView) findViewById(R.id.textView1);
        imgb001 = (ImageButton) findViewById(R.id.m1106_imb001);

        //宣告震動
        myVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);

        ArrayAdapter<CharSequence> adapSexList = ArrayAdapter.createFromResource(this, R.array.m1106_a001,
                android.R.layout.simple_spinner_item);
        adapSexList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s001.setAdapter(adapSexList);
        s001.setOnItemSelectedListener(spnon);
        imgb001.setOnTouchListener(this); // 登錄觸控監聽物件

    }

    private Spinner.OnItemSelectedListener spnon = new Spinner.OnItemSelectedListener() {
        public void onItemSelected(AdapterView parent, View v, int position, long id) {
            myVibrator.cancel();
            switch (position) {
                case 0:         // 停止震動
                    myVibrator.cancel();
                    break;

                case 1:         // 震動3秒
                    myVibrator.vibrate(3000);
                    break;

                case 2:        // 震動0.1秒
                    myVibrator.vibrate(100);
                    break;

                case 3:       // 停0.5秒之後震動1秒三遍
                    myVibrator.vibrate(new long[]{500, 1000, 500, 1000, 500, 1000}, -1);
                    // 這個是一個間歇性震動的方法，
                    // 第一個參數是一個long類型的數組（毫秒），
                    // 陣列單數（雙數角標）為等待時間，雙數（單數角標）為震動時間。
                    break;

                case 4:       // 停0.5秒震動1秒(持續重覆)
                    myVibrator.vibrate(new long[]{500, 1000}, 0);
                    break;

                case 5:       // 停0.5秒震動1秒(重覆5次)
                    for (int i = 0; i < 5; i++) { // repeat the pattern 5 times
                        myVibrator.vibrate(new long[]{500, 1000}, -1);
                        try {
                            Thread.sleep(500); // the time, the complete pattern
                            // needs
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            myVibrator.cancel();
                        }
                    }
                    break;
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    };

    @Override  /* 參數v是事件來源物件, e是儲存有觸控資訊的物件 */
    public boolean onTouch(View v, MotionEvent e) // 實作onTouchListener觸控監聽器介面的方法
    {
        Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (e.getAction() == MotionEvent.ACTION_DOWN) { // 按下螢幕中間的文字
            vb.vibrate(1000); // 震動五秒
        } else if (e.getAction() == MotionEvent.ACTION_UP) { // 放開螢幕中間的文字
            vb.cancel(); // 停止震動
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        if (myVibrator != null)
            myVibrator.cancel();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
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
