package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.File;

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

    @Override
    public AbstractAppointmentBook parse() throws ParserException
    {
        return null;
    }
}
