package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

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
}
