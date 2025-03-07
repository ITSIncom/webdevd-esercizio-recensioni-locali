
package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import it.itsincom.webdevd.web.service.SessionManager;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.*;

import java.net.URI;

@Path("/profilo")
public class ProfiloResource {

    private final Template index;
    private final Template profilo;
    private final SessionManager sessionManager;

    public ProfiloResource(Template index, Template profilo, SessionManager sessionManager) {
        this.index = index;
        this.profilo = profilo;
        this.sessionManager = sessionManager;
    }

    @GET
    public Response mostraProfilo(@Context HttpHeaders headers) {
        // Recupera il nome utente dalla sessione
        String username = sessionManager.getUsernameFromSession(headers);

        // Se l'utente non è autenticato, reindirizza al login
        if (username == null) {
            // Reindirizza alla pagina di login
            return Response.seeOther(URI.create("/login")).build();
        }

        // Passa il nome utente al template
        return Response.ok(profilo.data("username", username)).build();
    }
    @POST
    public Response logout(@CookieParam(SessionManager.NOME_COOKIE_SESSION) String sessionId) {
        if (sessionId != null) {
            sessionManager.removeUserFromSession(sessionId);
        }

        // Cancella il cookie della sessione
        return Response.seeOther(URI.create("/"))
                .cookie(new NewCookie(SessionManager.NOME_COOKIE_SESSION, "", "/", null, "Session expired", 0, false)) //rimuove completamente il cookie impostando maxAge a 0
                .build();
    }

}
