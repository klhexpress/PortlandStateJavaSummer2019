package edu.pdx.cs410J.nkhoi;

import com.google.common.annotations.VisibleForTesting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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

    private final Map<String, AppointmentBook> listofAppointmentBook = new HashMap<>();

    /**
     * Handles an HTTP GET request from a client by writing the definition of the
     * word specified in the "word" HTTP parameter to the HTTP response.  If the
     * "word" parameter is not specified, all of the entries in the dictionary
     * are written to the HTTP response.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/plain" );
/*
        String word = getParameter( WORD_PARAMETER, request );
        if (word != null) {
            writeDefinition(word, response);

        } else {
            writeAllDictionaryEntries(response);
        }*/
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

        String parameter = OWNER_PARAMETER;
        String owner = getRequireParameter(request, response, parameter);
        if (owner == null) return;

        String description = getRequireParameter(request, response, DESCRIPTION_PARAMETER);
        if (description == null) return;

        String beginTime = getRequireParameter(request, response, BEGINTIME_PARAMETER);
        if (beginTime == null) return;

        String endTime = getRequireParameter(request, response, ENDTIME_PARAMETER);
        if (endTime == null) return;

        //this.listofAppointmentBook.put(word, definition);

        String[] begin = new String[3];
        begin = splitString(beginTime);
        String[] end = new String[3];
        end = splitString(endTime);

        AppointmentBook book = new AppointmentBook(owner);
        this.listofAppointmentBook.put(owner, book);

        Appointment appt = new Appointment(description, begin[0], begin[1], begin[2], end[0], end[1], end[2]);
        book.addAppointment(appt);


        response.getWriter().println(appt.toString());
        response.setStatus(HttpServletResponse.SC_OK);
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
    AppointmentBook getAppointmentBook(String word) {
        return this.listofAppointmentBook.get(word);
    }
}
