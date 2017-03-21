package com.cidaassdk;

/**
 * Created by Suprada on 21-Mar-17.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by suprada on 19/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile {
    private String userId;
    private Date createTime;
    private String currentEMail;
    private String firstname;
    private String displayname;
    @JsonIgnore
    private String gender;
    @JsonIgnore
    private String emailStatus;
    private String profilePhotoURL;
    private String name;
    private String carbookIdentifier;
    private Date updateTime;
    private List<String> photoGallery;
    private List<String> iconGallery;
    //  private Gender gender;
    //  private AddressStatus emailStatus;
    private String profileIconURL;
    private List<String> emails;
    private List<String> phoneNumbers;
    private Locale userLocale;
    private Date birthday;
    private String utcOffset;
    private AddressEntity currentAddress;
    private List<UserAddress> userAddresses;
    private List<String> fcmIds;

    private boolean deleted;
    private UserLicenseInfoEntity licenseInfo;


    public UserLicenseInfoEntity getLicenseInfo() {
        return licenseInfo;
    }

    public void setLicenseInfo(UserLicenseInfoEntity licenseInfo) {
        this.licenseInfo = licenseInfo;
    }


    public List<String> getFcmIds() {
        return fcmIds;
    }

    public void setFcmIds(List<String> fcmIds) {
        this.fcmIds = fcmIds;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCarbookIdentifier() {
        return carbookIdentifier;
    }

    public void setCarbookIdentifier(String carbookIdentifier) {
        this.carbookIdentifier = carbookIdentifier;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getPhotoGallery() {
        return photoGallery;
    }

    public void setPhotoGallery(List<String> photoGallery) {
        this.photoGallery = photoGallery;
    }

    public List<String> getIconGallery() {
        return iconGallery;
    }

    public void setIconGallery(List<String> iconGallery) {
        this.iconGallery = iconGallery;
    }

    public String getProfileIconURL() {
        return profileIconURL;
    }

    public void setProfileIconURL(String profileIconURL) {
        this.profileIconURL = profileIconURL;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Locale getUserLocale() {
        return userLocale;
    }

    public void setUserLocale(Locale userLocale) {
        this.userLocale = userLocale;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    public AddressEntity getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(AddressEntity currentAddress) {
        this.currentAddress = currentAddress;
    }

    public List<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonIgnore
    public String getEmailStatus() {
        return emailStatus;
    }

    @JsonIgnore
    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getProfilePhotoURL() {
        return profilePhotoURL;
    }

    public void setProfilePhotoURL(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getCurrentEMail() {
        return currentEMail;
    }

    public void setCurrentEMail(String currentEMail) {
        this.currentEMail = currentEMail;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
}
