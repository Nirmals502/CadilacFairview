package mobile.cadilacfairview;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.soundpays.sdk.Soundpays;
import com.soundpays.sdk.SoundpaysConstants;
import com.soundpays.sdk.callbacks.SoundpaysAudioCallback;

import DATAMODEL.ApiUtils;
import DATAMODEL.Login_post;
import DATAMODEL.Login_service;
import DATAMODEL.Post_Sound_validate_;
import DATAMODEL.RetrofitClient;
import DATAMODEL.Shared_preference_model;
import DATAMODEL.Souncode_validate_response_body;
import DATAMODEL.Sound_validate_api_service;
import DATAMODEL.response_body;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class Scanning_screen extends AppCompatActivity {
    @BindView(R.id.progressBar)
    ProgressBar pro_bar;
    @BindView(R.id.textView3)
    TextView Txt_back;
    Soundpays soundpays;
    Boolean scanning = false;
    private ProgressDialog pDialog;
    @BindView(R.id.textView)
    TextView Txt_scanning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning_screen);
        ButterKnife.bind(this);
        soundpays = Soundpays.getInstance(Scanning_screen.this.getApplication());
        //pro_bar = (ProgressBar) findViewById(R.id.progressBar);
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        rotate.setDuration(4000);
        rotate.setRepeatCount(Animation.INFINITE);
        pro_bar.setAnimation(rotate);
        requestPermissionIfNeeded();

        //goToNextScreen();

        Txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login = new Intent(Scanning_screen.this, Home_screen.class);
                startActivity(int_login);
                finish();
            }
        });
        Txt_scanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CHECK = Txt_scanning.getText().toString();
                if (CHECK.contentEquals("SCAN")) {
                    beginAudioScan();
                    Txt_scanning.setText("SCANNING");
                    RotateAnimation rotate = new RotateAnimation(0, 360,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                            0.5f);

                    rotate.setDuration(4000);
                    rotate.setRepeatCount(Animation.INFINITE);
                    pro_bar.setAnimation(rotate);
                }
            }
        });
    }

    private void goToNextScreen() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent int_login = new Intent(Scanning_screen.this, Offer_response_screen.class);
                startActivity(int_login);
                finish();
            }
        }, 5000);
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
        Scanning_screen.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stopAudioScan();
                if (code == "-1") {
                    Toast.makeText(Scanning_screen.this, "Mic Permission Needed", Toast.LENGTH_LONG).show();
                    //requestPermissionIfNeeded();
                } else if (code == "0") {
                    Toast.makeText(Scanning_screen.this, "Scanning timed out or valid code not found", Toast.LENGTH_LONG).show();
                    pro_bar.getAnimation().cancel();
                    Txt_scanning.setText("SCAN");
                    //codeTextView.setText("Scanning timed out or valid code not found");
                } else {
                    Toast.makeText(Scanning_screen.this, code, Toast.LENGTH_LONG).show();
//                    Intent int_login = new Intent(Scanning_screen.this, Offer_response_screen.class);
//                    int_login.putExtra("Sound_code", code);
//                    startActivity(int_login);
//                    finish();


                    Post_Sound_validate_ Post = new Post_Sound_validate_();
                    Post.setAction(code);
                    Post.setCountry("CA");
                    Post.setProgram("CF");


                    Sound_validate_api_service mAPIInterface = RetrofitClient.getClient(ApiUtils.BASE_URL, Scanning_screen.this).create(Sound_validate_api_service.class);
                    mAPIInterface.CallApi(Post).enqueue(new Callback<Souncode_validate_response_body>() {
                        @Override
                        public void onResponse(Call<Souncode_validate_response_body> call, retrofit2.Response<Souncode_validate_response_body> response) {
                            //  pDialog.dismiss();
                            try {

                                if (response.isSuccessful()) {

                                    String Buttons = response.body().getButtons();
                                    if (Buttons.contentEquals("4")) {
                                        String Button1deal = response.body().getButton1_deal();
                                        String Button2deal = response.body().getButton2_deal();
                                        String Button3deal = response.body().getButton3_deal();
                                        String Button4deal = response.body().getButton4_deal();
                                        String Button1_img = response.body().getButton1_img();
                                        String Button2_img = response.body().getButton2_img();
                                        String Button3_img = response.body().getButton3_img();
                                        String Button4_img = response.body().getButton4_img();
                                        String Button1_text = response.body().getButton1_text();
                                        String Button2_text = response.body().getButton2_text();
                                        String Button3_text = response.body().getButton3_text();
                                        String Button4_text = response.body().getButton4_text();
                                        String button1_tpl = response.body().getButton1_tpl();
                                        String button2_tpl = response.body().getButton2_tpl();
                                        String button3_tpl = response.body().getButton3_tpl();
                                        String button4_tpl = response.body().getButton4_tpl();

                                        String Banner = response.body().getBanner();

                                        String Soundcode = response.body().getSoundcode();
                                        String Tittle = response.body().getTitle();

                                        SharedPreferences.Editor editor = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE).edit();
                                        //editor.putString(Shared_preference_model.Email, Edt_txt_Email.getText().toString());

//
//
//                                        Intent i1 = new Intent(Scanning_screen.this, Offer_response_screen.class);
                                        editor.putString("Button1deal", Button1deal);
                                        editor.putString("Button2deal", Button2deal);
                                        editor.putString("Button3deal", Button3deal);
                                        editor.putString("Button4deal", Button4deal);
                                        editor.putString("Button1_img", Button1_img);
                                        editor.putString("Button2_img", Button2_img);
                                        editor.putString("Button3_img", Button3_img);
                                        editor.putString("Button4_img", Button4_img);
                                        editor.putString("Button1_text", Button1_text);
                                        editor.putString("Button2_text", Button2_text);
                                        editor.putString("Button3_text", Button3_text);
                                        editor.putString("Button4_text", Button4_text);
                                        editor.putString("button1_tpl", button1_tpl);
                                        editor.putString("button2_tpl", button2_tpl);
                                        editor.putString("button3_tpl", button3_tpl);
                                        editor.putString("button4_tpl", button4_tpl);
                                        editor.putString("Banner", Banner);
                                        editor.putString("Buttons", Buttons);
                                        editor.putString("Soundcode", Soundcode);
                                        editor.putString("Tittle", Tittle);
                                        editor.apply();
//                                        startActivity(i1);
//                                        finish();
                                    } else if (Buttons.contentEquals("2")) {

                                        String Button1deal = response.body().getButton1_deal();
                                        String Button2deal = response.body().getButton2_deal();

                                        String Button1_img = response.body().getButton1_img();
                                        String Button2_img = response.body().getButton2_img();

                                        String Button1_text = response.body().getButton1_text();
                                        String Button2_text = response.body().getButton2_text();

                                        String button1_tpl = response.body().getButton1_tpl();
                                        String button2_tpl = response.body().getButton2_tpl();


                                        String Banner = response.body().getBanner();

                                        String Soundcode = response.body().getSoundcode();
                                        String Tittle = response.body().getTitle();

                                        SharedPreferences.Editor editor = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE).edit();


                                        editor.putString("Button1deal", Button1deal);
                                        editor.putString("Button2deal", Button2deal);

                                        editor.putString("Button1_img", Button1_img);
                                        editor.putString("Button2_img", Button2_img);

                                        editor.putString("Button1_text", Button1_text);
                                        editor.putString("Button2_text", Button2_text);
                                        editor.putString("button1_tpl", button1_tpl);
                                        editor.putString("button2_tpl", button2_tpl);
                                        editor.putString("Banner", Banner);
                                        editor.putString("Buttons", Buttons);
                                        editor.putString("Soundcode", Soundcode);
                                        editor.putString("Tittle", Tittle);
                                        editor.apply();
//

                                    }
                                    Intent i1 = new Intent(Scanning_screen.this, Offer_response_screen.class);
                                    startActivity(i1);
                                    finish();


                                } else {
                                    //String str_response = response.body().getErrormsg();


                                    Toast.makeText(Scanning_screen.this, "Code not found", Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {
                                // Toast.makeText(feedback.this, "Server error", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                                Toast.makeText(Scanning_screen.this, "Code not found", Toast.LENGTH_LONG).show();
                                pro_bar.getAnimation().cancel();
                                Txt_scanning.setText("SCAN");

                            }
                        }

                        @Override
                        public void onFailure(Call<Souncode_validate_response_body> call, Throwable t) {
                            pDialog.dismiss();
                            Toast.makeText(Scanning_screen.this, "Network error ", Toast.LENGTH_LONG).show();
                            pro_bar.getAnimation().cancel();
                            Txt_scanning.setText("SCAN");

                        }

                    });
                }


            }

        });

    }


    private void stopAudioScan() {
        //scanButton.setText("Scan");
        soundpays.stopAudioScan();
        scanning = false;
    }


    private void requestPermissionIfNeeded() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (Scanning_screen.this.checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                Scanning_screen.this.requestPermissions(
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        SoundpaysConstants.RECORD_AUDIO_REQUEST);

            } else {
                beginAudioScan();
            }
        } else {
            beginAudioScan();
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

                    beginAudioScan();


                    //beginAudioScan();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE).edit();
                    editor.putString("Permission", "Permission Denied");

                    editor.apply();
                    Intent int_login = new Intent(Scanning_screen.this, Home_screen.class);
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
