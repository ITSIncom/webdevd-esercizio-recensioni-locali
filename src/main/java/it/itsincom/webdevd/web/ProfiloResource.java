package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import it.itsincom.webdevd.web.service.SessionManager;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/profilo")
public class ProfiloResource {

    private final Template profilo;
    private final SessionManager sessionManager;

    public ProfiloResource(Template profilo, SessionManager sessionManager) {
        this.profilo = profilo;
        this.sessionManager = sessionManager;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance mostraProfilo(@CookieParam(SessionManager.NOME_COOKIE_SESSION) String sessionId) {
        String username = sessionManager.getUserFromSession(sessionId);
        return profilo.data("username", username);
    }
}