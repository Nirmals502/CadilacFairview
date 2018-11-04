package DATAMODEL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Login_service {
    @POST("members/")
    @Headers({"Accept: application/json"})
    Call<response_body> CallApi(@Body Login_post loginresult);
}
