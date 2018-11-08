package DATAMODEL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ADD_MY_ITEM_Api_service {
    @POST("offers/")
    @Headers({"Accept: application/json"})
    Call<ADD_ITEM_RESPONSE_BODY> CallApi(@Body Post_item_ result);
}
