package ir.mbaas.mashhadconnect.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.mbaas.mashhadconnect.R;
import ir.mbaas.mashhadconnect.models.Sponsor;
import ir.mbaas.mashhadconnect.models.Timetable;
import ir.mbaas.mashhadconnect.models.Timetables;

/**
 * Created by Mehdi on 7/10/2016.
 */
public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableHolder> {

    private int selectedItem = -1;
    private List<Timetable> timetables;

    public class TimetableHolder extends RecyclerView.ViewHolder {
        public TextView title, description, lecturer;
        public ImageView lecturerImg;

        public TimetableHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title);
            description = (TextView) view.findViewById(R.id.tv_desc);
            lecturerImg = (ImageView) view.findViewById(R.id.civ_lecturer);
            lecturer = (TextView) view.findViewById(R.id.tv_lecturer);
        }
    }

    public TimetableAdapter(Timetables timetables) {
        this.timetables = timetables.records;
    }

    @Override
    public TimetableHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_timetable, parent, false);

        return new TimetableHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TimetableHolder holder, int position) {
        Timetable timetable = timetables.get(position);

        holder.title.setText(timetable.title);
        holder.description.setText(timetable.description);

        if (timetable.lecturerImage == null || timetable.lecturerImage.isEmpty()) {
            holder.lecturerImg.setVisibility(View.GONE);
        }

        if (timetable.lecturer == null || timetable.lecturer.isEmpty()) {
            holder.lecturer.setVisibility(View.GONE);
        } else {
            holder.lecturer.setText(timetable.lecturer);
        }
    }

    @Override
    public int getItemCount() {
        return timetables.size();
    }

    public void setSelectedItem(int pos) {
        selectedItem = pos;
    }

    public int getSelectedItem() {
        return selectedItem;
    }
}
