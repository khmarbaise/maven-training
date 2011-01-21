package com.soebes.tools.cli;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.soebes.tools.DateDifferenceExample.Dates;

public class UtilityCLI {
    private static Logger LOGGER = Logger.getLogger(UtilityCLI.class);

    
    public static void main(String[] args) {
        new UtilityCLI(args);
    }

    public UtilityCLI(String[] args) {
        UtilityCommands commands = new UtilityCommands(args);
        if (commands.getMainCommand().isHelp()) {
            commands.getCommander().usage();
            System.exit(0);
        }

        if (commands.isDiffCommand()) {
            runDiff(commands.getDiffCommand());
        }
    }

    public void runDiff(DiffCommand diffCommand) {
        LOGGER.info("Diff command has been called.");
        Date fromDate = diffCommand.getDateFrom();
        Date toDate = diffCommand.getDateTo();

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
