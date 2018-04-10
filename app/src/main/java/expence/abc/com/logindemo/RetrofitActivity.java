package expence.abc.com.logindemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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

    private class NetworkCall extends AsyncTask<String, Void, String> {

        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected String doInBackground(String... strings) {
            String stringUrl = strings[0];
            String result = null;
            String inputLine;

            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();


                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_retrofit_call:
                RetrofitInterface retrofitInterface = RetrofitApi.getClient().create(RetrofitInterface.class);
                Call<ArrayList<RetrofitListModel>> call = retrofitInterface.getCall();
                call.enqueue(new Callback<ArrayList<RetrofitListModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<RetrofitListModel>> call, Response<ArrayList<RetrofitListModel>> response) {
                        ArrayList<RetrofitListModel> retrofitListModels = response.body();
                        Log.e("body", retrofitListModels.size() + "");
                    }

                    @Override
                    public void onFailure(Call<ArrayList<RetrofitListModel>> call, Throwable t) {

                    }
                });

                break;

            case R.id.btn_asynk_call:

                //Instantiate new instance of our class
                NetworkCall getRequest = new NetworkCall();
                //Perform the doInBackground method, passing in our url
                try {
                    //String to place our result in
                    String result = getRequest.execute("https://jsonplaceholder.typicode.com/posts").get();
                    Log.e("result", result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}
