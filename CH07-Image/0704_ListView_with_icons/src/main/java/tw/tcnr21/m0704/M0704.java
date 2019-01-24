package tw.tcnr21.m0704;

import android.app.ListActivity;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class M0704 extends ListActivity {

    private TextView mTxtResult;
    private String[] listFormResource;
    private ArrayList<Map<String,Object>> mList;

    private Integer[] b={
            R.drawable.s4,R.drawable.s2,
            R.drawable.sb,R.drawable.cb,
            R.drawable.chb,R.drawable.cut,
            R.drawable.fb,R.drawable.bt,
            R.drawable.sk,R.drawable.sc
    };
    private String[] introduce;
    private TextView nTxtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0704);
        setupViewComponent();


    }

    private void setupViewComponent() {
        mTxtResult=(TextView)findViewById(R.id.m0704_t001);
        nTxtResult=(TextView)findViewById(R.id.m0704_t004);
        listFormResource=getResources().getStringArray(R.array.weekday);
        introduce=getResources().getStringArray(R.array.des);
        mList=new ArrayList<Map<String,Object>>();

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        ImageView imageView =new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_START);



        for (int i=0;i<listFormResource.length;i++){
            Map<String,Object> item=new HashMap<String, Object>(); //hash map 用put放資料
            item.put("imgView",b[i]);
            item.put("introduce",introduce[i]);
            item.put("txtView",listFormResource[i]);
            mList.add(item);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,mList,
                R.layout.list_item,
                new String[]{"imgView","txtView","introduce"}, //取得KEY欄位的值
                new int[]{R.id.imgView,R.id.txtView,R.id.m0704_t001}); //LAYOUT欄位IP

        setListAdapter(adapter);
        //----------------------------
        ListView listView=getListView();
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(listViewOnItemClkLis);


        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
//設定ListView高度
        getListView().getLayoutParams().height = (int)(size.y*0.75);


    }

    AdapterView.OnItemClickListener listViewOnItemClkLis=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mTxtResult.setText(listFormResource[position]);
            nTxtResult.setText(introduce[position]);

        }
    };
}
