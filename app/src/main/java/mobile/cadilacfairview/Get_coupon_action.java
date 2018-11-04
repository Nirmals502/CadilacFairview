package mobile.cadilacfairview;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Get_coupon_action extends AppCompatActivity {
    @BindView(R.id.Rlv_direction)
    RelativeLayout Rlv_direction;
    @BindView(R.id.Rlv_back)
    RelativeLayout Rlv_back;
    @BindView(R.id.Rlv_my_offer)
    RelativeLayout Rlv_my_offer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_coupon_action);
        ButterKnife.bind(this);
        Rlv_back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFAA0000));
                    Intent int_login = new Intent(Get_coupon_action.this, Offer_response_screen.class);
                    startActivity(int_login);
                    finish();
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    view.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 000000000));

                }


                return false;
            }
        });
        Rlv_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent int_login = new Intent(Get_coupon_action.this, Take_me_direction.class);
//                startActivity(int_login);
                finish();
            }
        });
        Rlv_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
