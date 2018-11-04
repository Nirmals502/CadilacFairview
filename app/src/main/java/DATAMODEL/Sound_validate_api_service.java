package DATAMODEL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Sound_validate_api_service {

    @POST("soundcode/")
    @Headers({"Accept: application/json"})
    Call<Souncode_validate_response_body> CallApi(@Body Post_Sound_validate_ result);


}
