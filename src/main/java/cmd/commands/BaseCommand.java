package cmd.commands;

import cmd.commands.cd.CdCommand;
import cmd.commands.copy.CopyCommand;
import cmd.commands.del.DelCommand;
import cmd.commands.dir.DirCommand;
import cmd.commands.file.FileCommand;
import cmd.commands.mkdir.MakeDirCommand;
import cmd.commands.move.MoveCommand;
import picocli.CommandLine.Command;

/**
 * Base Command Class
 * <p/>
 * This parent command class is meant to register other commands as subcommands.
 * This can be done using the subcommands parameter of the @Command annotation.
 * <p/>
 * For a deeper understanding of how this is tied together please check the picocli documentation.
 * @see <a href="https://picocli.info/">picocli Documentation</a>
 */
@Command(
        name = "cmd",
        description = "base command",
        mixinStandardHelpOptions = true,
        subcommands = {DirCommand.class, DelCommand.class, CopyCommand.class, MoveCommand.class, MakeDirCommand.class, FileCommand.class, CdCommand.class})
public class BaseCommand implements Runnable {
    public BaseCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {
        /* intentionally empty */
    }

}
