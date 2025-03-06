package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import it.itsincom.webdevd.web.service.SessionManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("logout")
public class LogoutResource {

    private final SessionManager sessionManager;

    public LogoutResource(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
    @POST
    public Response logout(@CookieParam("Sessione") String idSession){
        sessionManager.removeUserFromSession(idSession);

        return Response.seeOther(URI.create("/login"))
                .cookie(new NewCookie.Builder("Sessione").value(null).build())
                .build();
    }


}
