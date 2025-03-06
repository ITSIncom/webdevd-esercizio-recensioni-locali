package it.itsincom.webdevd.web;


import it.itsincom.webdevd.web.service.SessionManager;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/logout")
public class LogoutResource {

    @Inject
    SessionManager sessionManager;

    @POST
    public Response logout(@CookieParam(SessionManager.NOME_COOKIE_SESSION) String sessionId) {
        if (sessionManager.getUserFromSession(sessionId) == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Sessione non valida").build();
        }
        sessionManager.removeUserFromSession(sessionId);
        return Response.seeOther(URI.create("/")).build();
    }
}
