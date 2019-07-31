package edu.pdx.cs410J.nkhoi;

import com.google.common.annotations.VisibleForTesting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>AppointmentBook</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple key/value pairs.
 */
public class AppointmentBookServlet extends HttpServlet
{
    static final String OWNER_PARAMETER = "owner";
    static final String DESCRIPTION_PARAMETER = "description";
    static final String BEGINTIME_PARAMETER = "beginTime";
    static final String ENDTIME_PARAMETER = "endTime";

    static final Map<String, AppointmentBook> listofAppointmentBook = new HashMap<>();

    /**
     * Handles an HTTP GET request from a client by writing the definition of the
     * word specified in the "word" HTTP parameter to the HTTP response.  If the
     * "word" parameter is not specified, all of the entries in the dictionary
     * are written to the HTTP response.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType("text/plain");
        String name = getParameter(OWNER_PARAMETER, request);
        String beginTime = getParameter(BEGINTIME_PARAMETER, request);
        String endTime = getParameter(ENDTIME_PARAMETER, request);
        Date begindateobject = null;
        Date endingdateobject = null;

        /*if(beginTime!=null && endTime!=null) {
            try {
                begindateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(beginTime);
                endingdateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(endTime);
                if (endingdateobject.compareTo(begindateobject) < 0) {
                    response.getWriter().println("ENDDATE CAN NOT BE EARLIER THAN BEGIN DATE");
                    return;
                }
            } catch (ParseException pe) {
                response.getWriter().println("MALFORMATTED DATE/TIME");
                return;
            }

            try {
                if (name != null) {
                    //response.getWriter().println(beginTime);
                    response.getWriter().println(name + "'s appointment book");
                    getAppointmentBook(name).prettydisplay(response.getWriter(), begindateobject, endingdateobject);
                } else {
                    response.getWriter().println("PARAMETER FOR OWNER NAME IS REQUIRED TO PRINT THE APPOINTMENT BOOK");
                }
            } catch (IOException io) {
                response.getWriter().println("CAN NOT PRINT " + name + "'S APPOINTMENT BOOK ");
                return;
            } catch (Exception e) {
                response.getWriter().println("THERE IS NO APPOINTMENT BOOK OWNER: " + name);
                return;
            }
        }
        else{
            try {
                if (name != null) {
                    //response.getWriter().println(beginTime);
                    response.getWriter().println(name + "'s appointment book");
                    getAppointmentBook(name).prettydisplay(response.getWriter());
                } else {
                    response.getWriter().println("PARAMETER FOR OWNER NAME IS REQUIRED");
                }
            } catch (IOException io) {
                response.getWriter().println("CAN NOT PRINT " + name + "'S APPOINTMENT BOOK ");
                return;
            } catch (Exception e) {
                response.getWriter().println("There is no Appointment Book for owner: " + name);
                return;
            }
        }*/

        if(beginTime!=null && endTime!=null) {
            try {
                begindateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(beginTime);
                endingdateobject = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(endTime);
                if (endingdateobject.compareTo(begindateobject) < 0) {
                    response.getWriter().println("ENDDATE CAN NOT BE EARLIER THAN BEGIN DATE");
                    return;
                }
            } catch (ParseException pe) {
                response.getWriter().println("MALFORMATTED DATE/TIME");
                return;
            }
        }

