package com.app.rifluxyssofficetask;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.BaseObservable;

import com.app.rifluxyssofficetask.databinding.ActivityFidelitynationalBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Objects;

/**
 * The type Fidelity view models.
 * Developed By : KishanthSubramaniyan
 * created Date : 14-Oct-2021
 */
public class FidelityViewModels extends BaseObservable implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, OfficeDetailsListAdapter.OnOfficeDetailsListener {

    //Initalize Object of the Activity FidelityNationalActivity
    private final FidelityNationalActivity mFidelityNationalActivity;

    //Initalize Object of the Databinding of ActivityFidelitynationalBinding
    private final ActivityFidelitynationalBinding mActivityFidelitynationalBinding;

    //Initalize Object of the officeDetailsPojoList
    private List<OfficeDetailsPojo> officeDetailsPojoList;

    //Initalize Object of the OfficeDetailsListAdapter Recyclerview Adapter
    private OfficeDetailsListAdapter officeDetailsListAdapter;

    //Initalize int variable
    private int adapterPosition;

    /**
     * Instantiates a new Fidelity view models.
     *
     * @param fidelityNationalActivity         the fidelity national activity
     * @param mActivityFidelitynationalBinding the m activity fidelitynational binding
     */
    public FidelityViewModels(FidelityNationalActivity fidelityNationalActivity, ActivityFidelitynationalBinding mActivityFidelitynationalBinding) {
        this.mFidelityNationalActivity = fidelityNationalActivity;
        this.mActivityFidelitynationalBinding = mActivityFidelitynationalBinding;
        initializeView();
    }

    /*initalize the method of the map */
    private void initializeView() {

        /*Gogle map Async in this Line*/
        SupportMapFragment mapFragment = (SupportMapFragment) mFidelityNationalActivity.getSupportFragmentManager().findFragmentById(R.id.map);
        if(mapFragment != null) { mapFragment.getMapAsync(this); }

        /*Static Data conversion of the List of the OfficeDetailPojo */
        officeDetailsPojoList = new StaticDataConvert(mFidelityNationalActivity).getStaticDataConvertList();
        /*Recyclerview Adapter initalize the OfficeDetailsListAdapter */
        officeDetailsListAdapter = new OfficeDetailsListAdapter(officeDetailsPojoList, this);
        /* Databinding using the set the RecyclerView Adapter */
        mActivityFidelitynationalBinding.setOfficeDetailsAdapter(officeDetailsListAdapter);

    }

    /**
     * Onclose clicked.
     */
    public void oncloseClicked() {
        /*on header backpresss click finish the Application */
        mFidelityNationalActivity.finishAffinity();
    }


    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

        // marker click the position of the getId using the return click Position
        int markerClickPosition = Integer.parseInt(marker.getId().replace(mFidelityNationalActivity.getResources().getString(R.string.m), ""));
        Log.e("position","marker---> " + markerClickPosition);
        //fullDetails Show onMarker Click based of the show the List of OffficeDetails full view
        OfficeDetailsPojo officeDetailsPojo = officeDetailsPojoList.get(markerClickPosition);
        //notify item changed to view the full details of the List
        officeDetailsListAdapter.setData(officeDetailsPojo, markerClickPosition);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //google map marker info window configure
        googleMap.setOnInfoWindowClickListener(this);

