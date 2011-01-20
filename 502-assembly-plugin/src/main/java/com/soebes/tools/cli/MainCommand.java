package com.soebes.tools.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Global options")
public class MainCommand {

    @Parameter(names = { "--help", "-help", "-?", "-h", "-H" }, description = "Get the global help")
    private boolean help;

    public boolean isHelp() {
        return help;
    }
}
