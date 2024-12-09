package me.javaexample.javademo.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import me.javaexample.javademo.exception.CustomException;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

public class ResourceUtils {

    public static File getFileFromResource(String filePath) throws IOException {

        InputStream inputStream = new ClassPathResource(filePath).getInputStream();
        try {
            File temp = File.createTempFile("temp", ".html");
            FileUtils.copyInputStreamToFile(inputStream, temp);

            return temp;
        } catch (Exception ex) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "failed to get contents from file");
        }
    }

    /**
     * it can be used for copy file.
     *
     * @param source if it is test.csv
     * @param destination maybe it is backup/test_{datetime}.csv
     * @throws IOException
     */
    static public void backupFile(String source, String destination) throws IOException {
        Path src = Paths.get(source);
        Path dest = Paths.get(destination);

        Files.createDirectories(dest.getParent());

        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
    }
}
