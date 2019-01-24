package tw.tcnr21.m0607;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class M0501 extends AppCompatActivity {
    //宣告變數
    private Button b001;
    private Spinner s001;
    private EditText e001;
    private TextView ans01;
    private String sSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0501);
        setupViewComponent();
    }

    private void setupViewComponent() {
        b001 = (Button) findViewById(R.id.m0501_b001);
        s001 = (Spinner) findViewById(R.id.m0501_s001);
        e001 = (EditText) findViewById(R.id.m0501_e001);
        ans01 = (TextView) findViewById(R.id.m0501_f000);

        // 設定 spinner item 選項------------
        ArrayAdapter<CharSequence> adapSexList = ArrayAdapter
                .createFromResource(this, R.array.m0501_a001,
                        android.R.layout.simple_spinner_item);
        adapSexList
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s001.setAdapter(adapSexList);
// ------------------------------------
// 準備 Listener a001Lis 需再設定 Listener
        s001.setOnItemSelectedListener(spnSexItemSelLis);
// 準備 Listener btnb001 需再設定 Listener
        b001.setOnClickListener(btnDoSugOnClick);

        Intent intent=this.getIntent();
        String mode_title=intent.getStringExtra("class_title");
        this.setTitle(mode_title);

    }

    private Spinner.OnItemSelectedListener spnSexItemSelLis = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            sSex = parent.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private Button.OnClickListener btnDoSugOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

      /*   String strSug = getString(R.string.m0501_f000);

         if (e001.getText().toString().trim().length() !=0){
             int iAge = Integer.parseInt(e001.getText().toString());
             if (sSex.equals(getString(R.string.chk01)))
                 if (iAge<28)
                     strSug+=getString((R.string.m0501_f001));
                 else if (iAge>33)
                     strSug+=getString((R.string.m0501_f002));



            }else {
             ans01.setText(getString(R.string.nospace));
         }

*/


           String strSug = getString(R.string.m0501_f000);
            //檢查年齡是否有輸入
            if (e001.getText().toString().trim().length() != 0) {
                int iAge = Integer.parseInt(e001.getText().toString());
                if (sSex.equals(getString(R.string.chk01)))
                    if (iAge < 28)
                        strSug += getString(R.string.m0501_f001);
                    else if (iAge > 33)
                        strSug += getString(R.string.m0501_f003);
                    else
                        strSug += getString(R.string.m0501_f002);

                else if (iAge < 25)
                    strSug += getString(R.string.m0501_f001);
                else if (iAge > 30)
                    strSug += getString(R.string.m0501_f003);
                else
                    strSug += getString(R.string.m0501_f002);
                ans01.setText(strSug);
            } else {
                ans01.setText(getString(R.string.nospace));
            }



          /*  String strSug = getString(R.string.m0501_f000);
            // 檢查 年齡是否有輸入
            if (e001.getText().toString().trim().length() != 0) {
                // -------- 判斷 輸入欄位 不可為 空白 -----------------
                // mEditText.getText().length() == 0 ps:trim() 去空白
                // mEditText.getText() == null
                // mEditText.getText().equals("")
                // --------------------------------------------------
                int iAge = Integer.parseInt(e001.getText().toString());
                if (sSex.equals(getString(R.string.chk01)))
                    //
                    if (iAge < 28)
                        strSug += getString(R.string.m0501_f001);
                    else if (iAge > 33)
                        strSug += getString(R.string.m0501_f003);
                    else
                        strSug += getString(R.string.m0501_f002);
                    //檢查女生年齡
                else if (iAge < 25)
                    strSug += getString(R.string.m0501_f001);
                else if (iAge > 30)
                    strSug += getString(R.string.m0501_f003);
                else
                    strSug += getString(R.string.m0501_f002);

                ans01.setText(strSug);
                //年齡輸入空白
            } else {
                ans01.setText(getString(R.string.nospace));
            }*/

        }
    };
}