package edu.pdx.cs410J.nkhoi;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;
import java.util.Collection;
import java.util.ArrayList;

/**
 * This class is represents an <code>AppointmentBook</code>.
 */
public class AppointmentBook extends AbstractAppointmentBook {
    private String owner;
    private ArrayList<AbstractAppointment> list;

    /**
     * Creates an empty <code>AppointmentBook</code>
     */
    public AppointmentBook() {
        owner = null;
        list = null;
    }

    /**
     * Creates a new <code>AppointmentBook</code>
     *
     * @param name   The name of the AppointmentBook's owner
     * @param target The appointment which you want to add to the AppointmentBook
     */
    public AppointmentBook(String name, Appointment target) {
        owner = name;
        list = new ArrayList<AbstractAppointment>();
        list.add(target);
    }

    /**
     * Creates a new <code>AppointmentBook</code> with owner name only
     *
     * @param name The name of the AppointmentBook's owner
     */
    public AppointmentBook(String name) {
        owner = name;
        list = null;
    }


    /**
     * @return the name of the AppointmentBook's owner
     */
    @Override
    public String getOwnerName() {
        return owner;
    }

    /**
     * @return the appointments in the AppointmentBook
     */
    @Override
    public Collection getAppointments() {
        return list;
    }

    /**
     * Wrapper function help to add an appointment to the AppointmentBook
     *
     * @param target The appointment added to the AppointmentBook
     */
    @Override
    public void addAppointment(AbstractAppointment target) {
        list = AddingAppointment(target);
    }

    /**
     * The function adding an appointment to the AppointmentBook
     *
     * @param target The appointment added to the AppointmentBook
     * @return the list of appointment in the AppointmentBook
     */
    private ArrayList<AbstractAppointment> AddingAppointment(AbstractAppointment target) {
        if (owner == null) {
            System.err.println("There is no name for the Appointment Book");
            System.exit(1);
        }
        if (list == null) {
            list = new ArrayList<AbstractAppointment>();
            list.add(target);
        } else {
            list.add(target);
        }
        return list;
    }

    /**
     * Display all the appointments in the AppointmentBook
     */
    public void display() {
        if (list == null) {
            throw new NullPointerException();
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        }
    }
}
