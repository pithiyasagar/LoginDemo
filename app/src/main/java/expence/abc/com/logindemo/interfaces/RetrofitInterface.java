package expence.abc.com.logindemo.interfaces;

import expence.abc.com.logindemo.model.RetrofitListModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("all")
    Call<RetrofitListModel> getCall();
}
