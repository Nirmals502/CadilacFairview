package mobile.cadilacfairview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Animations.animations;
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

public class Login_screen_ extends AppCompatActivity {
    private ProgressDialog pDialog;
    @BindView(R.id.button_login)
    Button Btn_login;
    @BindView(R.id.textView_signup)
    TextView Txt_signup;
    @BindView(R.id.editText_Email)
    EditText Edt_txt_Email;
    @BindView(R.id.editText_password)
    EditText Edt_txt_Paswword;
    animations anim = new animations();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_scren);
        ButterKnife.bind(this);

        Btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Edt_txt_Email.getText().toString().contentEquals("")) {
                    Edt_txt_Email.startAnimation(anim.Shake_Animation());
                    Edt_txt_Email.setError("Email required");
                    Intent int_login = new Intent(Login_screen_.this, Home_screen.class);
                    startActivity(int_login);
                    finish();

                } else if (Edt_txt_Paswword.getText().toString().contentEquals("")) {
                    Edt_txt_Paswword.startAnimation(anim.Shake_Animation());
                    Edt_txt_Paswword.setError("Password required");
                } else {
                    pDialog = new ProgressDialog(Login_screen_.this);
                    pDialog.setMessage("Please wait...");
                    pDialog.setCancelable(false);
                    pDialog.show();
                    Login_post Post = new Login_post();
                    Post.setAction("login");
                    Post.setCountry("CA");
                    Post.setDevice("android");
                    Post.setEmail(Edt_txt_Email.getText().toString());
                    Post.setProgram("CF");
                    Post.setLanguage("English");
                    Post.setPassword(Edt_txt_Paswword.getText().toString());

                    Login_service mAPIInterface = RetrofitClient.getClient(ApiUtils.BASE_URL, Login_screen_.this).create(Login_service.class);
                    mAPIInterface.CallApi(Post).enqueue(new Callback<response_body>() {
                        @Override
                        public void onResponse(Call<response_body> call, retrofit2.Response<response_body> response) {
                            pDialog.dismiss();
                            try {

                                if (response.isSuccessful()) {


//
                                    SharedPreferences.Editor editor = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE).edit();
                                    editor.putString(Shared_preference_model.Email, Edt_txt_Email.getText().toString());

                                    editor.apply();
                                    Intent int_login = new Intent(Login_screen_.this, Home_screen.class);
                                    startActivity(int_login);
                                    finish();

                                } else {
                                    //String str_response = response.body().getErrormsg();


                                    Toast.makeText(Login_screen_.this, "Invalid Username/Password", Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {
                                // Toast.makeText(feedback.this, "Server error", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                                Toast.makeText(Login_screen_.this, "Invalid Username/Password", Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<response_body> call, Throwable t) {
                            pDialog.dismiss();
                            Toast.makeText(Login_screen_.this, "Network error ", Toast.LENGTH_LONG).show();

                        }

                    });
                }

//                Intent int_login = new Intent(Login_screen_.this, Welcome_screen.class);
//                startActivity(int_login);
                //finish();
            }
        });
        Txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login = new Intent(Login_screen_.this, Signup_screen.class);
                startActivity(int_login);
            }
        });

    }
}
