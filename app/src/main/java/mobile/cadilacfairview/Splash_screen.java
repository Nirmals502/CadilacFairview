package mobile.cadilacfairview;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import Animations.animations;

public class Splash_screen extends AppCompatActivity {
    ImageView img_now, img_cf;
    ImageView img_topleft;
    ImageView img_Bottomright;
    animations anim = new animations();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_);
        img_now = (ImageView) findViewById(R.id.imageView_now);
        img_cf = (ImageView) findViewById(R.id.imb_splash_bg);
        img_topleft = (ImageView) findViewById(R.id.imageView_topleft);
        img_Bottomright = (ImageView) findViewById(R.id.imageView_bottom_right);
        img_cf.startAnimation(anim.inFromLeftAnimation());
        img_now.startAnimation(anim.inFromRightAnimation());
        // img_topleft.startAnimation(anim.inFromLeftAnimation());
        img_Bottomright.startAnimation(anim.inFromRightAnimation());
        Animation a1 = AnimationUtils.loadAnimation(Splash_screen.this, R.anim.top_left);
        Animation a1_bottom = AnimationUtils.loadAnimation(Splash_screen.this, R.anim.bottom_right);
        img_topleft.setAnimation(a1);
        //img_Bottomright.setAnimation(a1_bottom);
        goToNextScreen();
    }

    private void goToNextScreen() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent int_login = new Intent(Splash_screen.this, Email_send_screen.class);
                startActivity(int_login);
                finish();
            }
        }, 5000);
    }


}
