package com.example.huandog_v02;

import java.util.HashMap;
import java.util.Map;

public class User {

    public String userEmail;
    public String userPass;
    public String userName;
    public String userAddr;

    public Map<String, Object> info = new HashMap<>();

    public Map<String, Object> getInfo() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userEmail",userEmail);
        result.put("userPass", userPass);
        result.put("userName",userName);
        result.put("userAddr",userAddr);
        return info;
    }

    public User(){

   }

   public User(String userEmail,String userName, String userAddr ){
       this.userEmail = userEmail;
       this.userName = userName;
       this.userAddr = userAddr;
   }
   public User(String userEmail, String userPass, String userName, String userAddr ){
       this.userEmail = userEmail;
       this.userPass = userPass;
       this.userName = userName;
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
                ", userAddr='" + userAddr + '\'' +
                '}';
    }
}