        try {
            if (name != null) {
                //response.getWriter().println(beginTime);
                response.getWriter().println(name + "'s appointment book");
                if(begindateobject!=null && endingdateobject!=null)
                    getAppointmentBook(name).prettydisplay(response.getWriter(), begindateobject, endingdateobject);
                else
                    getAppointmentBook(name).prettydisplay(response.getWriter());
            } else {
                response.getWriter().println("PARAMETER FOR OWNER NAME IS REQUIRED TO PRINT THE APPOINTMENT BOOK");
            }
        } catch (IOException io) {
            response.getWriter().println("CAN NOT PRINT " + name + "'S APPOINTMENT BOOK ");
            return;
        } catch (Exception e) {
            response.getWriter().println("THERE IS NO APPOINTMENT BOOK OWNER: " + name);
            return;
        }


    }

    private String[] splitString(String input) {
        String[] words = input.split(" ");
        return words;
    }

    /**
     * Handles an HTTP POST request by storing the dictionary entry for the
     * "word" and "definition" request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType("text/plain");

        /*AppointmentBook book = new AppointmentBook("Harry");
        this.listofAppointmentBook.put("Harry", book);

        String begintest1 = "1/1/2019 02:00 am";
        String endtest1 = "1/1/2019 05:00 am";
        String[] begin =  splitString(begintest1);
        String[] end = splitString(endtest1);
        Appointment appt1 = new Appointment("Test 1", begin[0], begin[1], begin[2], end[0], end[1], end[2]);
        book.addAppointment(appt1);

        String begintest2 = "3/3/2019 02:00 am";
        String endtest2 = "3/3/2019 05:00 am";
        begin =  splitString(begintest2);
        end = splitString(endtest2);
        Appointment appt2 = new Appointment("Test 2", begin[0], begin[1], begin[2], end[0], end[1], end[2]);
        book.addAppointment(appt2);

        String begintest3 = "5/5/2019 02:00 am";
        String endtest3 = "5/5/2019 05:00 am";
        begin =  splitString(begintest3);
        end = splitString(endtest3);
        Appointment appt3 = new Appointment("Test 3", begin[0], begin[1], begin[2], end[0], end[1], end[2]);
        book.addAppointment(appt3);*/
        try {
            String owner = getRequireParameter(request, response, OWNER_PARAMETER);
            if (owner == null) return;

            String description = getRequireParameter(request, response, DESCRIPTION_PARAMETER);
            if (description == null) return;

            String beginTime = getRequireParameter(request, response, BEGINTIME_PARAMETER);
            if (beginTime == null) return;

            String endTime = getRequireParameter(request, response, ENDTIME_PARAMETER);
            if (endTime == null) return;


            String[] begin = splitString(beginTime);
            String[] end = splitString(endTime);

            Appointment appt = new Appointment(description, begin[0], begin[1], begin[2], end[0], end[1], end[2]);

            if (listofAppointmentBook.containsKey(owner)) {
                getAppointmentBook(owner).addAppointment(appt);
            } else {
                AppointmentBook book = new AppointmentBook(owner);
                this.listofAppointmentBook.put(owner, book);
                book.addAppointment(appt);
            }

            response.getWriter().println(appt.toString());
        /*response.getWriter().println(appt1.toString());
        response.getWriter().println(appt2.toString());
        response.getWriter().println(appt3.toString());*/
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IllegalArgumentException iae) {
            response.getWriter().println("ENDDATE CAN NOT BE EARLIER THAN BEGIN DATE");
            return;
        }catch (ParseException pe){
            response.getWriter().println("MALFORMATTED DATE/TIME");
            return;
        }
    }
    private String getRequireParameter(HttpServletRequest request, HttpServletResponse response, String parameter) throws IOException {
        String value = getParameter(parameter, request);
        if (value == null) {
            missingRequiredParameter(response, parameter);
            return null;
        }
        return value;
    }

    /**
     * Handles an HTTP DELETE request by removing all dictionary entries.  This
     * behavior is exposed for testing purposes only.  It's probably not
     * something that you'd want a real application to expose.
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        this.listofAppointmentBook.clear();

        PrintWriter pw = response.getWriter();
        pw.println(Messages.allDictionaryEntriesDeleted());
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);

    }

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter( HttpServletResponse response, String parameterName )
        throws IOException
    {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }
/*

    private void writeDefinition(String word, HttpServletResponse response ) throws IOException
    {
        String definition = this.dictionary.get(word);

        PrintWriter pw = response.getWriter();
        pw.println(Messages.formatDictionaryEntry(word, definition));

        pw.flush();

        response.setStatus( HttpServletResponse.SC_OK );
    }


    private void writeAllDictionaryEntries(HttpServletResponse response ) throws IOException
    {
        PrintWriter pw = response.getWriter();
        Messages.formatDictionaryEntries(pw, dictionary);

        pw.flush();

        response.setStatus( HttpServletResponse.SC_OK );
    }
*/
    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     *         <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
      String value = request.getParameter(name);
      if (value == null || "".equals(value)) {
        return null;

      } else {
        return value;
      }
    }

    @VisibleForTesting
    static AppointmentBook getAppointmentBook(String name) {
        return listofAppointmentBook.get(name);
    }
}
