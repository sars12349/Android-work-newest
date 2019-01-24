package tw.tcnr21.m0609;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Menu01 extends AppCompatActivity {

    private TextView tv;
    private Intent intent01=new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        setupViewComponent();
    }

    private void setupViewComponent() {
        TextView myname=(TextView)findViewById(R.id.myname);
        LinearLayout mlay02=(LinearLayout)findViewById(R.id.lay02);

       // LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,180);
        lp.setMargins(0,0,0,10);//設定物件之間的距離

        // 動態調整高度 抓取使用裝置尺寸
        DisplayMetrics displayMetrics=new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // String px = displayMetrics.widthPixels + " x " +
        // displayMetrics.heightPixels;
        // String dp = displayMetrics.xdpi + " x " + displayMetrics.ydpi;
        // String density = "densityDpi = " + displayMetrics.densityDpi +
        // ", density=" + displayMetrics.density + ", scaledDensity = " +
        // displayMetrics.scaledDensity;
        // myname.setText(px + "\n" + dp + "\n" +density + "\n" +
        // newscrollheight);
        int newscrollheight = displayMetrics.heightPixels * 75/100; // 設定ScrollView使用尺寸的4/5

        ScrollView scr01=(ScrollView)findViewById(R.id.scr01);
        scr01.getLayoutParams().height=newscrollheight;
        scr01.setLayoutParams(scr01.getLayoutParams());
        myname.setText("size:" +displayMetrics.widthPixels+"x"+displayMetrics.heightPixels+" "+getString(R.string.myname));
        //--------
        TextView objt001=(TextView)findViewById(R.id.objT001); //取出參考物件
        objt001.setVisibility(View.GONE);//設定參考物件隱藏不占空間

        //------
        try {
            for (int i=5;i<=20;i++) // 設定走20圈, 遇到沒資料自動停止
            {
                tv=new TextView(this);  // tv 繼承TextView
                tv.setId(i);
                // %02d執行十進制整數轉換d，格式化補零，寬度為2。 因此，一個int參數，它的值是7
                // ，將被格式化為"07"作為一個String
                String microNo=String.format("%02d",i);
                int id=getResources().getIdentifier("m"+microNo,"string",getPackageName()); // 取得string 裏頭相對應的ID 碼
                //---------------------------
                if (id==0){
                    break;  // 假如 getIdentifier 找不到滿足資料, 會傳回 0 , 所以中斷迴圈
                }
                //--------------------------
                tv.setText(id);

                tv.setLayoutParams(lp); // 套用格式
                tv.setTextColor(objt001.getCurrentTextColor()); // 以objt001字體顏色為依據
                tv.setGravity(objt001.getGravity()); // 以objt001字體靠右靠左
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, objt001.getTextSize()); // 設定文字大小
                tv.setBackground(objt001.getBackground()); // 設定背景
                tv.setPadding(objt001.getPaddingLeft(),
                        objt001.getPaddingTop(),
                        objt001.getPaddingRight(),
                        objt001.getPaddingBottom()); // 設定文字內間距 left,top,right,bottom
                mlay02.addView(tv);// 顯示textview物件

                tv.setOnClickListener(clkOn);

            }

        }catch (Exception e){
            return; // for 迴圈異常中斷到此跳出
        }

    }

    private TextView.OnClickListener clkOn=new TextView.OnClickListener(){

        @Override
        public void onClick(View v) {

            int ii=(v.getId());
            String mm=String.format("%02d",ii);
            String subname="m"+mm;

            //-----------------------------------
            intent01.putExtra("sel",ii);
            intent01.putExtra("subname",subname);
            intent01.setClass(Menu01.this,Menu02.class);
            startActivity(intent01);
        }
    } ;

}
