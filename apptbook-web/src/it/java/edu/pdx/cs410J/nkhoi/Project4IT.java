package edu.pdx.cs410J.nkhoi;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * An integration test for {@link Project4} that invokes its main method with
 * various arguments
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Project4IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");


    @Test
    public void test4AddAppointment() {
        String owner = "TEST";
        String description = "TESTING 1";
        String begindate = "1/1/2019";
        String begintime = "2:00";
        String beginmeridiem = "am";
        String enddate = "1/1/2019";
        String endtime = "3:00";
        String endmeridiem = "am";

        MainMethodResult result = invokeMain(Project4.class, HOSTNAME, PORT, owner, description, begindate, begintime, beginmeridiem, enddate,endtime,endmeridiem);
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(description));
        assertThat(out, out, containsString(begindate));
        assertThat(out, out, containsString(enddate));





        /*result = invokeMain( Project4.class, HOSTNAME, PORT, owner );

        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatDictionaryEntry(owner, description)));

        result = invokeMain( Project4.class, HOSTNAME, PORT );
        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatDictionaryEntry(owner, description)));*/
    }

    @Test
    public void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain(Project4.class);
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.MISSING_ARGS));
    }

    @Test
    public void test0RemoveAllMappings() throws IOException {
        AppointmentBookRestClient client = new AppointmentBookRestClient(HOSTNAME, Integer.parseInt(PORT));
        client.removeAllAppointmentBooks();
    }

    @Test
    public void test2EmptyServer() {
        MainMethodResult result = invokeMain(Project4.class, HOSTNAME, PORT);
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatWordCount(0)));
    }

    @Test
    public void test3NoDefinitions() {
        String owner = "TEST";
        MainMethodResult result = invokeMain(Project4.class, HOSTNAME, PORT, owner);
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatDictionaryEntry(owner, null)));
    }
}