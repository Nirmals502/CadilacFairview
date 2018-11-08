package mobile.cadilacfairview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import Adapters.RowItem;
import Adapters.Utils;
import DATAMODEL.ADD_ITEM_RESPONSE_BODY;
import DATAMODEL.ADD_MY_ITEM_Api_service;
import DATAMODEL.APIService_signup;
import DATAMODEL.ApiUtils;
import DATAMODEL.Deal_api_service;
import DATAMODEL.Deal_response_body;
import DATAMODEL.Delete_Api_service;
import DATAMODEL.Delete_response_body;
import DATAMODEL.Offer_list_api_service;
import DATAMODEL.Offer_value;
import DATAMODEL.Offerlist_response_boddy;
import DATAMODEL.Post_deal;
import DATAMODEL.Post_delete_items;
import DATAMODEL.Post_item_;
import DATAMODEL.Post_offer_list;
import DATAMODEL.RetrofitClient;
import DATAMODEL.Shared_preference_model;
import DATAMODEL.response_body;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class Home_screen extends AppCompatActivity {
    ImageView Img_Find_offer;
    RelativeLayout rlv_holder;
    private ProgressDialog pDialog;
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

    String str_check = "Plus", android_id = "";
    String Offers_array = "";
    int size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
        android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Img_Find_offer = (ImageView) findViewById(R.id.imageView_offer);
        rlv_holder = (RelativeLayout) findViewById(R.id.rlv_ellipse_holder);

        SharedPreferences prefs = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE);
        String Check_button_ = prefs.getString("Check_button", null);
        if (Check_button_ != null) {
            if (Check_button_.contentEquals("minus")) {

                img_plus.setImageResource(R.drawable.minus);

                listView.setVisibility(View.VISIBLE);


            } else {
                img_plus.setImageResource(R.drawable.plus);

                listView.setVisibility(View.GONE);
//                Animation animation = AnimationUtils.loadAnimation(Home_screen.this, R.anim.push_up_in);
//                listView.startAnimation(animation);
            }
        }
//        rowItems = new ArrayList<RowItem>();
//        for (int i = 0; i < images.length; i++) {
//            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
//            rowItems.add(item);
//
//        }


        pDialog = new ProgressDialog(Home_screen.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        Post_offer_list Post = new Post_offer_list();
        Post.setAction("list");
        Post.setCountry("CA");
        Post.setProgram("CF");
        Post.setDevice("5da1d14edfc532f0");


        Offer_list_api_service mAPIInterface = RetrofitClient.getClient(ApiUtils.BASE_URL, Home_screen.this).create(Offer_list_api_service.class);
        mAPIInterface.CallApi(Post).enqueue(new Callback<Offerlist_response_boddy>() {
            @Override
            public void onResponse(Call<Offerlist_response_boddy> call, retrofit2.Response<Offerlist_response_boddy> response) {
                pDialog.dismiss();
                try {
                    if (response.isSuccessful()) {


                        String errornum = response.body().getErrornum();
                        String errormsg = response.body().getErrormsg();
                        //String offers =response.body().getOffers();
                        //   Toast.makeText(Home_screen.this,offers,Toast.LENGTH_LONG).show();
                        List<Offer_value> Offer_list = response.body().getOffers();
                        int size = Offer_list.size();
                        if (size != 0) {
                            CustomListViewAdapter adapter = new CustomListViewAdapter(Home_screen.this,
                                    R.layout.my_offer_lv_layout, Offer_list);
                            listView.setAdapter(adapter);
                            Utils.setListViewHeightBasedOnChildren(listView);
                        }


                        // String respose = response.toString();


                    }
                } catch (Exception e) {
                    // Toast.makeText(feedback.this, "Server error", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<Offerlist_response_boddy> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(Home_screen.this, t.toString(), Toast.LENGTH_LONG).show();
                System.out.println("...................." + t.toString());

            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView DElete = (TextView) view.findViewById(R.id.View);


            }
        });

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
                //  if(size!=0) {
                if (str_check.contentEquals("Plus")) {
                    img_plus.setImageResource(R.drawable.minus);
                    str_check = "minus";
                    listView.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(Home_screen.this, R.anim.push_up_in);
                    listView.startAnimation(animation);
                    SharedPreferences.Editor editor = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE).edit();
                    editor.putString("Check_button", "minus");

                    editor.apply();
                } else if (str_check.contentEquals("minus")) {
                    img_plus.setImageResource(R.drawable.plus);
                    str_check = "Plus";
                    listView.setVisibility(View.GONE);

                    SharedPreferences.Editor editor = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE).edit();
                    editor.putString("Check_button", "Plus");

                    editor.apply();
                }
