package com.soebes.tools.cli;

import com.beust.jcommander.JCommander;


public class UtilityCommands {
    public static final String PROGRAMM_NAME = "utilitiy";
    public static final String DIFF_COMMAND = "diff";

    private MainCommand mainCommand;
    private DiffCommand diffCommand;

    private JCommander commander;

    public UtilityCommands(String[] args) {
        mainCommand = new MainCommand();

        diffCommand = new DiffCommand();

        commander = new JCommander(mainCommand);

        getCommander().addCommand(DIFF_COMMAND, diffCommand);

        getCommander().setProgramName(PROGRAMM_NAME);
        getCommander().parse(args);
    }

    public boolean isDiffCommand() {
        if (DIFF_COMMAND.equals(getCommander().getParsedCommand())) {
            return true;
        } else {
            return false;
        }
    }

    public JCommander getCommander() {
        return this.commander;
    }

    public MainCommand getMainCommand() {
        return this.mainCommand;
    }

    public DiffCommand getDiffCommand() {
        return this.diffCommand;
    }

}
