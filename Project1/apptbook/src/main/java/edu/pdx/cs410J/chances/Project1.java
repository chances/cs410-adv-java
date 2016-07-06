package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The main class for the CS410J appointment book Project
 */
public class Project1 {

  public static final String USAGE_DOCUMENTATION = "usage: java edu.pdx.cs410J.chances.Project1 [options] <args>\n" +
          "\targs are (in this order):\n" +
          "\t\towner The person whose owns the appt book\n" +
          "\t\tdescription A description of the appointment\n" +
          "\t\tbeginTime When the appt begins (24-hour time)\n" +
          "\t\tendTime When the appt ends (24-hour time)\n" +
          "\toptions are (options may appear in any order):\n" +
          "\t\t-print Prints a description of the new appointment\n" +
          "\t\t-README Prints a README for this project and exits\n" +
          "\tDate and time should be in the format: mm/dd/yyyy hh:mm";
  public static final int EXPECTED_NUM_ARGS = 6;

  public static void main(String[] args) {
    // Refer to one of Dave's classes so that we can be sure it is on the classpath
    Class c = AbstractAppointmentBook.class;

    ArrayList<String> argsList = new ArrayList<>(Arrays.asList(args));

    // Print the README if flag is present
    if (argsList.contains("-README")) {
      System.out.println("Chance Snow - Project 1\n" +
              "\tAppointment Book Application\n\n" +
              "Create an appointment in a new appointment book.\n");
      System.out.print(USAGE_DOCUMENTATION);

      System.exit(0);
    }

    // Handle print option
    boolean shouldPrintDescription = argsList.get(0).equals("-print");

    if (shouldPrintDescription) {
      argsList.remove(0);
    }

    // Handle too few or extraneous arguments
    int argsListSize = argsList.size();
    if (argsListSize < EXPECTED_NUM_ARGS) {
      System.err.println("Missing command line arguments");
    } else if (argsListSize > EXPECTED_NUM_ARGS) {
      System.err.println("Missing command line arguments");
    }

    if (argsListSize == EXPECTED_NUM_ARGS) {
      // Parse appointment args
      String owner = argsList.get(0);
      String description = argsList.get(1);
      String beginTime = String.join(" ", argsList.get(2), argsList.get(3));
      String endTime = String.join(" ", argsList.get(4), argsList.get(5));

      System.out.println(beginTime);
      System.out.println(endTime);

      Appointment appointment = new Appointment(description);

      if (shouldPrintDescription) {
//        System.out.println(appointment);
      }

      // TODO: Only exit successfully when dates are parsed
      System.exit(0);
    }

    // Default to error
    System.err.println();
    System.err.println(USAGE_DOCUMENTATION);
    System.exit(1);
  }

}

/*

The -print option cannot appear after the args, but the options can appear in any order.  That is, a command line like

$ java -jar target/apptbook-1.0-SNAPSHOT.jar Dave -print 7/15/2016 14:39  7/15/2016 15:39

should create an appointment whose description is "-print".  Note that I won't test this case, though.

A command line like

$ java -jar target/apptbook-1.0-SNAPSHOT.jar Dave "Save the whales" 7/15/2016 14:39  7/15/2016 15:39 -print

should issue an error because there are extraneous command line arguments.

A command line like:

$ java -jar target/apptbook-1.0-SNAPSHOT.jar -print -README Dave "Save the whales" 7/15/2016 14:39  7/15/2016 15:39

should print the README and exit because that's what you always do when -README is provided.

 */