//                }else{
//                    Toast.makeText(Home_screen.this,"No offers found",Toast.LENGTH_LONG).show();
//                }
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
//                SharedPreferences prefs = getSharedPreferences(Shared_preference_model.MyPREFERENCES, MODE_PRIVATE);
//                String Permission_ = prefs.getString("Permission", null);
//                if (Permission_ != null) {
//                    if (Permission_.contentEquals("Permission Granted")) {
//                        Intent int_login = new Intent(Home_screen.this, Scanning_screen.class);
//                        startActivity(int_login);
//                        finish();
//                    } else if (Permission_.contentEquals("Permission Denied")) {
//                        Intent int_login = new Intent(Home_screen.this, Find_offer_screen.class);
//                        startActivity(int_login);
//                        finish();
//                    }
//
//                } else {
                Intent int_login = new Intent(Home_screen.this, Scanning_screen.class);
                startActivity(int_login);
                finish();
                // }
            }
        });
    }

    public class CustomListViewAdapter extends ArrayAdapter<Offer_value> {

        Context context;
        List<Offer_value> Offer_list;
        private ProgressDialog pDialog;

        public CustomListViewAdapter(Context context, int resourceId,
                                     List<Offer_value> items) {
            super(context, resourceId, items);
            this.context = context;
            this.Offer_list = items;
        }

        /*private view holder class*/
        private class ViewHolder {
            ImageView imageView;
            TextView Txt_Coupon;
            TextView Txt_Delete;
            Button Btn_Remove;

        }


        public View getView(final int position, View convertView, ViewGroup parent) {
            CustomListViewAdapter.ViewHolder holder = null;
            final Offer_value rowItem = getItem(position);

            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.my_offer_lv_layout, null);
                holder = new CustomListViewAdapter.ViewHolder();

                holder.imageView = (ImageView) convertView.findViewById(R.id.img_offer_icon);
                holder.Txt_Coupon = (TextView) convertView.findViewById(R.id.Delete);
                holder.Txt_Delete = (TextView) convertView.findViewById(R.id.View);
                holder.Btn_Remove = (Button) convertView.findViewById(R.id.button_remove);
                convertView.setTag(holder);
            } else
                holder = (CustomListViewAdapter.ViewHolder) convertView.getTag();
            String Offer_image = rowItem.getOffer_logo();
            String Str_offer_type = rowItem.getOffer_type();
            Str_offer_type = StringUtils.capitalize(Str_offer_type.toLowerCase().trim());

            Picasso.get().load(Offer_image).placeholder(R.drawable.progress_bar).into(holder.imageView);
            holder.Txt_Coupon.setText("View\n" + Str_offer_type);
            holder.Txt_Delete.setText("Delete\n" + Str_offer_type);
            final CustomListViewAdapter.ViewHolder finalHolder = holder;
            holder.Txt_Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalHolder.Txt_Coupon.setVisibility(View.INVISIBLE);
                    finalHolder.Txt_Delete.setVisibility(View.INVISIBLE);
                    finalHolder.Btn_Remove.setVisibility(View.VISIBLE);


                }
            });
            holder.Btn_Remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toast.makeText(context, position, Toast.LENGTH_LONG).show();
                    System.out.println("......................" + position);

                    pDialog = new ProgressDialog(context);
                    pDialog.setMessage("Please wait...");
                    pDialog.setCancelable(false);
                    pDialog.show();

                    Post_delete_items Post = new Post_delete_items();
                    Post.setAction("delete");
                    Post.setCountry("CA");
                    Post.setProgram("CF");
                    Post.setDevice("5da1d14edfc532f0");
                    Post.setOffer_id(rowItem.getOffer_id());


                    Delete_Api_service mAPIInterface = RetrofitClient.getClient(ApiUtils.BASE_URL, context).create(Delete_Api_service.class);
                    mAPIInterface.CallApi(Post).enqueue(new Callback<Delete_response_body>() {
                        @Override
                        public void onResponse(Call<Delete_response_body> call, retrofit2.Response<Delete_response_body> response) {
                            pDialog.dismiss();
                            try {
                                if (response.isSuccessful()) {


                                    String errornum = response.body().getErrornum();
                                    String errormsg = response.body().getErrormsg();


                                    pDialog = new ProgressDialog(Home_screen.this);
                                    pDialog.setMessage("Please wait...");
                                    pDialog.setCancelable(false);
                                    pDialog.show();

                                    Post_offer_list Post = new Post_offer_list();
                                    Post.setAction("list");
                                    Post.setCountry("CA");
                                    Post.setProgram("CF");
                                    Post.setDevice("5da1d14edfc532f0");


                                    Offer_list_api_service mAPIInterface = RetrofitClient.getClient(ApiUtils.BASE_URL, Home_screen.this).create(Offer_list_api_service.class);
                                    mAPIInterface.CallApi(Post).enqueue(new Callback<Offerlist_response_boddy>() {
                                        @Override
                                        public void onResponse(Call<Offerlist_response_boddy> call, retrofit2.Response<Offerlist_response_boddy> response) {
                                            pDialog.dismiss();
                                            try {
                                                if (response.isSuccessful()) {


                                                    String errornum = response.body().getErrornum();
                                                    String errormsg = response.body().getErrormsg();
                                                    //String offers =response.body().getOffers();
                                                    //   Toast.makeText(Home_screen.this,offers,Toast.LENGTH_LONG).show();
                                                    List<Offer_value> Offer_list = response.body().getOffers();
                                                    int size = Offer_list.size();
                                                    if (size != 0) {
                                                        CustomListViewAdapter adapter = new CustomListViewAdapter(Home_screen.this,
                                                                R.layout.my_offer_lv_layout, Offer_list);
                                                        listView.setAdapter(adapter);
                                                        Utils.setListViewHeightBasedOnChildren(listView);
                                                    }


                                                    // String respose = response.toString();


                                                }
                                            } catch (Exception e) {
                                                // Toast.makeText(feedback.this, "Server error", Toast.LENGTH_LONG).show();
                                                e.printStackTrace();

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Offerlist_response_boddy> call, Throwable t) {
                                            pDialog.dismiss();
                                            Toast.makeText(Home_screen.this, t.toString(), Toast.LENGTH_LONG).show();
                                            System.out.println("...................." + t.toString());

                                        }

                                    });

//                                Offer_list.remove(position);
//                                notifyDataSetChanged();


                                    // String respose = response.toString();


                                }
                            } catch (Exception e) {
                                // Toast.makeText(feedback.this, "Server error", Toast.LENGTH_LONG).show();
                                e.printStackTrace();

                            }
                        }

                        @Override
                        public void onFailure(Call<Delete_response_body> call, Throwable t) {
                            pDialog.dismiss();
                            Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
                            //System.out.println("...................." + t.toString());

                        }

                    });
                }
            });

            // holder.imageView.setImageResource(Offer_image);

            return convertView;
        }
    }

}
