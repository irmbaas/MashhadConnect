package ir.mbaas.mashhadconnect.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ir.mbaas.mashhadconnect.R;
import ir.mbaas.mashhadconnect.models.RssFeed;
import ir.mbaas.mashhadconnect.helpers.StaticMethods;

/**
 * Created by Mahdi on 6/1/2016.
 */
public class RssAdapter extends RecyclerView.Adapter<RssAdapter.RssHolder> {

    private int selectedItem = -1;
    private List<RssFeed> rssFeeds;

    public class RssHolder extends RecyclerView.ViewHolder {
        public TextView title, date, description;

        public RssHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title);
            description = (TextView) view.findViewById(R.id.tv_desc);
            date = (TextView) view.findViewById(R.id.tv_date);
        }
    }

    public RssAdapter(List<RssFeed> rssFeeds) {
        this.rssFeeds = rssFeeds;
    }

    @Override
    public RssHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_rss, parent, false);

        return new RssHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RssHolder holder, int position) {
        final RssFeed rssFeed = rssFeeds.get(position);

        holder.title.setText(rssFeed.title);
        holder.description.setText(Html.fromHtml(rssFeed.description));
        holder.date.setText(StaticMethods.formatDatetime(rssFeed.pubDate));

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rssFeed.link == null || rssFeed.link.isEmpty())
                    return;

                StaticMethods.openBrowser(holder.itemView.getContext(), rssFeed.link);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rssFeeds.size();
    }

    public void setSelectedItem(int pos) {
        selectedItem = pos;
    }

    public int getSelectedItem() {
        return selectedItem;
    }
}
