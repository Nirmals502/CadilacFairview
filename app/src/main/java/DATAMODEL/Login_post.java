package DATAMODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login_post {
    @SerializedName("action")
    @Expose
    private String action;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @SerializedName("device")
    @Expose
    private String device;


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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @SerializedName("language")
    @Expose
    private String language;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @SerializedName("email")
    @Expose
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SerializedName("password")
    @Expose
    private String password;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


}
