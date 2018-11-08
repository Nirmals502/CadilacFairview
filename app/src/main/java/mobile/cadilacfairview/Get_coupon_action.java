package mobile.cadilacfairview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DATAMODEL.ADD_ITEM_RESPONSE_BODY;
import DATAMODEL.ADD_MY_ITEM_Api_service;
import DATAMODEL.ApiUtils;
import DATAMODEL.Deal_api_service;
import DATAMODEL.Deal_response_body;
import DATAMODEL.Post_deal;
import DATAMODEL.Post_item_;
import DATAMODEL.RetrofitClient;
import DATAMODEL.Shared_preference_model;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class Get_coupon_action extends AppCompatActivity {
    private ProgressDialog pDialog;
    @BindView(R.id.Rlv_direction)
    RelativeLayout Rlv_direction;
    @BindView(R.id.Rlv_back)
    RelativeLayout Rlv_back;
    @BindView(R.id.Rlv_my_offer)
    RelativeLayout Rlv_my_offer;
    @BindView(R.id.imageView_pic)
    ImageView Img_main_pic;
    @BindView(R.id.Rlv_circle_holder)
    ImageView Img_logo;
    @BindView(R.id.textView_reserved)
    TextView Txt_tittle;
    @BindView(R.id.textView_present_this)
    TextView Txt_Subtittle;
    @BindView(R.id.textView_code_to_any)
    TextView Txt_Barcode_text;
    @BindView(R.id.imageView_qr_code)
    ImageView Img_Barcode;
    String Deal = "";
    String barcode_format = "";
    String barcode_text = "";
    String banner = "";


    String retailer_id = "";
    String deal = "";
    String Str_Color = "";
    String Str_size = "";
    String Str_quantity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_coupon_action);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                Deal = null;
            } else {
                Deal = extras.getString("deal");
            }
        } else {
            Deal = (String) savedInstanceState.getSerializable("deal");
        }

        pDialog = new ProgressDialog(Get_coupon_action.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        Post_deal Post = new Post_deal();
        Post.setAction(Deal);
        Post.setCountry("CA");
        Post.setProgram("CF");


        Deal_api_service mAPIInterface = RetrofitClient.getClient(ApiUtils.BASE_URL, Get_coupon_action.this).create(Deal_api_service.class);
        mAPIInterface.CallApi(Post).enqueue(new Callback<Deal_response_body>() {
            @Override
            public void onResponse(Call<Deal_response_body> call, retrofit2.Response<Deal_response_body> response) {
                pDialog.dismiss();
                try {

                    if (response.isSuccessful()) {


                        String errornum = response.body().getErrornum();
                        String errormsg = response.body().getErrormsg();
                        deal = response.body().getDeal();
                        banner = response.body().getBanner();
                        retailer_id = response.body().getRetailer_id();
                        String retailer = response.body().getRetailer();
                        String logo = response.body().getLogo();
                        String title = response.body().getTitle();
                        String subtitle = response.body().getSubtitle();
                        String colors = response.body().getColors();
                        String sizes = response.body().getSizes();
                        String icon = response.body().getIcon();
                        String text_icon = response.body().getText_icon();
                        String is_coupon = response.body().getIs_coupon();
                        barcode_text = response.body().getBarcode_text();
                        barcode_format = response.body().getBarcode_format();

                        String action_verb = response.body().getAction_verb();

                        String action_data = response.body().getAction_data();

                        Picasso.get().load(icon).placeholder(R.drawable.progress_bar).into(Img_logo);
                        Picasso.get().load(banner).placeholder(R.drawable.progress_bar).into(Img_main_pic);
                        Txt_tittle.setText(title);
                        Txt_Barcode_text.setText(barcode_text);
                        Txt_Subtittle.setText(subtitle);



                        SharedPreferences prefs = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE);
                        String Email = prefs.getString(Shared_preference_model.Email, null);

                        pDialog = new ProgressDialog(Get_coupon_action.this);
                        pDialog.setMessage("Please wait...");
                        pDialog.setCancelable(false);
                        pDialog.show();

                        Post_item_ Post_ = new Post_item_();
                        Post_.setAction("offer");
                        Post_.setCountry("CA");
                        Post_.setProgram("CF");
                        Post_.setEmail(Email);
                        Post_.setRetailer_id(retailer_id);
                        Post_.setDeal(deal);
                        Post_.setColor("0");

                        Post_.setSize("0");
                        Post_.setQuantity("0");

                        ADD_MY_ITEM_Api_service mAPIInterface = RetrofitClient.getClient(ApiUtils.BASE_URL, Get_coupon_action.this).create(ADD_MY_ITEM_Api_service.class);
                        mAPIInterface.CallApi(Post_).enqueue(new Callback<ADD_ITEM_RESPONSE_BODY>() {
                            @Override
                            public void onResponse(Call<ADD_ITEM_RESPONSE_BODY> call, retrofit2.Response<ADD_ITEM_RESPONSE_BODY> response) {
                                pDialog.dismiss();
                                try {

                                    if (response.isSuccessful()) {


                                        String errornum = response.body().getErrornum();
                                        String errormsg = response.body().getErrormsg();
                                        String OFFER_code = response.body().getOffercode();

                                        try {
                                            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                                            try {
                                                BitMatrix bitMatrix = multiFormatWriter.encode(OFFER_code, BarcodeFormat.CODE_128, 200, 200);
                                                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                                                Img_Barcode.setImageBitmap(bitmap);
                                            } catch (WriterException e) {
                                                e.printStackTrace();
                                            }
                                        } catch (java.lang.IllegalArgumentException e) {
                                            e.printStackTrace();
                                        }

                                    } else {
                                        String str_response = response.body().getErrormsg();


                                        // Toast.makeText(Hold_action_screen.this, str_response, Toast.LENGTH_LONG).show();
                                    }
                                } catch (Exception e) {
                                    // Toast.makeText(feedback.this, "Server error", Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                    // Toast.makeText(Hold_action_screen.this, "Code not found", Toast.LENGTH_LONG).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<ADD_ITEM_RESPONSE_BODY> call, Throwable t) {
                                pDialog.dismiss();
                                // Toast.makeText(Hold_action_screen.this, "Network error ", Toast.LENGTH_LONG).show();

                            }

                        });






                    } else {


                        Toast.makeText(Get_coupon_action.this, "Code not found", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    // Toast.makeText(feedback.this, "Server error", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    Toast.makeText(Get_coupon_action.this, "Code not found", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Deal_response_body> call, Throwable t) {
                pDialog.dismiss();
               // Toast.makeText(Get_coupon_action.this, "Network error ", Toast.LENGTH_LONG).show();

            }

        });


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
                Intent int_login = new Intent(Get_coupon_action.this, Take_me_direction.class);
                startActivity(int_login);
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
