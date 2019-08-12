package edu.pdx.cs410j.nkhoi;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

/**
 * This class is represents a <code>PrettyPrinter</code>.
 */
public class PrettyPrinter {
    private String fileName;

    /**
     * Creates a new  <code>PrettyPrinter</code> object
     *
     * @param input The name of the file will be written to
     */
    public PrettyPrinter(String input) {
        fileName = input;
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
     * @param var the AppointmentBook which will be written to file
     * @throws IOException throw an exception in case it's fail to write the file
     */

    public void dump(edu.pdx.cs410j.nkhoi.AppointmentBook var) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("Owner: " + var.getOwnerName());
        bufferedWriter.newLine();

        Collection<edu.pdx.cs410j.nkhoi.Appointment> temp = var.getAppointments();
        for (edu.pdx.cs410j.nkhoi.Appointment i : temp) {
            bufferedWriter.write("Description: " + i.getDescription());
            bufferedWriter.newLine();
            bufferedWriter.write("Begindate: " + i.getBeginTime());
            bufferedWriter.newLine();
            bufferedWriter.write("Endingdate: " + i.getEndTime());
            bufferedWriter.newLine();
            bufferedWriter.write("Appointment duration: " + durationBetween(i.getBeginTime(), i.getEndTime()) + " minutes");
            bufferedWriter.newLine();
            bufferedWriter.write("--------------------");
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
