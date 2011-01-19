package com.soebes.tools.cli;

import org.apache.commons.cli2.builder.ArgumentBuilder;
import org.apache.commons.cli2.builder.CommandBuilder;
import org.apache.commons.cli2.builder.DefaultOptionBuilder;
import org.apache.commons.cli2.builder.GroupBuilder;
import org.apache.commons.cli2.option.Command;

public class CLIBase {
    private Command command = null;

    protected static final DefaultOptionBuilder obuilder = new DefaultOptionBuilder();
    protected static final ArgumentBuilder abuilder = new ArgumentBuilder();
    protected static final CommandBuilder cbuilder = new CommandBuilder();
    protected static final GroupBuilder gbuilder = new GroupBuilder();

    public Command getCommand() {
        return this.command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

}
