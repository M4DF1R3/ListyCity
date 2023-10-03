package com.example.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private ArrayAdapter<City> cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {
                "Edmonton", "Vancouver"
        };

        String[] provinces = {
                "AB", "BC"
        };
        dataList = new ArrayList<City>();

        for (int i = 0; i < cities.length; i++) {
            City city = new City(cities[i], provinces[i]);
            dataList.add(city);
        }

        cityList = findViewById(R.id.city_list);

        cityAdapter = new CustomList(this, dataList);
        cityList.setAdapter(cityAdapter);

        final FloatingActionButton addButton = findViewById(R.id.add_city_buton);
        addButton.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY");
        });
        setUpListViewListener();
    }

    @Override
    public void onOKPressed(City city) {
        dataList.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    private void setUpListViewListener() {
        cityList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//              Long click on a item and it will edit
                Context context = getApplicationContext();

                // Create new AddCityFragment and show it to user
                AddCityFragment editCityFragment = new AddCityFragment();
                editCityFragment.show(getSupportFragmentManager(), "EDIT_CITY");
                editCityFragment.editCity(dataList.get(i));


                cityAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}