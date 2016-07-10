package ir.mbaas.mashhadconnect.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ir.mbaas.mashhadconnect.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroductionFragment extends Fragment {


    public IntroductionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_introduction, container, false);

        TextView introTextPart1 = (TextView) view.findViewById(R.id.tv_connect_text1);
        introTextPart1.setText(Html.fromHtml(view.getResources()
                .getString(R.string.connect_introduction_text1)));

        TextView introTextPart2 = (TextView) view.findViewById(R.id.tv_connect_text2);
        introTextPart2.setText(Html.fromHtml(view.getResources()
                .getString(R.string.connect_introduction_text2)));

        return view;
    }

}
