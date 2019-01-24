package tw.tcnr09.m0609e;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;



public class Menu01 extends AppCompatActivity {

    private TextView tv;
    private Intent intent01 = new Intent();

    //1:segment 2:chapter 3:festival
    private  int menu_flag = 1;
    private String u_name = "m";
    private  int u_start = 5;
    private String TAG = "TCNR21==>";
    private String subname ="m";
    private int segment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        setupViewCompoment();

    }

    private void setupViewCompoment() {

        TextView myname = (TextView)findViewById(R.id.myname);
        LinearLayout  mylay02 = (LinearLayout)findViewById(R.id.lay02);

        //LinearLayout parameter
        LinearLayout.LayoutParams layP =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        layP.setMargins(0,0,0,10);

        // 動態調整高度 抓取使用裝置尺寸
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        /**
         *
         * String px = displayMetrics.widthPixels + " x " + displayMetrics.heightPixels ;
         *
         * String dp = displayMetrics.xdpi + " x " + displayMetrics.ydpi ;
         * String density = "densityDpi = " +
         *                                 displayMetrics.densityDpi +
         *                                 " , density = " +
         *                                 displayMetrics.density +
         *                                 " ,  scaledDensity = " +
         *                                 displayMetrics.scaledDensity ;
         *
         *  myname.setText( px + "\n" + dp + "\n" + density + "\n" + newscrollheight ) ;
         *
         **/
        // Reset  ScrollView  height  is display  size 4 / 5
        int newscrollheight = displayMetrics.heightPixels * 75 / 100 ;
        ScrollView scr01 = (ScrollView)findViewById(R.id.scr01) ;
        scr01.getLayoutParams().height = newscrollheight ;
        scr01.setLayoutParams(scr01.getLayoutParams());


        //show message
        myname.setText("size : " + displayMetrics.widthPixels +
                                        " x "     + displayMetrics.heightPixels +
                                        "   "      + getString(R.string.myname ) ) ;

        TextView objt01 = (TextView)findViewById(R.id.objT001);
        //This view is invisible, and it doesn't take any space for layout purposes
        objt01.setVisibility(View.GONE);

        /**********************************************************************
         *          remove linearlayout all view 20181129 by castle
         **********************************************************************/

        mylay02.removeAllViews();

        /*********************************************************************
         *          Set next page flag and title      20181129 by castle        *
         *********************************************************************/
        switch (menu_flag){
            case 1:
                menu_flag = 2;
                this.setTitle(R.string.app_name);
                break;
            case 2:
                menu_flag = 3;
                this.setTitle( this.getResources().getIdentifier( subname,"string",getPackageName()));
                break;
            case 3:
                menu_flag = 1;
                this.setTitle( this.getResources().getIdentifier( subname,"string",getPackageName()));
                break;
        }

        /***            end of  switch            ****/

        try {

            for ( int i = u_start ; i <= 20 ; i++ ){
                tv = new TextView(this);
                tv.setId( i );

                /***
                 *                  設定新TextView的ID    %02d 執行十進制整數轉換d，
                 *                  格式化補零，寬度為2。 因此，一個int參數，它的值是7 ，將被格式化為"07"作為一個String
                 */

                String microNo = String.format("%02d", i) ;
                Log.d(TAG,microNo);


//                int id =getResources().getIdentifier("m"+microNo , "string", getPackageName());

                /***
                 *                  GetIdentifier
                 *
                 */
                int id =getResources().getIdentifier(""+u_name+microNo , "string", getPackageName());

                /**
                 *              IF getIdentifier not find data, return 0 , so interrupt loop
                 */

                if( id == 0 ){
                    break;
                    }


                tv.setText(id);

                //set layout
                tv.setLayoutParams(layP);
                tv.setTextColor(objt01.getCurrentTextColor()); // 以objt001字體顏色為依據
                tv.setGravity(objt01.getGravity()); // 以objt001字體靠右靠左
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, objt01.getTextSize()); // 設定文字大小
                tv.setBackground(objt01.getBackground()); // 設定背景

                tv.setPadding(objt01.getPaddingLeft(),
                        objt01.getPaddingTop(),
                        objt01.getPaddingRight(),
                        objt01.getPaddingBottom()   ); // 設定文字內間距 left,top,right,bottom

                mylay02.addView(tv); //textview add to layout2
                tv.setOnClickListener(clkOn);

            }

        }catch (Exception e){
            return;
        }





    }

//20181128 by oldpa
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

//            if (keyCode == KeyEvent.KEYCODE_BACK) {
//                Toast.makeText(Menu01.this, "返回鍵無效", Toast.LENGTH_SHORT).show();
//                return true;
//            }


        /***************************************************
         *                      20181130 author PJ
         ***************************************************/

        String mm2 = String.format ( "%02d", segment ) ;


        if(menu_flag >0) {

            switch (menu_flag){
                case 1:
                    menu_flag = 2;
                    /**
                     * @mathod
                     *                  public String substring (int start, int end)
                     * @Parameters
                     *                  start     the start offset.
                     *                  end      the end+1 offset.
                     */
                    //Returns a string containing the given subsequence of this string.
                    subname=subname.substring(0,3);
                    u_name =subname;
                    u_start =1;

                    break;

                case 2:
                    if(true){
                        return true;

                    }

                    break;

                case 3:

                    menu_flag =1;
                    u_name = "m";
                    u_start =5;


                    break;

            }


            setupViewCompoment();

        }
        else{

            Toast.makeText(Menu01.this, "Top now", Toast.LENGTH_SHORT).show();
        }


        return true;

//        return super.onKeyDown(keyCode, event);
    }


    private TextView.OnClickListener clkOn = new TextView.OnClickListener() {
        @Override
        public void onClick(View v) {

            segment = v.getId();
            String mm = String.format ( "%02d", segment ) ;

            /*****************************************************************************
             *          Set next page u_name and u_start      20181129 by castle        *
             *****************************************************************************/

            switch (menu_flag){
                case 1:
                    u_name = "m";
                    u_start =5;
                    break;

                case 2:
                    subname = "m" + mm;
                    u_name =subname;
                    u_start =1;
                    break;

                case 3:
                    subname=subname+mm;
                    u_name = subname;
                    u_start =1;
                    break;

            }

            setupViewCompoment();

        }
    };





//    private  TextView.OnClickListener clkOn = new TextView.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            int segment = v.getId();
//            String mm = String.format ( "%02d", segment ) ;
//            String subname = "m" + mm;
//
//            intent01.putExtra("subname" , subname ) ;
//            intent01.setClass(Menu01.this , Menu02.class);
//            startActivity(intent01) ;
//        }
//    };









}
