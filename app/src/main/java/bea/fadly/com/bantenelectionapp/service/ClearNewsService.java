package bea.fadly.com.bantenelectionapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by DIGIKOM-EX4 on 12/4/2016.
 */

public class ClearNewsService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        PrefManager.getInstance(this).clearListNews();
        Log.d("NEW PARAMETER", "------------NEWS CLEARED ----------");
        super.onTaskRemoved(rootIntent);
    }
}
