package com.openclassrooms.entrevoisins.ui.neighbour_Info.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_Info.Activity.NeighbourInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class NeighbourInfoHeaderFragment extends Fragment {


    private TextView mTextView;
    private ImageView mImageView;
    private Neighbour mNeighbour;


    public NeighbourInfoHeaderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_neighbour_info_header, container, false);
        mTextView = mView.findViewById(R.id.header_text);
        mImageView = mView.findViewById(R.id.header_image);
        udpateUIWithInfos();

        return mView;
    }


    /**
     * Method to get the neighbours infos and put in the UI
     */

    public void udpateUIWithInfos() {
        mNeighbour = NeighbourInfo.getNeighbourInFragment();
        mTextView.setText(mNeighbour.getName());
        Glide.with(this)
                .load(mNeighbour.getAvatarUrl())
                .into(mImageView);

    }


}
