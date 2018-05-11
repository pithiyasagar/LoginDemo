package expence.abc.com.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Initialize all view here
    private EditText edt_email, ext_pass;
    private Button btn_login;
    private TextView txt_no_account_yet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = findViewById(R.id.edt_email);
        ext_pass = findViewById(R.id.edt_pass);
        btn_login = findViewById(R.id.btn_login);
        txt_no_account_yet = findViewById(R.id.txt_no_account_yet);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                break;

            case R.id.txt_no_account_yet:
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }
}
