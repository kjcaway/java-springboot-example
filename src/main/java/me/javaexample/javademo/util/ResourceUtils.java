package me.javaexample.javademo.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
}
