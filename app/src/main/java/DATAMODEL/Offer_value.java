package DATAMODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offer_value {
    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    @SerializedName("offer_id")
    @Expose
    private String offer_id;

    public String getOffer_logo() {
        return offer_logo;
    }

    public void setOffer_logo(String offer_logo) {
        this.offer_logo = offer_logo;
    }

    @SerializedName("offer_logo")
    @Expose
    private String offer_logo;

    public String getOffer_type() {
        return offer_type;
    }

    public void setOffer_type(String offer_type) {
        this.offer_type = offer_type;
    }

    @SerializedName("offer_type")
    @Expose
    private String offer_type;
}
