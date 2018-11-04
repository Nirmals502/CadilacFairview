package mobile.cadilacfairview;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.soundpays.sdk.Soundpays;
import com.soundpays.sdk.SoundpaysConstants;
import com.soundpays.sdk.callbacks.SoundpaysAudioCallback;

import Animations.animations;
import DATAMODEL.Shared_preference_model;

public class Find_offer_screen extends AppCompatActivity {
    Button Btn_allow;
    ProgressBar pro_bar;
    animations anim = new animations();
    Soundpays soundpays;
    Boolean scanning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_offer_screen);
        Btn_allow = (Button) findViewById(R.id.button_allow);
        pro_bar = (ProgressBar) findViewById(R.id.progressBar2);
        pro_bar.setAnimation(anim.Rotate_animation());
        soundpays = Soundpays.getInstance(Find_offer_screen.this.getApplication());
        Btn_allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestPermissionIfNeeded();
            }
        });


    }


    private void requestPermissionIfNeeded() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (Find_offer_screen.this.checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                Find_offer_screen.this.requestPermissions(
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        SoundpaysConstants.RECORD_AUDIO_REQUEST);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case SoundpaysConstants.RECORD_AUDIO_REQUEST: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(Find_offer_screen.this,"Permission Granted",Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE).edit();
                    editor.putString("Permission", "Permission Granted");

                    editor.apply();

                    Intent int_login = new Intent(Find_offer_screen.this, Scanning_screen.class);
                    startActivity(int_login);
                    finish();
                    //beginAudioScan();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE).edit();
                    editor.putString("Permission", "Permission Denied");

                    editor.apply();
                    Intent int_login = new Intent(Find_offer_screen.this, Home_screen.class);
                    startActivity(int_login);
                    finish();
                    // Toast.makeText(Find_offer_screen.this,"Permission Denied",Toast.LENGTH_LONG).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}
