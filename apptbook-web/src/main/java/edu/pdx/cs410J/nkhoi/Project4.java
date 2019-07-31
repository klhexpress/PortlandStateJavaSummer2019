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

    private static String[] splitString(String input) {
        String[] words = input.split(" ");
        return words;
    }

    public static void main(String... args) {
        String hostName = null;
        String portString = null;
        String owner = null;
        String description = null;
        String beginDate = null;
        String beginTime = null;
        String beginMeridiem = null;
        String endDate = null;
        String endTime = null;
        String endMeridiem = null;


        for (String arg : args) {
            if (hostName == null) {
                hostName = arg;

            } else if (portString == null) {
                portString = arg;

            } else if (owner == null) {
                owner = arg;

            } else if (description == null) {
                description = arg;
            } else if (beginDate == null) {
                beginDate = arg;
            } else if (beginTime == null) {
                beginTime = arg;
            } else if (beginMeridiem == null) {
                beginMeridiem = arg;
            } else if (endDate == null) {
                endDate = arg;
            } else if (endTime == null) {
                endTime = arg;
            } else if (endMeridiem == null) {
                endMeridiem = arg;
            } else {
                usage("Extraneous command line argument: " + arg);
            }
        }

        if (hostName == null) {
            usage( MISSING_ARGS );

        } else if ( portString == null) {
            usage( "Missing port" );
        }

        int port;
        try {
            port = Integer.parseInt( portString );

        } catch (NumberFormatException ex) {
            usage("Port \"" + portString + "\" must be an integer");
            return;
        }


        AppointmentBookRestClient client = new AppointmentBookRestClient(hostName, port);

        String message;
        try {
            if (owner == null) {
                // Print all owner/description pairs
                System.err.println("MISSING ARGUMENT");

            } else if (description == null) {
                // Print all dictionary entries
                //message = Messages.formatDictionaryEntry(owner, client.getDefinition(owner));

            } else {
                // Create new appointmentBook
                message = client.addAppointment(owner,description,beginDate + " " + beginTime + " " + beginMeridiem,endDate + " " + endTime + " " + endMeridiem);
                System.out.println(message);
                //System.exit(1);
            }

        } catch ( IOException ex ) {
            error("While contacting server: " + ex);
            return;
        }

        try {
            //System.out.println("in here");
            message=client.searchAppointment(owner,"1/1/2019 01:00 am", "4/4/2019 10:00 pm");
            System.out.println(message);
        }catch ( IOException ex ) {
                error("While contacting server: " + ex);
                return;
            }

        System.exit(0);


        //AppointmentBookRestClient client = new AppointmentBookRestClient(hostName, port);
        /*String message;

        //AppointmentBookServlet.listofAppointmentBook.
        Date begindateobject, endingdateobject;
        String hostnameString="";
        String portString = "";
        String owner = null;
        String description = null;
        String beginDate = null;
        String beginTime = null;
        String beginMeridiem = null;
        String endDate = null;
        String endTime = null;
        String endMeridiem = null;

        boolean flag = false;
        boolean needSearch = false;
        boolean isPrettyOpt = false;

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
                isPrettyOpt = true;
            }
            if (!args[i].equals("-print") && !args[i].equals("-README") && !args[i].equals("-host") && !args[i].equals(hostnameString) && !args[i].equals("-port") && !args.equals(portString) && !args[i].equals("-search")) {
                if (needSearch == false && args.length - i == 8) {
                    owner = args[i];
                    description = args[i + 1];
                    beginDate = args[i + 2];
                    beginTime = args[i + 3];
                    beginMeridiem = args[i + 4];
                    endDate = args[i + 5];
                    endTime = args[i + 6];
                    endMeridiem = args[i + 7];
                    //message = client.addAppointment(owner, description, beginDate + " " + beginTime + " " + beginMeridiem, endDate + " " + endTime + " " + endMeridiem);
                    flag = true;

                } else {
                    System.err.println("Missing arguments OR Extraneous arguments");
                    System.exit(1);
                }
            }
        }

        int port;
        try {
            port = Integer.parseInt( portString );
            AppointmentBookRestClient client = new AppointmentBookRestClient(hostnameString, port);
            message = client.addAppointment(owner,description,beginDate + " " + beginTime + " " + beginMeridiem,endDate + " " + endTime + " " + endMeridiem);
        } catch (NumberFormatException ex) {
            System.err.println("Port \"" + portString + "\" must be an integer");
            System.exit(1);
        }catch (IOException ex) {
            System.err.println("A connection to the server cannot be established: " + ex);
            System.exit(1);
        }










        if(needSearch == true && args.length - i != 7){
            System.err.println("Missing arguments OR Extraneous arguments");
            System.exit(1);
        }else if(needSearch && args.length - i == 7 ){
            //DO SEARCH FOR APPOINTMENT BETWEEN TIMES IN HERE
            try {
                begindateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(args[i+1] + " " + args[i+2] + " " + args[i+3]);
                endingdateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(args[i+4] + " " + args[i+5] + " " + args[i+6]);
                if (endingdateobject.compareTo(begindateobject) < 0) {
                    System.err.println("ENDDATE CAN NOT BE EARLIER THAN BEGIN DATE");
                    System.exit(1);
                }
                AppointmentBookServlet.getAppointmentBook(args[i]).prettydisplay(begindateobject, endingdateobject);
            } catch (ParseException pe) {
                System.err.println("MALFORMATTED DATE/TIME");
                System.exit(1);
            } catch (IOException io) {
                System.err.println("CAN NOT PRINT " + args[i] + "'S APPOINTMENT BOOK ");
                System.exit(1);
            }
            //NEED HANDLE PRINT CASE. SHOULD USE A SEPERATE LOOP LIKE PROJECT 3??
        }else

        /*
        //NOT DONE FROM HERE
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
                    FileExist = 1;
                } catch (FileNotFoundException ex) {
                    System.err.println("CAN NOT OPEN FILE: " + fileName + ". CREATING NEW FILE");
                    FileExist = 0;
                    infile = temp;
                    try {
                        TextDumper test = new TextDumper(fileName);
                        test.dump(infile);
                    } catch (IOException io1) {
                        System.err.println("Error writing to file ");
                    }
                } catch (ParserException p) {
                    System.err.println("CAN NOT PARSE STRING");
                }
                if (FileExist == 1)
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
            } else if (args[i].equals("-pretty")) {
                isPrettyOpt = true;
            }
        }
        */

        System.exit(0);
    }





    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);

        System.exit(1);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java Project4 host port [owner] [description]");
        err.println("  host         Host of web server");
        err.println("  port         Port of web server");
        err.println("  owner         Word in dictionary");
        err.println("  description   Definition of owner");
        err.println();
        err.println("This simple program posts words and their definitions");
        err.println("to the server.");
        err.println("If no description is specified, then the owner's description");
        err.println("is printed.");
        err.println("If no owner is specified, all dictionary entries are printed");
        err.println();

        System.exit(1);
    }
}



