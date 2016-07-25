package ir.mbaas.mashhadconnect.helpers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.Display;
import android.view.WindowManager;

import java.util.Date;

/**
 * Created by Mahdi on 2/8/2016.
 */
public class StaticMethods {
    public static boolean isConnectionAvailable(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    /*
     * getting screen width
    */
    public static int getScreenWidth(Context ctx) {
        int columnWidth;
        WindowManager wm = (WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;
        return columnWidth;
    }

    /*
     * getting screen width
    */
    public static int getScreenHeight(Context ctx) {
        int columnHeight;
        WindowManager wm = (WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnHeight = point.y;
        return columnHeight;
    }

    public static void openBrowser(Context ctx, String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        ctx.startActivity(intent);
    }

    public static String formatDatetime(Date date) {
        JalaliCalendar.YearMonthDate jalali = JalaliCalendar.gregorianToJalali(
                new JalaliCalendar.YearMonthDate(date));

        return jalali.toString();
    }
}
