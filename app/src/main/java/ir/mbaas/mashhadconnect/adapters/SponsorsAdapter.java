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
import ir.mbaas.mashhadconnect.models.Subject;

/**
 * Created by Mehdi on 7/10/2016.
 */
public class SponsorsAdapter extends RecyclerView.Adapter<SponsorsAdapter.SponsorHolder> {

    private int selectedItem = -1;
    private List<Sponsor> sponsors;

    public class SponsorHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ImageView logo;

        public SponsorHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title);
            description = (TextView) view.findViewById(R.id.tv_desc);
            logo = (ImageView) view.findViewById(R.id.iv_logo);
        }
    }

    public SponsorsAdapter(List<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }

    @Override
    public SponsorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_sponsors, parent, false);

        return new SponsorHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SponsorHolder holder, int position) {
        Sponsor sponsor = sponsors.get(position);

        holder.title.setText(sponsor.title);
        holder.description.setText(Html.fromHtml(holder.itemView.getResources()
                .getString(sponsor.description)));
        holder.logo.setImageResource(sponsor.logo);
    }

    @Override
    public int getItemCount() {
        return sponsors.size();
    }

    public void setSelectedItem(int pos) {
        selectedItem = pos;
    }

    public int getSelectedItem() {
        return selectedItem;
    }
}
