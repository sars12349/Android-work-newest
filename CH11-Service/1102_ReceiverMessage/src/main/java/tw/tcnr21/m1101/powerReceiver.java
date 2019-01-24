package tw.tcnr21.m1101;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class powerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,context.getString(R.string.m1101_msg09) ,Toast.LENGTH_LONG).show();
    }
    public static void main(String[] args) {

    }
}
