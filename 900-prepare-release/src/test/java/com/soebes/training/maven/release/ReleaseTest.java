package com.soebes.training.maven.release;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ReleaseTest extends UnitTestBase {

    private final String REPOSITORY_DIRECTORY = getTargetDir() + "/repos";

    private final String REPOSITORY_URL = "file://" + REPOSITORY_DIRECTORY;

    private final String WORKING_COPY_DIRECTORY = getTargetDir() + "/wc-900-release";

    /**
     * execute a program in interactive mode which means
     * to print everything to console (System.out) or System.err
     * and pipeline all input to System.in
     * @param executable The program which will be executed.
     * @throws IOException
     */
    public int executeInteractive(String executable, File workgingDirectory, List<String> parameters) throws IOException {
        CommandLine cmdLine = new CommandLine(executable);
        for (String parameter : parameters) {
            cmdLine.addArgument(parameter);
        }

        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(System.out, System.err, System.in );

        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(workgingDirectory);
        executor.setStreamHandler(pumpStreamHandler);

        ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
        executor.setWatchdog(watchdog);

        int exitValue = 0;
        exitValue = executor.execute(cmdLine);
        return exitValue;
    }

    public void createRepository(File currentWorkingDirectory, URI repository) throws IOException {
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("create");
        parameters.add(repository.toString());
        svnAdmin(currentWorkingDirectory, parameters);
    }

    public void svnAdmin(File currentWorkingDirectory, List<String> parameters) throws IOException {
        executeInteractive("svnadmin", currentWorkingDirectory, parameters);
    }

    public void svnImport(URI repository, String importFolder) throws IOException {
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("import");
        parameters.add(importFolder);
        parameters.add(repository.toString());
        parameters.add("-m");
        parameters.add("[unit test] import.");
        executeInteractive("svn", new File(getTargetDir()), parameters);
    }

    public void svnMkdir(URI repository) throws IOException {
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("mkdir");
        parameters.add(repository.toString());
        parameters.add("-m");
        parameters.add("[unit test] added location.");
        executeInteractive("svn", new File(getTargetDir()), parameters);
    }

    public void svnCheckout(URI repository, String workingCopyFolder) throws IOException {
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("checkout");
        parameters.add(repository.toString());
        parameters.add(workingCopyFolder);
        executeInteractive("svn", new File(getTargetDir()), parameters);
    }

    private void changeWorkingDirectory(String newFolder) {
        //change to the workspace folder.
        System.setProperty("user.dir", newFolder);
    }

    @BeforeClass
    public void beforeSuite() throws IOException, ExecuteException {

        changeWorkingDirectory(getTargetDir());

        File repositoryFolder = new File(REPOSITORY_DIRECTORY);
        if (repositoryFolder.exists()) {
            FileUtils.deleteDirectory(repositoryFolder);
        }

        File pwsWorkspaceFolder = new File(WORKING_COPY_DIRECTORY);
        if (pwsWorkspaceFolder.exists()) {
            FileUtils.deleteDirectory(pwsWorkspaceFolder);
        }

        createRepository(new File(getTargetDir()), URI.create(REPOSITORY_DIRECTORY));

        svnImport(URI.create(REPOSITORY_URL + "/900-release/trunk"), getTargetDir() + "/900-release");

        // Create the tags/branches folder.
        svnMkdir(URI.create(REPOSITORY_URL + "/900-release/tags"));
        svnMkdir(URI.create(REPOSITORY_URL + "/900-release/branches"));

        svnCheckout(URI.create(REPOSITORY_URL + "/900-release/trunk"), WORKING_COPY_DIRECTORY);
    }

    @Test
    public void commandWorkspaceCreateTest() {
        //Bootstrap empty test to make sure unit tests will run so the beforeClass etc. will run as well.
        Assert.assertTrue(true);
    }

}
