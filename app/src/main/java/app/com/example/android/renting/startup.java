package app.com.example.android.renting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class startup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        getanimationfrombottom();
        getuserselection();

    }

    public void getanimationfrombottom(){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutwithanim);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_buttom_up);
        linearLayout.setAnimation(animation);
    }

    private void getuserselection() {
        ImageView imageView = (ImageView) findViewById(R.id.quesinfo);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(startup.this);
                builder.setTitle("No Worries");
                builder.setMessage("If you are looking for rentals, view as 'Rent seeker' or select 'Owner' if you are looking for tenant\n You can change this whenever you want");
                builder.setCancelable(true);
                builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        Button enterbutton = (Button) findViewById(R.id.enterbutton);
        enterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(), login.class));
                startActivity(i);
            }
        });
    }
}
