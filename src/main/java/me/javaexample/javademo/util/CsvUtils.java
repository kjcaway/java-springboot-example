package me.javaexample.javademo.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CsvUtils {

    public List<String> read(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        InputStreamReader in = new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(filename)),
            StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(in);
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    static public List<String> readFromAbsolute(String filename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filename);

        List<String> lines = new ArrayList<>();
        InputStreamReader in = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(in);
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    static public void writeToAbsolute(String[] columns, List<String> data, String fileName) {
        BufferedWriter csvFile;
        try {
            Path path = Paths.get(fileName);
            csvFile = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            csvFile.write("\uFEFF"); // for korean language breaking

            for (int i = 0; i < columns.length; i++) {
                csvFile.write(columns[i]);
                if (i != columns.length - 1) {
                    csvFile.write(',');
                }
            }
            csvFile.newLine();
            for (String datum : data) {
                String[] columnData = datum.split(",");
                for (int j = 0; j < columnData.length; j++) {
                    csvFile.write(columnData[j]);
                    if (j != columnData.length - 1) {
                        csvFile.write(',');
                    }
                }
                csvFile.newLine();
            }
            csvFile.flush();
            csvFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
