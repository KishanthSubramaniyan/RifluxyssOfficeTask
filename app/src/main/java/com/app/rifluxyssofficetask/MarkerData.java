package com.app.rifluxyssofficetask;

import com.google.android.gms.maps.model.LatLng;

public class MarkerData {

    private LatLng latLng;
    private String tittle;

    public MarkerData(LatLng latLng, String tittle) {
        this.latLng = latLng;
        this.tittle = tittle;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getTittle() {
        return tittle;
    }

}
