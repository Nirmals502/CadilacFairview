package mobile.cadilacfairview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.LightingColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class Hold_Book_confirmation extends AppCompatActivity {
    RelativeLayout rlv_back;
    String Str_Banner = "";
    String Barcode_format = "";
    String Barcode_Text = "";
    @BindView(R.id.imageView_pic)
    ImageView img_Banner;
    @BindView(R.id.imageView_qr_code)
    ImageView BarCode_image;
    @BindView(R.id.Rlv_circle_holder)
    ImageView Img_Rlv_crcle;
    @BindView(R.id.textView_reserved)
    TextView Txt_Tittle;
    @BindView(R.id.textView_present_this)
    TextView Txt_subtittle;
    @BindView(R.id.Rlv_direction)

    RelativeLayout Rlv_direction;
    @BindView(R.id.textView_code_to_any)
    TextView TXT_Barcode_txt;
    String title = "";
    String subtitle = "";
    String icon = "";
    String Offer_code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hold_book_confirm);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                Str_Banner = null;
                Barcode_format = null;
                Barcode_Text = null;
                title = null;
                subtitle = null;
                icon = null;
                Offer_code= null;
            } else {
                Str_Banner = extras.getString("BANNER");
                Barcode_Text = extras.getString("Barcode_Text");
                Barcode_format = extras.getString("Barcode_format");
                title = extras.getString("title");
                subtitle = extras.getString("subtitle");
                icon = extras.getString("icon");
                Offer_code=extras.getString("Offer_code");

            }
        } else {

            Str_Banner = (String) savedInstanceState.getSerializable("BANNER");
            Barcode_Text = (String) savedInstanceState.getSerializable("Barcode_Text");
            Barcode_format = (String) savedInstanceState.getSerializable("Barcode_format");
            title = (String) savedInstanceState.getSerializable("title");
            subtitle = (String) savedInstanceState.getSerializable("subtitle");
            icon = (String) savedInstanceState.getSerializable("icon");
            Offer_code=(String) savedInstanceState.getSerializable("Offer_code");
        }
       // Toast.makeText(Hold_Book_confirmation.this, Barcode_format, Toast.LENGTH_LONG).show();
        Picasso.get().load(Str_Banner).placeholder(R.drawable.progress_bar).into(img_Banner);
        Picasso.get().load(icon).placeholder(R.drawable.progress_bar).into(Img_Rlv_crcle);
        Txt_Tittle.setText(title);
        Txt_subtittle.setText(subtitle);
        TXT_Barcode_txt.setText(Barcode_Text);
        try {


            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(Offer_code, BarcodeFormat.CODE_128, 200, 200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                BarCode_image.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        } catch (java.lang.IllegalArgumentException e) {
            e.printStackTrace();
        }

        rlv_back = (RelativeLayout) findViewById(R.id.Rlv_back);
        rlv_back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    view.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFAA0000));
                    Intent int_login = new Intent(Hold_Book_confirmation.this, Offer_response_screen.class);
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
                Intent int_login = new Intent(Hold_Book_confirmation.this, Take_me_direction.class);
                startActivity(int_login);
                finish();
            }
        });
    }
}
