package expence.abc.com.logindemo;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout tilName, tilEmail, tilPass, tilConfirmPass, tilAddress;
    private RadioGroup rgpGender;
    private RadioButton rbnGender;
    private CheckBox chkSinging, chkDancing, chkSwimming, chkTraveling;

    private String hobbies;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tilName = findViewById(R.id.til_name);
        tilEmail = findViewById(R.id.til_email);
        tilPass = findViewById(R.id.til_pass);
        tilConfirmPass = findViewById(R.id.til_confirm_pass);
        rgpGender = findViewById(R.id.rgp_gender);
        chkSinging = findViewById(R.id.chk_singing);
        chkDancing = findViewById(R.id.chk_dancing);
        chkSwimming = findViewById(R.id.chk_swimming);
        chkTraveling = findViewById(R.id.chk_traveling);
        tilAddress = findViewById(R.id.til_address);

        databaseHelper = new DatabaseHelper(RegisterActivity.this);
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

    private boolean validateName() {
        String nameInput = tilName.getEditText().getText().toString().trim();

        if (nameInput.isEmpty()) {
            tilName.setError(getString(R.string.field_cant_be_empty));
            return false;
        } else if (nameInput.length() > 15) {
            tilName.setError(getString(R.string.name_is_too_long));
            return false;
        } else {
            tilName.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passInput = tilPass.getEditText().getText().toString().trim();
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        Pattern pattern = null;
        pattern = Pattern.compile(PASSWORD_PATTERN);

        if (passInput.isEmpty()) {
            tilPass.setError(getString(R.string.field_cant_be_empty));
            return false;
        } else if (passInput.length() < 8 || !pattern.matcher(passInput).matches()) {
            tilPass.setError(getString(R.string.password_should_be_strong));
            return false;
        } else {
            tilPass.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String passInput = tilPass.getEditText().getText().toString().trim();
        String confirmPassInput = tilConfirmPass.getEditText().getText().toString().trim();

        if (confirmPassInput.isEmpty()) {
            tilConfirmPass.setError(getString(R.string.field_cant_be_empty));
            return false;
        } else if (!passInput.equals(confirmPassInput)) {
            tilConfirmPass.setError(getString(R.string.password_can_not_match));
            return false;
        } else {
            tilConfirmPass.setError(null);
            return true;
        }
    }

    private boolean validateAddress() {
        String addressInput = tilAddress.getEditText().getText().toString().trim();

        if (addressInput.isEmpty()) {
            tilAddress.setError(getString(R.string.field_cant_be_empty));
            return false;
        } else {
            tilAddress.setError(null);
            return true;
        }
    }

    private boolean validateGender() {
        if (rgpGender.getCheckedRadioButtonId() == -1) {
            Snackbar.make(rgpGender, getString(R.string.please_select_gender), Snackbar.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateHobbies() {
        if (chkDancing.isChecked() | chkSinging.isChecked() | chkSwimming.isChecked() | chkTraveling.isChecked()) {
            return true;
        } else {
            Snackbar.make(chkDancing, getString(R.string.please_select_hobby), Snackbar.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:

                if (!validateName() | !validateEmail() | !validatePassword() | !validateConfirmPassword() | !validateAddress()) {
                    return;
                } else if (!validateGender()) {
                    return;
                } else if (!validateHobbies()) {
                    return;
                }

                ContentValues contentValues = new ContentValues();
                // `id` and `timestamp` will be inserted automatically.
                // no need to add them
                contentValues.put(databaseHelper.COLUMN_NAME, tilName.getEditText().getText().toString().trim());
                contentValues.put(databaseHelper.COLUMN_EMAIL, tilEmail.getEditText().getText().toString().trim());
                contentValues.put(databaseHelper.COLUMN_PASSWORD, tilPass.getEditText().getText().toString().trim());
                contentValues.put(databaseHelper.COLUMN_ADDRESS, tilAddress.getEditText().getText().toString().trim());

                // find the radiobutton by returned id
                rbnGender = findViewById(rgpGender.getCheckedRadioButtonId());

                if (rbnGender.getText().equals(getString(R.string.male))) {
                    contentValues.put(databaseHelper.COLUMN_GENDER, 1);
                } else if (rbnGender.getText().equals(getString(R.string.female))) {
                    contentValues.put(databaseHelper.COLUMN_GENDER, 0);
                }

                StringBuilder sb = new StringBuilder();

                if (chkSinging.isChecked()) {
                    sb.append(", " + chkSinging.getText());
                }

                if (chkDancing.isChecked()) {
                    sb.append(", " + chkDancing.getText());
                }

                if (chkSwimming.isChecked()) {
                    sb.append(", " + chkSwimming.getText());
                }

                if (chkTraveling.isChecked()) {
                    sb.append(", " + chkTraveling.getText());
                }

                if (sb.length() > 0) { // No toast if the string is empty
                    // Remove the first comma
                    hobbies = sb.deleteCharAt(sb.indexOf(",")).toString();
                } else {
                    hobbies = "";
                }


                /*chkSinging.setChecked(false);
                chkDancing.setChecked(false);
                chkSwimming.setChecked(false);
                chkTraveling.setChecked(false);

                if (hobbies.contains(getString(R.string.singing))) {
                    chkSinging.setChecked(true);
                }

                if (hobbies.contains(getString(R.string.dancing))) {
                    chkDancing.setChecked(true);
                }

                if (hobbies.contains(getString(R.string.swimming))) {
                    chkSwimming.setChecked(true);
                }

                if (hobbies.contains(getString(R.string.traveling))) {
                    chkTraveling.setChecked(true);
                }*/

                Log.e("hobbies", hobbies);


                contentValues.put(databaseHelper.COLUMN_HOBBY, hobbies);

                Log.e("insertUser", databaseHelper.insertUser(contentValues) + "");

                Log.e("getUsersCount", databaseHelper.getUsersCount() + "");

                break;

            case R.id.txt_already_member:
                Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                break;
        }
    }
}
