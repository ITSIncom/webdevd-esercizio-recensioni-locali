package it.itsincom.webdevd.web;


import it.itsincom.webdevd.web.service.SessionManager;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/logout")
public class LogoutResource {

    @Inject
    SessionManager sessionManager;

    @POST
    public Response logout(@CookieParam("sessionId") String sessionId) {
        if (sessionId == null || sessionManager.getUserFromSession(sessionId) == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Sessione non valida").build();
        }
        sessionManager.removeUserFromSession(sessionId);
        return Response.ok("Logout effettuato")
                .cookie(new jakarta.ws.rs.core.NewCookie("sessionId", "", "/", null, null, 0, false))
                .build();
    }
}
