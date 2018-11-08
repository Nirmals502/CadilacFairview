package mobile.cadilacfairview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class Take_me_direction extends AppCompatActivity {
    @BindView(R.id.Rlv_back)
    RelativeLayout Rlv_back;
    String deal = "";
    String Deal = "";
    private ProgressDialog pDialog;

    @BindView(R.id.imageView_main_image)
    ImageView Img_banner;

    @BindView(R.id.textView_header)
    TextView Tittle;

    @BindView(R.id.textView_product_name)
    TextView Txt_description;

    String barcode_format = "";
    String barcode_text = "";
    String banner = "";


    String retailer_id = "";

    String Str_Color = "";
    String Str_size = "";
    String Str_quantity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_me_direction);
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

        pDialog = new ProgressDialog(Take_me_direction.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        Post_deal Post = new Post_deal();
        Post.setAction(Deal);
        Post.setCountry("CA");
        Post.setProgram("CF");


        Deal_api_service mAPIInterface = RetrofitClient.getClient(ApiUtils.BASE_URL, Take_me_direction.this).create(Deal_api_service.class);
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


                        Picasso.get().load(banner).placeholder(R.drawable.progress_bar).into(Img_banner);
                        Tittle.setText(title);

                        Txt_description.setText(subtitle);







        /*
            public void setPrompt (CharSequence prompt)
                Sets the prompt to display when the dialog is shown.
         */


                    } else {
                        //String str_response = response.body().getErrormsg();


                        Toast.makeText(Take_me_direction.this, "Response not found", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    // Toast.makeText(feedback.this, "Server error", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    Toast.makeText(Take_me_direction.this, "Response not found", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Deal_response_body> call, Throwable t) {
                pDialog.dismiss();
                // Toast.makeText(Get_coupon_action.this, "Network error ", Toast.LENGTH_LONG).show();

            }

        });


        Rlv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_login = new Intent(Take_me_direction.this, Offer_response_screen.class);
                startActivity(int_login);
                finish();
            }
        });
    }
}
