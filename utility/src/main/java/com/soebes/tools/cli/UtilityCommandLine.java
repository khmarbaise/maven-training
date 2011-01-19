package com.soebes.tools.cli;

import org.apache.commons.cli2.CommandLine;
import org.apache.commons.cli2.Group;
import org.apache.commons.cli2.Option;
import org.apache.commons.cli2.OptionException;
import org.apache.commons.cli2.commandline.Parser;
import org.apache.commons.cli2.option.Command;

public class UtilityCommandLine extends CLIBase {

    private Group commands = null;
    private Group utilityOptions = null;

    // All global options
    private Option globalOptionH = null;

    // All available commands
    private Command diffCommand = null;

    private DiffCommand cliDiffCommand = null;

    private void initGlobalOptions() {
        globalOptionH = obuilder.withShortName("H").withLongName("help")
                .withDescription("Displays usage information for command.")
                .create();
    }

    private void initCommands() {
        diffCommand = cliDiffCommand.getCommand();
    }

    public void init() {
        initCommands();
        initGlobalOptions();

        commands = gbuilder.withName("commands").withOption(diffCommand)
                .create();

        setUtilityOptions(gbuilder.withName("utility-global options")
                .withOption(globalOptionH).withOption(commands).create());

    }

    public UtilityCommandLine() {
        cliDiffCommand = new DiffCommand();
        init();
    }

    public DiffCommand getScliDiffCommand() {
        return cliDiffCommand;
    }

    public CommandLine doParseArgs(String[] args) throws OptionException {
        final Parser parser = new Parser();
        parser.setGroup(getUtilityOptions());
        return parser.parse(args);
    }

    public Command getDiffCommand() {
        return diffCommand;
    }

    public Option getGlobalOptionH() {
        return globalOptionH;
    }

    public void setGlobalOptionH(Option globalOptionH) {
        this.globalOptionH = globalOptionH;
    }

    public void setUtilityOptions(Group utilityOptions) {
        this.utilityOptions = utilityOptions;
    }

    public Group getUtilityOptions() {
        return utilityOptions;
    }

}
