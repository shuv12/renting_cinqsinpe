package app.com.example.android.renting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {
/*
    private TextView textwelcome;
    private CallbackManager mCallbackManager;
    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            if (profile != null){
                textwelcome.setText("Welcome");
            }

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        gettosignup();
    }



    private void gettosignup() {
        TextView textView = (TextView) findViewById(R.id.signuptobutton);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), signup.class);
                startActivity(i);
            }
        });


        Button button = (Button) findViewById(R.id.signinbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainHome.class);
                startActivity(i);
            }
        });

    }



    //@Override
   // protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     //   super.onActivityResult(requestCode, resultCode, data);
      //  mCallbackManager.onActivityResult(requestCode,resultCode,data);
    //}
}
