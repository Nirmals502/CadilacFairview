package mobile.cadilacfairview;

import android.Manifest;
import android.content.Intent;
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
                Intent int_login = new Intent(Find_offer_screen.this, Scanning_screen.class);
                startActivity(int_login);
                finish();
//                String check_text = Btn_allow.getText().toString();
//                if (check_text.contentEquals("ALLOW")) {
//                    requestPermissionIfNeeded();
//                    Btn_allow.setText("STOP SCANNING");
//                } else if (check_text.contentEquals("STOP SCANNING")) {
//                    //requestPermissionIfNeeded();
//                    stopAudioScan();
//                    Btn_allow.setText("ALLOW");
//                }


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

            } else {

                beginAudioScan();
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
                    beginAudioScan();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
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

    private void stopAudioScan() {
        //scanButton.setText("Scan");
        soundpays.stopAudioScan();
        scanning = false;
    }

    private void beginAudioScan() {

        //Please declare what you wish to do below once a code has been retrieved
        soundpays.beginAudioScan(new SoundpaysAudioCallback() {
            @Override
            public void onSuccess() {
                //This is the main callback when a code is detected use getCode to retrieve the code.
                receivedCode(getCode());

            }

            @Override
            public void onFailure(Exception e) {

            }
        }, 10);
    }

    private void receivedCode(final String code) {
        /* The code will either return as
        0 for timeout or non valid code if a valid list of codes was specified when calling soundpays.beginAudioScan()
       -1 if sdk requires app mic permissions
        8 digit code if code was detected and considered valid.
        */
        Find_offer_screen.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stopAudioScan();
                if (code == "-1") {
                    Toast.makeText(Find_offer_screen.this, "Mic Permission Needed", Toast.LENGTH_LONG).show();
                    requestPermissionIfNeeded();
                } else if (code == "0") {
                    Toast.makeText(Find_offer_screen.this, "Scanning timed out or valid code not found", Toast.LENGTH_LONG).show();
                    //codeTextView.setText("Scanning timed out or valid code not found");
                } else {
                    Toast.makeText(Find_offer_screen.this, code, Toast.LENGTH_LONG).show();
                    Intent int_login = new Intent(Find_offer_screen.this, Scanning_screen.class);
                    startActivity(int_login);
                    finish();
                }
            }
        });
    }
}
