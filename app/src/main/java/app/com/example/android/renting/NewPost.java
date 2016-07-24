package app.com.example.android.renting;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;

public class NewPost extends AppCompatActivity {
    private static final int SELECT_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);


        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hscrollv);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_left_to_right);
        horizontalScrollView.setAnimation(animation);


       /* FrameLayout frameLayout = (FrameLayout) findViewById(R.id.map_frag);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GetLocationMapsActivity.class);
                startActivity(intent);
            }
        });*/

       /* final LatLng baneshwor = new LatLng(27.280000,87.290000);
        GoogleMap googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        Marker marker = googleMap.addMarker(new MarkerOptions().position(baneshwor).draggable(true).title("SET Location"));
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent intent = new Intent(getApplicationContext(),GetLocationMapsActivity.class);
                startActivity(intent);
            }
        });*/





        ImageButton imageuploadbutton = (ImageButton) findViewById(R.id.add_home_image);
        imageuploadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickintent = new Intent();
                pickintent.setType("image/*");
                pickintent.setAction(Intent.ACTION_GET_CONTENT);

                Intent takephotointent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                String pickTitle = "Select or take new Picture";

                Intent chooserIntent = Intent.createChooser(pickintent,pickTitle);
                chooserIntent.putExtra(
                        Intent.EXTRA_INITIAL_INTENTS,
                        new Intent[]{takephotointent}
                );
                startActivityForResult(chooserIntent,SELECT_PICTURE);
            }
        });
    }
}
