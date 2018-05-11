package expence.abc.com.logindemo;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtEmail, edtPass, edtConfirmPass, edtAddress;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPass = findViewById(R.id.edt_pass);
        edtConfirmPass = findViewById(R.id.edt_confirm_pass);
        edtAddress = findViewById(R.id.edt_address);

        databaseHelper = new DatabaseHelper(RegisterActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:

                ContentValues contentValues = new ContentValues();
                // `id` and `timestamp` will be inserted automatically.
                // no need to add them
                contentValues.put(databaseHelper.COLUMN_NAME, edtName.getText().toString());
                contentValues.put(databaseHelper.COLUMN_EMAIL, edtEmail.getText().toString());
                contentValues.put(databaseHelper.COLUMN_PASSWORD, edtPass.getText().toString());
                contentValues.put(databaseHelper.COLUMN_ADDRESS, edtAddress.getText().toString());
                contentValues.put(databaseHelper.COLUMN_GENDER, 1);
                contentValues.put(databaseHelper.COLUMN_HOBBY, 1);

                Log.e("insertUser", databaseHelper.insertUser(contentValues)+"");

                Log.e("getUsersCount", databaseHelper.getUsersCount()+"");

                break;

            case R.id.txt_already_member:
                Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                break;
        }
    }
}
