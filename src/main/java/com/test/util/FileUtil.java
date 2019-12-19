package com.test.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class FileUtil {

    public void writeErrorFIle(List<String[]> errors, String fileUrl){
        try{
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(System.getProperty("user.dir")+"/../"+fileUrl)); CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Email", "Role", "Errors"));) {
                for (String arr[]:errors){
                    csvPrinter.printRecord(arr);
                }
                csvPrinter.flush();
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

}
