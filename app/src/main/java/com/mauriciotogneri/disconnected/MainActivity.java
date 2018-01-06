package com.mauriciotogneri.disconnected;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends WearableActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turnOnScreen();
        startVibration();
    }

    private void turnOnScreen()
    {
        try
        {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
            wl.acquire();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void startVibration()
    {
        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        if (vibrator != null)
        {
            long[] vibrationPattern = {0, 500, 500};
            vibrator.vibrate(VibrationEffect.createWaveform(vibrationPattern, 1));

            findViewById(R.id.stop).setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    vibrator.cancel();
                    finish();
                }
            });
        }
    }
}