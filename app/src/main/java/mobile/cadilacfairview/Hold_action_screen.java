package mobile.cadilacfairview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DATAMODEL.ApiUtils;
import DATAMODEL.Deal_api_service;
import DATAMODEL.Deal_response_body;
import DATAMODEL.Post_Sound_validate_;
import DATAMODEL.Post_deal;
import DATAMODEL.RetrofitClient;
import DATAMODEL.Souncode_validate_response_body;
import DATAMODEL.Sound_validate_api_service;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class Hold_action_screen extends AppCompatActivity {

    private ProgressDialog pDialog;
    @BindView(R.id.spinner_Color)
    Spinner spinner_color;
    @BindView(R.id.spinner_Size)
    Spinner Spiner_size;
    @BindView(R.id.spinner_quantity)
    EditText spinner_quantity;
    @BindView(R.id.rlv___)
    ImageView Rlv_hold_action;
    @BindView(R.id.Rlv_back)
    RelativeLayout rlv_back;
    @BindView(R.id.imageView_main_image)
    ImageView Img_banner;
    @BindView(R.id.textView_header)
    TextView Txt_tittle;

    @BindView(R.id.textView_product_name)
    TextView Txt_Product_name;

    @BindView(R.id.textView_description)
    TextView Txt_description;
    String barcode_format = "";
    String barcode_text = "";
    String banner = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hold_action_screen);
        ButterKnife.bind(this);

        pDialog = new ProgressDialog(Hold_action_screen.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        Post_deal Post = new Post_deal();
        Post.setAction("2");
        Post.setCountry("CA");
        Post.setProgram("CF");


        Deal_api_service mAPIInterface = RetrofitClient.getClient(ApiUtils.BASE_URL, Hold_action_screen.this).create(Deal_api_service.class);
        mAPIInterface.CallApi(Post).enqueue(new Callback<Deal_response_body>() {
            @Override
            public void onResponse(Call<Deal_response_body> call, retrofit2.Response<Deal_response_body> response) {
                pDialog.dismiss();
                try {

                    if (response.isSuccessful()) {


                        String errornum = response.body().getErrornum();
                        String errormsg = response.body().getErrormsg();
                        String deal = response.body().getDeal();
                        banner = response.body().getBanner();
                        String retailer_id = response.body().getRetailer_id();
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
                        Picasso.get().load(text_icon).placeholder(R.drawable.progress_bar).into(Rlv_hold_action);
                        Txt_tittle.setText(retailer);
                        Txt_Product_name.setText(title);
                        Txt_description.setText(subtitle);
                        String[] Colors = colors.split(",");
                        String[] Size = sizes.split(",");


                        final List<String> plantsList = new ArrayList<>(Arrays.asList(Colors));

                        // Initializing an ArrayAdapter
                        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                                Hold_action_screen.this, android.R.layout.simple_spinner_item, plantsList) {
                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                if (position % 2 == 1) {
                                    // Set the item background color
                                    tv.setBackgroundColor(Color.parseColor("#E6E6E6"));
                                } else {
                                    // Set the alternate item background color
                                    tv.setBackgroundColor(Color.parseColor("#D8D8D8"));
                                }
                                return view;
                            }
                        };

                        final List<String> ListSize = new ArrayList<>(Arrays.asList(Size));

                        // Initializing an ArrayAdapter
                        final ArrayAdapter<String> spinnerArrayAdapterr = new ArrayAdapter<String>(
                                Hold_action_screen.this, android.R.layout.simple_spinner_item, ListSize) {
                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                if (position % 2 == 1) {
                                    // Set the item background color
                                    tv.setBackgroundColor(Color.parseColor("#E6E6E6"));
                                } else {
                                    // Set the alternate item background color
                                    tv.setBackgroundColor(Color.parseColor("#D8D8D8"));
                                }
                                return view;
                            }
                        };



        /*
            public void setPrompt (CharSequence prompt)
                Sets the prompt to display when the dialog is shown.
         */


                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        spinner_color.setAdapter(spinnerArrayAdapter);
                        spinnerArrayAdapterr.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        Spiner_size.setAdapter(spinnerArrayAdapterr);
                    } else {
                        //String str_response = response.body().getErrormsg();


                        Toast.makeText(Hold_action_screen.this, "Code not found", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    // Toast.makeText(feedback.this, "Server error", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    Toast.makeText(Hold_action_screen.this, "Code not found", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Deal_response_body> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(Hold_action_screen.this, "Network error ", Toast.LENGTH_LONG).show();

            }

        });


        Rlv_hold_action.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFAA0000));
                    Intent int_login = new Intent(Hold_action_screen.this, Hold_Book_confirmation.class);
                    int_login.putExtra("Barcode_format", barcode_format);
                    int_login.putExtra("Barcode_Text", barcode_text);
                    int_login.putExtra("BANNER", banner);

                    startActivity(int_login);
                    finish();
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    view.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 000000000));

                }


                return false;
            }
        });
        rlv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent int_login = new Intent(Hold_action_screen.this, Offer_response_screen.class);
//                startActivity(int_login);
                finish();
            }
        });


    }
}

