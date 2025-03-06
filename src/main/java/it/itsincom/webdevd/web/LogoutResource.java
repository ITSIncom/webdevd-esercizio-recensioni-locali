package it.itsincom.webdevd.web;

import it.itsincom.webdevd.web.service.SessionManager;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/logout")
public class LogoutResource {

    private final SessionManager sessionManager;

    public LogoutResource(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @POST
    public Response processaLogout(@CookieParam(SessionManager.NOME_COOKIE_SESSION) String sessionId) {
        if (sessionId != null) {
            sessionManager.removeUserFromSession(sessionId);
        }
        NewCookie expiredCookie = new NewCookie(SessionManager.NOME_COOKIE_SESSION, "", "/", null, null, 0, false);
        return Response.seeOther(URI.create("/")).cookie(expiredCookie).build();
    }
}