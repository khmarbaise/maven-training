package com.soebes.tools.cli;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.apache.commons.cli2.CommandLine;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.soebes.tools.cli.UtilityCommandLine;

/**
 * @author Karl Heinz Marbaise
 *
 */
@Test
public class UtilityCLITest {

	private UtilityCommandLine utilityCLI = null;

	@SuppressWarnings("unused")
	@BeforeClass
	private void beforeClass() throws Exception {
		utilityCLI = new UtilityCommandLine();
	}

	public void testNothingGiven() throws Exception {
		CommandLine cl = utilityCLI.doParseArgs(new String[0]);
		assertNotNull(cl, "The return value of the parse is null!");
	}

	public void testGlobalOptionH() throws Exception {
		final String[] args = new String[] { "-H" };
		CommandLine cl = utilityCLI.doParseArgs(args);
		assertNotNull(cl, "The return value of the parse is null!");
		assertTrue(cl.hasOption(utilityCLI.getGlobalOptionH()));
	}

	public void testCommandCheckDiffWithFromOnly() throws Exception {
		final String[] args = new String[] { "diff", "--from", "23.03.2010 13:10:00" };
		CommandLine cl = utilityCLI.doParseArgs(args);
		assertTrue(cl.hasOption(utilityCLI.getDiffCommand()));
	}

	public void testCommandCheckDiffWithFromAndTo() throws Exception {
		final String[] args = new String[] { "diff", "--from", "23.03.2010 13:10:00", "--to", "24.03.2010 12:30:00" };
		CommandLine cl = utilityCLI.doParseArgs(args);
		assertTrue(cl.hasOption(utilityCLI.getDiffCommand()));
	}

}
