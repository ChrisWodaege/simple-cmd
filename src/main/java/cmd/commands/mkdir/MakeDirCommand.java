package cmd.commands.mkdir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import cmd.SimpleCmd;
import picocli.CommandLine;

@CommandLine.Command(
        name = "mkdir",
        description = "Creates a new directory or file",
        mixinStandardHelpOptions = true)

public class MakeDirCommand implements Runnable {

    @CommandLine.Option(names = {"-f", "-file"}, description = "create a file, default is directory")
    private boolean isFile;

    @CommandLine.Parameters(index = "0", description = "path of new directory or file")
    private File newPath;

    private final String newFolderName = "New Folder";
    private final String newFileName = "new_file.txt";

    public MakeDirCommand() {
    }


    @Override
    public void run() {
        if (isFile){
            createFile();
        } else {
            createFolder();
        }
    }

    private void createFolder() {

        Path newDirectory = Paths.get(SimpleCmd.getCurrentLocation() + File.separator + newPath.toString());
//        File nestedDirectory = new File(newDirectory, "nested_directory");
//
//        nestedDirectory.mkdirs();
        if (Files.notExists(newDirectory) && !Files.isDirectory(newDirectory)){
        try{
            Files.createDirectory(newDirectory);

        } catch(IOException e){
                e.printStackTrace();
        }
        System.out.println("new folder created");

        } else {
            System.out.println("directory already exists");
        }
    }

    private void createFile() {
        String currentLocation = SimpleCmd.getCurrentLocation().getAbsolutePath();
        Path resultPath = Paths.get(currentLocation + File.separator + newPath.toString() + ".txt");
        if(Files.notExists(resultPath)) {
            try {
                Files.createFile(resultPath);
                System.out.println("new file created");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
                System.out.println("file already exists");
        }



    }
}
