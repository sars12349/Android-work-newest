package tw.tcnr21.m0707;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class M0707 extends AppCompatActivity implements View.OnClickListener {

    private ImageView img01,img02;
    private TextView ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0707);
        setupViewComponent();
    }

    private void setupViewComponent() {
        img01=(ImageView)findViewById(R.id.m0707_img);
        img02=(ImageView)findViewById(R.id.m0707_imgbutton);
        ans=(TextView)findViewById(R.id.m0707_t001);

        img01.setOnClickListener(this);
        img02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.m0707_img:
                ans.setText(getString(R.string.m0707_t001)+getString(R.string.m0707_img));
                break;
            case R.id.m0707_imgbutton:
                ans.setText(getString(R.string.m0707_t001)+getString(R.string.m0707_imgbutton));
                break;
        }
    }
}
