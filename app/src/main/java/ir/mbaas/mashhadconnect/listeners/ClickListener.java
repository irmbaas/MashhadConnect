package ir.mbaas.mashhadconnect.listeners;

import android.view.View;

/**
 * Created by Mahdi on 6/18/2016.
 */
public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
