package app.com.example.android.renting;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Durbarmarg = new LatLng(27.717245, 85.323960);
        LatLng Myhome = new LatLng(27.694030, 85.330178);
        Marker marker1 = mMap.addMarker(new MarkerOptions().position(Myhome).title("Myhome").snippet("Welcome to baneshwor"));
        Marker marker2 = mMap.addMarker(new MarkerOptions().position(Durbarmarg).title("Durbarmarg").snippet("Best place to shop and dine"));
        CameraUpdate homeangle = CameraUpdateFactory.newLatLng(Myhome);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);

        mMap.moveCamera(homeangle);
        mMap.animateCamera(zoom);

        marker1.showInfoWindow();
        marker2.showInfoWindow();
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String title = marker.getTitle();
                Intent i = new Intent(getApplicationContext(), detailpost.class);
                i.putExtra("titlekey",title);
                startActivity(i);
            }
        });
    }
}
