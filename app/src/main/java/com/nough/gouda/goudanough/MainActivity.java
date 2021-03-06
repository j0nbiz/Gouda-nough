package com.nough.gouda.goudanough;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nough.gouda.goudanough.fragments.Header;
import com.nough.gouda.goudanough.fragments.Navigation;
import com.nough.gouda.goudanough.fragments.RestaurantListView;

public class MainActivity extends AppCompatActivity implements Navigation.OnNavigationListener  {
    private static final String TAG = "Main Activity";
    private RestaurantListViewAdapter adapter;
    private Restaurant[] favourite_restaurants;
    // Fragment objects. Should be better named tbh.
    private Header header;
    private Navigation nav;
    private RestaurantListView list;
    private TextView list_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_title = (TextView)findViewById(R.id.main_listview_title);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        // create the fragments to be used in this layout
        header = new Header();
        nav = new Navigation();
        list = new RestaurantListView();
        if(savedInstanceState == null){
            // add the fragments to the linear layout
            ft.add(R.id.main_fragment_container,header, "header");
            ft.add(R.id.main_fragment_container,nav,"navigation_menu");
            ft.add(R.id.list_container, list, "list_view");
            ft.commit();
        }

        // Dummy dataset for the list view adapter
        // should display the recent viewed restaurants.
        Restaurant[] rs = new Restaurant[2];
        rs[0] = new Restaurant("My resto", "https://google.com", "food","514-559-7108",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
        rs[1] = new Restaurant("My resto2", "https://google.com", "food","514-559-7108",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
        adapter = new RestaurantListViewAdapter(this,R.layout.restaurant_listview,rs);

        list.setAdapter(adapter);
    }

    @Override
    public void setFavourites(Restaurant[] favourite_restaurants) {
        this.favourite_restaurants = favourite_restaurants;
        adapter.setDataset(favourite_restaurants);
        list_title.setText("Favourites");
    }
}
