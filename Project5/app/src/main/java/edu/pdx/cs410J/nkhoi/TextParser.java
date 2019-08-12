package edu.pdx.cs410j.nkhoi;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

/**
 * This class is represents a <code>TextParser</code>.
 */
public class TextParser{
    private String fileName;
    private FileReader fileReader;

    /**
     * Creates a new  <code>TextParser</code> object
     *
     * @param input The name of the file will be parsed
     * @throws FileNotFoundException throw an exception in case the file can't be opened/found
     */
    public TextParser(String input) throws FileNotFoundException {
        fileName = input;
        fileReader = new FileReader(fileName);
    }

    /**
     * Creates a new <code>splittingString</code>
     *
     * @param input The input string which will be split
     * @return the array of split Strings
     */
    private String[] splittingString(String input) {
        String[] subString = input.split(" ");
        return subString;
    }

    /**
     * @return the AppointmentBook read from the file
     * @throws ParserException in case it's fail to parse
     */

    public AppointmentBook parse() throws ParseException {
        AppointmentBook temp = new AppointmentBook();
        Appointment inFile;
        String ch;
        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ch = bufferedReader.readLine();

            String owner = null, desc = null, begindate = null, endingdate = null;
            String begin[] = new String[3];
            String end[] = new String[3];

            if (ch.startsWith("Owner: ")) {
                owner = ch.substring(ch.indexOf("Owner:") + 7, ch.length());
                temp = new AppointmentBook(owner);
            } else {
                System.err.println("FILE ERROR FORMAT");
                System.exit(1);
            }
            ch = bufferedReader.readLine();

            while (ch != null) {
                if (ch.startsWith("Description: ")) {
                    desc = ch.substring(ch.indexOf("Description:") + 13, ch.length());
                    //System.out.println(desc);
                } else {
                    System.err.println("FILE IS MALFORMATTED");
                    System.exit(1);
                }

                ch = bufferedReader.readLine();
                if (ch.startsWith("Begindate: ")) {
                    begindate = ch.substring(ch.indexOf("Begindate:") + 11, ch.length());
                    begin = splittingString(begindate);
                } else {
                    System.err.println("FILE ERROR FORMAT");
                    System.exit(1);
                }

                ch = bufferedReader.readLine();
                if (ch.startsWith("Endingdate: ")) {
                    endingdate = ch.substring(ch.indexOf("Endingdate:") + 12, ch.length());
                    end = splittingString(endingdate);
                } else {
                    System.err.println("FILE ERROR FORMAT");
                    System.exit(1);
                }
                inFile = new Appointment(desc, begin[0], begin[1], begin[2], end[0], end[1], end[2]);
                temp.addAppointment(inFile);
                ch = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("ERROR IN READING FILE ");
        }
        return temp;
    }
}
