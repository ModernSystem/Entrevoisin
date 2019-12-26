package com.openclassrooms.entrevoisins.ui.neighbour_fav;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.Utils.ItemClickSupport;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_Info.Activity.NeighbourInfo;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import java.util.List;

import static org.greenrobot.eventbus.EventBus.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteNeighbourListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Neighbour> mNeighbours;


    public FavoriteNeighbourListFragment() {
        // Required empty public constructor
    }

    public static FavoriteNeighbourListFragment newInstance() {
        FavoriteNeighbourListFragment fragment = new FavoriteNeighbourListFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: initialized ");
        initList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initList();
        configureOnClickRecyclerView();
        return view;
    }

    /**
     * Initiate Favorite List
     */

    private void initList() {
        mNeighbours = ListNeighbourActivity.mFavNeighbourList;
        mRecyclerView.setAdapter(new FavoriteListAdapter(mNeighbours));
    }

    /**
     * Configure the RecyclerView ( set an item click listener on the Neighbours Item )
     */

    public void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_neighbour)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(getActivity(), NeighbourInfo.class);
                        Neighbour mId = mNeighbours.get(position);
                        intent.putExtra("text", mId);
                        startActivity(intent);
                    }
                });
    }

}
