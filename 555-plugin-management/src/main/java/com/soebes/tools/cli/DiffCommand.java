package com.soebes.tools.cli;

import java.util.Date;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Creat delta between two given timepoints.")
public class DiffCommand extends MainCommand {

    @Parameter(names = {"--from", "-from"}, converter= DateConverter.class,description = "Define the timeoint from dd.MM.yyy HH:mm:ss")
    private Date dateFrom;

    @Parameter(names = {"--to", "-to"}, converter= DateConverter.class, description = "Define the timepoint to dd.MM.yyyy HH:mm:ss if not given the current date will be used.")
    private Date dateTo;

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

}
