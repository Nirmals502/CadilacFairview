package mobile.cadilacfairview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.skyfishjy.library.RippleBackground;

public class Welcome_screen extends AppCompatActivity {
RelativeLayout rlv_;
ImageView img_skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        rlv_ = (RelativeLayout)findViewById(R.id.rlv_ellipse_holder);
        img_skip=(ImageView)findViewById(R.id.imageView_skip);
        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        rippleBackground.startRippleAnimation();
        img_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login = new Intent(Welcome_screen.this, Home_screen.class);
                startActivity(int_login);
                finish();
            }
        });
    }
}
