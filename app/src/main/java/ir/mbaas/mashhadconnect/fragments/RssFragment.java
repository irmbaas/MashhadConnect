package ir.mbaas.mashhadconnect.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ir.mbaas.mashhadconnect.R;
import ir.mbaas.mashhadconnect.adapters.RssAdapter;
import ir.mbaas.mashhadconnect.apis.IRssCallback;
import ir.mbaas.mashhadconnect.apis.RequestRss;
import ir.mbaas.mashhadconnect.listeners.ClickListener;
import ir.mbaas.mashhadconnect.listeners.RecyclerTouchListener;
import ir.mbaas.mashhadconnect.models.RssFeed;
import ir.mbaas.mashhadconnect.views.DividerItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class RssFragment extends Fragment implements IRssCallback {

    private List<RssFeed> rssFeeds = new ArrayList<>();

    private RecyclerView recyclerView;
    private RssAdapter mAdapter;
    private TextView tvNoRss;

    public static String rssUrl;

    public RssFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rss, container, false);

        tvNoRss = (TextView) view.findViewById(R.id.tv_no_rss);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_contents);

        mAdapter = new RssAdapter(rssFeeds);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(
                getActivity().getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        RequestRss requestRss = new RequestRss(getActivity(), rssUrl, this);
        requestRss.execute();

        return view;
    }

    @Override
    public void onSuccess(List<RssFeed> feeds) {
        if (feeds == null)
            return;

        rssFeeds.clear();

        for (RssFeed rssFeed : feeds) {
            rssFeeds.add(rssFeed);
        }

        if (feeds.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            tvNoRss.setVisibility(View.GONE);
            mAdapter.notifyDataSetChanged();
        } else {
            tvNoRss.setText(R.string.no_rss_to_show);
        }
    }

    @Override
    public void onError() {
        tvNoRss.setText(R.string.no_rss_to_show);
    }
}
