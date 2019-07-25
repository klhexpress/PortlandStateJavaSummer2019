package edu.pdx.cs410J.nkhoi;
import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is represents a <code>TextDumper</code>.
 */
public class TextDumper implements AppointmentBookDumper {

    private String fileName;

    /**
     * Creates a new  <code>TextParser</code> object
     *
     * @param input The name of the file will be written to
     */
    public TextDumper(String input) {
        fileName = input;
    }


    /**
     * @param var the AppointmentBook which will be written to file
     * @throws IOException throw an exception in case it's fail to write the file
     */
    @Override
    public void dump(AbstractAppointmentBook var) throws IOException {
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
        }

        bufferedWriter.close();
    }
}


