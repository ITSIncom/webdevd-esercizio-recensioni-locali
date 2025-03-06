package it.itsincom.webdevd.service;

import it.itsincom.webdevd.model.Locale;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LocaliManager {

    private static final Path FILE_PATH = Path.of("data/locali.csv");

    private CSVParser getCSVParser() throws IOException {
        List<String> lines = Files.readAllLines(FILE_PATH);
        return CSVParser.parse(String.join("\n", lines), CSVFormat.DEFAULT);
    }

    private boolean alreadyExists(String name, String address) {
        try {
            CSVParser csvParser = getCSVParser();
            for (CSVRecord csvRecord : csvParser.getRecords()) {
                if (name.equals(csvRecord.get(1)) && address.equals(csvRecord.get(2))) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean add(String name, String address, String photoPath) {
        if (alreadyExists(name, address)) {
            return false;
        }

        int newId = 1; // ID di default se il file è vuoto

        try {
            List<String> lines = Files.readAllLines(FILE_PATH);
            if (!lines.isEmpty()) {
                String lastLine = lines.getLast();
                String[] lastRecord = lastLine.split(",");
                newId = Integer.parseInt(lastRecord[0]) + 1;
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }

        try (CSVPrinter csvPrinter = new CSVPrinter(
                Files.newBufferedWriter(
                        FILE_PATH,
                        StandardOpenOption.APPEND,
                        StandardOpenOption.CREATE
                ),
                CSVFormat.DEFAULT
        )) {
            csvPrinter.printRecord(newId, name, address, photoPath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public Map<String, String> search(String name){
        Map<String, String> locali = new HashMap<>();

        try {
            CSVParser csvParser = getCSVParser();
            for (CSVRecord csvRecord : csvParser.getRecords()) {
                if (csvRecord.get(1).contains(name)) {
                    locali.put(csvRecord.get(0), csvRecord.get(1));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return locali;
    }
}
