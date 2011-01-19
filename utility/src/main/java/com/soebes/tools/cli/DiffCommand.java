package com.soebes.tools.cli;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.cli2.CommandLine;
import org.apache.commons.cli2.Group;
import org.apache.commons.cli2.Option;
import org.apache.commons.cli2.option.Command;

public class DiffCommand extends CLIBase {

    public static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat(
            "dd.MM.yyyy HH:mm:ss");

    private Option optionFrom = null;
    private Option optionTo = null;

    public DiffCommand() {
        setCommand(createCommand());
    }

    private Command createCommand() {
        optionFrom = obuilder
                .withShortName("F")
                .withLongName("from")
                .withRequired(true)
                .withArgument(
                        abuilder.withName("from").withMinimum(1).withMaximum(1)
                                .create())
                .withDescription(
                        "Define the timepoint from dd.MM.yyyy HH:mm:ss")
                .create();

        optionTo = obuilder
                .withShortName("T")
                .withLongName("to")
                .withArgument(
                        abuilder.withName("to").withMinimum(1).withMaximum(1)
                                .create())
                .withDescription(
                        "Define the timepoint to dd.MM.yyyy HH:mm:ss if not given the current date will be used.")
                .create();

        Group diffOptionIndex = gbuilder.withOption(optionFrom)
                .withOption(optionTo).create();

        return cbuilder.withName("diff").withName("di")
                .withDescription("Caclulate the difference between two dates")
                .withChildren(diffOptionIndex).create();
    }

    public Option getOptionFrom() {
        return optionFrom;
    }

    public Option getOptionTo() {
        return optionTo;
    }

    public String getFrom(CommandLine cline) {
        String result = (String) cline.getValue((getOptionFrom()));
        if (result != null) {
            return result.trim();
        } else {
            return null;
        }
    }

    public String getTo(CommandLine cline) {
        String result = (String) cline.getValue((getOptionTo()));
        if (result != null) {
            return result.trim();
        } else {
            return DEFAULT_FORMAT.format(new Date());
        }
    }

    public Date parseDate(String dateStr) {
        Date result = null;
        try {
            result = DEFAULT_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
