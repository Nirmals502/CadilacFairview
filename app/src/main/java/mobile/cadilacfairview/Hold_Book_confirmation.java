package mobile.cadilacfairview;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class Hold_Book_confirmation extends AppCompatActivity {
RelativeLayout rlv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hold_book_confirm);
        rlv_back = (RelativeLayout)findViewById(R.id.Rlv_back);
        rlv_back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFAA0000));
                    Intent int_login = new Intent(Hold_Book_confirmation.this, Offer_response_screen.class);
                    startActivity(int_login);
                   // finish();
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    view.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 000000000));

                }


                return false;
            }
        });
    }
}
