package cmd.commands.move;

import cmd.SimpleCmd;
import cmd.commands.dir.DirCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@CommandLine.Command(
        name = "move",
        description = "Moves files or directories to another directory",
        mixinStandardHelpOptions = true)
public class MoveCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(MoveCommand.class);

    public MoveCommand() {
        /* intentionally empty */
    }

    private Path basePath;

    @CommandLine.Parameters(index = "0", description = "source path of the file to move")
    private File source;

    @CommandLine.Parameters(index = "1", description = "target path to move file to")
    private File target;

    @Override
    public void run() {
        try {
            basePath = SimpleCmd.getCurrentLocation().toPath();
            LOG.info("\nsource: " + source.toPath().toAbsolutePath() + "  " + "target: " + target.toPath().toAbsolutePath() + "\n");
            boolean a = source.setReadable(true);
            source.setExecutable(true, false);
            boolean b = target.setWritable(true, false);
            target.setExecutable(true, false);
            boolean c = target.canWrite();
            if (a && b) {
                Files.move(source.toPath().toAbsolutePath(), target.toPath().toAbsolutePath(), StandardCopyOption.ATOMIC_MOVE);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
