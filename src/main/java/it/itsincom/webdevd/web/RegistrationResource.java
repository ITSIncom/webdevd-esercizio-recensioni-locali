package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import it.itsincom.webdevd.service.UtentiManager;
import it.itsincom.webdevd.web.validation.CredentialValidationErrors;
import it.itsincom.webdevd.web.validation.CredentialValidator;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

@Path("/registrazione")
public class RegistrationResource {

    private final Template registrazione;
    private final UtentiManager utentiManager;
    private final CredentialValidator credentialValidator;

    public RegistrationResource(Template registrazione, UtentiManager utentiManager, CredentialValidator credentialValidator) {
        this.registrazione = registrazione;
        this.utentiManager = utentiManager;
        this.credentialValidator = credentialValidator;
    }

    @GET
    public TemplateInstance mostraPaginaRegistrazione() {
        return registrazione.data("message", null);
    }

    @POST
    public Response processaRegistrazione(
            @FormParam("username") String username,
            @FormParam("password") String password) throws IOException {

        String messaggioErrore = null;
        CredentialValidationErrors usernameError = credentialValidator.validateUsername(username);

        if (usernameError != null) {
            messaggioErrore = "Username non valido, motivo: " + usernameError.name();
        }

        CredentialValidationErrors passwordError = credentialValidator.validatePassword(password);
        if (passwordError != null) {
            messaggioErrore = "Password non valida, motivo: " + passwordError.name();
        }

        if (messaggioErrore != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(registrazione.data("message", messaggioErrore))
                    .build();
        }

        try(FileWriter fw = new FileWriter("data/credential.csv",true)) {
            fw.write(username + ";" + password + "\n");
            return Response.seeOther(URI.create("/login")).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
