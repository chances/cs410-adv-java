package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;

/**
 * @author chancesnow
 */
public class TextParser implements AppointmentBookParser
{
    private File file;

    /**
     * Create a new appt book parser given a file path.
     *
     * @param filePath The file path
     */
    public TextParser(String filePath)
    {
        file = new File(filePath);

        // Exceptional state when file doesn't exist or file is a directory
        if (!file.exists() || file.isDirectory()) {
            file = null;
        }
    }

    /**
     * Parse an appointment book from a file.
     *
     * @return Parsed appointment book
     * @throws ParserException
     */
    @Override
    public AbstractAppointmentBook parse() throws ParserException
    {
        if (file != null) {
            try (
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(new FileInputStream(file))
                    )
            ) {
                String owner = br.readLine();

                AppointmentBook book = new AppointmentBook(owner);


                // Parse appointments
                //
                // Long lists of triplets of data, three lines each:
                // 1. Description
                // 2. Begin date
                // 3. End date
                //
                int step = 0;
                Date beginTime = null;
                Date endTime = null;

                String line = br.readLine();
                while (line != null) {

                    switch (step) {
                        case 0:
                        case 1:
                            Date date;
                            try {
                                date = Appointment.DATE_FORMAT.parse(line);
                            } catch (Exception ex) {
                                throw new ParserException(
                                        "Could not parse date \"" +
                                                line + "\"",
                                        ex
                                );
                            }

                            if (date != null) {
                                if (step == 0) {
                                    beginTime = date;
                                } else {
                                    endTime = date;
                                }
                            }

                            step = (step == 0) ? 1 : 2;
                            break;
                        case 2:
                            String description = line;

                            // Unescape double quotes and newlines
                            description = description.replaceAll("&quot;", "\"");
                            description = description.replaceAll("&#13;", "\r");
                            description = description.replaceAll("&#10;", "\n");

                            // Finished with appointment, add to the appt book
                            Appointment appointment =  new Appointment(description);
                            appointment.setBeginTime(beginTime);
                            appointment.setBeginTime(endTime);

                            book.getAppointments().add(appointment);

                            beginTime = null;
                            endTime = null;

                            step = 0;
                            break;
                    }

                    line = br.readLine();
                }

                boolean unfinished = step != 0;
                if (unfinished) {
                    throw new ParserException("Reached malformed appointment " +
                            "(missing required data)");
                }
            } catch (IOException ex) {
                throw new ParserException(ex.getMessage(), ex);
            }
        }

        return null;
    }
}
