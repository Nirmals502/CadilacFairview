package DATAMODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Offerlist_response_boddy {
    public String getErrornum() {
        return errornum;
    }

    public void setErrornum(String errornum) {
        this.errornum = errornum;
    }

    @SerializedName("errornum")
    @Expose
    private String errornum;

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    @SerializedName("errormsg")
    @Expose
    private String errormsg;


//    public String getOffers() {
//        return offers;
//    }
//
//    public void setOffers(String offers) {
//        this.offers = offers;
//    }

    public List<Offer_value> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer_value> offers) {
        this.offers = offers;
    }

    @SerializedName("offers")
    @Expose
    private List<Offer_value> offers;
//    @SerializedName("offers")
//    @Expose
//    private String offers;


}
