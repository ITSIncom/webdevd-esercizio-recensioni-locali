package it.itsincom.webdevd.web.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.NewCookie;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class SessionManager {

    public static final String NOME_COOKIE_SESSION = "Sessione";

    private final Map<String, String> sessions = new ConcurrentHashMap<>();

    public NewCookie createUserSession(String username) {
        String idSessione = UUID.randomUUID().toString();
        sessions.put(idSessione, username);
        return new NewCookie.Builder(NOME_COOKIE_SESSION).value(idSessione).build();
    }

    public String getUserFromSession(String sessionId) {
        return sessions.get(sessionId);
    }

    public String getUsernameFromSession(HttpHeaders headers) {
        if (headers.getCookies().containsKey(NOME_COOKIE_SESSION)) {
            String sessionId = headers.getCookies().get(NOME_COOKIE_SESSION).getValue();
            return getUserFromSession(sessionId);
        }
        return null;
    }


    public void removeUserFromSession(String sessionId) {
        System.out.println("Removing session: " + sessionId);
        sessions.remove(sessionId);
        System.out.println("Current sessions: " + sessions); //debug
    }
}