package ir.mbaas.mashhadconnect.helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

import ir.mbaas.mashhadconnect.models.Timetables;

/**
 * Created by Mehdi on 7/18/2016.
 */
public class TimetableFile extends AsyncTask<Void, Void, Boolean> {
    private String TAG = "TimetableFile";

    private Context context;
    private ICallback ic;
    private int rawFileId;
    private Timetables timetables;

    public TimetableFile(Context context, int rawFileId, ICallback ic) {
        this.context = context;
        this.ic = ic;
        this.rawFileId = rawFileId;
    }

    protected Boolean doInBackground(Void... params) {
        try {
            InputStream contactsStream = context.getResources().openRawResource(rawFileId);
            ObjectMapper mapper = new ObjectMapper();

            if (contactsStream != null)
                timetables = mapper.readValue(contactsStream, Timetables.class);
        } catch (IOException e) {
            onError(e);
            return false;
        } catch (Exception e) {
            onError(e);
            return false;
        }

        return true;
    }

    protected void onPostExecute(Boolean result) {
        if(ic == null)
            return;

        if (result) {
            ic.onSuccess(timetables);
        } else {
            ic.onError();
        }
    }

    protected void onError(Exception e) {
        Log.e(TAG, e != null ? e.toString() : "Error");
    }
}