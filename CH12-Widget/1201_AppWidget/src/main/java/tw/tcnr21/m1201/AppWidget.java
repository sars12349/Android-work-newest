package tw.tcnr21.m1201;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AppWidget extends AppWidgetProvider {
    private final String TAG = "tcnr21=>";
    private static AlarmManager mAlarmManager;
    private static PendingIntent mPendingIntent;

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(TAG, "onDeleted()");
        mAlarmManager.cancel(mPendingIntent);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(TAG, "onDisabled()");
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(TAG, "onEnabled()");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (!intent.getAction().equals("tcnr21.tw.m1201.MY_OWN_WIDGET_UPDATE"))
            return;

        Log.d(TAG, "onReceive()");

        AppWidgetManager appWidgetMan = AppWidgetManager.getInstance(context);
        ComponentName thisAppWidget = new ComponentName(context.getPackageName(),
                AppWidget.class.getName());
        int[] appWidgetIds = appWidgetMan.getAppWidgetIds(thisAppWidget);

        onUpdate(context, appWidgetMan, appWidgetIds);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(TAG, "onUpdate()");
    }

    static void SaveAlarmManager(AlarmManager alarmManager, PendingIntent pendingIntent) {
        mAlarmManager = alarmManager;
        mPendingIntent = pendingIntent;
    }

}
