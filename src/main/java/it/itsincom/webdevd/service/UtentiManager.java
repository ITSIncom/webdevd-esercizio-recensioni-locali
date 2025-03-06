package it.itsincom.webdevd.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

@ApplicationScoped
public class UtentiManager {

    private static final Path FILE_PATH = Path.of("data/utenti.csv");

    private CSVParser getCSVParser() throws IOException {
        List<String> lines = Files.readAllLines(FILE_PATH);
        return CSVParser.parse(String.join("\n", lines), CSVFormat.DEFAULT);
    }

    private boolean alreadyExists(String username) {
        try {
            CSVParser csvParser = getCSVParser();
            for (CSVRecord csvRecord : csvParser.getRecords()) {
                if (username.equals(csvRecord.get(0))) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean add(String username, String password) {
        if (alreadyExists(username)) {
            System.out.println("Already exists");
            return false;
        }

        try (CSVPrinter csvPrinter = new CSVPrinter(
                Files.newBufferedWriter(
                        FILE_PATH,
                        StandardOpenOption.APPEND,
                        StandardOpenOption.CREATE
                ),
                CSVFormat.DEFAULT
        )) {
            csvPrinter.printRecord(username, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Added");
        return true;
    }

    public boolean checkPassword(String inputUsername, String inputPassword) {
        try {
            CSVParser csvParser = getCSVParser();
            for (CSVRecord csvRecord : csvParser.getRecords()) {
                if (inputUsername.equals(csvRecord.get(0)) && inputPassword.equals(csvRecord.get(1))) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
