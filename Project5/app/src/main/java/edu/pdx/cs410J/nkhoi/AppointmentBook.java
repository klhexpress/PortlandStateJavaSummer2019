package edu.pdx.cs410j.nkhoi;

//import edu.pdx.cs410j.AbstractAppointment;
//import edu.pdx.cs410j.AbstractAppointmentBook;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * This class is represents an <code>AppointmentBook</code>.
 */
public class AppointmentBook extends AbstractAppointmentBook<Appointment> {
    private String owner;
    private ArrayList<edu.pdx.cs410j.nkhoi.Appointment> list;

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
    public AppointmentBook(String name, edu.pdx.cs410j.nkhoi.Appointment target) {
        owner = name;
        list = new ArrayList<edu.pdx.cs410j.nkhoi.Appointment>();
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
     * Compare the <code>durationBetween</code> 2 dates
     *
     * @param one The begin date
     * @param two the end date
     * @return the time different of 2 dates in minutes
     */
    private long durationBetween(Date one, Date two) {
        long difference = (one.getTime() - two.getTime()) / (1000 * 60);
        return Math.abs(difference);
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
     * This also sort the appointments in the AppointmentBook as they are added
     *
     * @param target The appointment added to the AppointmentBook
     */
    @Override
    public void addAppointment(edu.pdx.cs410j.nkhoi.Appointment target) {

        list = AddingAppointment(target);
        if (list.size() > 1)
            Collections.sort(list);                 //new code
    }

    /**
     * The function adding an appointment to the AppointmentBook
     *
     * @param target The appointment added to the AppointmentBook
     * @return the list of appointment in the AppointmentBook
     */
    private ArrayList<edu.pdx.cs410j.nkhoi.Appointment> AddingAppointment(edu.pdx.cs410j.nkhoi.Appointment target) {
        if (owner == null) {
            System.err.println("There is no name for the Appointment Book");
            System.exit(1);
        }
        if (list == null) {
            list = new ArrayList<edu.pdx.cs410j.nkhoi.Appointment>();
            list.add(target);
        } else {
            list.add(target);
        }
        return list;
    }

    private boolean isWithinRange(Date testDate, Date startDate, Date endDate) {
        return !(testDate.before(startDate) || testDate.after(endDate));
    }

    public String prettydisplay(int pos, Date startDate, Date endDate) throws IOException {
        if (list == null) {
            return "No appointment";
        }
        if (pos >= list.size())
            return "";//else {
        if (isWithinRange(list.get(pos).getBeginTime(), startDate, endDate) && isWithinRange(list.get(pos).getEndTime(), startDate, endDate))
            return ("Description: " + list.get(pos).getDescription() + "\nBegin Time: " + list.get(pos).getBeginTime()
                    + "\nEnd Time: " + list.get(pos).getEndTime() + "\n" + "Duration: " + durationBetween(list.get(pos).getBeginTime(), list.get(pos).getEndTime())
                    + " minutes" + "\n--------------------") + "\n" + prettydisplay(pos+1, startDate,endDate);
        else
            return prettydisplay(pos+1, startDate,endDate);
    }


    /**
     * Pretty display all the appointments in the AppointmentBook
     */
    public String prettydisplay(int pos) {
        if (list == null) {
            return "No appointment";
        }
        if(pos >= list.size())
            return "";//else {

        return ("Description: " + list.get(pos).getDescription() + "\nBegin Time: " + list.get(pos).getBeginTime()
                        + "\nEnd Time: " + list.get(pos).getEndTime() + "\n" + "Duration: " + durationBetween(list.get(pos).getBeginTime(), list.get(pos).getEndTime())
                        + " minutes" + "\n--------------------") + "\n" + prettydisplay(pos +1);

    }
}
