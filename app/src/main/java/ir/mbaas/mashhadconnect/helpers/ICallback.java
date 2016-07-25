package ir.mbaas.mashhadconnect.helpers;

import ir.mbaas.mashhadconnect.models.Timetables;

/**
 * Created by Mahdi on 6/5/2016.
 */
public interface ICallback {
    public void onSuccess(Timetables timetables);
    public void onError();
}
