package com.openclassrooms.entrevoisins.ui.neighbour_Info.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NeighbourAboutFragment extends Fragment {


    public NeighbourAboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_neighbour_about, container, false);
    }

}
