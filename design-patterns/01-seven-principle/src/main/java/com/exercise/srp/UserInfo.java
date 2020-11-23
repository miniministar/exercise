package com.exercise.srp;

public class UserInfo {
    private String userName;
    private String address;

    //非单一原则
    private void modifyUserInfo(String userName, String address) {
        this.userName = userName;
        this.address = address;
    }

    //单一职责
    private void modifyUserName(String userName) {
        this.userName = userName;
    }
}
