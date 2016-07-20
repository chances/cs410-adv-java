package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.ParserException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * The main class for the CS410J appointment book Project
 */
public class Project3
{

  // TODO: Refactor this monolith...

  public static final String USAGE_DOCUMENTATION = "usage: java edu.pdx.cs410J.chances.Project1 [options] <args>\n" +
          "\targs are (in this order):\n" +
          "\t\towner\t\tThe person whose owns the appt book\n" +
          "\t\tdescription\tA description of the appointment\n" +
          "\t\tbeginTime\tWhen the appt begins (24-hour time)\n" +
          "\t\tendTime\t\tWhen the appt ends (24-hour time)\n" +
          "\toptions are (options may appear in any order):\n" +
          "\t\t-pretty file\tPretty print the appointment book to\n" +
          "\t\t\t\t\t\ta text file or to standard out. (file -)\n" +
          "\t\t-textFile file\tWhere to read/write the appointment book\n" +
          "\t\t-print\t\t\tPrints a description of the new appointment\n" +
          "\t\t-README\t\t\tPrints a README for this project and exits\n" +
          "\tDate and time should be in the format: mm/dd/yyyy hh:mm";
  public static final int EXPECTED_NUM_ARGS = 8;

  public static void main(String[] args) {
    // Refer to one of Dave's classes so that we can be sure it is on the classpath
    Class c = AbstractAppointmentBook.class;

    ArrayList<String> argsList = new ArrayList<>(Arrays.asList(args));

    // Print the README if flag is present or zero arguments
    if (argsList.contains("-README") || argsList.size() == 0) {
      System.out.println("Chance Snow - Project 3\n" +
              "\tAppointment Book Application\n\n" +
              "Create an appointment in a new or existing appointment book.\n");
      System.out.print(USAGE_DOCUMENTATION);

      System.exit(0);
    }

    // Handle print option
    boolean shouldPrintDescription = argsList.contains("-print");

    if (shouldPrintDescription) {
      argsList.remove(argsList.indexOf("-print"));
    }

    AppointmentBook book = null;

    // Handle file arg option and parse appointment book if necessary
    boolean shouldUseFile = argsList.contains("-textFile");
    String filePath = null;

    if (shouldUseFile) {
      int fileOptionIndex = argsList.indexOf("-textFile");

      argsList.remove(fileOptionIndex);

      try {
        filePath = argsList.get(fileOptionIndex);

        TextParser parser = new TextParser(filePath);
        book = (AppointmentBook) parser.parse();

        argsList.remove(fileOptionIndex);
      } catch (IndexOutOfBoundsException | ParserException ex) {
        if (ex instanceof ParserException) {
          System.err.println("Could not load appointment book from file, " +
                  filePath + ":\n");
          System.err.println(ex.getMessage());

          System.exit(2);
        } else {
          // The file path option parameter could not be parsed
          // Therefore, no file should be written to later on
          shouldUseFile = false;
        }
      }
    }

    if (book == null) {
      book = new AppointmentBook();
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
      String beginTime = String.join(" ", argsList.get(2), argsList.get(3), argsList.get(4));
      String endTime = String.join(" ", argsList.get(5), argsList.get(6), argsList.get(7));

      if (book.getOwnerName() != null &&
              book.getOwnerName().length() > 0 &&
              !book.getOwnerName().equals(owner)) {
        // Specified owner does not match that of the appt book file's owner
        System.err.println("Specified owner, " + owner + ", does not own loaded " +
                "appointment book");

        System.exit(3);
      } else {
        book.setOwnerName(owner);
      }

      DateFormat[] formats = new DateFormat[]{
              new SimpleDateFormat("MM/dd/yyyy hh:mm a"),
              new SimpleDateFormat("MM/dd/yyyy h:mm a"),
              new SimpleDateFormat("M/dd/yyyy hh:mm a"),
              new SimpleDateFormat("M/dd/yyyy h:mm a"),
              new SimpleDateFormat("MM/d/yyyy hh:mm a"),
              new SimpleDateFormat("MM/d/yyyy h:mm a"),
              new SimpleDateFormat("M/d/yyyy hh:mm a"),
              new SimpleDateFormat("M/d/yyyy h:mm a")
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

        // Dump appt book to file, if necessary and able
        if (shouldUseFile && filePath != null) {
          TextDumper dumper = new TextDumper(filePath);

          try {
            dumper.dump(book);
          } catch (IOException ex) {
            System.err.println("Could not write appointment book to file:\n");
            System.err.println(ex.getMessage());

            System.exit(2);
          }
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
