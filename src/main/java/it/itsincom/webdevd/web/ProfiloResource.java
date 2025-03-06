
package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import it.itsincom.webdevd.web.service.SessionManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/profilo")
public class ProfiloResource {

    private final Template profilo;
    private final SessionManager sessionManager;

    public ProfiloResource(Template profilo, SessionManager sessionManager) {
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
}
