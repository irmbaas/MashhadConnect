package ir.mbaas.mashhadconnect.listeners;

import android.content.Context;
import android.os.Bundle;

import ir.mbaas.sdk.listeners.GcmMessageListener;
import ir.mbaas.sdk.listeners.GcmRegistrationListener;

/**
 * Created by Mehdi on 6/12/2016.
 */
public class CustomGcmListener implements GcmMessageListener, GcmRegistrationListener {

    @Override
    public void onMessageReceived(Context context, String from, Bundle data) {
    }

    @Override
    public void onTokenAvailable(Context context, String token, boolean updated) {
    }

    @Override
    public void onTokenDeleted(Context context) {
    }

    @Override
    public void onGooglePlayServiceUnavailable(Context context) {
    }

    @Override
    public void successRegistrationOnMBaaS(Context context, String token) {
    }

    @Override
    public void failedRegistrationOnMBaaS(Context context, String token) {
    }
}
