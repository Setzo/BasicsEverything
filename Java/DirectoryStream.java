import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wojciech Pruszak on 17.06.16.
 */
public class Main {

    public static void main(String[] args) {

        try {
            DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get("/home/setzo/vmf/"));

            ds.forEach(path -> {

                File file = path.toFile();

                if (file.isDirectory()) {
                    return;
                }

                String ext = file.getName().substring(file.getName().lastIndexOf('.') + 1);

                System.out.println(file.getName() + " " + ext);

                if (ext.trim().equals("txt")) {
                    try {
                        Main.createTestFile(path);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTestFile(Path path) throws Exception {

        List<String> desiredContent = new ArrayList<String>();

        List<String> fileContent = Files.readAllLines(path);

        fileContent.stream().map(line ->
                "\"" + line + "\"" + "\t\"" + line + "\""
        )
                .forEach(desiredContent::add);

        Files.write(path, desiredContent);
    }

    private static void trimFile(Path path) throws Exception {

        List<String> desiredContent = new ArrayList<String>();

        List<String> fileContent = Files.readAllLines(path);

        System.out.println(path.getFileName());

        fileContent.stream().filter(line ->
                line.substring(line.lastIndexOf('.') + 1).equals("mdl")
        ).map(line ->
                line.substring("models/".length(), line.length() - ".mdl".length())
        )
                .forEach(desiredContent::add);

        Files.write(path, desiredContent);
    }

}
