package edu.pdx.cs410j.nkhoi;

import java.io.Serializable;
import java.util.Collection;

public abstract class AbstractAppointmentBook<T extends AbstractAppointment> implements Serializable {
    public AbstractAppointmentBook() {
    }

    public abstract String getOwnerName();

    public abstract Collection<T> getAppointments();

    public abstract void addAppointment(T var1);

    public final String toString() {
        this.getOwnerName();
        return null;
        //return "haha";
    }
}

