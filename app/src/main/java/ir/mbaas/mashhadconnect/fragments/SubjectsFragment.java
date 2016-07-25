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
import ir.mbaas.mashhadconnect.adapters.RssAdapter;
import ir.mbaas.mashhadconnect.adapters.SubjectsAdapter;
import ir.mbaas.mashhadconnect.listeners.ClickListener;
import ir.mbaas.mashhadconnect.listeners.RecyclerTouchListener;
import ir.mbaas.mashhadconnect.models.RssFeed;
import ir.mbaas.mashhadconnect.models.Subject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectsFragment extends Fragment {

    private List<Subject> subjects = new ArrayList<>();

    private RecyclerView recyclerView;
    private SubjectsAdapter mAdapter;

    public SubjectsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subjects, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_subjects);

        initializeSubjects();
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

    private void initializeSubjects() {
        Subject subject = new Subject(R.string.subject1_title, R.string.subject1_description,
                R.string.subject1_lecturer, R.drawable.big_data, R.drawable.az);
        subjects.add(subject);

        subject = new Subject(R.string.subject2_title, R.string.subject2_description,
                R.string.subject2_lecturer, R.drawable.ci, R.drawable.mh);
        subjects.add(subject);

        subject = new Subject(R.string.subject3_title, R.string.subject3_description,
                R.string.subject3_lecturer, R.drawable.iot, R.drawable.he);
        subjects.add(subject);
        mAdapter = new SubjectsAdapter(subjects);
    }

}
