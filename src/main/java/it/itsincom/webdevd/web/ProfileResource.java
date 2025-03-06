package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/profile")
public class ProfileResource {

    private final Template profile;

    public ProfileResource(Template profile) {
        this.profile = profile;
    }

    @GET
    public TemplateInstance drawProfile() {
        return profile.instance();
    }

    @POST
    public Response processProfile(@QueryParam("username") String username) {
        return Response.ok(profile.data("username",username).render()).build();
    }
}
