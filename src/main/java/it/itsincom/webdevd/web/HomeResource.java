package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import it.itsincom.webdevd.web.service.SessionManager;
import jakarta.ws.rs.*;

@Path("/")
public class HomeResource {

    private final Template index;
    private final SessionManager sessionManager;

    public HomeResource(Template index, SessionManager sessionManager) {
        this.index = index;
        this.sessionManager = sessionManager;
    }

    @GET
    public TemplateInstance mostraHome(@CookieParam(SessionManager.NOME_COOKIE_SESSION) String sessionId) {
        boolean isLoggedIn = true;
        if (sessionId == null || sessionId.isEmpty()) {
            isLoggedIn = false;
        } else {
            String user = sessionManager.getUserFromSession(sessionId);
            if (user == null) {
                isLoggedIn = false;
            }
        }
        return index.data("loggedIn", isLoggedIn);
    }
}
