package com.openclassrooms.entrevoisins.ui.neighbour_Info.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_Info.Fragments.NeighbourAboutFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_Info.Fragments.NeighbourInfoHeaderFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_Info.Fragments.NeighbourInfoInfoFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

public class NeighbourInfo extends AppCompatActivity {

    /**
     * Handle 3 fragments ( for neighbours informations ).
     */
    static Neighbour mNeighbour;
    private NeighbourInfoHeaderFragment mNeighbourInfoHeaderFragment;
    private NeighbourInfoInfoFragment mNeighbourInfoInfoFragment;
    private NeighbourAboutFragment mNeighbourAboutFragment;
    private FloatingActionButton mFavButton;
    private ImageButton mImageButton;

    /**
     *
     * @return neighbour in different fragments
     */

    public static Neighbour getNeighbourInFragment() {
        return mNeighbour;
    }



    @Override
    protected void onResume() {
        super.onResume();
        // FavButton star configuration
        favButtonStar();
    }


    // Configure fragments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_info);

        //FavButton configuration

        mFavButton = findViewById(R.id.neighbourInfo_fav_button);
        favButtonStar();
        mFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrRemoveFav();
            }
        });

        //BackButton Configuration

        mImageButton = findViewById(R.id.neighbourInfo_backButton);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        configureFragment();
        getNeighbour();
    }

    /**
     * Configure fragments in the activity
     */

    private void configureFragment() {

        mNeighbourInfoHeaderFragment = new NeighbourInfoHeaderFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.neighbourInfo_header_activity, mNeighbourInfoHeaderFragment)
                .commit();
        mNeighbourInfoInfoFragment = new NeighbourInfoInfoFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.neighbourInfo_infos, mNeighbourInfoInfoFragment)
                .commit();
        mNeighbourAboutFragment = new NeighbourAboutFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.neighbourInfo_description, mNeighbourAboutFragment)
                .commit();


    }


    //Get the neighbour info we ask to see
    public Neighbour getNeighbour() {
        mNeighbour = (Neighbour) getIntent().getSerializableExtra("text");
        return mNeighbour;
    }

    /**
     * Method to add or remove a fav from our List
     */

    private void addOrRemoveFav() {
        if (ListNeighbourActivity.mFavNeighbourList.contains(mNeighbour)) {
            ListNeighbourActivity.removeFav(mNeighbour);
            mFavButton.setImageResource(R.drawable.ic_star_border_white_24dp);

        } else {
            ListNeighbourActivity.addFav(mNeighbour);
            mFavButton.setImageResource(R.drawable.ic_star_white_24dp);
        }


    }

    /**
     * Method to set the star empty or not if neighbour is in our favlist
     */

    private void favButtonStar() {
        if (ListNeighbourActivity.mFavNeighbourList.contains(mNeighbour)) {
            mFavButton.setImageResource(R.drawable.ic_star_white_24dp);
        } else
            mFavButton.setImageResource(R.drawable.ic_star_border_white_24dp);
    }

}

