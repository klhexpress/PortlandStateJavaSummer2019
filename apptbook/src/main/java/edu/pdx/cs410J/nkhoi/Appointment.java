package edu.pdx.cs410J.nkhoi;

import edu.pdx.cs410J.AbstractAppointment;

/**
 * This class is represents an <code>Appointment</code>.
 */
public class Appointment extends AbstractAppointment {
  private String description;
  private String begintime;
  private String endingtime;
  private String begindate;
  private String endingdate;

  /**
   * Creates a new <code>splittingString</code>
   *
   * @param input The input string which will be split
   * @throws NumberFormatException Throw an exception when the input date/time is malformatted
   */
  private int[] splittingString(String input) throws NumberFormatException {
    String[] words = input.split("/|\\:");
    if (words.length > 3) {
      System.err.println("Date/Time is malformatted");
      System.exit(1);
    }
    int[] number = new int[3];
    int i = 0;
    for (String w : words)
      number[i] = Integer.parseInt(words[i++]);
    return number;
  }

  /**
   * Creates an empty <code>Appointment</code>
   */
  public Appointment() {
  }

  /**
   * Creates a new <code>Appointment</code>
   *
   * @param description The description of the appointment
   * @param begindate   The begindate of the appointment (mm/dd/yyyy)
   * @param begintime   The begintime of the appointment (24-hour time)
   * @param endingdate  The endingdate of the appointment (mm/dd/yyyy)
   * @param endingtime  The endingtime of the appointment (24-hour time)
   */
  public Appointment(String description, String begindate, String begintime, String endingdate, String endingtime) {
    this.description = description;
    this.begindate = begindate;
    this.begintime = begintime;
    this.endingdate = endingdate;
    this.endingtime = endingtime;

    try {
      int[] startdates = splittingString(this.begindate);
      int[] enddates = splittingString(this.endingdate);

      if (startdates[0] > 12 || startdates[0] < 0 || startdates[1] < 0 || startdates[1] > 31) {
        System.err.println("ERROR START DATE");
        System.exit(1);
      }
      if (enddates[0] > 12 || enddates[0] < 0 || enddates[1] < 0 || enddates[1] > 31) {
        System.err.println("ERROR END DATE");
        System.exit(1);
      }

      int[] starttimes = splittingString(begintime);
      int[] endtimes = splittingString(endingtime);

      if (starttimes[0] > 23 || starttimes[0] < 0 || starttimes[1] < 0 || starttimes[1] > 59) {
        System.err.println("ERROR START TIME");
        System.exit(1);
      }
      if (endtimes[0] > 23 || endtimes[0] < 0 || endtimes[1] < 0 || endtimes[1] > 59) {
        System.err.println("ERROR END TIME");
        System.exit(1);
      }
    } catch (NumberFormatException ex) {
      System.err.println("Date/Time is malformatted");
      System.exit(1);
    }
  }

  /**
   * @return the begintime and the begindate of the appointment
   */
  @Override
  public String getBeginTimeString() {
    return begindate + " " + begintime;
  }

  /**
   * @return the endingdate and the endingtime of the appointment
   */
  @Override
  public String getEndTimeString() {
    return endingdate + " " + endingtime;
  }

  /**
   * @return the description of the appointment
   */
  @Override
  public String getDescription() {
    return description;
  }
}
