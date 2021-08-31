package com.intuit.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table
public class AccessRequest
{


    @Id
    //defining id as column name
    @Column
    private int requestId;
    @Column
    private String request;
    @Column
    private int digitalIdentityNumber;
    @Column
    private String status;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getDigitalIdentityNumber() {
        return digitalIdentityNumber;
    }

    public void setDigitalIdentityNumber(int digitalIdentityNumber) {
        this.digitalIdentityNumber = digitalIdentityNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AccessRequest{" +
                "requestId=" + requestId +
                ", request='" + request + '\'' +
                ", digitalIdentityNumber='" + digitalIdentityNumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}