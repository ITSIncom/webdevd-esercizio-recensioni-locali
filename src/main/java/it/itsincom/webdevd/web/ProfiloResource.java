package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import it.itsincom.webdevd.service.UtentiManager;
import it.itsincom.webdevd.web.service.SessionManager;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/profilo")
public class ProfiloResource {
        private final Template profilo;
        private SessionManager sessionManager;

        public ProfiloResource(Template profilo, SessionManager sessionManager, UtentiManager utentiManager) {
        this.profilo=profilo;
        this.sessionManager=sessionManager;
        }

        @GET
        @Produces(MediaType.TEXT_HTML)
        public Response mostraProfilo(@CookieParam("sessionId") String sessionId) {
                if (sessionManager.getUserFromSession(sessionId) == null) {
                        return Response.status(Response.Status.UNAUTHORIZED).entity("Sessione non valida").build();
                }

                String username = sessionManager.getUserFromSession(sessionId);

                if (username == null) {
                        return Response.status(Response.Status.NOT_FOUND).entity("Utente non trovato").build();
                }

                TemplateInstance pagina = profilo.data("user", username);
                return Response.ok(pagina.render()).build();
        }
}

