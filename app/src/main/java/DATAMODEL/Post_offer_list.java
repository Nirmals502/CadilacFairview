package DATAMODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post_offer_list {
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @SerializedName("action")
    @Expose
    private String action;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @SerializedName("country")
    @Expose
    private String country;

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @SerializedName("program")
    @Expose
    private String program;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @SerializedName("device")
    @Expose
    private String device;
}
