package ir.mbaas.mashhadconnect.helpers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.Display;
import android.view.WindowManager;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        Date now = new Date();
        long diffSecs = ((new Date()).getTime() - date.getTime()) / 1000;
        Format formatter;

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        int ddof = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.setTime(now);
        int ndof = calendar.get(Calendar.DAY_OF_WEEK);
        int ny = calendar.get(Calendar.YEAR);

        if (diffSecs < 86400 && ndof == ddof) {
            formatter = new SimpleDateFormat("HH:mm");
            return formatter.format(date);
        } else if (diffSecs < 604800 && ndof != ddof) {
            formatter = new SimpleDateFormat("E");
            return formatter.format(date);
        }

        JalaliCalendar.YearMonthDate jalali = JalaliCalendar.gregorianToJalali(
                new JalaliCalendar.YearMonthDate(date));

        JalaliCalendar.YearMonthDate jalaliNow = JalaliCalendar.gregorianToJalali(
                new JalaliCalendar.YearMonthDate(now));

        if (diffSecs < 31536000 && jalali.getYear() == jalaliNow.getYear()) {
            return jalali.toStringMonthAndDay();
        }

        return jalali.toString();
    }
}
