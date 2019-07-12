package edu.pdx.cs410J.nkhoi;

/**
 * The main class for the CS410J appointment book Project
 */

public class Project1 {
  static final String README = "KHOI NGUYEN - PROJECT 1 - This project will take owner name, description, begintime, and ending time to create an appointment. Then the program will add that appointment to the Appointment Book with the [option] to print that appointment";

  public static void main(String[] args) {

    if (args.length == 0) {
      System.err.println("Missing command line arguments");
      System.exit(1);
    }
    if (args[0].equals("-README")) {
      System.err.println(README);
      System.exit(1);
    }

    AppointmentBook list = new AppointmentBook();
    Appointment appointment = new Appointment();  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    boolean flag = false;
    for (int i = 0; i < args.length && flag == false; i++) {
      if (!args[i].equals("-print") && !args[i].equals("-README")) {
        if (args.length - i == 6) {
          appointment = new Appointment(args[i + 1], args[i + 2], args[i + 3], args[i + 4], args[i + 5]);
          list = new AppointmentBook(args[i], appointment);
          flag = true;
        } else {
          System.err.println("Missing arguments OR Extraneous arguments");
          System.exit(1);
        }
      }
    }

    for (int i = 0; !args[i].equals(appointment.getDescription()); i++) {
      if (args[i].equals("-README")) {
        System.err.println(README);
        System.exit(1);
      } else if (args[i].equals("-print")) {
        System.out.println(appointment.toString());
      }
    }
  }
}