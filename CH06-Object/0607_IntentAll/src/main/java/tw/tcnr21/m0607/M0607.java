package tw.tcnr21.m0607;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class M0607 extends AppCompatActivity {

    private Button b0500,b0501,b0502,b0503,b0504,b0505;
    private Intent intent=new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0607);
        setupComponent();
    }

    private void setupComponent() {
        b0500=(Button)findViewById(R.id.m0000_b0500);
        b0501=(Button)findViewById(R.id.m0000_b0501);
        b0502=(Button)findViewById(R.id.m0000_b0502);
        b0503=(Button)findViewById(R.id.m0000_b0503);
        b0504=(Button)findViewById(R.id.m0000_b0504);
        b0505=(Button)findViewById(R.id.m0000_b0505);

        b0500.setOnClickListener(sele_btn);
        b0501.setOnClickListener(sele_btn);
        b0502.setOnClickListener(sele_btn);
        b0503.setOnClickListener(sele_btn);
        b0504.setOnClickListener(sele_btn);
        b0505.setOnClickListener(sele_btn);
        }
        private Button.OnClickListener sele_btn=new Button.OnClickListener(){


            @Override
            public void onClick(View v) {

                switch (v.getId())
                {
                    case R.id.m0000_b0500:
                        intent.putExtra("class_title",getString(R.string.m0000_b0500));
                        intent.setClass(M0607.this,M0500.class);
                        break;
                    case R.id.m0000_b0501:
                        intent.putExtra("class_title",getString(R.string.m0000_b0501));
                        intent.setClass(M0607.this,M0501.class);
                        break;
                    case R.id.m0000_b0502:
                        intent.putExtra("class_title",getString(R.string.m0000_b0502));
                        intent.setClass(M0607.this,M0502.class);
                        break;
                    case R.id.m0000_b0503:
                        intent.putExtra("class_title",getString(R.string.m0000_b0503));
                        intent.setClass(M0607.this,M0503.class);
                        break;
                    case R.id.m0000_b0504:
                        intent.setClass(M0607.this,M0504.class);
                        break;
                    case R.id.m0000_b0505:
                        intent.putExtra("class_title",getString(R.string.m0000_b0505));
                        intent.setClass(M0607.this,M0505.class);
                        break;
                }

                startActivity(intent);


            }
        };
}
