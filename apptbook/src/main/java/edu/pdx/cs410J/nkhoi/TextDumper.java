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
     * @param input
     *        The name of the file will be written to
     */
    public TextDumper(String input){
        fileName = input;
    }


    /**
     * @param var1 the AppointmentBook which will be written to file
     * @throws IOException in case it's fail to write the file
     */
    @Override
    public void dump(AbstractAppointmentBook var1) throws IOException {
        // Assume default encoding.
        FileWriter fileWriter = new FileWriter(fileName);

        // Always wrap FileWriter in BufferedWriter.
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Note that write() does not automatically
        // append a newline character.
        bufferedWriter.write("Owner: " + var1.getOwnerName());
        bufferedWriter.newLine();

        //Appointment []temp = new Appointment[100];
        //      var1.getAppointments().toArray(temp);
        Collection<Appointment> temp3 = var1.getAppointments();
        for (Appointment temp2 : temp3) {
            bufferedWriter.write("Description: " + temp2.getDescription());
            bufferedWriter.newLine();
            bufferedWriter.write("Begindate: " + temp2.getBeginTimeString());
            bufferedWriter.newLine();
            bufferedWriter.write("Endingdate: " + temp2.getEndTimeString());
            bufferedWriter.newLine();
        }

        // Always close files.
        bufferedWriter.close();

    }
}


