package tw.tcnr21.m1101;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String sender = intent.getExtras().getString("sender_name");
        Toast.makeText(context,
                context.getString(R.string.m1101_msg07) + sender +context.getString(R.string.m1101_msg06),
                Toast.LENGTH_SHORT).show();
    }
    public static void main(String[] args) {


    }
}
