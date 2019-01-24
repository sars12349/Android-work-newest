package tw.tcnr01.shop;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class shop02 extends AppCompatActivity implements View.OnClickListener {

    private ImageView img01;
    private TextView txt01;
    private Button btn01,btn02,btn03;
    private Intent intent=new Intent();
    private Dialog mapDlg;

    private Integer[] shop={
            R.drawable.shop001,R.drawable.shop002,R.drawable.shop003
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop02_layout);
        setupViewComponent();
    }

    private void setupViewComponent() {
        img01=(ImageView)findViewById(R.id.shop02_img01);
        txt01=(TextView)findViewById(R.id.shop02_txt01);
        btn01=(Button)findViewById(R.id.shop02_btn01);
        btn02=(Button)findViewById(R.id.shop02_btn02);
        btn03=(Button)findViewById(R.id.shop02_btn03);

        img01.setOnClickListener(this);
        txt01.setOnClickListener(this);
        btn01.setOnClickListener(shopdialog);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(favoritetoast);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.shop02_img01:
                intent.putExtra("class_title", getString(R.string.app_name));
                intent.setClass(shop02.this, shop03.class);
                break;
            case R.id.shop02_txt01:
                intent.putExtra("class_title", getString(R.string.app_name));
                intent.setClass(shop02.this, shop03.class);
                break;
//            case R.id.shop02_btn01:
//                break;

               case R.id.shop02_btn02:
                   intent.putExtra("class_title", getString(R.string.app_name));
                   intent.setClass(shop02.this, product.class);
                   break;

//            case R.id.shop02_btn03:
//                    mapDlg = new Dialog(shop02.this);
//                    mapDlg .setTitle(getString(R.string.shop01_img01));
//                    mapDlg .setCancelable(true);
//                    mapDlg .setContentView(R.layout.shop_map_dialog);
//                    mapDlg .show();
//                    break;
        }
        startActivity(intent);
    }

    private Button.OnClickListener shopdialog= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            mapDlg = new Dialog(shop02.this);
            mapDlg .setCancelable(false);
            mapDlg .setContentView(R.layout.shop_map_dialog);
            ImageButton btncancel=(ImageButton)mapDlg.findViewById(R.id.shop_map_dialog_btncancel);
            btncancel.setOnClickListener(productbtncancel);
            mapDlg .show();
        }
        private Button.OnClickListener productbtncancel= new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapDlg.cancel();
            }
        };
    };

    private Button.OnClickListener favoritetoast= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(),R.string.shop02_m001,
                    Toast.LENGTH_SHORT).show();
        }
    };

}
