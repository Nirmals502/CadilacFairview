package mobile.cadilacfairview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_screen_ extends AppCompatActivity {
Button Btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_scren);
        Btn_login = (Button)findViewById(R.id.button_login);
        Btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login =new Intent(Login_screen_.this,Welcome_screen.class);
                startActivity(int_login);
                finish();
            }
        });

    }
}