/*for (int i = 0; i < args.length && !flag; i++) {
            if(args[i].equals("-host")){
                hostnameString=args[i+1];
            }
            if(args[i].equals("-port")){
                portString = args[i+1];
            }
            if(args[i].equals("-search")){
                needSearch = true;
            }
            if(args[i].equals("-print")){
                isPrettyOpt = true;
            }
            if (!args[i].equals("-print") && !args[i].equals("-README") && !args[i].equals("-host") && !args[i].equals(hostnameString) && !args[i].equals("-port") && !args.equals(portString) && !args[i].equals("-search") ) {
                if(needSearch == true && args.length - i != 7){
                    System.err.println("Missing arguments OR Extraneous arguments");
                    System.exit(1);
                }else if(needSearch && args.length - i == 7 ){
                    //DO SEARCH FOR APPOINTMENT BETWEEN TIMES IN HERE
                    try {
                        begindateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(args[i+1] + " " + args[i+2] + " " + args[i+3]);
                        endingdateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(args[i+4] + " " + args[i+5] + " " + args[i+6]);
                        if (endingdateobject.compareTo(begindateobject) < 0) {
                            System.err.println("ENDDATE CAN NOT BE EARLIER THAN BEGIN DATE");
                            System.exit(1);
                        }
                        AppointmentBookServlet.getAppointmentBook(args[i]).prettydisplay(begindateobject, endingdateobject);
                    } catch (ParseException pe) {
                        System.err.println("MALFORMATTED DATE/TIME");
                        System.exit(1);
                    } catch (IOException io) {
                    System.err.println("CAN NOT PRINT " + args[i] + "'S APPOINTMENT BOOK ");
                        System.exit(1);
                    }
                    //NEED HANDLE PRINT CASE. SHOULD USE A SEPERATE LOOP LIKE PROJECT 3??
                }else if (needSearch == false && args.length - i == 8) {
                    try {
                        owner = args[i];
                        description = args[i + 1];
                        beginDate = args[i + 2];
                        beginTime = args[i + 3];
                        beginMeridiem = args[i + 4];
                        endDate = args[i + 5];
                        endTime = args[i + 6];
                        endMeridiem = args[i + 7];
                        message = client.addAppointment(owner, description, beginDate + " " + beginTime + " " + beginMeridiem, endDate + " " + endTime + " " + endMeridiem);
                        flag = true;
                    }catch ( IOException ex ) {
                        error("A connection to the server cannot be established: " + ex);
                        System.exit(1);
                    }
                } else {
                    System.err.println("Missing arguments OR Extraneous arguments");
                    System.exit(1);
                }
            }
        }
 */