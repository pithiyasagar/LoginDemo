package expence.abc.com.logindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import expence.abc.com.logindemo.interfaces.RetrofitInterface;
import expence.abc.com.logindemo.model.RetrofitListModel;
import expence.abc.com.logindemo.network.RetrofitApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_network_call:

                RetrofitInterface retrofitInterface = RetrofitApi.getClient().create(RetrofitInterface.class);
                Call<RetrofitListModel> call = retrofitInterface.getCall();
                call.equals(new Callback<RetrofitListModel>() {
                    @Override
                    public void onResponse(Call<RetrofitListModel> call, Response<RetrofitListModel> response) {
                        Log.e("body", response.body().toString() + "");

                    }

                    @Override
                    public void onFailure(Call<RetrofitListModel> call, Throwable t) {

                    }
                });

                break;
        }
    }
}
