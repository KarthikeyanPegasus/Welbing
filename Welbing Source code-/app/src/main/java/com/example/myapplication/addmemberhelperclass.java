package com.example.myapplication;

public class addmemberhelperclass {
    String fullname, age, Height, weight, Sexuality,previousAllergies,icon;

    public addmemberhelperclass(String fullname, String age, String Height, String weight, String Sexuality, String previousAllergies,String icon) {
        this.fullname = fullname;
        this.age = age;
        this.Height = Height;
        this.weight = weight;
        this.Sexuality = Sexuality;
        this.previousAllergies = previousAllergies;
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSexuality() {
        return Sexuality;
    }

    public void setSexuality(String sexuality) {
        Sexuality = sexuality;
    }

    public String getPreviousAllergies() {
        return previousAllergies;
    }

    public void setPreviousAllergies(String previousAllergies) {
        this.previousAllergies = previousAllergies;
    }
}
