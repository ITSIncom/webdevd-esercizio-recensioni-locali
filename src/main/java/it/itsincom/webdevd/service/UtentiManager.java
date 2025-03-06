package it.itsincom.webdevd.service;

import it.itsincom.webdevd.model.Utente;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;

@ApplicationScoped
public class UtentiManager {

    public void saveUserToFile(Utente utente) {
        try (Writer writer = new FileWriter("utenti.csv", true);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL)) {

            csvPrinter.printRecord(utente.getId(), utente.getUsername(), utente.getPassword());
            csvPrinter.flush();

            System.out.println("Updated file");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public List<Utente> loadUsersFromFile()  {
        List<Utente> utentiRegistrati = new ArrayList<>();
        try (Reader reader = new FileReader("utenti.csv");
             CSVParser csvParser = new CSVParser(reader, CSVFormat.EXCEL.withHeader())) {

            for (CSVRecord record : csvParser)
            {
                String id = record.get("id_utente");
                String email = record.get("username");
                String password = record.get("password");

                Utente utenteRegistrato = new Utente(id, email, password);
                utentiRegistrati.add(utenteRegistrato);
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return utentiRegistrati;
    }

    public boolean isUsernameUnivocal(Utente utente) {
        List<Utente> utentiRegistrati = loadUsersFromFile();

        for (Utente utenteRegistrato : utentiRegistrati) {
            if (utenteRegistrato.getUsername().equals(utente.getUsername())) {
                return false;
            }
        }
        return true;
    }

    public int getUserNumber() {
        List<Utente> utentiRegistrati = loadUsersFromFile();
        return utentiRegistrati.size();
    }

    public boolean isUserRegistered(String inputUsername, String inputPassword) {
        List<Utente> utentiRegistrati = loadUsersFromFile();

        for (Utente utenteRegistrato : utentiRegistrati) {
            if (utenteRegistrato.getUsername().equals(inputUsername) && utenteRegistrato.getPassword().equals(inputPassword)) {
                return true;
            }
        }
        return false;
    }
}
