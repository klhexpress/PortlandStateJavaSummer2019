package edu.pdx.cs410J.nkhoi;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * The main class that parses the command line and communicates with the
 * Appointment Book server using REST.
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";
    static final String README = "KHOI NGUYEN - PROJECT 4 - This project will let user to add, retrieve appointments throw " +
            "web browser. However, user can also interact with server through the command line. Depend on which options are" +
            " provided, the programs will require the number of arguments among owner name, description, begintime, and " +
            "ending time. The program will add an appointment to the Appointment Book stored on server. Search the " +
            "Appointments for the owner between begintime and endtime. Additionally, you can also print all the appointments " +
            "for the owner or print the most recently add appointment";

    public static void main(String... args) {

        if(args.length == 0){
            System.err.println("MISSING ARGUMENT");
            System.exit(1);
        }
        String message;
        Date begindateobject, endingdateobject;
        String hostnameString = null;
        String portString = null;
        String owner = null;
        String description = null;
        String beginDate = null;
        String beginTime = null;
        String beginMeridiem = null;
        String endDate = null;
        String endTime = null;
        String endMeridiem = null;

        AppointmentBookRestClient client = null;
        int port;
        boolean flag = false;
        boolean needSearch = false;
        boolean isPrintOpt = false;
        boolean isREADMEOpt = false;

        for (int i = 0; i < args.length && !flag; i++) {
            if (args[i].equals("-host")) {
                hostnameString = args[i + 1];
            }
            if (args[i].equals("-port")) {
                portString = args[i + 1];
            }
            if (args[i].equals("-search")) {
                needSearch = true;
            }
            if (args[i].equals("-print")) {
                isPrintOpt = true;
            }
            if (args[i].equals("-README")) {
                isREADMEOpt = true;
            }
            if (!args[i].equals("-print") && !args[i].equals("-README") && !args[i].equals("-host") && !args[i].equals(hostnameString)
                    && !args[i].equals("-port") && !args[i].equals(portString) && !args[i].equals("-search")) {
                if (hostnameString == null || portString == null) {
                    System.err.println("HOST WITHOUT A PORT IS ERROR AND VICE VERSA.");
                    System.exit(1);
                } else {
                    try {
                        port = Integer.parseInt(portString);
                        client = new AppointmentBookRestClient(hostnameString, port);
                    } catch (NumberFormatException ex) {
                        System.err.println("Port \"" + portString + "\" must be an integer");
                        System.exit(1);
                    }
                }
                if (needSearch == false && args.length - i == 1) {
                    try {
                        message = client.searchAppointment(args[i]);
                        System.out.println(message);
                    } catch (IOException ex) {
                        System.err.println("CAN NOT PRINT " + args[i] + "'S APPOINTMENT BOOK ");
                        System.exit(1);
                    }
                    flag = true;
                } else if (needSearch == true && args.length - i == 7) {
                    //SEARCH FOR APPOINTMENT BETWEEN TIMES IN HERE
                    try {
                        begindateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(args[i + 1] + " " + args[i + 2] + " " + args[i + 3]);
                        endingdateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(args[i + 4] + " " + args[i + 5] + " " + args[i + 6]);
                        if (endingdateobject.compareTo(begindateobject) < 0) {
                            System.err.println("ENDDATE CAN NOT BE EARLIER THAN BEGIN DATE");
                            System.exit(1);
                        }
                        message = client.searchAppointment(args[i], args[i + 1] + " " + args[i + 2] + " " + args[i + 3], args[i + 4] + " " + args[i + 5] + " " + args[i + 6]);
                        System.out.println(message);
                    } catch (ParseException pe) {
                        System.err.println("MALFORMATTED DATE/TIME");
                        System.exit(1);
                    } catch (IOException io) {
                        System.err.println("CAN NOT PRINT " + args[i] + "'S APPOINTMENT BOOK ");
                        System.exit(1);
                    }
                    flag = true;
                } else if (needSearch == false && args.length - i == 8) {
                    owner = args[i];
                    description = args[i + 1];
                    beginDate = args[i + 2];
                    beginTime = args[i + 3];
                    beginMeridiem = args[i + 4];
                    endDate = args[i + 5];
                    endTime = args[i + 6];
                    endMeridiem = args[i + 7];
                    try {
                        message = client.addAppointment(owner, description, beginDate + " " + beginTime + " " + beginMeridiem, endDate + " " + endTime + " " + endMeridiem);
                        if (isPrintOpt == true)
                            System.out.println(message);
                    } catch (IOException ex) {
                        System.err.println("While contacting server: " + ex);
                        System.exit(1);
                    }
                    flag = true;

                } else {
                    System.err.println("Missing arguments OR Extraneous arguments");
                    System.exit(1);
                }
            }
        }

        if (isREADMEOpt == true) {
            System.out.println(README);
            System.exit(0);
        }
        System.exit(0);
    }

}


