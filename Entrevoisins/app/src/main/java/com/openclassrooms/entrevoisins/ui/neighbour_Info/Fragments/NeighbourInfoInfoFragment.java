package com.openclassrooms.entrevoisins.ui.neighbour_Info.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_Info.Activity.NeighbourInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class NeighbourInfoInfoFragment extends Fragment {

    private Neighbour mNeighbour;
    private TextView mName;
    private TextView mLocation;
    private TextView mPhoneNumber;
    private TextView mSocialLink;


    public NeighbourInfoInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_neighbour_info_info, container, false);
        mName = view.findViewById(R.id.infos_name);
        mLocation = view.findViewById(R.id.infos_location);
        mPhoneNumber = view.findViewById(R.id.infos_phone_number);
        mSocialLink = view.findViewById(R.id.infos_social_link);
        udpateUiWithInfos();
        return view;
    }

    private void udpateUiWithInfos() {
        mNeighbour = NeighbourInfo.getNeighbourInFragment();
        mName.setText(mNeighbour.getName());
    }

}
