package expence.abc.com.logindemo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {

    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl("https://services.groupkt.com/country/get/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
