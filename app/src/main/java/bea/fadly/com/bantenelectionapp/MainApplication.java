package bea.fadly.com.bantenelectionapp;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.adobe.creativesdk.foundation.AdobeCSDKFoundation;
import com.adobe.creativesdk.foundation.auth.IAdobeAuthClientCredentials;

/**
 * Created by DIGIKOM-EX4 on 11/25/2016.
 */

public class MainApplication extends Application implements IAdobeAuthClientCredentials {

    /* Be sure to fill in the two strings below. */
    private static final String CREATIVE_SDK_CLIENT_ID = "9dd81c3764744ebd867d9b9570ee1ee7";
    private static final String CREATIVE_SDK_CLIENT_SECRET = "c0f3bf9e-5b71-4293-a8a4-d4b14bc86beb";

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        AdobeCSDKFoundation.initializeCSDKFoundation(getApplicationContext());
    }

    @Override
    public String getClientID() {
        return CREATIVE_SDK_CLIENT_ID;
    }

    @Override
    public String getClientSecret() {
        return CREATIVE_SDK_CLIENT_SECRET;
    }
}
