package it.itsincom.webdevd.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@ApplicationScoped
public class UserManager {

    public boolean add(String username, String password) {
        try(FileWriter fw = new FileWriter("data/credential.csv",true)) {
            fw.write(username + ";" + password + "\n");
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkPassword(String inputUsername, String inputPassword) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/credential.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(";");

                if (credentials.length >= 2) {
                    String username = credentials[0].trim();
                    String password = credentials[1].trim();

                    if (username.equals(inputUsername) && password.equals(inputPassword)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }
}
