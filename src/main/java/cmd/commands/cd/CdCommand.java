package cmd.commands.cd;

import cmd.SimpleCmd;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Command(
        name="cd",
        description = "Change current directory to target directory on path",
        mixinStandardHelpOptions = true)
public class CdCommand implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(CdCommand.class);

    @Parameters(index = "0", description = "absolute path of the new directory")
    private File target;

    @Override
    public void run() {

        if (target.isDirectory()){
            SimpleCmd.setCurrentLocation(target);
        } else {
            LOG.info("Is a file, no directory: {}\n", target.getAbsolutePath());
        }

    }
}
