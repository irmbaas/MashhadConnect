package ir.mbaas.mashhadconnect.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.mbaas.mashhadconnect.R;
import ir.mbaas.mashhadconnect.adapters.TimetableAdapter;
import ir.mbaas.mashhadconnect.helpers.ICallback;
import ir.mbaas.mashhadconnect.helpers.TimetableFile;
import ir.mbaas.mashhadconnect.models.Timetable;
import ir.mbaas.mashhadconnect.models.Timetables;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimetableFragment extends Fragment implements ICallback {
    private Timetables timetables;

    private RecyclerView recyclerView;
    private TimetableAdapter mAdapter;

    public TimetableFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        new TimetableFile(getActivity(), R.raw.timetable, this).execute();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_timetable);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        timetables = new Timetables();
        mAdapter = new TimetableAdapter(timetables);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onSuccess(Timetables timetables) {
        if (timetables == null || timetables.records.size() == 0)
            return;

        this.timetables.records.clear();

        for (Timetable timetable : timetables.records) {
            this.timetables.records.add(timetable);
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {

    }
}
