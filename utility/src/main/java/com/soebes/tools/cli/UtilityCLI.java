package com.soebes.tools.cli;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.cli2.CommandLine;
import org.apache.commons.cli2.Group;
import org.apache.commons.cli2.HelpLine;
import org.apache.commons.cli2.OptionException;
import org.apache.commons.cli2.util.HelpFormatter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.soebes.tools.DateDifferenceExample.Dates;

public class UtilityCLI {
    private static Logger LOGGER = Logger.getLogger(UtilityCLI.class);

    private static final int HELP_OPTION_DESCRIPTION_INDENT = 30;

    private static UtilityCommandLine utilitycli = new UtilityCommandLine();
    private static CommandLine commandLine = null;

    public static void main(String[] args) {
        try {
            commandLine = utilitycli.doParseArgs(args);
        } catch (OptionException e) {
            System.err
                    .println("Error: Unexpected Option given on command line. "
                            + e);
            System.exit(1);
        }

        if (commandLine.hasOption(utilitycli.getGlobalOptionH())) {
            printHelp();
            System.exit(0);
        } else if (commandLine.hasOption(utilitycli.getDiffCommand())) {
            runDiff(utilitycli.getScliDiffCommand());
        } else {
            System.err
                    .println("Error: You should define delta as command or use -H option to get further detailed information.");
            System.exit(1);
        }
    }

    private static void printHelp() {
        StringBuffer help = new StringBuffer();
        Group utilityOptions = utilitycli.getUtilityOptions();
        List helpLines = utilityOptions.helpLines(0,
                HelpFormatter.DEFAULT_DISPLAY_USAGE_SETTINGS, null);
        String descriptionPad = StringUtils.repeat(" ",
                HELP_OPTION_DESCRIPTION_INDENT);
        int descriptionIndent = HelpFormatter.DEFAULT_FULL_WIDTH
                - HELP_OPTION_DESCRIPTION_INDENT;
        for (Iterator i = helpLines.iterator(); i.hasNext();) {
            HelpLine helpLine = (HelpLine) i.next();
            String usage = helpLine.usage(
                    HelpFormatter.DEFAULT_LINE_USAGE_SETTINGS, null);
            String usageWrapped = WordUtils.wrap(usage,
                    HelpFormatter.DEFAULT_FULL_WIDTH);
            help.append(usageWrapped).append("\n");
            String description = helpLine.getDescription();
            if (description != null) {
                String descriptionWrapped = WordUtils.wrap(description,
                        descriptionIndent, "\n" + descriptionPad, true);
                help.append(descriptionPad).append(descriptionWrapped)
                        .append("\n");
            }
        }
        System.out.println(help);
    }

    private static void runDiff(DiffCommand diffCommand) {
        LOGGER.info("Diff command has been called.");
        String from = diffCommand.getFrom(commandLine);
        String to = diffCommand.getTo(commandLine);

        Date fromDate = diffCommand.parseDate(from);
        Date toDate = diffCommand.parseDate(to);

        // Creates two calendars instances
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        // Set the date for both of the calendar instance
        cal1.setTime(fromDate);
        cal2.setTime(toDate);

        // Get the represented date in milliseconds
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();

        long diff = milis2 - milis1;

        System.out.print("Delta: ");
        for (Dates item : Dates.values()) {
            long result = diff / item.getDivider();
            if (result == 0) {
                continue;
            }
            System.out.print(result + " " + item.name() + " ");
            diff -= result * item.getDivider();
        }
        System.out.println("");

    }
}
