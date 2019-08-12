package edu.pdx.cs410j.nkhoi;


import java.io.Serializable;
import java.util.Date;

public abstract class AbstractAppointment implements Serializable {
    public AbstractAppointment() {
    }

    public abstract String getBeginTimeString();

    public abstract String getEndTimeString();

    public Date getBeginTime() {
        return null;
    }

    public Date getEndTime() {
        return null;
    }

    public abstract String getDescription();

    public final String toString() {
        return this.getDescription() + " from " + this.getBeginTimeString() + " to " + this.getEndTimeString();
    }
}