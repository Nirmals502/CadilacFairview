package DATAMODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deal_response_body {


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

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    @SerializedName("deal")
    @Expose
    private String deal;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    @SerializedName("banner")
    @Expose
    private String banner;

    public String getRetailer_id() {
        return retailer_id;
    }

    public void setRetailer_id(String retailer_id) {
        this.retailer_id = retailer_id;
    }

    @SerializedName("retailer_id")
    @Expose
    private String retailer_id;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @SerializedName("logo")
    @Expose
    private String logo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("title")
    @Expose
    private String title;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @SerializedName("subtitle")
    @Expose
    private String subtitle;

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    @SerializedName("colors")
    @Expose
    private String colors;

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    @SerializedName("sizes")
    @Expose
    private String sizes;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @SerializedName("icon")
    @Expose
    private String icon;

    public String getText_icon() {
        return text_icon;
    }

    public void setText_icon(String text_icon) {
        this.text_icon = text_icon;
    }

    @SerializedName("text_icon")
    @Expose
    private String text_icon;

    public String getIs_coupon() {
        return is_coupon;
    }

    public void setIs_coupon(String is_coupon) {
        this.is_coupon = is_coupon;
    }

    @SerializedName("is_coupon")
    @Expose
    private String is_coupon;

    public String getBarcode_text() {
        return barcode_text;
    }

    public void setBarcode_text(String barcode_text) {
        this.barcode_text = barcode_text;
    }

    @SerializedName("barcode_text")
    @Expose
    private String barcode_text;


    public String getBarcode_format() {
        return barcode_format;
    }

    public void setBarcode_format(String barcode_format) {
        this.barcode_format = barcode_format;
    }

    @SerializedName("barcode_format")
    @Expose
    private String barcode_format;

    public String getAction_verb() {
        return action_verb;
    }

    public void setAction_verb(String action_verb) {
        this.action_verb = action_verb;
    }

    @SerializedName("action_verb")
    @Expose
    private String action_verb;

    public String getAction_data() {
        return action_data;
    }

    public void setAction_data(String action_data) {
        this.action_data = action_data;
    }

    @SerializedName("action_data")
    @Expose
    private String action_data;

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    @SerializedName("retailer")
    @Expose
    private String retailer;


}
