package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/aggiungiLocale")
public class AggiungiLocaleResource {
    private final Template aggiungiLocale;

    public AggiungiLocaleResource(Template aggiungiLocale) {
        this.aggiungiLocale = aggiungiLocale;
    }

    @GET
    public TemplateInstance mostraAggiungiLocale() {
        return aggiungiLocale.instance();
    }
}
