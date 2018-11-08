package DATAMODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ADD_ITEM_RESPONSE_BODY {
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

    public String getOffercode() {
        return offercode;
    }

    public void setOffercode(String offercode) {
        this.offercode = offercode;
    }

    @SerializedName("offercode")
    @Expose
    private String offercode;
}
