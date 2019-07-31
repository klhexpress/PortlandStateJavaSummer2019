package edu.pdx.cs410J.nkhoi;

import edu.pdx.cs410J.AbstractAppointment;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This class is represents an <code>Appointment</code>.
 */
public class Appointment extends AbstractAppointment implements Comparable <Appointment> {
  private String description;
  private String begintime;
  private String endingtime;
  private String begindate;
  private String endingdate;
  private String beginmMeridiem;
  private String endMeridiem;
  private Date begindateobject;
  private Date endingdateobject;

  /**
   * Creates an empty <code>Appointment</code>
   */
  public Appointment() {
  }

  /**
   * Creates a new <code>Appointment</code>
   *
   * @param description    The description of the appointment
   * @param begindate      The begindate of the appointment (mm/dd/yyyy)
   * @param begintime      The begintime of the appointment (12-hour time)
   * @param beginmMeridiem The beginMeridiem of the appointment (AM or PM)
   * @param endingdate     The endingdate of the appointment (mm/dd/yyyy)
   * @param endingtime     The endingtime of the appointment (12-hour time)
   * @param endMeridiem    The endMeridiem of the appointment (AM or PM)
   */
  public Appointment(String description, String begindate, String begintime, String beginmMeridiem, String endingdate, String endingtime, String endMeridiem) throws IllegalArgumentException,ParseException {
    this.description = description;
    this.begindate = begindate;
    this.begintime = begintime;
    this.endingdate = endingdate;
    this.endingtime = endingtime;
    this.beginmMeridiem = beginmMeridiem;
    this.endMeridiem = endMeridiem;

    Locale locale = Locale.getDefault();

      try {
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);
        begindateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(begindate + " " + begintime + " " + beginmMeridiem);
        endingdateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(endingdate + " " + endingtime + " " + endMeridiem);
        if (endingdateobject.compareTo(begindateobject) < 0) {
          throw new IllegalArgumentException();
        }
      } catch (ParseException pe) {
        throw new ParseException("MALFORMATTED DATE/TIME",0);
      }

  }

  /**
   * @return the begintime and the begindate of the appointment
   */
  @Override
  public String getBeginTimeString() {
    return begindate + " " + begintime + " " + beginmMeridiem;
  }

  /**
   * @return the endingdate and the endingtime of the appointment
   */
  @Override
  public String getEndTimeString() {
    return endingdate + " " + endingtime + " " + endMeridiem;
  }

  /**
   * @return the description of the appointment
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * @return the begindate date object of the appointment
   */
  @Override
  public Date getBeginTime() {
    return begindateobject;
  }

  /**
   * @return the enddate date object of the appointment
   */
  @Override
  public Date getEndTime() {
    return endingdateobject;
  }

  /**
   * Implement the <code>compareTo</code> method of the Comparable interface
   *
   * @param target The appointment which will be compared to
   * @return the different value of 2 appointments based on their begintime, endtime, or descriptions
   */
  @Override
  public int compareTo(Appointment target) {   // new code
    if (this.getBeginTime().compareTo(target.getBeginTime()) != 0)
      return this.getBeginTime().compareTo(target.getBeginTime());
    else if (this.getEndTime().compareTo(target.getEndTime()) != 0)
      return this.getEndTime().compareTo(target.getEndTime());
    else if (this.getDescription().toUpperCase().compareTo(target.getDescription().toUpperCase()) != 0)
      return this.getDescription().toUpperCase().compareTo(target.getDescription().toUpperCase());
    else
      return 0;
  }
}
