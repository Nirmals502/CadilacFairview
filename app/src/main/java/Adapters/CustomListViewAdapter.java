//package Adapters;
//
//import java.util.List;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.squareup.picasso.Picasso;
//
//import org.apache.commons.lang3.StringUtils;
//
//import DATAMODEL.ApiUtils;
//import DATAMODEL.Delete_Api_service;
//import DATAMODEL.Delete_response_body;
//import DATAMODEL.Offer_list_api_service;
//import DATAMODEL.Offer_value;
//import DATAMODEL.Offerlist_response_boddy;
//import DATAMODEL.Post_delete_items;
//import DATAMODEL.Post_offer_list;
//import DATAMODEL.RetrofitClient;
//import mobile.cadilacfairview.Home_screen;
//import mobile.cadilacfairview.Login_screen_;
//import mobile.cadilacfairview.R;
//import retrofit2.Call;
//import retrofit2.Callback;
//
//public class CustomListViewAdapter extends ArrayAdapter<Offer_value> {
//
//    Context context;
//    List<Offer_value> Offer_list;
//    private ProgressDialog pDialog;
//
//    public CustomListViewAdapter(Context context, int resourceId,
//                                 List<Offer_value> items) {
//        super(context, resourceId, items);
//        this.context = context;
//        this.Offer_list = items;
//    }
//
//    /*private view holder class*/
//    private class ViewHolder {
//        ImageView imageView;
//        TextView Txt_Coupon;
//        TextView Txt_Delete;
//        Button Btn_Remove;
//
//    }
//
//
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//        final Offer_value rowItem = getItem(position);
//
//        LayoutInflater mInflater = (LayoutInflater) context
//                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.my_offer_lv_layout, null);
//            holder = new ViewHolder();
//
//            holder.imageView = (ImageView) convertView.findViewById(R.id.img_offer_icon);
//            holder.Txt_Coupon = (TextView) convertView.findViewById(R.id.Delete);
//            holder.Txt_Delete = (TextView) convertView.findViewById(R.id.View);
//            holder.Btn_Remove = (Button) convertView.findViewById(R.id.button_remove);
//            convertView.setTag(holder);
//        } else
//            holder = (ViewHolder) convertView.getTag();
//        String Offer_image = rowItem.getOffer_logo();
//        String Str_offer_type = rowItem.getOffer_type();
//        Str_offer_type = StringUtils.capitalize(Str_offer_type.toLowerCase().trim());
//
//        Picasso.get().load(Offer_image).placeholder(R.drawable.progress_bar).into(holder.imageView);
//        holder.Txt_Coupon.setText("View\n" + Str_offer_type);
//        holder.Txt_Delete.setText("Delete\n" + Str_offer_type);
//        final ViewHolder finalHolder = holder;
////        holder.Txt_Delete.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                finalHolder.Txt_Coupon.setVisibility(View.INVISIBLE);
////                finalHolder.Txt_Delete.setVisibility(View.INVISIBLE);
////                finalHolder.Btn_Remove.setVisibility(View.VISIBLE);
////
////
////            }
////        });
////        holder.Btn_Remove.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                // Toast.makeText(context, position, Toast.LENGTH_LONG).show();
////                System.out.println("......................" + position);
////
////                pDialog = new ProgressDialog(context);
////                pDialog.setMessage("Please wait...");
////                pDialog.setCancelable(false);
////                pDialog.show();
////
////                Post_delete_items Post = new Post_delete_items();
////                Post.setAction("delete");
////                Post.setCountry("CA");
////                Post.setProgram("CF");
////                Post.setDevice("5da1d14edfc532f0");
////                Post.setOffer_id(rowItem.getOffer_id());
////
////
////                Delete_Api_service mAPIInterface = RetrofitClient.getClient(ApiUtils.BASE_URL, context).create(Delete_Api_service.class);
////                mAPIInterface.CallApi(Post).enqueue(new Callback<Delete_response_body>() {
////                    @Override
////                    public void onResponse(Call<Delete_response_body> call, retrofit2.Response<Delete_response_body> response) {
////                        pDialog.dismiss();
////                        try {
////                            if (response.isSuccessful()) {
////
////
////                                String errornum = response.body().getErrornum();
////                                String errormsg = response.body().getErrormsg();
////                                Intent int_login = new Intent(context, Home_screen.class);
////                                context.startActivity(int_login);
////
//////                                Offer_list.remove(position);
//////                                notifyDataSetChanged();
////
////
////
////                                // String respose = response.toString();
////
////
////                            }
////                        } catch (Exception e) {
////                            // Toast.makeText(feedback.this, "Server error", Toast.LENGTH_LONG).show();
////                            e.printStackTrace();
////
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<Delete_response_body> call, Throwable t) {
////                        pDialog.dismiss();
////                        Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
////                        //System.out.println("...................." + t.toString());
////
////                    }
////
////                });
////            }
////        });
//
//        // holder.imageView.setImageResource(Offer_image);
//
//        return convertView;
//    }
//}