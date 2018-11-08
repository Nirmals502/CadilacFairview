package DATAMODEL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Delete_Api_service {
    @POST("myoffers/")
    @Headers({"Accept: application/json"})
    Call<Delete_response_body> CallApi(@Body Post_delete_items result);


}
