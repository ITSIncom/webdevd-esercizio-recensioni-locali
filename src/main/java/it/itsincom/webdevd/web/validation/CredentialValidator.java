package it.itsincom.webdevd.web.validation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CredentialValidator {

    public CredentialValidationErrors validateUsername(String username) {
        boolean ok = username != null && !username.isEmpty();
        if (!ok) {
            return CredentialValidationErrors.EMPTY_USERNAME;
        }
        return null;
    }

    public CredentialValidationErrors validatePassword(String password) {
        boolean ok = password != null && !password.isEmpty();
        if (!ok) {
            return CredentialValidationErrors.EMPTY_PASSWORD;
        }
        if (password.length() < 8) {
            return CredentialValidationErrors.PASSSWORD_TOO_SHORT;
        }
        return null;
    }

    public CredentialValidationErrors validateUsernameLogin(String username) throws IOException {
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

    public CredentialValidationErrors validatePasswordLogin(String password) throws IOException {
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


    public CredentialValidationErrors validateUsernameRegister(String username) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("data/credential.csv"))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String fileUsername = line.split(";")[0];
                if (fileUsername.equals(username)) {
                    return CredentialValidationErrors.DUPLICATE_USERNAME;
                }
            }
        }
        return null ;
    }
}
