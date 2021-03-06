package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author chancesnow
 */
public class TextDumper implements AppointmentBookDumper
{
    File file;

    /**
     * Construct a new appt book dumper (writer) given a file path
     *
     * @param filePath The file path
     */
    public TextDumper(String filePath)
    {
        file = new File(filePath);

        // Exceptional state when file is a directory
        if (file.isDirectory()) {
            file = null;
        }
    }

    /**
     * Dump (overwrite) an appointment book to a file
     *
     * @param appointmentBook The appointment book to dump (write)
     * @throws IOException
     */
    @Override
    public void dump(AbstractAppointmentBook appointmentBook) throws IOException
    {
        if (file != null) {
            PrintWriter pw = new PrintWriter(file);

            pw.println(appointmentBook.getOwnerName());

            for (Appointment appointment :
                    ((AppointmentBook) appointmentBook).getAppointments()) {
                pw.println(
                        Appointment.DATE_FORMAT.format(
                                appointment.getBeginTime()
                        )
                );
                pw.println(
                        Appointment.DATE_FORMAT.format(
                                appointment.getEndTime()
                        )
                );

                // Escape description from double quotes, and newlines
                String description = appointment.getDescription();
                description = description.replaceAll("\"", "&quot;");
                description = description.replaceAll("\\r", "&#13;");
                description = description.replaceAll("\\n", "&#10;");

                pw.println(description);
            }

            pw.close();
        }
    }
}
