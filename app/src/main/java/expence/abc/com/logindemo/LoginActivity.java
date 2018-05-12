package expence.abc.com.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Initialize all view here
    private TextInputLayout tilEmail, tilPass;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tilEmail = findViewById(R.id.til_email);
        tilPass = findViewById(R.id.til_pass);

        databaseHelper = new DatabaseHelper(LoginActivity.this);
    }

    private boolean validateEmail() {
        String emailInput = tilEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            tilEmail.setError(getString(R.string.field_cant_be_empty));
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            tilEmail.setError(getString(R.string.email_is_note_valid));
            return false;
        } else {
            tilEmail.setError(null);
            return true;
        }
    }

    private boolean validatePass() {
        String passInput = tilPass.getEditText().getText().toString().trim();

        if (passInput.isEmpty()) {
            tilPass.setError(getString(R.string.field_cant_be_empty));
            return false;
        } else {
            tilPass.setError(null);
            return true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                if (!validateEmail() | !validatePass()) {
                    return;
                }

                if (databaseHelper.login(tilEmail.getEditText().getText().toString().trim(), tilPass.getEditText().getText().toString().trim())) {
                    Snackbar.make(tilEmail, "Login Success...", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(tilEmail, getString(R.string.invalid_credential), Snackbar.LENGTH_LONG).show();
                }
                break;

            case R.id.txt_no_account_yet:
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }
}
