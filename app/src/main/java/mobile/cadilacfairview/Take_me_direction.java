package mobile.cadilacfairview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Take_me_direction extends AppCompatActivity {
    @BindView(R.id.Rlv_back)
    RelativeLayout Rlv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_me_direction);
        ButterKnife.bind(this);
        Rlv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent int_login = new Intent(Take_me_direction.this, Offer_response_screen.class);
//                startActivity(int_login);
                finish();
            }
        });
    }
}
