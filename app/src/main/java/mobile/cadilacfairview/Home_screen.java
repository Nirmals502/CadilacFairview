package mobile.cadilacfairview;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Home_screen extends AppCompatActivity {
ImageView Img_Find_offer;
RelativeLayout rlv_holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Img_Find_offer=(ImageView)findViewById(R.id.imageView_offer);
        rlv_holder=(RelativeLayout) findViewById(R.id.rlv_ellipse_holder);
        Img_Find_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

                Img_Find_offer.startAnimation(animFadein);
                Intent int_login = new Intent(Home_screen.this, Find_offer_screen.class);
                startActivity(int_login);
                finish();
            }
        });
//        rlv_holder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
//
//                rlv_holder.startAnimation(animFadein);
//                Intent int_login = new Intent(Home_screen.this, Find_offer_screen.class);
//                startActivity(int_login);
//                finish();
//            }
//        });
        rlv_holder.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xf0f47521,PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();

                        Intent int_login = new Intent(Home_screen.this, Find_offer_screen.class);
                        startActivity(int_login);
                        finish();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();

                        break;
                    }
                }
                return false;
            }
        });
    }
}
