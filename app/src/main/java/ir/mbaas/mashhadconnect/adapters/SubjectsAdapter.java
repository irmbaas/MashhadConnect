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
import ir.mbaas.mashhadconnect.models.Subject;

/**
 * Created by Mehdi on 7/10/2016.
 */
public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.SubjectHolder> {

    private int selectedItem = -1;
    private List<Subject> subjects;

    public class SubjectHolder extends RecyclerView.ViewHolder {
        public TextView title, lecturer, description;
        public ImageView poster;

        public SubjectHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title);
            description = (TextView) view.findViewById(R.id.tv_desc);
            lecturer = (TextView) view.findViewById(R.id.tv_lecturer);
            poster = (ImageView) view.findViewById(R.id.iv_poster);
        }
    }

    public SubjectsAdapter(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public SubjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_subjects, parent, false);

        return new SubjectHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SubjectHolder holder, int position) {
        Subject subject = subjects.get(position);

        holder.title.setText(subject.title);
        holder.description.setText(Html.fromHtml(holder.itemView.getResources()
                .getString(subject.description)));
        holder.lecturer.setText(subject.lecturer);
        holder.poster.setImageResource(subject.image);
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void setSelectedItem(int pos) {
        selectedItem = pos;
    }

    public int getSelectedItem() {
        return selectedItem;
    }
}
