package fr.nailu.invoicegenerator.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;

@Component
public class Parser {
    public CSVParser init(String path) throws IOException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        Reader reader = new BufferedReader(fr);

        CSVFormat format = CSVFormat.RFC4180
                .withHeader();

        return new CSVParser(reader, format);
    }
}
