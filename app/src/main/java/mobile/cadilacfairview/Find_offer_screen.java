package mobile.cadilacfairview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ProgressBar;

public class Find_offer_screen extends AppCompatActivity {
    Button Btn_allow;
    ProgressBar pro_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_offer_screen);
        Btn_allow = (Button) findViewById(R.id.button_allow);
        pro_bar = (ProgressBar) findViewById(R.id.progressBar2);
        Btn_allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login = new Intent(Find_offer_screen.this, Scanning_screen.class);
                startActivity(int_login);
                finish();
            }
        });
//        Animation anim = new MyAnimation(pro_bar, 100);
//        anim.setDuration(3000);
//        pro_bar.startAnimation(anim);
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        rotate.setDuration(4000);
        rotate.setRepeatCount(Animation.INFINITE);
        pro_bar.setAnimation(rotate);
//        ProgressBarAnimation mProgressAnimation = new ProgressBarAnimation(pro_bar, 1000);
//
//
//        /* Update progress later anywhere in code: */
//        mProgressAnimation.setProgress(100);
    }


}
