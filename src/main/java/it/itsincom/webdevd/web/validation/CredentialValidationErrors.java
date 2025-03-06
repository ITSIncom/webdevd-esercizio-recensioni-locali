package it.itsincom.webdevd.web.validation;

public enum CredentialValidationErrors {
    EMPTY_USERNAME("Lo username è vuoto"),
    EMPTY_PASSWORD("La password è vuota"),
    DUPLICATE_USERNAME("Lo username è già presente"),
    PASSWORD_TOO_SHORT("La password è troppo corta");

    private final String message;

    CredentialValidationErrors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

