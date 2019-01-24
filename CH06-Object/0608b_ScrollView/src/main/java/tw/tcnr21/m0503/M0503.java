package tw.tcnr21.m0503;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class M0503 extends AppCompatActivity {
    private Button btn01;
    private TextView txt01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0503);
        setupViewcomponent();
    }

    private void setupViewcomponent() {

        btn01=(Button)findViewById(R.id.m0503_btn01);
        txt01=(TextView)findViewById(R.id.m0503_txt01);
        //設定button案件的事件
        btn01.setOnClickListener(btn01OnClick);

    }

    private Button.OnClickListener btn01OnClick= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            String ans01=getString(R.string.m0503_txt01);


               try{
                   for(int i=1;i<99;i++){
                       String idName="m0503_chb"+ String.format("%02d",i);
                       int resID=getResources().getIdentifier(idName,"id",getPackageName());
                       CheckBox btn=((CheckBox)findViewById(resID));
                       if(btn.isChecked()) ans01+=btn.getText().toString();
                   }
                }catch (Exception e ){

            }
            txt01.setText(ans01);
        }
    };

}
