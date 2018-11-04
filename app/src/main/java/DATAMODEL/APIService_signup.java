package DATAMODEL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService_signup {

   // @FormUrlEncoded
    @POST("members/")
    @Headers({"Accept: application/json"})
    Call<response_body> CallApi(@Body Post_signup loginresult);
}
