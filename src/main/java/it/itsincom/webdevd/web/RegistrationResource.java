package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import it.itsincom.webdevd.service.UserManager;
import it.itsincom.webdevd.web.validation.CredentialValidationErrors;
import it.itsincom.webdevd.web.validation.CredentialValidator;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;

@Path("/register")
public class RegistrationResource {

    private final Template register;
    private final UserManager utentiManager;
    private final CredentialValidator credentialValidator;

    public RegistrationResource(Template register, UserManager utentiManager, CredentialValidator credentialValidator) {
        this.register = register;
        this.utentiManager = utentiManager;
        this.credentialValidator = credentialValidator;
    }

    @GET
    public TemplateInstance mostraPaginaRegistrazione() {
        return register.data("message", null);
    }

    @POST
    public Response processaRegistrazione(
            @FormParam("username") String username,
            @FormParam("password") String password) throws IOException {


        String messaggioErrore = null;
        CredentialValidationErrors usernameError = credentialValidator.validateUsername(username);
        CredentialValidationErrors usernameExist = credentialValidator.validateUsernameRegister(username);

        if (usernameExist != null) {
            messaggioErrore = "Username non valida, motivo: " + usernameExist.name();
        }

        if (usernameError != null) {
            messaggioErrore = "Username non valido, motivo: " + usernameError.name();
        }

        CredentialValidationErrors passwordError = credentialValidator.validatePassword(password);
        if (passwordError != null) {
            messaggioErrore = "Password non valida, motivo: " + passwordError.name();
        }

        if (messaggioErrore != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(register.data("message", messaggioErrore))
                    .build();
        }

        utentiManager.add(username, password);

        return Response.seeOther(URI.create("/login")).build();

    }
}
