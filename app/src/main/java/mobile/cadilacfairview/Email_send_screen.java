package mobile.cadilacfairview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Email_send_screen extends AppCompatActivity {
Button Btn_send_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_send_screen);
        Btn_send_email=(Button)findViewById(R.id.button_signup);
        Btn_send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login = new Intent(Email_send_screen.this, Check_your_mail.class);
                startActivity(int_login);
                finish();
            }
        });
    }
}
