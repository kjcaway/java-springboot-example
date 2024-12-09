package me.javaexample.javademo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
public class CsvUtilsTest {

    @Nested
    public class writeAndReadTest {

        final String TEST_FILE = "/src/test/resources/sample/test.csv";

        @Test
        public void writeAndRead() throws IOException {
            String userDir = System.getProperty("user.dir");
            String[] columns = {"first", "second", "third"};
            List<String> data = List.of("f1,f2,f3", "s1,s2,s3", "t1,t2,t3");
            CsvUtils.writeToAbsolute(columns, data, userDir + TEST_FILE);

            List<String> actual = CsvUtils.readFromAbsolute(userDir + TEST_FILE);
            List<String> expected = new ArrayList<>(data);

            Assertions.assertArrayEquals(expected.toArray(), actual.stream().skip(1).toArray());
        }
    }
}
