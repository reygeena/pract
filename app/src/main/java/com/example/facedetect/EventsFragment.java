package com.example.facedetect;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventsFragment   extends Fragment {

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         return  inflater.inflate(R.layout.fragment_events, container, false);
    /*String[] menuItems={ "No Class Tomorrow",
            "Sports Day",
            "ICT Meetup",
            "Parents Day",
            "Parents Teacher Meeting",

    };
    ListView listView=(ListView)view.findViewById(R.id.list);
        ArrayAdapter<String>listViewAdapter=new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems
        );
        listView.setAdapter(listViewAdapter);
        return  view;*/
    }
}