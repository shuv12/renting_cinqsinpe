package app.com.example.android.renting;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by shuvam on 08-08-2016.
 */
public class Setting extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.perference);
    }
}
