package mobile.cadilacfairview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                Button1deal = "";
                Button2deal = "";
                Button3deal = "";
                Button4deal = "";
                Button1_img = "";
                Button2_img = "";
                Button3_img = "";
                Button4_img = "";
                Button1_text = "";
                Button2_text = "";
                Button3_text = "";
                Button4_text = "";
                button1_tpl = "";
                button2_tpl = "";
                button3_tpl = "";
                button4_tpl = "";
                Banner = "";
                Soundcode = "";
                Tittle = "";
                Buttons = "";
                //newString= null;
            } else {
                Buttons = extras.getString("Buttons");
                if (Buttons.contentEquals("4")) {
                    Lnr_layout2.setVisibility(View.VISIBLE);
                    Button1deal = extras.getString("Button1deal");
                    Button2deal = extras.getString("Button2deal");
                    Button3deal = extras.getString("Button3deal");
                    Button4deal = extras.getString("Button4deal");
                    Button1_img = extras.getString("Button1_img");
                    Button2_img = extras.getString("Button2_img");
                    Button3_img = extras.getString("Button3_img");
                    Button4_img = extras.getString("Button4_img");
                    Button1_text = extras.getString("Button1_text");
                    Button2_text = extras.getString("Button2_text");
                    Button3_text = extras.getString("Button3_text");
                    Button4_text = extras.getString("Button4_text");
                    button1_tpl = extras.getString("button1_tpl");
                    button2_tpl = extras.getString("button2_tpl");
                    button3_tpl = extras.getString("button3_tpl");
                    button4_tpl = extras.getString("button4_tpl");
                    Banner = extras.getString("Banner");
                    Soundcode = extras.getString("Soundcode");
                    Tittle = extras.getString("Tittle");


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
                    Button1deal = extras.getString("Button1deal");
                    Button2deal = extras.getString("Button2deal");

                    Button1_img = extras.getString("Button1_img");
                    Button2_img = extras.getString("Button2_img");

                    Button1_text = extras.getString("Button1_text");
                    Button2_text = extras.getString("Button2_text");

                    button1_tpl = extras.getString("button1_tpl");
                    button2_tpl = extras.getString("button2_tpl");

                    Banner = extras.getString("Banner");
                    Soundcode = extras.getString("Soundcode");
                    Tittle = extras.getString("Tittle");


                    txt_offr.setText(Tittle);
                    Txt_Get_coupon.setText(Button1_text);
                    Txt_hold.setText(Button2_text);


                    Picasso.get().load(Banner).into(Img_banner);
                    Picasso.get().load(Button1_img).into(Img_get_coupon);
                    Picasso.get().load(Button2_img).into(img_hold);


                }


                // newString= extras.getString("STRING_I_NEED");
            }
        } else {
            Buttons = (String) savedInstanceState.getSerializable("Buttons");
            if (Buttons.contentEquals("4")) {
                Button1deal = (String) savedInstanceState.getSerializable("Button1deal");
                Button2deal = (String) savedInstanceState.getSerializable("Button2deal");
                Button3deal = (String) savedInstanceState.getSerializable("Button3deal");
                Button4deal = (String) savedInstanceState.getSerializable("Button4deal");
                Button1_img = (String) savedInstanceState.getSerializable("Button1_img");
                Button2_img = (String) savedInstanceState.getSerializable("Button2_img");
                Button3_img = (String) savedInstanceState.getSerializable("Button3_img");
                Button4_img = (String) savedInstanceState.getSerializable("Button4_img");
                Button1_text = (String) savedInstanceState.getSerializable("Button1_text");
                Button2_text = (String) savedInstanceState.getSerializable("Button2_text");
                Button3_text = (String) savedInstanceState.getSerializable("Button3_text");
                Button4_text = (String) savedInstanceState.getSerializable("Button4_text");
                button1_tpl = (String) savedInstanceState.getSerializable("button1_tpl");
                button2_tpl = (String) savedInstanceState.getSerializable("button2_tpl");
                button3_tpl = (String) savedInstanceState.getSerializable("button3_tpl");
                button4_tpl = (String) savedInstanceState.getSerializable("button4_tpl");
                Banner = (String) savedInstanceState.getSerializable("Banner");
                Soundcode = (String) savedInstanceState.getSerializable("Soundcode");
                Tittle = (String) savedInstanceState.getSerializable("Tittle");
            } else if (Buttons.contentEquals("2")) {
                Button1deal = (String) savedInstanceState.getSerializable("Button1deal");
                Button2deal = (String) savedInstanceState.getSerializable("Button2deal");

                Button1_img = (String) savedInstanceState.getSerializable("Button1_img");
                Button2_img = (String) savedInstanceState.getSerializable("Button2_img");

                Button1_text = (String) savedInstanceState.getSerializable("Button1_text");
                Button2_text = (String) savedInstanceState.getSerializable("Button2_text");

                button1_tpl = (String) savedInstanceState.getSerializable("button1_tpl");
                button2_tpl = (String) savedInstanceState.getSerializable("button2_tpl");

                Banner = (String) savedInstanceState.getSerializable("Banner");
                Soundcode = (String) savedInstanceState.getSerializable("Soundcode");
                Tittle = (String) savedInstanceState.getSerializable("Tittle");
            }

            //newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }


        Rlv_hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button2_tpl.contentEquals("picker")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Hold_action_screen.class);
                    startActivity(int_login);
                   // finish();
                } else if (button2_tpl.contentEquals("coupon")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Get_coupon_action.class);
                    startActivity(int_login);
                    //finish();
                } else if (button2_tpl.contentEquals("map")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Take_me_direction.class);
                    startActivity(int_login);
                   // finish();
                }
            }
        });
        Rlv_get_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button1_tpl.contentEquals("picker")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Hold_action_screen.class);
                    startActivity(int_login);
                   // finish();
                } else if (button1_tpl.contentEquals("coupon")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Get_coupon_action.class);
                    startActivity(int_login);
                    //finish();
                } else if (button1_tpl.contentEquals("map")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Take_me_direction.class);
                    startActivity(int_login);
                   // finish();
                }
            }
        });
        Rlv_fitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button3_tpl.contentEquals("picker")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Hold_action_screen.class);
                    startActivity(int_login);
                   // finish();
                } else if (button3_tpl.contentEquals("coupon")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Get_coupon_action.class);
                    startActivity(int_login);
                    //finish();
                } else if (button3_tpl.contentEquals("map")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Take_me_direction.class);
                    startActivity(int_login);
                    //finish();
                }
            }
        });

        Rlv_take_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button4_tpl.contentEquals("picker")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Hold_action_screen.class);
                    startActivity(int_login);
                   // finish();
                } else if (button4_tpl.contentEquals("coupon")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Get_coupon_action.class);
                    startActivity(int_login);
                    //finish();
                } else if (button4_tpl.contentEquals("map")) {
                    Intent int_login = new Intent(Offer_response_screen.this, Take_me_direction.class);
                    startActivity(int_login);
                   // finish();
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
