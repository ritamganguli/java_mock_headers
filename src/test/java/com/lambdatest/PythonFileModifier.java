import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PythonFileModifier {

    private static final Object lock = new Object();

    public static void modifyLineInFile(String placeholder, String newValue) {
        String filePath = "ritam.py"; // Define your file path here
        int lineNumber = 7; // Define your line number here
        String newContent = placeholder + " = '" + newValue + "'";

        synchronized (lock) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                // Replace the specific line
                lines.set(lineNumber - 1, newContent);
                // Write back to the file
                Files.write(Paths.get(filePath), lines);
                System.out.println("Line " + lineNumber + " modified successfully.");
            } catch (IOException e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
