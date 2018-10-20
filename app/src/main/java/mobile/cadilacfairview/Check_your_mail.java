package mobile.cadilacfairview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;

public class Check_your_mail extends AppCompatActivity {
    ProgressBar pro_bar;
    Button Btn_resent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_your_mail);
        pro_bar = (ProgressBar) findViewById(R.id.progressBar2);
        Btn_resent=(Button)findViewById(R.id.button_resend);
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        rotate.setDuration(4000);
        rotate.setRepeatCount(Animation.INFINITE);
        pro_bar.setAnimation(rotate);
        Btn_resent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login = new Intent(Check_your_mail.this, Termsnd_condition.class);
                startActivity(int_login);
                finish();
            }
        });
    }
}
