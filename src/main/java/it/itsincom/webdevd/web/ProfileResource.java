package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import it.itsincom.webdevd.web.service.SessionManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/profile")
public class ProfileResource {

    private final Template profile;
    private final SessionManager sessionManager;

    public ProfileResource(Template profile, SessionManager sessionManager) {
        this.profile = profile;
        this.sessionManager = sessionManager;
    }

    @GET
    public TemplateInstance drawProfile(@CookieParam("Sessione") String idSession) {
        String user = sessionManager.getUserFromSession(idSession);
        return profile.data("username", user);
    }

    @POST
    public Response processProfile(@QueryParam("username") String username) {
        return Response.ok(profile.data("username",username).render()).build();
    }
}
