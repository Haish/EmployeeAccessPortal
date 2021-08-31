package com.intuit.updateaccessrequest.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//defining class name as Table name
@Table
public class Application {

    @Id
    //defining id as column name
    @Column
    private int appId;
    //defining id as column name
    @Column
    private int digitalIdentityNumber;
    @Column
    private String appName;

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getDigitalIdentityNumber() {
        return digitalIdentityNumber;
    }

    public void setDigitalIdentityNumber(int digitalIdentityNumber) {
        this.digitalIdentityNumber = digitalIdentityNumber;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String toString() {
        return "Application{" +
                "appId=" + appId +
                ", digitalIdentityNumber=" + digitalIdentityNumber +
                ", appName='" + appName + '\'' +
                '}';
    }
}
