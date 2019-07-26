package edu.pdx.cs410J.nkhoi;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;


/**
 * A unit test for the {@link AppointmentBookServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
public class AppointmentBookServletTest {

  /*
  @Test
  public void initiallyServletContainsNoDictionaryEntries() throws ServletException, IOException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    int expectedWords = 0;
    verify(pw).println(Messages.formatWordCount(expectedWords));
    verify(response).setStatus(HttpServletResponse.SC_OK);
  }
*/

  @Test
  public void addAppointmenttotheAppointmentBook() throws ServletException, IOException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    String owner = "TESTING";
    String description = "TEST 1";
    String beginTime = "1/1/2019 1:00 am";
    String endTime = "1/1/2019 2:00 am";
  
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("owner")).thenReturn(owner);
    when(request.getParameter("description")).thenReturn(description);
    when(request.getParameter("beginTime")).thenReturn(beginTime);
    when(request.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);



    servlet.doPost(request, response);
    verify(response).setStatus(HttpServletResponse.SC_OK);

    AppointmentBook book = servlet.getAppointmentBook(owner);
    assertThat(book,is(notNullValue()));

    assertThat(book.getAppointments().size(),equalTo(1));
    assertThat(book.getOwnerName(), equalTo(owner));

    Collection<Appointment> temp = book.getAppointments();
    Appointment test = temp.iterator().next();
    assertThat(test,is(notNullValue()));
    assertThat(test.getDescription(),equalTo(description));
    assertThat(test.getBeginTimeString(),equalTo(beginTime));
    assertThat(test.getEndTimeString(),equalTo(endTime));

    verify(pw).println(test.toString());
  }

}
