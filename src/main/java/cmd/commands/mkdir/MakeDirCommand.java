package cmd.commands.mkdir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import picocli.CommandLine;

@CommandLine.Command(
        name = "mkdir",
        description = "Creates a new directory or file",
        mixinStandardHelpOptions = true)

public class MakeDirCommand implements Runnable {

    private final String newFolderName = "New Folder";
    private final String newFileName = "new_file.txt";

    @Override
    public void run() {
        createFolder();
//        createFile();
    }

    private void createFolder() {

        File newDirectory = new File(System.getProperty("user.dir") + File.separator + newFolderName);
        File nestedDirectory = new File(newDirectory, "nested_directory");

        nestedDirectory.mkdirs();
        System.out.println("new folder created");
    }

    private void createFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(newFileName, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("new file created");
        writer.close();
    }
}
