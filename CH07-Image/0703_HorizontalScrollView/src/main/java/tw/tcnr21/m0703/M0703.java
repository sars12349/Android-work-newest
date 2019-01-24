package tw.tcnr21.m0703;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class M0703 extends AppCompatActivity implements
        ViewSwitcher.ViewFactory,
        AdapterView.OnItemClickListener {

    private Integer[] imgArr={
            R.drawable.img01,R.drawable.img02,
            R.drawable.img03,R.drawable.img04,
            R.drawable.img05,R.drawable.img06,
            R.drawable.img07,R.drawable.img08,
            R.drawable.img09,R.drawable.img10,
            R.drawable.img11,R.drawable.img12,
            R.drawable.img13,R.drawable.img14,
            R.drawable.img15,R.drawable.img16,
    };
    private Integer[] thumbImgArr={
            R.drawable.img01th,R.drawable.img02th,
            R.drawable.img03th,R.drawable.img04th,
            R.drawable.img05th,R.drawable.img06th,
            R.drawable.img07th,R.drawable.img08th,
            R.drawable.img09th,R.drawable.img10th,
            R.drawable.img11th,R.drawable.img12th,
            R.drawable.img13th,R.drawable.img14th,
            R.drawable.img15th,R.drawable.img16th,
    };
    private GridView gridview;
    private ImageSwitcher imgSwi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0703);
        setupViewComponent();
    }

    private void setupViewComponent() {
        gridview=(GridView)findViewById(R.id.m0703_g001);

        setGridView();

        imgSwi=(ImageSwitcher)findViewById(R.id.m0703_im01);
        imgSwi.setFactory((ViewSwitcher.ViewFactory)this);

        gridview.setOnItemClickListener((AdapterView.OnItemClickListener)this);

    }

    private void setGridView() {
        int size=thumbImgArr.length; //找出陣列有幾張圖
        int length=100; //設定縮圖的寬度

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float destiny=dm.density;
        int gridviewWidth=(int)(size*(length+4)*destiny*0.8);
        int itemWidth=(int)(length*destiny*0.8);

        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                gridviewWidth,LinearLayout.LayoutParams.WRAP_CONTENT);

        gridview.setLayoutParams(params);
        gridview.setColumnWidth(itemWidth);
        gridview.setHorizontalSpacing(5);
        gridview.setStretchMode(GridView.NO_STRETCH);
        gridview.setNumColumns(size);

        gridview.setAdapter(new GridAdapter(this,thumbImgArr));
    }


    @Override
    public View makeView() {
        ImageView v =new ImageView(this);
        v.setBackgroundColor(0xFF000000);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Integer ss=(int)(Math.random()*4+1);
        switch (ss)
        {
            case 1:
                imgSwi.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_alpha_out));
                imgSwi.clearAnimation();
                imgSwi.setAnimation(null);
                imgSwi.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_alpha_in));
                Toast.makeText(getApplicationContext(),"alpha",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                imgSwi.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_trans_out));
                imgSwi.clearAnimation();
                imgSwi.setAnimation(null);
                imgSwi.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_trans_in));
                Toast.makeText(getApplicationContext(),"trans",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                imgSwi.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_scale_rotate_out));
                imgSwi.clearAnimation();
                imgSwi.setAnimation(null);
                imgSwi.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_scale_rotate_in));
                Toast.makeText(getApplicationContext(),"rotate",Toast.LENGTH_SHORT).show();
                break;
            case 4:
                imgSwi.setOutAnimation(null);
                imgSwi.setInAnimation(null);
                imgSwi.clearAnimation();
                imgSwi.setAnimation(null);
                Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_trans_bounce);
                anim.setInterpolator(new BounceInterpolator());
                imgSwi.setAnimation(anim);
                Toast.makeText(getApplicationContext(),"Bounce",Toast.LENGTH_SHORT).show();
                break;
        }
        imgSwi.setImageResource(imgArr[position]);

    }
}
