package mobile.cadilacfairview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import Adapters.CustomListViewAdapter;
import Adapters.RowItem;
import Adapters.Utils;
import DATAMODEL.Shared_preference_model;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Home_screen extends AppCompatActivity {
    ImageView Img_Find_offer;
    RelativeLayout rlv_holder;
    public static final Integer[] images = {R.drawable.roots,
            R.drawable.aldo_logo, R.drawable.softmoc};
    public static final String[] titles = new String[]{"Strawberry",
            "Banana", "Orange", "Mixed"};

    public static final String[] descriptions = new String[]{
            "It is an aggregate accessory fruit",
            "It is the largest herbaceous flowering plant", "Citrus Fruit",
            "Mixed Fruits"};

    @BindView(R.id.List_my_offer)
    ListView listView;
    List<RowItem> rowItems;
    @BindView(R.id.imageView_Plus)
    ImageView img_plus;

    @BindView(R.id.img_setting2)
    ImageView Img_setting;

    String str_check = "Plus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);

        Img_Find_offer = (ImageView) findViewById(R.id.imageView_offer);
        rlv_holder = (RelativeLayout) findViewById(R.id.rlv_ellipse_holder);
        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < images.length; i++) {
            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
            rowItems.add(item);

        }
        CustomListViewAdapter adapter = new CustomListViewAdapter(Home_screen.this,
                R.layout.my_offer_lv_layout, rowItems);
        listView.setAdapter(adapter);
        Utils.setListViewHeightBasedOnChildren(listView);


        Img_Find_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

                Img_Find_offer.startAnimation(animFadein);

            }
        });
        img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (str_check.contentEquals("Plus")) {
                    img_plus.setImageResource(R.drawable.minus);
                    str_check = "minus";
                    listView.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(Home_screen.this, R.anim.push_up_in);
                    listView.startAnimation(animation);
                } else if (str_check.contentEquals("minus")) {
                    img_plus.setImageResource(R.drawable.plus);
                    str_check = "Plus";
                    listView.setVisibility(View.GONE);
                }
            }
        });
        Img_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login = new Intent(Home_screen.this, Setting_screen.class);
                startActivity(int_login);
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
//        rlv_holder.setOnTouchListener(new View.OnTouchListener() {
//
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN: {
//                        v.getBackground().setColorFilter(0xf0f47521, PorterDuff.Mode.SRC_ATOP);
//                        v.invalidate();
//
//
//
//                        break;
//                    }
//                    case MotionEvent.ACTION_UP: {
//                        v.getBackground().clearColorFilter();
//                        v.invalidate();
//
//                        break;
//                    }
//                }
//                return false;
//            }
//        });
        rlv_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE);
                String Permission_ = prefs.getString("Permission", null);
                if (Permission_ != null) {
                    if (Permission_.contentEquals("Permission Granted")) {
                        Intent int_login = new Intent(Home_screen.this, Scanning_screen.class);
                        startActivity(int_login);
                        finish();
                    } else if (Permission_.contentEquals("Permission Denied")) {
                        Intent int_login = new Intent(Home_screen.this, Find_offer_screen.class);
                        startActivity(int_login);
                        finish();
                    }

                } else {
                    Intent int_login = new Intent(Home_screen.this, Find_offer_screen.class);
                    startActivity(int_login);
                    finish();
                }
            }
        });
    }

}
