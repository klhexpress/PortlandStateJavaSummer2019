package edu.pdx.cs410J.nkhoi;
import java.io.*;

public class Project2 {

    static String fileName = "sample.txt";

    public static void readfile() {
        String ch;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ch = bufferedReader.readLine();
            while(ch != null ) {
                System.out.println(ch);
                ch = bufferedReader.readLine();
            }

            // Always close files.
            bufferedReader.close();
        }catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }catch (IOException e) {
            // exception handling
        }
    }

    public static void writefile(String args1, String args2, String args3, String args4, String args5, String args6) {
        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write("Owner: " + args1);
            bufferedWriter.newLine();
            bufferedWriter.write("Description: " + args2);
            bufferedWriter.newLine();
            bufferedWriter.write("Begindate: " + args3);
            bufferedWriter.newLine();
            bufferedWriter.write("Begintime: " + args4);
            bufferedWriter.newLine();
            bufferedWriter.write("Endingdate: " + args5);
            bufferedWriter.newLine();
            bufferedWriter.write("Endingtime: " + args6);
            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Missing command line");
            System.exit(1);
        }

        System.out.println("This is project 2 " + args[0]);
        if (args.length == 6)
            writefile(args[0], args[1], args[2], args[3], args[4], args[5]);
        else{
            System.out.println("Not enough args");
            readfile();
        }




        System.exit(0);
    }
}












/*int ch;
        try(FileReader fileReader = new FileReader(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ch = bufferedReader.read();

            while(ch != -1) {
                System.out.println((char)ch);
                ch = bufferedReader.read();
            }
        } catch (FileNotFoundException e) {
            // exception handling
            System.out.println(
                    "Unable to open file '" +
                            args[0] + "'");
        } catch (IOException e) {
            // exception handling
        }*/