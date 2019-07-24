package edu.pdx.cs410J.nkhoi;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

public class PrettyPrinter implements AppointmentBookDumper<AppointmentBook> {
    private String fileName;

    public PrettyPrinter(String input) {
        fileName = input;
    }

    private long durationBetween(Date one, Date two) {
        long difference = (one.getTime() - two.getTime()) / (1000 * 60);
        return Math.abs(difference);
    }

    /**
     * @param var the AppointmentBook which will be written to file
     * @throws IOException throw an exception in case it's fail to write the file
     */
    @Override
    public void dump(AppointmentBook var) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("Owner: " + var.getOwnerName());
        bufferedWriter.newLine();

        Collection<Appointment> temp = var.getAppointments();
        for (Appointment i : temp) {
            bufferedWriter.write("Description: " + i.getDescription());
            bufferedWriter.newLine();
            bufferedWriter.write("Begindate: " + i.getBeginTimeString());
            bufferedWriter.newLine();
            bufferedWriter.write("Endingdate: " + i.getEndTimeString());
            bufferedWriter.newLine();
            bufferedWriter.write("Appointment duration: " + durationBetween(i.getBeginTime(), i.getEndTime()) + " minutes");
            bufferedWriter.newLine();
            bufferedWriter.write("--------------------");
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
