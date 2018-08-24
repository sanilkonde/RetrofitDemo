package com.example.sanil.retrofitexample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sanil on 31-08-2017.
 */

public class ModelArrayPOST {
    @SerializedName("Contex")
    @Expose
    private Contex contex;
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("RoleName")
    @Expose
    private String roleName;
    @SerializedName("UserID")
    @Expose
    private String userID;
    @SerializedName("RMName")
    @Expose
    private String rMName;
    @SerializedName("RandomString")
    @Expose
    private String randomString;

    public Contex getContex() {
        return contex;
    }

    public void setContex(Contex contex) {
        this.contex = contex;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRMName() {
        return rMName;
    }

    public void setRMName(String rMName) {
        this.rMName = rMName;
    }

    public String getRandomString() {
        return randomString;
    }

    public void setRandomString(String randomString) {
        this.randomString = randomString;
    }

    public class Contex {

        @SerializedName("ClientTimeOffSet")
        @Expose
        private Double clientTimeOffSet;
        @SerializedName("ExpiresOn")
        @Expose
        private String expiresOn;
        @SerializedName("IsUTC")
        @Expose
        private Boolean isUTC;
        @SerializedName("LongDateFormat")
        @Expose
        private Object longDateFormat;
        @SerializedName("LongDateTimeFormat")
        @Expose
        private Object longDateTimeFormat;
        @SerializedName("Token")
        @Expose
        private Object token;
        @SerializedName("IsSuccess")
        @Expose
        private Boolean isSuccess;
        @SerializedName("Message")
        @Expose
        private String message;

        public Double getClientTimeOffSet() {
            return clientTimeOffSet;
        }

        public void setClientTimeOffSet(Double clientTimeOffSet) {
            this.clientTimeOffSet = clientTimeOffSet;
        }

        public String getExpiresOn() {
            return expiresOn;
        }

        public void setExpiresOn(String expiresOn) {
            this.expiresOn = expiresOn;
        }

        public Boolean getIsUTC() {
            return isUTC;
        }

        public void setIsUTC(Boolean isUTC) {
            this.isUTC = isUTC;
        }

        public Object getLongDateFormat() {
            return longDateFormat;
        }

        public void setLongDateFormat(Object longDateFormat) {
            this.longDateFormat = longDateFormat;
        }

        public Object getLongDateTimeFormat() {
            return longDateTimeFormat;
        }

        public void setLongDateTimeFormat(Object longDateTimeFormat) {
            this.longDateTimeFormat = longDateTimeFormat;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public Boolean getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(Boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

}
