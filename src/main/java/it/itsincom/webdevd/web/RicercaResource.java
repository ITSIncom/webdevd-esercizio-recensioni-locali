package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/ricerca")
public class RicercaResource {

    private final Template ricerca;

    public RicercaResource(Template ricerca) {
        this.ricerca = ricerca;
    }

    @POST
    public TemplateInstance mostraPaginaRicerca(@FormParam("search") String search)
    {
        return ricerca.data("ricerca", search);
    }
}
