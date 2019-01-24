package tw.tcnr21.m1105;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;

public class M1105 extends AppCompatActivity {

    private TextView t001;
    private float x1,y1,x2,y2;
    int range=50;  //兩點距離
    int ran=60; //兩點角度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1105);
        setupViewComponent();

    }

    private void setupViewComponent() {
        t001=(TextView)findViewById(R.id.m1105_t001);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 觸動事件
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: //按下
                x1=event.getX();
                y1=event.getY();
                break;

            case MotionEvent.ACTION_MOVE: //拖曳
                    break;
            case MotionEvent.ACTION_UP: //放開
                x2=event.getX();
                y2=event.getY();
                // 判斷左右的方法，因為屏幕的左上角是：0，0 點右下角是max,max
                // 並且移動距離需大於 > range
                float xbar=Math.abs(x2-x1);
                float ybar=Math.abs(y2-y1);
                double z=Math.sqrt(xbar*xbar+ybar*ybar);
                int angle =Math.round((float)(Math.asin(ybar/z)/Math.PI*180)); //角度

                if (x1!=0&&y1!=0){
                    if(x1-x2>range){
                        t001.setText("向左滑動\n"+"滑動參考值x1="+x1+"x2="+x2+"r="+(x1-x2)+"\n"+"ang="+angle);
                    }
                    if (x2-x1>range){
                        t001.setText("向右滑動\n"+"滑動參值x1="+x1+"x2="+x2+"r="+(x2-x1)+"\n"+"ang="+angle);
                    }
                    if (y2-y1>range&&angle>ran){
                        //往下角度需大於50
                        t001.setText("向下滑動\n"+"滑動參值y1="+y1+"y2="+y2+"r="+(y2-y1)+"\n"+"ang="+angle);
                    }
                    if (y1-y2>range&&angle>ran){
                        //往上角度需大於50
                        t001.setText("向上滑動\n"+"滑動參值y1="+y1+"y2="+y2+"r="+(y1-y2)+"\n"+"ang="+angle);
                    }
                }


                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
