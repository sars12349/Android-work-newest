package tw.tcnr01.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class shop01 extends AppCompatActivity implements View.OnClickListener {

    private ImageView img01,img02;
    private Intent intent=new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop01_layout);
        setupViewComponent();
    }

    private void setupViewComponent() {
        img01=(ImageView)findViewById(R.id.shop01_img01);
        img02=(ImageView)findViewById(R.id.shop01_img02);

        img01.setOnClickListener(this);
        img02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.shop01_img01:
                intent.putExtra("class_title", getString(R.string.app_name));
                intent.setClass(shop01.this, shop02.class);
                break;
            case R.id.shop01_img02:
                intent.putExtra("class_title", getString(R.string.app_name));
                intent.setClass(shop01.this, shop02.class);
                break;
        }
        startActivity(intent);


    }
}
