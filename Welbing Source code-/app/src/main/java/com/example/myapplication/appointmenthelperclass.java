package com.example.myapplication;

public class appointmenthelperclass {
    String appointyname, appointytime, appointydate, appointmentmode;

    public appointmenthelperclass(String appointyname, String appointytime, String appointydate, String appointmentmode) {
        this.appointyname = appointyname;
        this.appointytime = appointytime;
        this.appointydate = appointydate;
        this.appointmentmode = appointmentmode;
    }

    public String getAppointyname() {
        return appointyname;
    }

    public void setAppointyname(String appointyname) {
        this.appointyname = appointyname;
    }

    public String getAppointytime() {
        return appointytime;
    }

    public void setAppointytime(String appointytime) {
        this.appointytime = appointytime;
    }

    public String getAppointydate() {
        return appointydate;
    }

    public void setAppointydate(String appointydate) {
        this.appointydate = appointydate;
    }

    public String getAppointmentmode() {
        return appointmentmode;
    }

    public void setAppointmentmode(String appointmentmode) {
        this.appointmentmode = appointmentmode;
    }
}
