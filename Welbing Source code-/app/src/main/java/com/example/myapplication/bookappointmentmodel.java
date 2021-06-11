package com.example.myapplication;

public class bookappointmentmodel {
    String drname,drspeciality,dravailability;
    private int onclick;

    public bookappointmentmodel(String drname, String drspeciality, String dravailability) {
        this.drname = drname;
        this.drspeciality = drspeciality;
        this.dravailability = dravailability;

    }

    public String getDrname() {
        return drname;
    }

    public void setDrname(String drname) {
        this.drname = drname;
    }

    public String getDrspeciality() {
        return drspeciality;
    }

    public void setDrspeciality(String drspeciality) {
        this.drspeciality = drspeciality;
    }

    public String getDravailability() {
        return dravailability;
    }

    public void setDravailability(String dravailability) {
        this.dravailability = dravailability;
    }
}
