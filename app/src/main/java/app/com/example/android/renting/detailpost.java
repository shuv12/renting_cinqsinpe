package app.com.example.android.renting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class detailpost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpost);
        Bundle extras = getIntent().getExtras();
        String title = extras.getString("titlekey");

        TextView textView = (TextView) findViewById(R.id.welcomedetailtext);
        textView.setText("Welcome to detail view of : " + title);
    }
}
