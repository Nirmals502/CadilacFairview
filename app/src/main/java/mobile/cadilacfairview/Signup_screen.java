package mobile.cadilacfairview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Signup_screen extends AppCompatActivity {
    Button Btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        Btn_signup = (Button)findViewById(R.id.button_signup);
        Btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login =new Intent(Signup_screen.this,Find_offer_screen.class);
                startActivity(int_login);
                finish();
            }
        });
    }
}
