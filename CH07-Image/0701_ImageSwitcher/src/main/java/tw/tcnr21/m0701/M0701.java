package tw.tcnr21.m0701;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

public class M0701 extends AppCompatActivity  implements
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
    private GridView gridView;
    private ImageSwitcher imgSwi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0701);
        setupViewComponent();
    }

    private void setupViewComponent() {
        gridView=(GridView)findViewById(R.id.m0701_g001);
        imgSwi=(ImageSwitcher)findViewById(R.id.m0701_im01);
        //將縮圖填入gripview
        gridView.setAdapter(new GridAdapter(this,thumbImgArr));

        //GridView元件的事件處理
        gridView.setOnItemClickListener(this);
        //取得GridView元件
        imgSwi.setFactory(this);
    }

    @Override
        public View makeView() {
        ImageView v = new ImageView(this);
        v.setBackgroundColor(0xFF000000);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return v;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        imgSwi.setImageResource(imgArr[position]);
    }
}
