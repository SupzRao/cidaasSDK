package com.cidaassdk;

/**
 * Created by Suprada on 21-Mar-17.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * Created by suprada on 19/2/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile {
    public String id;
    public String provider;
    public String ssoId;
    public String username;
    public String email;
    public String mobile;
    public String firstname;
    public String lastname;
    public String displayName;
    public String createTime;
    public boolean active;
    public boolean emailVerified;
    public boolean mobileNoVerified;
    public boolean smsNotificationEnabled;
    public boolean googleAuthenticatorEnabled;
    public String currentLocale;
    public String userStatus;
    public String identityJRString;
    public CustomFields customFields;
    public List<String> roles;
    public boolean twofactorenabled;
    public String  lastLoggedTime;
    public String lastUsedSocialIdentity;
    public String photoURL;
    public String usedProviders;
    public String customFieldWithMetadata;
    public String groups;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isMobileNoVerified() {
        return mobileNoVerified;
    }

    public void setMobileNoVerified(boolean mobileNoVerified) {
        this.mobileNoVerified = mobileNoVerified;
    }

    public boolean isSmsNotificationEnabled() {
        return smsNotificationEnabled;
    }

    public void setSmsNotificationEnabled(boolean smsNotificationEnabled) {
        this.smsNotificationEnabled = smsNotificationEnabled;
    }

    public boolean isGoogleAuthenticatorEnabled() {
        return googleAuthenticatorEnabled;
    }

    public void setGoogleAuthenticatorEnabled(boolean googleAuthenticatorEnabled) {
        this.googleAuthenticatorEnabled = googleAuthenticatorEnabled;
    }

    public String getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(String currentLocale) {
        this.currentLocale = currentLocale;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getIdentityJRString() {
        return identityJRString;
    }

    public void setIdentityJRString(String identityJRString) {
        this.identityJRString = identityJRString;
    }

    public CustomFields getCustomFields() {
        return customFields;
    }

    public void setCustomFields(CustomFields customFields) {
        this.customFields = customFields;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isTwofactorenabled() {
        return twofactorenabled;
    }

    public void setTwofactorenabled(boolean twofactorenabled) {
        this.twofactorenabled = twofactorenabled;
    }

    public String getLastLoggedTime() {
        return lastLoggedTime;
    }

    public void setLastLoggedTime(String lastLoggedTime) {
        this.lastLoggedTime = lastLoggedTime;
    }

    public String getLastUsedSocialIdentity() {
        return lastUsedSocialIdentity;
    }

    public void setLastUsedSocialIdentity(String lastUsedSocialIdentity) {
        this.lastUsedSocialIdentity = lastUsedSocialIdentity;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getUsedProviders() {
        return usedProviders;
    }

    public void setUsedProviders(String usedProviders) {
        this.usedProviders = usedProviders;
    }

    public String getCustomFieldWithMetadata() {
        return customFieldWithMetadata;
    }

    public void setCustomFieldWithMetadata(String customFieldWithMetadata) {
        this.customFieldWithMetadata = customFieldWithMetadata;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}
