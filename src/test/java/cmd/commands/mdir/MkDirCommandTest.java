package cmd.commands.mdir;

import cmd.SimpleCmd;
import cmd.commands.AbstractCommandTest;
import cmd.commands.mkdir.MakeDirCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MkDirCommandTest extends AbstractCommandTest {

    @Test
    void testMkDirectory(@TempDir Path tempDir) throws IOException{
//        given
        prepareTestFolder(tempDir);
        SimpleCmd.setCurrentLocation(tempDir.toFile());
        String[] args = {"TestOrdner"};
        MakeDirCommand mkCommand = CommandLine.populateCommand(new MakeDirCommand(), args);

//        when
        mkCommand.run();

//        then
        Path resultPath = Paths.get(SimpleCmd.getCurrentLocation() + File.separator + "TestOrdner");
        System.out.println(resultPath.toAbsolutePath().toString());
        assertTrue(Files.exists(resultPath));
    }

    private void prepareTestFolder(@TempDir Path tempDir) throws IOException {
        // for other possible usages of @TempDir see https://www.baeldung.com/junit-5-temporary-directory
        Path myFile = tempDir.resolve("myFile.txt");
        Files.write(myFile, Collections.singletonList(""));

        Path folder1 = tempDir.resolve("folder1");
        Path directory = Files.createDirectory(folder1, noAttributes);

        Path myFile2 = directory.resolve("myFile2.txt");
        Files.write(myFile2, Collections.singletonList(""));
    }
}
