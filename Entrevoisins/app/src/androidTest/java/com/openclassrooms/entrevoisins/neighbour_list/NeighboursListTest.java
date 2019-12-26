package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_Info.Activity.NeighbourInfo;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private NeighbourApiService service;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);


    private ListNeighbourActivity mActivity;

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        ListNeighbourActivity.initList();

    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        int ITEMS_COUNT=12;
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * When we click on an item, details activity is lunched
     */
    @Test
    public void myNeighboursList_iTemClick_Lunch_Details() {

        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(ViewMatchers.withId(R.id.neighbourInfo)).check(matches(isDisplayed()));
    }

    /**
     * On the details activity, we check that the textview is fill
     */
    @Test
    public void NeighbourInfo_textView_check() {
        //We click on an  in position 1
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        // We check that the textview is fill with the neighbour we click on name
        onView(ViewMatchers.withId(R.id.header_text))
                .check(matches(withText(NeighbourInfo.getNeighbourInFragment().getName())));
    }

    @Before
    public void setFavList(){
        service = DI.getNewInstanceApiService();
        Neighbour mNeighbourToAdd1 = service.getNeighbours().get(1);
        Neighbour mNeighbourToAdd2 = service.getNeighbours().get(2);
        ListNeighbourActivity.addFav(mNeighbourToAdd1);
        ListNeighbourActivity.addFav(mNeighbourToAdd2);

    }

    @Test
    public void NeighbourFavList_check(){
        setFavList();
        onView(ViewMatchers.withId(R.id.fav_neighbours_list))
                .check(withItemCount(ListNeighbourActivity.mFavNeighbourList.size()));
        int i=0;
        while (i<=ListNeighbourActivity.mFavNeighbourList.size()){

            ListNeighbourActivity.mFavNeighbourList.contains( onData(ViewMatchers.withId(R.id.fav_neighbours_list))
                    .atPosition(i));
            i++;

        }
    }
}