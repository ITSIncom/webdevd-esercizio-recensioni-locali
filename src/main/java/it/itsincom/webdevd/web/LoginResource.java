package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/login")
public class LoginResource {

    private final Template login;

    public LoginResource(Template login) {
        this.login = login;
    }

    @GET
    public TemplateInstance mostraPaginaLogin() {
        return login.instance();
    }

    @POST
    public void processaLogin() {
    }
}
