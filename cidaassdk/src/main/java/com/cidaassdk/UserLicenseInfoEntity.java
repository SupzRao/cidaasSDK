package com.cidaassdk;

import java.util.Date;

/**
 * Created by Giri on 2/21/2017.
 */

public class UserLicenseInfoEntity {

    private String licenseNumber;
    private String licenseCategory;
    private String licensePictureUrl;
    private String greenCardNumber;
    private Date licenseValidity;

    public Date getLicenseValidity() {
        return licenseValidity;
    }

    public void setLicenseValidity(Date licenseValidity) {
        this.licenseValidity = licenseValidity;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseCategory() {
        return licenseCategory;
    }

    public void setLicenseCategory(String licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

    public String getLicensePictureUrl() {
        return licensePictureUrl;
    }

    public void setLicensePictureUrl(String licensePictureUrl) {
        this.licensePictureUrl = licensePictureUrl;
    }

    public String getGreenCardNumber() {
        return greenCardNumber;
    }

    public void setGreenCardNumber(String greenCardNumber) {
        this.greenCardNumber = greenCardNumber;
    }

}
