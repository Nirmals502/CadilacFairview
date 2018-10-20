package mobile.cadilacfairview;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Hold_action_screen extends AppCompatActivity {
    @BindView(R.id.spinner_Color)
    Spinner spinner_color;
    @BindView(R.id.spinner_Size)
    Spinner Spiner_size;
    @BindView(R.id.spinner_quantity)
    Spinner spinner_quantity;
    @BindView(R.id.rlv___)
    RelativeLayout Rlv_hold_action;
    String[] Colors = new String[]{
            "Color",
            "Red",
            "Blue",
            "White",
    };
    String[] Size = new String[]{
            "Size",
            "5",
            "7",
            "8",
            "9",
    };
    String[] quantity = new String[]{
            "Quantity",
            "1",
            "2",
            "3",
            "4",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hold_action_screen);
        ButterKnife.bind(this);

        Rlv_hold_action.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFAA0000));
                    Intent int_login = new Intent(Hold_action_screen.this, Hold_Book_confirmation.class);
                    startActivity(int_login);
                    finish();
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    view.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 000000000));

                }


                return false;
            }
        });


        final List<String> plantsList = new ArrayList<>(Arrays.asList(Colors));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, plantsList) {
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position % 2 == 1) {
                    // Set the item background color
                    tv.setBackgroundColor(Color.parseColor("#FFF9A600"));
                } else {
                    // Set the alternate item background color
                    tv.setBackgroundColor(Color.parseColor("#FFE49200"));
                }
                return view;
            }
        };

        final List<String> ListSize = new ArrayList<>(Arrays.asList(Size));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapterr = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, ListSize) {
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position % 2 == 1) {
                    // Set the item background color
                    tv.setBackgroundColor(Color.parseColor("#FFF9A600"));
                } else {
                    // Set the alternate item background color
                    tv.setBackgroundColor(Color.parseColor("#FFE49200"));
                }
                return view;
            }
        };

        final List<String> Quantity = new ArrayList<>(Arrays.asList(quantity));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter_quantity = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, Quantity) {
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position % 2 == 1) {
                    // Set the item background color
                    tv.setBackgroundColor(Color.parseColor("#FFF9A600"));
                } else {
                    // Set the alternate item background color
                    tv.setBackgroundColor(Color.parseColor("#FFE49200"));
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
        spinnerArrayAdapter_quantity.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_quantity.setAdapter(spinnerArrayAdapter_quantity);
    }
}

