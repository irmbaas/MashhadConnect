package ir.mbaas.mashhadconnect.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ir.mbaas.mashhadconnect.R;
import ir.mbaas.mashhadconnect.activities.MainActivity;
import ir.mbaas.mashhadconnect.helpers.StaticMethods;
import ir.mbaas.mashhadconnect.models.Sponsor;
import ir.mbaas.mashhadconnect.models.Subject;

/**
 * Created by Mehdi on 7/10/2016.
 */
public class SponsorsAdapter extends RecyclerView.Adapter<SponsorsAdapter.SponsorHolder> {

    private int selectedItem = -1;
    private List<Sponsor> sponsors;
    private MainActivity mainActivity;

    public class SponsorHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ImageView logo;
        public Button website, news;

        public SponsorHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title);
            description = (TextView) view.findViewById(R.id.tv_desc);
            logo = (ImageView) view.findViewById(R.id.iv_logo);
            website = (Button) view.findViewById(R.id.btn_website);
            news = (Button) view.findViewById(R.id.btn_news);
        }
    }

    public SponsorsAdapter(List<Sponsor> sponsors, MainActivity mainActivity) {
        this.sponsors = sponsors;
        this.mainActivity = mainActivity;
    }

    @Override
    public SponsorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_sponsors, parent, false);

        return new SponsorHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SponsorHolder holder, int position) {
        Sponsor sponsor = sponsors.get(position);

        holder.title.setText(sponsor.title);
        holder.description.setText(Html.fromHtml(holder.itemView.getResources()
                .getString(sponsor.description)));

        Glide.with(holder.itemView.getContext())
                .load(sponsor.logo)
                .into(holder.logo);

        final String urlStr = holder.itemView.getResources().getString(sponsor.url);
        final String rssStr = holder.itemView.getResources().getString(sponsor.rss);

        if (urlStr != null  && !urlStr.isEmpty()) {
            holder.website.setVisibility(View.VISIBLE);
            holder.website.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StaticMethods.openBrowser(holder.itemView.getContext(), urlStr);
                }
            });
        } else {
            holder.website.setVisibility(View.GONE);
        }

        if (rssStr != null  && !rssStr.isEmpty()) {
            holder.news.setVisibility(View.VISIBLE);
            holder.news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mainActivity == null)
                        return;
                    mainActivity.createRssFragment(rssStr);
                }
            });
        } else {
            holder.news.setVisibility(View.GONE);
        }
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
