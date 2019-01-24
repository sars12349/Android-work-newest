package tw.tcnr21.m0604;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class M0604 extends AppCompatActivity {

    private Button b001,b002,b003,b004;
    private Toast toast;

    // private Object toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0604);
        setupViewComponent();
    }

    private void setupViewComponent() {

        b001=(Button)findViewById(R.id.m0604_b001);
        b002=(Button)findViewById(R.id.m0604_b002);
        b003=(Button)findViewById(R.id.m0604_b003);
        b004=(Button)findViewById(R.id.m0604_b004);

        b001.setOnClickListener(btoast);
        b002.setOnClickListener(btoast);
        b003.setOnClickListener(btoast);
        b004.setOnClickListener(btoast);
    }

    private Button.OnClickListener btoast=new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            toast=null;
            switch (v.getId()){
                case R.id.m0604_b001:
                    Toast.makeText(getApplicationContext(),getText(R.string.m0604_t001),Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "id = "+v.getId(),Toast.LENGTH_LONG).show();
                    break;
                    
                case R.id.m0604_b002:
                    toast = Toast.makeText(getApplicationContext(), getString(R.string.m0604_b002), Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM | Gravity.END,10,20);
                    toast.show();
                    break;
                    
                case R.id.m0604_b003:
                    toast = Toast.makeText(getApplicationContext(), getText(R.string.m0604_t002), Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    LinearLayout toastView=(LinearLayout) toast.getView();
                    ImageView imageCodeProject = new ImageView(getApplicationContext());
                    imageCodeProject.setImageResource(R.drawable.net);
                    toastView.addView(imageCodeProject,0);
                    toast.show();
                    break;
                    
                case  R.id.m0604_b004:
                    LayoutInflater inflater=getLayoutInflater();
                    View layout=inflater.inflate(R.layout.custom,(ViewGroup)findViewById(R.id.llToast));

                    ImageView image =(ImageView) layout.findViewById(R.id.tvimageToast);
                    image.setImageResource(R.drawable.net);
                    TextView tittle=(TextView)layout.findViewById(R.id.tvTittleToast);
                    tittle.setText(getString(R.string.m0604_t001).toString());
                    TextView text =(TextView)layout.findViewById(R.id.tvtextToast);
                    text.setText(getString(R.string.m0604_t002).toString());

                    toast=new Toast(getApplicationContext());
                    toast.setGravity(Gravity.END| Gravity.BOTTOM,50,450);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
                    break;
                    
            }

        }
    };
}
