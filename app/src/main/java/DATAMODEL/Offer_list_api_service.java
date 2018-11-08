package DATAMODEL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Offer_list_api_service {
    @POST("myoffers/")
    @Headers({"Accept: application/json"})
    Call<Offerlist_response_boddy> CallApi(@Body Post_offer_list result);


}
