package cmd.commands.file;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * "Read File" command class
 * <p/>
 * Executing the command reads a file, which is denoted by the parameter.
 */
@Command(
        name = "file",
        description = "Show file content",
        mixinStandardHelpOptions = true)
public class FileCommand implements Runnable {

    public static final Logger LOG = LoggerFactory.getLogger(FileCommand.class);

    @Parameters(index = "0", description = "path of the file to read")
    private File file;

    public FileCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {
        String absolutePath = file.getAbsolutePath();
        boolean isFile = Files.isRegularFile(file.toPath());
        if (isFile){
            try {
                Desktop.getDesktop().edit(file);
                System.out.println(FileUtils.readFileToString(file, "UTF-8"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
        LOG.info("{} is no file \n", absolutePath);

        }
    }
}
