package Class;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class SaveFile {
    public static void main(String[] args) {
        Path file = Paths.get("src/SaveFile.txt");
        String s = "abcdefg";
        byte[] data = s.getBytes();
        OutputStream output;
        try {
            output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            output.write(data);
            output.flush();
            output.close();
        } catch (FileAlreadyExistsException e) {
            System.out.println("Already exists");
        } catch (Exception e) {
            System.out.println("Message: " + e);
        }
    }
}
