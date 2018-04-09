package expence.abc.com.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_login_sqlite:
                Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                break;

            case R.id.btn_recycler_view:
                Intent intentRecyclerView = new Intent(MainActivity.this, ItemListRecyclerActivity.class);
                startActivity(intentRecyclerView);
                break;

            case R.id.btn_base_adapter:
                //Intent intentBaseAdapter = new Intent(MainActivity.this, ItemListBaseActivity.class);
                //startActivity(intentBaseAdapter);

                TempDatabaseHelper tempDatabaseHelper = new TempDatabaseHelper(MainActivity.this);
                tempDatabaseHelper.insertUser();
                tempDatabaseHelper.selectUser();
                break;

            case R.id.btn_retrofit:
                Intent intentRetrofit = new Intent(MainActivity.this, RetrofitActivity.class);
                startActivity(intentRetrofit);
                break;
        }
    }
}