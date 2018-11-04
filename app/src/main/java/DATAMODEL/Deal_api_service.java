package DATAMODEL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Deal_api_service {

    @POST("deals/")
    @Headers({"Accept: application/json"})
    Call<Deal_response_body> CallApi(@Body Post_deal result);


}
