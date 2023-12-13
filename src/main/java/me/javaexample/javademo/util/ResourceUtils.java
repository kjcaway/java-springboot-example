package me.javaexample.javademo.util;

import me.javaexample.javademo.exception.CustomException;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ResourceUtils {
    public static File getFileFromResource(String filePath) throws IOException {

        InputStream inputStream = new ClassPathResource(filePath).getInputStream();
        try {
            File temp = File.createTempFile("temp", ".html");
            FileUtils.copyInputStreamToFile(inputStream, temp);

            return temp;
        } catch (Exception ex) {
            throw new CustomException("failed to get contents from file");
        }
    }
}
