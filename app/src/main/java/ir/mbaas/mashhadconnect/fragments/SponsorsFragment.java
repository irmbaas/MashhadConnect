package ir.mbaas.mashhadconnect.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ir.mbaas.mashhadconnect.R;
import ir.mbaas.mashhadconnect.adapters.SponsorsAdapter;
import ir.mbaas.mashhadconnect.listeners.ClickListener;
import ir.mbaas.mashhadconnect.listeners.RecyclerTouchListener;
import ir.mbaas.mashhadconnect.models.Sponsor;

/**
 * A simple {@link Fragment} subclass.
 */
public class SponsorsFragment extends Fragment {

    private List<Sponsor> sponsors = new ArrayList<>();

    private RecyclerView recyclerView;
    private SponsorsAdapter mAdapter;

    public SponsorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_sponsors);

        initializeSponsors();
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

        return view;
    }

    private void initializeSponsors() {
        Sponsor sponsor = new Sponsor(R.string.sponsor1_title, R.string.sponsor1_description,
                R.drawable.mashadkala, R.string.sponsor1_url, R.string.sponsor1_rss);
        sponsors.add(sponsor);

        sponsor = new Sponsor(R.string.sponsor2_title, R.string.sponsor2_description,
                R.drawable.jamilseir, R.string.sponsor2_url, R.string.sponsor2_rss);
        sponsors.add(sponsor);

        sponsor = new Sponsor(R.string.sponsor3_title, R.string.sponsor3_description,
                R.drawable.erfan, R.string.sponsor3_url, R.string.sponsor3_rss);
        sponsors.add(sponsor);
        mAdapter = new SponsorsAdapter(sponsors);
    }
}
