package mobile.cadilacfairview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import DATAMODEL.Shared_preference_model;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Offer_response_screen extends AppCompatActivity {
    @BindView(R.id.imageView_hold_)
    ImageView img_hold;
    @BindView(R.id.imageView_get_couponn)
    ImageView Img_get_coupon;
    @BindView(R.id.imageView_take_me)
    ImageView Img_take_me;
    @BindView(R.id.imageView_main_image)
    ImageView Img_banner;
    @BindView(R.id.text_Vw_offer)
    TextView txt_offr;
    @BindView(R.id.Rlv_back)
    RelativeLayout rlv_back;

    @BindView(R.id.imageView5)
    ImageView Book_a_fitting_room;

    @BindView(R.id.textView18)
    TextView Txt_Get_coupon;
    @BindView(R.id.textView19)
    TextView Txt_hold;
    @BindView(R.id.textView20)
    TextView Txt_Book_a_fitting_room;
    @BindView(R.id.textView21)
    TextView Txt_take_me;


    @BindView(R.id.rlv_container)
    RelativeLayout Rlv_get_coupon;
    @BindView(R.id.Rlv_hold_)
    RelativeLayout Rlv_hold;
    @BindView(R.id.Rlv_fitting_)
    RelativeLayout Rlv_fitting;
    @BindView(R.id.Rlv_take_me)
    RelativeLayout Rlv_take_me;


    String Button1deal = "";
    String Button2deal = "";
    String Button3deal = "";
    String Button4deal = "";
    String Button1_img = "";
    String Button2_img = "";
    String Button3_img = "";
    String Button4_img = "";
    String Button1_text = "";
    String Button2_text = "";
    String Button3_text = "";
    String Button4_text = "";
    String button1_tpl = "";
    String button2_tpl = "";
    String button3_tpl = "";
    String button4_tpl = "";

    String Banner = "";

    String Soundcode = "";
    String Tittle = "";
    String Buttons = "";

    @BindView(R.id.linear2)
    LinearLayout Lnr_layout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_response_screen);
        ButterKnife.bind(this);


        SharedPreferences prefs = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE);
        Buttons = prefs.getString("Buttons", null);
        if (Buttons.contentEquals("4")) {
            Lnr_layout2.setVisibility(View.VISIBLE);
            Button1deal = prefs.getString("Button1deal", null);
            Button2deal = prefs.getString("Button2deal", null);
            Button3deal = prefs.getString("Button3deal", null);
            Button4deal = prefs.getString("Button4deal", null);
            Button1_img = prefs.getString("Button1_img", null);
            Button2_img = prefs.getString("Button2_img", null);
            Button3_img = prefs.getString("Button3_img", null);
            Button4_img = prefs.getString("Button4_img", null);
            Button1_text = prefs.getString("Button1_text", null);
            Button2_text = prefs.getString("Button2_text", null);
            Button3_text = prefs.getString("Button3_text", null);
            Button4_text = prefs.getString("Button4_text", null);
            button1_tpl = prefs.getString("button1_tpl", null);
            button2_tpl = prefs.getString("button2_tpl", null);
            button3_tpl = prefs.getString("button3_tpl", null);
            button4_tpl = prefs.getString("button4_tpl", null);
            Banner = prefs.getString("Banner", null);
            Soundcode = prefs.getString("Soundcode", null);
            Tittle = prefs.getString("Tittle", null);


            txt_offr.setText(Tittle);
            Txt_Get_coupon.setText(Button1_text);
            Txt_hold.setText(Button2_text);
            Txt_Book_a_fitting_room.setText(Button3_text);
            Txt_take_me.setText(Button4_text);


            Picasso.get().load(Banner).placeholder(R.drawable.progress_bar).into(Img_banner);
            Picasso.get().load(Button1_img).placeholder(R.drawable.progress_bar).into(Img_get_coupon);
            Picasso.get().load(Button2_img).into(img_hold);
            Picasso.get().load(Button3_img).placeholder(R.drawable.progress_bar).into(Book_a_fitting_room);
            Picasso.get().load(Button4_img).placeholder(R.drawable.progress_bar).into(Img_take_me);

            // img_hold;


        } else if (Buttons.contentEquals("2")) {
            Lnr_layout2.setVisibility(View.INVISIBLE);
            Button1deal = prefs.getString("Button1deal", null);
            Button2deal = prefs.getString("Button2deal", null);

            Button1_img = prefs.getString("Button1_img", null);
            Button2_img = prefs.getString("Button2_img", null);

            Button1_text = prefs.getString("Button1_text", null);
            Button2_text = prefs.getString("Button2_text", null);

            button1_tpl = prefs.getString("button1_tpl", null);
            button2_tpl = prefs.getString("button2_tpl", null);

            Banner = prefs.getString("Banner", null);
            Soundcode = prefs.getString("Soundcode", null);
            Tittle = prefs.getString("Tittle", null);


            txt_offr.setText(Tittle);
            Txt_Get_coupon.setText(Button1_text);
            Txt_hold.setText(Button2_text);


            Picasso.get().load(Banner).into(Img_banner);
            Picasso.get().load(Button1_img).into(Img_get_coupon);
            Picasso.get().load(Button2_img).into(img_hold);

        } else if (Buttons.contentEquals("0")) {

            Intent int_login = new Intent(Offer_response_screen.this, Get_coupon_action.class);
            int_login.putExtra("deal", "0");
            startActivity(int_login);
            finish();

        }


        Rlv_hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button2_tpl.contentEquals("picker")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Hold_action_screen.class);
                    int_login.putExtra("deal", Button2deal);

                    startActivity(int_login);
                    finish();
                } else if (button2_tpl.contentEquals("coupon")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Get_coupon_action.class);
                    int_login.putExtra("deal", Button2deal);
                    startActivity(int_login);
                    finish();
                } else if (button2_tpl.contentEquals("map")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Take_me_direction.class);
                    int_login.putExtra("deal", Button2deal);
                    startActivity(int_login);
                    finish();
                }
            }
        });
        Rlv_get_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button1_tpl.contentEquals("picker")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Hold_action_screen.class);
                    int_login.putExtra("deal", Button1deal);
                    startActivity(int_login);
                    finish();
                } else if (button1_tpl.contentEquals("coupon")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Get_coupon_action.class);
                    int_login.putExtra("deal", Button1deal);
                    startActivity(int_login);
                    finish();
                } else if (button1_tpl.contentEquals("map")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Take_me_direction.class);
                    int_login.putExtra("deal", Button1deal);

                    startActivity(int_login);
                    finish();
                }
            }
        });
        Rlv_fitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button3_tpl.contentEquals("picker")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Hold_action_screen.class);
                    int_login.putExtra("deal", Button3deal);
                    startActivity(int_login);
                    finish();
                } else if (button3_tpl.contentEquals("coupon")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Get_coupon_action.class);
                    int_login.putExtra("deal", Button3deal);
                    startActivity(int_login);
                    finish();
                } else if (button3_tpl.contentEquals("map")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Take_me_direction.class);
                    int_login.putExtra("deal", Button3deal);
                    startActivity(int_login);
                    finish();
                }
            }
        });

        Rlv_take_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button4_tpl.contentEquals("picker")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Hold_action_screen.class);
                    int_login.putExtra("deal", Button4deal);
                    startActivity(int_login);
                    finish();
                } else if (button4_tpl.contentEquals("coupon")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Get_coupon_action.class);
                    int_login.putExtra("deal", Button4deal);
                    startActivity(int_login);
                    finish();
                } else if (button4_tpl.contentEquals("map")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Take_me_direction.class);
                    int_login.putExtra("deal", Button4deal);
                    startActivity(int_login);
                    finish();
                }
            }
        });


        rlv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login = new Intent(Offer_response_screen.this, Home_screen.class);
                startActivity(int_login);
                finish();

            }
        });
    }
}
