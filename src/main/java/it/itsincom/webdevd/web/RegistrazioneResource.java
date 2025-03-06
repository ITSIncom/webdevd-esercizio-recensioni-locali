package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/registrazione")
public class RegistrazioneResource {

    private final Template registrazione;

    public RegistrazioneResource(Template registrazione) {
        this.registrazione = registrazione;
    }

    @GET
    public TemplateInstance mostraPaginaRegistrazione() {
        return registrazione.instance();
    }

    @POST
    public void processaRegistrazione() {

    }
}
