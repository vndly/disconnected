package com.mauriciotogneri.disconnected;

import android.content.Intent;

import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.WearableListenerService;

public class ListenerService extends WearableListenerService
{
    @Override
    public void onPeerConnected(Node node)
    {
        startAlarm();
    }

    @Override
    public void onPeerDisconnected(Node node)
    {
        startAlarm();
    }

    private void startAlarm()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}