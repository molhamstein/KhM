package com.brainSocket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.brainSocket.khednima3ak.R;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;

import com.google.android.gms.maps.model.Marker;

public class MapInfoWindowAdapter implements InfoWindowAdapter {

	
    private final View markerview;

    public MapInfoWindowAdapter(Context context) {
    	
    	LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        markerview = inflater.inflate(R.layout.map_info_window, null); 
    }

    public View getInfoWindow(Marker marker) {      
        return markerview;
    }

    public View getInfoContents(Marker marker) {
    	
    	
        //LatLng latLng = marker.getPosition();

        // Getting reference to the TextView to set latitude
        TextView name = (TextView) markerview.findViewById(R.id.name);

        name.setText(marker.getTitle());

        // Returning the view containing InfoWindow contents
        return markerview;
    }



}
