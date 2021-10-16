package com.app.rifluxyssofficetask;

import android.app.Activity;
import android.text.TextUtils;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StaticDataConvert {

    private final Activity  activity;

    // creating array list for adding all our locations.
    // in below line we are initializing our array list.
    private final List<MarkerData> locationArrayList = new ArrayList<>();

    public StaticDataConvert(Activity activity) {
        this.activity = activity;
    }

    protected List<OfficeDetailsPojo> getStaticDataConvertList() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        InputStream is = activity.getResources().openRawResource(R.raw.officedetails_data);
        List<OfficeDetailsPojo> officeDetailsPojoList = gson.fromJson(new InputStreamReader(is) , new TypeToken<List<OfficeDetailsPojo>>() {}.getType());
        for (OfficeDetailsPojo officeDetailsPojo:officeDetailsPojoList) {
            officeDetailsPojo.setOfficeName(officeDetailsPojo.getOfficeName() == null || TextUtils.isEmpty(officeDetailsPojo.getOfficeName()) ? "-" : officeDetailsPojo.getOfficeName());
            officeDetailsPojo.setOfficeAddress1(officeDetailsPojo.getOfficeAddress1() == null || TextUtils.isEmpty(officeDetailsPojo.getOfficeAddress1()) ? "-" : officeDetailsPojo.getOfficeAddress1());
            officeDetailsPojo.setOfficeAddress2(officeDetailsPojo.getOfficeAddress2() == null || TextUtils.isEmpty(officeDetailsPojo.getOfficeAddress2()) ? "-" : officeDetailsPojo.getOfficeAddress2());
            officeDetailsPojo.setOfficePhone(officeDetailsPojo.getOfficePhone() == null || TextUtils.isEmpty(officeDetailsPojo.getOfficePhone()) ? "-" : officeDetailsPojo.getOfficePhone());
            officeDetailsPojo.setOfficePhone(officeDetailsPojo.getOfficePhone() == null || TextUtils.isEmpty(officeDetailsPojo.getOfficePhone()) ? "-" : officeDetailsPojo.getOfficePhone());
            officeDetailsPojo.setOfficeEmail(officeDetailsPojo.getOfficeEmail() == null || TextUtils.isEmpty(officeDetailsPojo.getOfficeEmail()) ? "-" : officeDetailsPojo.getOfficeEmail());
            officeDetailsPojo.setOfficeWebsite(officeDetailsPojo.getOfficeWebsite() == null || TextUtils.isEmpty(officeDetailsPojo.getOfficeWebsite()) ? "-" : officeDetailsPojo.getOfficeWebsite());
            officeDetailsPojo.setOfficeFacebook(officeDetailsPojo.getOfficeFacebook() == null || TextUtils.isEmpty(officeDetailsPojo.getOfficeFacebook()) ? "-" : officeDetailsPojo.getOfficeFacebook());
        }
        return officeDetailsPojoList;
    }

    protected List<MarkerData> getLatLngList() {

        List<OfficeDetailsPojo> officeDetailsPojos = getStaticDataConvertList();
        if (officeDetailsPojos.size() > 0) {
            for (OfficeDetailsPojo detailsPojo:officeDetailsPojos) {
                // on below line we are adding our locations in our list.
                locationArrayList.add(new MarkerData(new LatLng(Double.parseDouble(detailsPojo.getLatitute()),Double.parseDouble(detailsPojo.getLongitute())),detailsPojo.getOfficeName()));
            }
        }
        return locationArrayList;
    }
}
