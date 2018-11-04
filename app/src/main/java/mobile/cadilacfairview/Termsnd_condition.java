package mobile.cadilacfairview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import butterknife.BindView;

public class Termsnd_condition extends AppCompatActivity {
    Button Btn_Confirm;
    @BindView(R.id.checkBox_terms_conditions)
    CheckBox check_terms_condition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsnd_condition);
        Btn_Confirm = (Button) findViewById(R.id.button_confirm);
        Btn_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_terms_condition.isChecked()) {
                    Intent int_login = new Intent(Termsnd_condition.this, Welcome_screen.class);
                    startActivity(int_login);
                    finish();
                } else {
                    Toast.makeText(Termsnd_condition.this, "Please accept terms and condition", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
