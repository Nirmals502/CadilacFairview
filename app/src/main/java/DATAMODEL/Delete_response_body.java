package DATAMODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Delete_response_body {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("status")
    @Expose
    private String status;
}
