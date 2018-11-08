package mobile.cadilacfairview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Animations.animations;
import DATAMODEL.APIService_signup;
import DATAMODEL.ApiUtils;
import DATAMODEL.RetrofitClient;
import DATAMODEL.Post_signup;
import DATAMODEL.Shared_preference_model;
import DATAMODEL.response_body;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class Signup_screen extends AppCompatActivity {
    @BindView(R.id.button_signup)
    Button Btn_signup;
    private APIService_signup mAPIService;
    @BindView(R.id.editText_Email)
    EditText edt_txt_First_name;
    @BindView(R.id.editText_last_name)
    EditText Edt_txt_last_name;
    @BindView(R.id.editText_email)
    EditText Edt_txt_Email;
    @BindView(R.id.editText_password)
    EditText Edt_txt_password;
    @BindView(R.id.textView_login)
    TextView Txt_login;
    animations anim = new animations();
    private ProgressDialog pDialog;
    String android_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        ButterKnife.bind(this);
         android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        // mAPIService = ApiUtils.getAPIService();
        Txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login = new Intent(Signup_screen.this, Login_screen_.class);
                startActivity(int_login);
                finish();

            }
        });
        Btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_txt_First_name.getText().toString().contentEquals("")) {
                    edt_txt_First_name.startAnimation(anim.Shake_Animation());
                    edt_txt_First_name.setError("First name required");
                } else if (Edt_txt_last_name.getText().toString().contentEquals("")) {
                    Edt_txt_last_name.startAnimation(anim.Shake_Animation());
                    Edt_txt_last_name.setError("Last name required");
                } else if (Edt_txt_Email.getText().toString().contentEquals("")) {
                    Edt_txt_Email.startAnimation(anim.Shake_Animation());
                    Edt_txt_Email.setError("Email required");
                } else if (Edt_txt_password.getText().toString().contentEquals("")) {
                    Edt_txt_password.startAnimation(anim.Shake_Animation());
                    Edt_txt_password.setError("Password required");
                } else {

                    pDialog = new ProgressDialog(Signup_screen.this);
                    pDialog.setMessage("Please wait...");
                    pDialog.setCancelable(false);
                    pDialog.show();
                    Post_signup Post = new Post_signup();
                    Post.setAction("register");
                    Post.setCountry("CA");
                    Post.setDevice(android_id);
                    Post.setEmail(Edt_txt_Email.getText().toString());
                    Post.setProgram("CF");
                    Post.setFirstname(edt_txt_First_name.getText().toString());
                    Post.setLastname(Edt_txt_last_name.getText().toString());
                    Post.setLanguage("English");
                    Post.setPassword(Edt_txt_password.getText().toString());

                    APIService_signup mAPIInterface = RetrofitClient.getClient(ApiUtils.BASE_URL, Signup_screen.this).create(APIService_signup.class);
                    mAPIInterface.CallApi(Post).enqueue(new Callback<response_body>() {
                        @Override
                        public void onResponse(Call<response_body> call, retrofit2.Response<response_body> response) {
                            pDialog.dismiss();
                            try {
                                if (response.isSuccessful()) {

                                    // String respose = response.toString();
                                    String str_response = response.body().getErrormsg();


                                    Toast.makeText(Signup_screen.this, str_response, Toast.LENGTH_LONG).show();
                                    SharedPreferences.Editor editor = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE).edit();
                                    editor.putString(Shared_preference_model.Email, Edt_txt_Email.getText().toString());

                                    editor.apply();
                                    Intent int_login = new Intent(Signup_screen.this, Termsnd_condition.class);
                                    int_login.putExtra("Check_screen","Signup");
                                    startActivity(int_login);
                                    finish();

                                }
                            } catch (Exception e) {
                                // Toast.makeText(feedback.this, "Server error", Toast.LENGTH_LONG).show();
                                e.printStackTrace();

                            }
                        }

                        @Override
                        public void onFailure(Call<response_body> call, Throwable t) {
                            pDialog.dismiss();
                            Toast.makeText(Signup_screen.this, "error ", Toast.LENGTH_LONG).show();

                        }

                    });
                }
            }
        });


    }

}







