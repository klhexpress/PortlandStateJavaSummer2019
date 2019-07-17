package edu.pdx.cs410J.nkhoi;
import edu.pdx.cs410J.ParserException;
import java.io.*;

/**
 * The main class for the CS410J appointment book Project
 */


public class Project2 {

    static final String README = "KHOI NGUYEN - PROJECT 2 - This project will take owner name, description, begintime, and ending time to create an appointment. " +
                                    "The program will add that appointment to the Appointment Book with the [option] to print that appointment. Addtionally, with the " +
                                    "-textFile option, you can reads the contents of a text file and from it creates an appointment book with " +
                                    "its associated appointments then add the new appointment from command line and write it back to the file";

    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Missing command line");
            System.exit(1);
        }

        AppointmentBook infile = new AppointmentBook();

        String fileName = null;
        AppointmentBook temp = new AppointmentBook();
        Appointment FromCommandLine = new Appointment();

        boolean flag = false;
        for (int i = 0; i < args.length && !flag; i++) {
            if (args[i].equals("-textFile")) {
                fileName = args[i + 1];
            }
            if (!args[i].equals("-print") && !args[i].equals("-README") && !args[i].equals("-textFile") && !args[i].equals(fileName)) {
                if (args.length - i == 6) {
                    FromCommandLine = new Appointment(args[i + 1], args[i + 2], args[i + 3], args[i + 4], args[i + 5]);
                    temp = new AppointmentBook(args[i], FromCommandLine);
                    flag = true;
                } else {
                    System.err.println("Missing arguments OR Extraneous arguments: " + args.length + "-" + i);
                    System.exit(1);
                }
            }
        }

        boolean FileExist = true;
        for (int i = 0; !args[i].equals(FromCommandLine.getDescription()); i++) {
            if (args[i].equals("-README")) {
                System.err.println(README);
                System.exit(1);
            } else if (args[i].equals("-print")) {
                System.out.println(FromCommandLine.toString());
            } else if (args[i].equals("-textFile")) {           //new code
                try {
                    TextParser test2 = new TextParser(args[i + 1]);
                    infile = test2.parse();
                } catch (FileNotFoundException ex) {
                    System.err.println("CAN NOT OPEN FILE: " + fileName + ". CREATING NEW FILE");
                    FileExist = false;
                    infile = temp;
                    try {
                        TextDumper test = new TextDumper(fileName);
                        test.dump(infile);
                    } catch (IOException io1) {
                        System.err.println("Error writing to file ");
                    }
                } catch (ParserException p) {
                    System.err.println("FAIL TO PARSE");
                }
                if (FileExist)
                    if (temp.getOwnerName().equals(infile.getOwnerName())) {
                        try {
                            infile.addAppointment(FromCommandLine);
                            TextDumper test = new TextDumper(fileName);
                            test.dump(infile);
                        } catch (IOException io2) {
                            System.err.println("Error writing to file ");
                        }
                    } else {
                        System.err.println("Name from command line does not match in file");
                        System.exit(1);
                    }
            }
        }

        infile.display();

        System.exit(0);
    }
}
