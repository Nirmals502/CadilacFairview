package mobile.cadilacfairview;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Offer_response_screen extends AppCompatActivity {
    ImageView img_hold, Img_get_coupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_response_screen);
        img_hold = (ImageView) findViewById(R.id.imageView_hold_);
        Img_get_coupon = (ImageView) findViewById(R.id.imageView_get_coupon);

        img_hold.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        Intent int_login = new Intent(Offer_response_screen.this, Hold_action_screen.class);
                        startActivity(int_login);
                        finish();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {

                    }
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();

                        break;
                    }
                }

                return false;
            }
        });
        Img_get_coupon.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        Intent int_login = new Intent(Offer_response_screen.this, Get_coupon_action.class);
                        startActivity(int_login);
                        finish();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {

                    }
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();

                        break;
                    }
                }

                return false;
            }
        });
    }
}
