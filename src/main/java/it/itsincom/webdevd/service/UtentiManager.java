package it.itsincom.webdevd.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ApplicationScoped
public class UtentiManager {

    public boolean add(String username, String password) {
        try(FileWriter fw = new FileWriter("data/credential.csv",true)) {
            fw.write(username + ";" + password + "\n");
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkPassword(String inputUsername, String inputPassword) {
        try(FileReader fw = new FileReader("data/credential.csv")) {
            BufferedReader br = new BufferedReader(fw);
            String line = br.readLine();

            for (int i = 0; i < line.length(); i++) {
                String username = line.split(";")[0];
                String password = line.split(";")[1];

                if (username.equals(inputUsername) && password.equals(inputPassword)) {
                    return true;
                }
            }

            return false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