        // inside on map ready method
        // we will be displaying all our markers.
        // for adding markers we are running for loop and
        // inside that we are drawing marker on our map.
        List<MarkerData> locationList = new StaticDataConvert(mFidelityNationalActivity).getLatLngList();
        if (locationList.size() > 0) {

            for (MarkerData markerData : locationList) {

                // below line is use to add marker to each location of our array list.
                googleMap.addMarker(new MarkerOptions().position(markerData.getLatLng()).title(markerData.getTittle()));

                // below lin is use to zoom our camera on map.
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(11.0f));

                // below line is use to move our camera to the specific location.
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerData.getLatLng(), 11));
            }
        }
    }

    /*Adapter directions && Call && Email && website && Facebook click item configure the Viewmodel */
    /* using interface based on the click event */
    @Override
    public void onOfficeDetailsShow(int adapterPosition, int clickPosition) {

        /*Click position based on the switch case execute the condition */
        switch (clickPosition) {

            case 1:

                /*latitude && longtitude based on the redirect the Google map of the Intent */
                /* start activity of the corresponding latitude && longtitude */
                String url = "http://maps.google.com/maps?q=loc:" + officeDetailsPojoList.get(adapterPosition).getLatitute() + "," + officeDetailsPojoList.get(adapterPosition).getLongitute() + " (" + officeDetailsPojoList.get(adapterPosition).getOfficeName() + ")";
                Intent intentAddress = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                mFidelityNationalActivity.startActivity(intentAddress);
                break;

            case 2:

                /*call Permission check in this Line*/
                int permissionLocation = ContextCompat.checkSelfPermission(Objects.requireNonNull(mFidelityNationalActivity), Manifest.permission.CALL_PHONE);
                /*if permission call Phone granted go to the permission granted*/
                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {

                    /*office Phone number based on the redirect the Call of the Intent */
                    /* start activity of the corresponding office Phone number */
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + officeDetailsPojoList.get(adapterPosition).getOfficePhone().replace("P:", "")));
                    mFidelityNationalActivity.startActivity(callIntent);

                } else {

                    /* store the adapter position */
                    /* requesting Call phone Permission */
                    this.adapterPosition = adapterPosition;
                    ActivityCompat.requestPermissions(mFidelityNationalActivity, new String[]{Manifest.permission.CALL_PHONE}, 100);
                }
                break;

            case 3:

                /* chhecking the officeEmail not Null && officeEmail not empty checking */
                if (officeDetailsPojoList.get(adapterPosition).getOfficeEmail() != null && !officeDetailsPojoList.get(adapterPosition).getOfficeEmail().equals("-")) {

                    /* Intent of the compose Email */
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_EMAIL, officeDetailsPojoList.get(adapterPosition).getOfficeEmail());
                    mFidelityNationalActivity.startActivity(Intent.createChooser(intent, "Send Email"));
                }
                break;

            case 4:

                /* checking the OfficeWebsite not Null && OfficeWebsite not empty checking */
                if (officeDetailsPojoList.get(adapterPosition).getOfficeWebsite() != null && !officeDetailsPojoList.get(adapterPosition).getOfficeWebsite().equals("-")) {

                    /*Intent of the Website redirect */
                    String officeWebsite = officeDetailsPojoList.get(adapterPosition).getOfficeWebsite().replace("https://", "");
                    Intent intentURL = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + officeWebsite));
                    mFidelityNationalActivity.startActivity(intentURL);
                }
                break;

            case 5:

                /* checking the OfficeFacebook not Null && OfficeFacebook not empty checking */
                if (officeDetailsPojoList.get(adapterPosition).getOfficeFacebook() != null && !officeDetailsPojoList.get(adapterPosition).getOfficeFacebook().equals("-")) {

                    try {
                        /*Intent of the Facebook Profile Page redirect */
                        Intent intentFacebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/n/?" + officeDetailsPojoList.get(adapterPosition).getOfficeFacebook().replace(" ", "").trim() + "/"));
                        /*package manager resolveActivity not null checking in this line */
                        mFidelityNationalActivity.startActivity(intentFacebook);
                    } catch (ActivityNotFoundException activityNotFoundException) {
                        activityNotFoundException.printStackTrace();
                    }

                }
                break;
        }
    }

    /**
     * Call phone permission.
     */
    /*Call Phone Permission user Allow redirect the Action Call */
    public void callPhonePermission() {

        /*store the adapterPosition based on the Call the Office Phone number */
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + officeDetailsPojoList.get(adapterPosition).getOfficePhone().replace("P:", "")));
        /* startActivity of the callIntent */
        mFidelityNationalActivity.startActivity(callIntent);

    }

}

