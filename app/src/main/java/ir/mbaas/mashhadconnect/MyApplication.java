package ir.mbaas.mashhadconnect;

import android.app.Application;
import android.content.pm.PackageManager;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import ir.mbaas.sdk.MBaaS;

/**
 * Created by Mahdi on 6/29/2016.
 */
@ReportsCrashes(formUri = "http://mbaas.ir/api/acra/78fec8fc")
public class MyApplication extends Application {

    public static String versionName = "1.0.0";
    public static int versionCode = 1;

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            versionCode = getPackageManager().getPackageInfo(this.getPackageName(), 0).versionCode;
            versionName = getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        ACRA.init(this);
        MBaaS.init(this);
    }
}
