package com.example.huandog_v02;

public class User {

    public String userEmail;
    public String userPass;
    public String userName;
    public String userPhone;
    public String userAddr;

   public User(){

   }

   public User(String userEmail, String userPass, String userName, String userPhone, String userAddr ){
       this.userEmail = userEmail;
       this.userPass = userPass;
       this.userName = userName;
       this.userPhone = userPhone;
       this.userAddr = userAddr;

   }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmail='" + userEmail + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddr='" + userAddr + '\'' +
                '}';
    }
}
