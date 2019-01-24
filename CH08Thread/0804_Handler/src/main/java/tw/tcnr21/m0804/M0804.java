package tw.tcnr21.m0804;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Scanner;

public class M0804 extends AppCompatActivity {


    private ImageView imgSwi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0804);
        setupViewComponent();
    }

    private void setupViewComponent() {
        imgSwi=(ImageView)findViewById(R.id.m0804_img01);
        UserThread myThread=new UserThread(this);
        myThread.start();
    }
    Handler myHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) //msg裡的一個物件
            {
                case 0:
                    imgSwi.setImageResource(R.drawable.flow01b);
                    break;
                case 1:
                    imgSwi.setImageResource(R.drawable.flow02b);
                    break;
                case 2:
                    imgSwi.setImageResource(R.drawable.flow03b);
                    break;
                case 3:
                    imgSwi.setImageResource(R.drawable.flow04b);
                    break;
            }


        }
    };


}
