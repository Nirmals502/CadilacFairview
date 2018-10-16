package mobile.cadilacfairview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Info_screen extends AppCompatActivity {
    Button Btn_login,Btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_screen);
        Btn_login = (Button)findViewById(R.id.button_login);
        Btn_signup=(Button)findViewById(R.id.button_sign_up);
        Btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login =new Intent(Info_screen.this,Login_screen_.class);
                startActivity(int_login);
                finish();
            }
        });
        Btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login =new Intent(Info_screen.this,Signup_screen.class);
                startActivity(int_login);
                finish();
            }
        });
    }
}
