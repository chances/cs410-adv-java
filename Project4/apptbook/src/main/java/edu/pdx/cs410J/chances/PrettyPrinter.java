package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @author chancesnow
 */
public class PrettyPrinter implements AppointmentBookDumper
{
    File file;

    /**
     * Construct a new pretty printer, printing to standard out
     */
    public PrettyPrinter()
    {
        this.file = null;
    }

    /**
     * Construct a new pretty printer given the file path to print to
     *
     * @param filePath The file path
     */
    public PrettyPrinter(String filePath)
    {
        file = new File(filePath);

        // Exceptional state when file is a directory
        if (file.isDirectory()) {
            file = null;
        }
    }

    /**
     * Dump (overwrite) an appointment book to a file in a pretty format
     *
     * @param appointmentBook The appointment book to dump (write)
     * @throws IOException
     */
    @Override
    public void dump(AbstractAppointmentBook appointmentBook) throws IOException
    {
        PrintWriter pw;

        if (file != null) {
            pw = new PrintWriter(file);
        } else {
            pw = new PrintWriter(System.out);
        }

        pw.println(appointmentBook.getOwnerName() + "'s Appointment Book\n");

        int count = 0;

        for (Appointment appointment :
                ((AppointmentBook) appointmentBook).getAppointments()) {
            if (count > 0) {
                pw.println("\n----------------------------------------------\n");
            }

            pw.println(appointment.getDescription() + "\n");

            pw.println("Beginning at: " +
                    Appointment.DATE_FORMAT.format(
                            appointment.getBeginTime()
                    )
            );
            pw.println("Ending at: " +
                    Appointment.DATE_FORMAT.format(
                            appointment.getEndTime()
                    ) + "\n"
            );

            long durationInMinutes =
                    TimeUnit.MILLISECONDS.toMinutes(appointment.getDuration());
            pw.println("Duration: " + durationInMinutes + " minutes");

            count += 1;
        }

        // If printing to a file, close it
        if (file != null) {
            pw.close();
        }
    }
}
