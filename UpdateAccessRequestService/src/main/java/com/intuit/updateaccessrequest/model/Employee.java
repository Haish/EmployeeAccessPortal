package com.intuit.updateaccessrequest.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//defining class name as Table name
@Table
public class Employee {

    @Id
    //defining id as column name
    @Column
    private int digitalIdentityNumber;
    @Column
    private String name;
    @Column
    private String role;
    @Column
    private int managerId;

    public int getDigitalIdentityNumber() {
        return digitalIdentityNumber;
    }

    public void setDigitalIdentityNumber(int digitalIdentityNumber) {
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

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "digitalIdentityNumber=" + digitalIdentityNumber +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", managerId='" + managerId + '\'' +
                '}';
    }
}
