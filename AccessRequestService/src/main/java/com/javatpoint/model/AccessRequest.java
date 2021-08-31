package com.javatpoint.model;
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
    private String digitalIdentityNumber;
    @Column
    private String name;
    @Column
    private String role;
    @Column
    private String managerId;
    @Column
    private String status;
    @Column
    private String errorMessage;

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

    public String getDigitalIdentityNumber() {
        return digitalIdentityNumber;
    }

    public void setDigitalIdentityNumber(String digitalIdentityNumber) {
        this.digitalIdentityNumber = digitalIdentityNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "AccessRequest{" +
                "requestId=" + requestId +
                ", request='" + request + '\'' +
                ", digitalIdentityNumber=" + digitalIdentityNumber +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", managerId='" + managerId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}