package it.itsincom.webdevd.web.validation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CredentialValidator {

    public CredentialValidationErrors validateUsername(String username) throws IOException {
        boolean ok = username != null && !username.isEmpty();
        if (!ok) {
            return CredentialValidationErrors.EMPTY_USERNAME;
        }
        return null;
    }

    public CredentialValidationErrors validatePassword(String password) throws IOException {
        boolean ok = password != null && !password.isEmpty();
        if (!ok) {
            return CredentialValidationErrors.EMPTY_PASSWORD;
        }
        if (password.length() < 8) {
            return CredentialValidationErrors.PASSSWORD_TOO_SHORT;
        }
        return null;
    }

    public CredentialValidationErrors validateUsernameLogIn(String username) throws IOException {
        boolean ok = username != null && !username.isEmpty();
        if (!ok) {
            return CredentialValidationErrors.EMPTY_USERNAME;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("data/credential.csv"))) {
            String line;

            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String fileUsername = line.split(";")[0];
                if (fileUsername.equals(username)) {
                    return null;
                }
            }
        }
        return CredentialValidationErrors.NOTEXIST_USERNAME;
    }

    public CredentialValidationErrors validatePasswordLogIn(String password) throws IOException {
        boolean ok = password != null && !password.isEmpty();
        if (!ok) {
            return CredentialValidationErrors.EMPTY_PASSWORD;
        }
        if (password.length() < 8) {
            return CredentialValidationErrors.PASSSWORD_TOO_SHORT;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("data/credential.csv"))) {
            String line;

            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String filePassword = line.split(";")[1];
                if (filePassword.equals(password)) {
                    return null;
                }
            }
        }
        return CredentialValidationErrors.PASSSWORD_IS_WRONG;
    }
}
