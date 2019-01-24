package tw.tcnr01.shop;

import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class product extends AppCompatActivity {
    private ImageView img01;
    private Dialog productDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        setupViewComponent();
    }

    private void setupViewComponent() {
    img01=(ImageView)findViewById(R.id.product_img01);

    img01.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            productDlg = new Dialog(product.this);
            productDlg .setCancelable(false);
            productDlg .setContentView(R.layout.product_dialog);
            ImageButton btncancel=(ImageButton)productDlg.findViewById(R.id.product_dialog_btncancel);
            btncancel.setOnClickListener(productbtncancel);
            productDlg .show();

        }
        private Button.OnClickListener productbtncancel= new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDlg.cancel();
            }
        };
    });

    }
}
