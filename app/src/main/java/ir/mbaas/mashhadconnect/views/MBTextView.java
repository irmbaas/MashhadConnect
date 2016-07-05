package ir.mbaas.mashhadconnect.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Mahdi on 2/6/2016.
 */
public class MBTextView extends TextView {

    public MBTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/IRANSans.ttf"));
    }
}
