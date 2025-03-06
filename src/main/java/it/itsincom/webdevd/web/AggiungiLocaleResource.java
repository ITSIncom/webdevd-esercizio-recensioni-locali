package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/aggiungi-locale")
public class AggiungiLocaleResource {

    private final Template aggiuntaLocale;

    public AggiungiLocaleResource(Template aggiuntaLocale) {
        this.aggiuntaLocale = aggiuntaLocale;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance mostraPaginaAggiuntaLocale() {
        return aggiuntaLocale.instance();
    }
}