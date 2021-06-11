package com.example.myapplication;

public class Signuphelperclass {
    String fullname,Emailid,DOB,Username,password;

    public Signuphelperclass(String fullname, String emailid, String DOB, String username, String password) {
        this.fullname = fullname;
        Emailid = emailid;
        this.DOB = DOB;
        Username = username;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmailid() {
        return Emailid;
    }

    public void setEmailid(String emailid) {
        Emailid = emailid;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
