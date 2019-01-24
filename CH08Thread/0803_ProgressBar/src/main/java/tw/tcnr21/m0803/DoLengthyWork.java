package tw.tcnr21.m0803;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import java.util.Calendar;
public class DoLengthyWork extends Thread{

    private Handler mHandler;
    private ProgressBar mProBar,pa2,pa3,pa4;
    String TAG="tcnr21=>";
    private int von;
    private int Flag;

    public void run() {
        Calendar begin = Calendar.getInstance();

        do {
            Calendar now = Calendar.getInstance();
            final int iDiffSec = 60 * (now.get(Calendar.MINUTE) //迴圈經過幾秒鐘
                    - begin.get(Calendar.MINUTE)) + now.get(Calendar.SECOND)
                    - begin.get(Calendar.SECOND);
            if (iDiffSec * 10 > 100) {
                mHandler.post(new Runnable() {
                    public void run() {
                        mProBar.setProgress(100);
                        /*pa2.setVisibility(View.INVISIBLE);
                        pa3.setVisibility(View.INVISIBLE);
                        pa4.setVisibility(View.INVISIBLE);*/
                    }
                });
                //System.exit(0);//整個class結束,離開程式
                von=1;
                break; //跳出
            }
            mHandler.post(new Runnable() {
                public void run() {
                    mProBar.setProgress(iDiffSec * 10);
                }
            });
            if (iDiffSec * 20 < 100)
                mHandler.post(new Runnable() {
                    public void run() {
                        mProBar.setSecondaryProgress(iDiffSec * 20);
                    }
                });
            else
                mHandler.post(new Runnable() {
                    public void run() {
                        mProBar.setSecondaryProgress(100);
                    }
                });
        } while (Flag!=1);
        pa2.setVisibility(View.INVISIBLE);
        pa3.setVisibility(View.INVISIBLE);
        pa4.setVisibility(View.INVISIBLE);
    }

    public void setHandler(Handler h) {
        mHandler=h;
    }


    public void von(int i) {
        von=i;
    }

    public void setProgressBar(ProgressBar...proBar) {
        mProBar=proBar[0];
        pa2=proBar[1];
        pa3=proBar[2];
        pa4=proBar[3];

    }

    public void setFlag(int i){
        Flag=i;
    }
}
