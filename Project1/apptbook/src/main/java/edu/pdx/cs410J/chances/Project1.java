package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
      System.err.println("Too few command line arguments");
    } else if (argsListSize > EXPECTED_NUM_ARGS) {
      System.err.println("Too many command line arguments");
    }

    if (argsListSize == EXPECTED_NUM_ARGS) {
      // Parse appointment args
      String owner = argsList.get(0);
      String description = argsList.get(1);
      String beginTime = String.join(" ", argsList.get(2), argsList.get(3));
      String endTime = String.join(" ", argsList.get(4), argsList.get(5));

      AppointmentBook book = new AppointmentBook(owner);

      DateFormat[] formats = new DateFormat[]{
              new SimpleDateFormat("mm/dd/yyyy kk:mm"),
              new SimpleDateFormat("mm/dd/yyyy k:mm"),
              new SimpleDateFormat("m/dd/yyyy kk:mm"),
              new SimpleDateFormat("m/dd/yyyy k:mm"),
              new SimpleDateFormat("mm/d/yyyy kk:mm"),
              new SimpleDateFormat("mm/d/yyyy k:mm"),
              new SimpleDateFormat("m/d/yyyy kk:mm"),
              new SimpleDateFormat("m/d/yyyy k:mm")
      };
      for (DateFormat format : formats) {
        format.setLenient(false);
      }

      Date begin = tryParseDate(formats, beginTime);
      Date end = tryParseDate(formats, endTime);

      if (begin == null) {
        System.err.println("Malformed begin time");
      } else if (end == null) {
        System.err.println("Malformed end time");
      } else {
        Appointment appointment = new Appointment(description);

        appointment.setBeginTime(begin);
        appointment.setEndTime(end);

        // Add the successfully parsed appointment to the book
        book.addAppointment(appointment);

        if (shouldPrintDescription) {
          System.out.println(appointment);
        }

        System.exit(0);
      }
    }

    // Default to error
    System.err.println();
    System.err.println(USAGE_DOCUMENTATION);
    System.exit(1);
  }

  /**
   * Try to parse a date-time string given an array of DateFormat
   *
   * @param formats Array of DateFormat objects
   * @param dateTime Date-time string to try to parse
   * @return Null or successfully parsed Date
     */
  static Date tryParseDate(DateFormat[] formats, String dateTime) {
    for (DateFormat format : formats) {
      Date date = tryParseDate(format, dateTime);
      if (date != null) {
        return date;
      }
    }

    return null;
  }

  /**
   * Try to parse a date-time string given a DateFormat
   *
   * @param format DateFormat object
   * @param dateTime Date-time string to try to parse
   * @return Null or successfully parsed Date
     */
  static Date tryParseDate(DateFormat format, String dateTime) {
    try {
      return format.parse(dateTime);
    } catch (ParseException ex) {
      return null;
    }
  }
}
