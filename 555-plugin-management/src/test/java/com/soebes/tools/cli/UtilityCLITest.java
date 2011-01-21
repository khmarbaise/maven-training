package com.soebes.tools.cli;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

/**
 * @author Karl Heinz Marbaise
 * 
 */
@Test
public class UtilityCLITest {

    public void testNothingGiven() throws Exception {
        UtilityCommands cli = new UtilityCommands(new String[0]);
        assertNotNull(cli, "The return value of the parse is null!");
    }

    public void testGlobalOptionH() throws Exception {
        final String[] args = new String[] { "-H" };
        UtilityCommands cli = new UtilityCommands(args);
        assertNotNull(cli, "The return value of the parse is null!");
        assertTrue(cli.getMainCommand().isHelp());
        assertFalse(cli.isDiffCommand());
    }

    public void testCommandCheckDiffWithFromOnly() throws Exception {
        final String[] args = new String[] { "diff", "--from", "23.03.2010 13:10:00" };
        UtilityCommands cli = new UtilityCommands(args);
        assertTrue(cli.isDiffCommand());
        assertNotNull(cli.getDiffCommand().getDateFrom());
        assertNull(cli.getDiffCommand().getDateTo());
    }

    public void testCommandCheckDiffWithFromAndTo() throws Exception {
        final String[] args = new String[] { "diff", "--from", "23.03.2010 13:10:00", "--to", "24.03.2010 12:30:00" };
        UtilityCommands cli = new UtilityCommands(args);
        assertTrue(cli.isDiffCommand());
        assertNotNull(cli.getDiffCommand().getDateFrom());
        assertNotNull(cli.getDiffCommand().getDateTo());
    }

}
