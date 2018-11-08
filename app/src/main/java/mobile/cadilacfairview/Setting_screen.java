package mobile.cadilacfairview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import DATAMODEL.ApiUtils;
import DATAMODEL.Login_post;
import DATAMODEL.Login_service;
import DATAMODEL.RetrofitClient;
import DATAMODEL.Shared_preference_model;
import DATAMODEL.response_body;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class Setting_screen extends AppCompatActivity {
    @BindView(R.id.textView11)
    TextView Txt_terms_conditon;
    @BindView(R.id.textView13)
    TextView Txt_logout;
    private ProgressDialog pDialog;
    String android_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);
        ButterKnife.bind(this);
        android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        SharedPreferences prefs = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE);
        final String Email = prefs.getString(Shared_preference_model.Email, null);
        Txt_terms_conditon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login = new Intent(Setting_screen.this, Termsnd_condition.class);
                int_login.putExtra("Check_screen","Setting");
                startActivity(int_login);
                finish();
            }
        });

        Txt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialog = new ProgressDialog(Setting_screen.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();
                Login_post Post = new Login_post();
                Post.setAction("logout");
                Post.setCountry("CA");
                Post.setDevice(android_id);
                Post.setEmail(Email);
                Post.setProgram("CF");
                Post.setLanguage("English");


                Login_service mAPIInterface = RetrofitClient.getClient(ApiUtils.BASE_URL, Setting_screen.this).create(Login_service.class);
                mAPIInterface.CallApi(Post).enqueue(new Callback<response_body>() {
                    @Override
                    public void onResponse(Call<response_body> call, retrofit2.Response<response_body> response) {
                        pDialog.dismiss();
                        try {

                            if (response.isSuccessful()) {


//
                                SharedPreferences.Editor editor = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE).edit();
                                //editor.putString(Shared_preference_model.Email, Edt_txt_Email.getText().toString());

                                editor.clear();
                                editor.commit();
                                Intent int_login = new Intent(Setting_screen.this, Login_screen_.class);
                                startActivity(int_login);
                                finish();

                            } else {
                                //String str_response = response.body().getErrormsg();


                                // Toast.makeText(Setting_screen.this, "Invalid Username/Password", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            // Toast.makeText(feedback.this, "Server error", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                            // Toast.makeText(Login_screen_.this, "Invalid Username/Password", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<response_body> call, Throwable t) {
                        pDialog.dismiss();
                        Toast.makeText(Setting_screen.this, "Network error ", Toast.LENGTH_LONG).show();

                    }

                });
            }


        });

    }
}
