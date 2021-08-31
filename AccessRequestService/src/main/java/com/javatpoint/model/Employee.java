package com.javatpoint.model;



public class Employee {

    private long digitalIdentityNumber;
    private String name;
    private Role role;
    private String managerId;

    public long getDigitalIdentityNumber() {
        return digitalIdentityNumber;
    }

    public void setDigitalIdentityNumber(long digitalIdentityNumber) {
        this.digitalIdentityNumber = digitalIdentityNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
