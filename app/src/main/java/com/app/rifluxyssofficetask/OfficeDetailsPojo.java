package com.app.rifluxyssofficetask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfficeDetailsPojo {

    @SerializedName("office_name")
    @Expose
    private String officeName;
    @SerializedName("office_address_1")
    @Expose
    private String officeAddress1;
    @SerializedName("office_address_2")
    @Expose
    private String officeAddress2;
    @SerializedName("office_phone")
    @Expose
    private String officePhone;
    @SerializedName("office_fax")
    @Expose
    private String officeFax;
    @SerializedName("office_email")
    @Expose
    private String officeEmail;
    @SerializedName("office_website")
    @Expose
    private String officeWebsite;
    @SerializedName("office_facebook")
    @Expose
    private String officeFacebook;
    @SerializedName("Latitute")
    @Expose
    private String latitute;
    @SerializedName("Longitute")
    @Expose
    private String longitute;

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeAddress1() {
        return officeAddress1;
    }

    public void setOfficeAddress1(String officeAddress1) {
        this.officeAddress1 = officeAddress1;
    }

    public String getOfficeAddress2() {
        return officeAddress2;
    }

    public void setOfficeAddress2(String officeAddress2) {
        this.officeAddress2 = officeAddress2;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getOfficeFax() {
        return officeFax;
    }

    public void setOfficeFax(String officeFax) {
        this.officeFax = officeFax;
    }

    public String getOfficeEmail() {
        return officeEmail;
    }

    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }

    public String getOfficeWebsite() {
        return officeWebsite;
    }

    public void setOfficeWebsite(String officeWebsite) {
        this.officeWebsite = officeWebsite;
    }

    public String getOfficeFacebook() {
        return officeFacebook;
    }

    public void setOfficeFacebook(String officeFacebook) {
        this.officeFacebook = officeFacebook;
    }

    public String getLatitute() {
        return latitute;
    }

    public void setLatitute(String latitute) {
        this.latitute = latitute;
    }

    public String getLongitute() {
        return longitute;
    }

    public void setLongitute(String longitute) {
        this.longitute = longitute;
    }
}
