package expence.abc.com.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_name, edt_email, ext_pass, ext_confirm_pass;
    private Button btn_register;
    private TextView txt_already_member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        ext_pass = findViewById(R.id.ext_pass);
        ext_confirm_pass = findViewById(R.id.ext_confirm_pass);
        btn_register = findViewById(R.id.btn_register);
        txt_already_member = findViewById(R.id.txt_already_member);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:

                break;

            case R.id.txt_already_member:
                Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                break;
        }
    }
}
