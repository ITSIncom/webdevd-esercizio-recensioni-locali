package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/ricerca")
public class RicercaResource {

    private final Template ricerca;

    public RicercaResource(Template ricerca) {
        this.ricerca = ricerca;
    }

    @GET
    public TemplateInstance mostraPaginaRicerca()
    {
        return ricerca.instance();
    }
}
