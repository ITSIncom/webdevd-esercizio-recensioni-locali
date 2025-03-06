package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import it.itsincom.webdevd.service.LocaliManager;
import it.itsincom.webdevd.web.service.SessionManager;
import jakarta.ws.rs.*;

import java.util.Map;

@Path("/")
public class HomeResource {

    private final Template index;
    private final SessionManager sessionManager;
    private final LocaliManager localiManager;

    public HomeResource(Template index, SessionManager sessionManager, LocaliManager localiManager) {
        this.index = index;
        this.sessionManager = sessionManager;
        this.localiManager = localiManager;
    }

    @GET
    public TemplateInstance mostraHome(@CookieParam(SessionManager.NOME_COOKIE_SESSION) String sessionId) {
        boolean isLoggedIn = isLoggedIn(sessionId);
        return index.data("loggedIn", isLoggedIn,
                            "locali", null);
    }

    @POST
    @Path("/ricerca")
    public TemplateInstance ricercaLocali(@CookieParam(SessionManager.NOME_COOKIE_SESSION) String sessionId,
                                          @FormParam("search") String name){

        boolean isLoggedIn = isLoggedIn(sessionId);

        if(name.length() >= 3){
            Map<String, String> locali = localiManager.search(name);

            return index.data("loggedIn", isLoggedIn,
                    "locali", locali);
        }
        return index.data("loggedIn", isLoggedIn,
                "locali", null);
    }


    private boolean isLoggedIn(String sessionId) {
        boolean isLoggedIn = true;
        if (sessionId == null || sessionId.isEmpty()) {
            isLoggedIn = false;
        } else {
            String user = sessionManager.getUserFromSession(sessionId);
            if (user == null) {
                isLoggedIn = false;
            }
        }
        return isLoggedIn;
    }

}
