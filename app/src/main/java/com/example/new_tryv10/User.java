package com.example.new_tryv10;

public class User {

    String uanme;
    String uaddress;
    String unumb;
    String usubj;

    public User() {
    }

    public User(String uanme, String unumb, String uaddress, String usubj) {
        this.uanme = uanme;
        this.unumb = unumb;
        this.uaddress = uaddress;
        this.usubj = usubj;
    }

    @Override
    public String toString() {
        return "User{" +
                "uanme='" + uanme + '\'' +
                ", uaddress='" + uaddress + '\'' +
                ", unumb='" + unumb + '\'' +
                ", usubj='" + usubj + '\'' +
                '}';
    }

    public String getUanme() {
        return uanme;
    }

    public void setUanme(String uanme) {
        this.uanme = uanme;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public String getUnumb() {
        return unumb;
    }

    public void setUnumb(String unumb) {
        this.unumb = unumb;
    }

    public String getUsubj() {
        return usubj;
    }

    public void setUsubj(String usubj) {
        this.usubj = usubj;
    }
}
