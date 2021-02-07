package com.sthoksdevs.sthoks.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sthoksdevs.sthoks.R;

public class AboutFragment extends Fragment implements OnMapReadyCallback {
    GoogleMap mMap;

    @Override 
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override 
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_about, viewGroup, false);
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        return inflate;
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        this.mMap.addMarker(new MarkerOptions().position(new LatLng(-25.7058081d, 28.1261017d)));
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-25.7058081d, 28.1261017d), 13.0f));
    }
}
