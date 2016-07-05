package ir.mbaas.mashhadconnect.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Mahdi on 5/31/2016.
 */
public class MBButton extends Button {

    public MBButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/IRANSans.ttf"));
    }
}
