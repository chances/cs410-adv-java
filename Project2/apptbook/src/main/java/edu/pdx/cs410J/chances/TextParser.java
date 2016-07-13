package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;

/**
 * @author chancesnow
 */
public class TextParser implements AppointmentBookParser
{
    private File file;

    public TextParser(String filePath)
    {
        file = new File(filePath);

        if (!file.exists() || !file.isDirectory()) {
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
            String line;

            try (
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(new FileInputStream(file))
                    )
            ) {
                line = br.readLine();
            } catch (IOException ex) {
                throw new ParserException(ex.getMessage(), ex);
            }
        }

        return null;
    }
}
