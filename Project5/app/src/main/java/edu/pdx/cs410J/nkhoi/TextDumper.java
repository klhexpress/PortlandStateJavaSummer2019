package edu.pdx.cs410j.nkhoi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is represents a <code>TextDumper</code>.
 */
public class TextDumper  {

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
    public void dump(File temp2, AppointmentBook var) throws IOException {

        FileOutputStream outputStream = new FileOutputStream(temp2);
        FileWriter fileWriter = new FileWriter(outputStream.getFD());
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

    public void dumpnamefile(File temp,int total, String name[]) throws IOException{
        FileOutputStream outputStream = new FileOutputStream(temp);
        FileWriter fileWriter = new FileWriter(outputStream.getFD());
        //FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("Total: " + total);
        bufferedWriter.newLine();

        for(int i=0; i<total;i++){
            bufferedWriter.write(name[i]);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}


