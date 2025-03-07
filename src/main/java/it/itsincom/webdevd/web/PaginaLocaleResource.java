package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

@Path("/pagina_locale")
public class PaginaLocaleResource {
    private final Template paginaLocale;

    public PaginaLocaleResource(Template paginaLocale) {
        this.paginaLocale = paginaLocale;
    }

    @GET
    public TemplateInstance mostraPaginaLocale() {
        return paginaLocale.instance();
    }

    @POST
    public TemplateInstance paginaLocalePost(
            @FormParam("Nome") String nome,
            @FormParam("Indirizzo") String indirizzo
    )
    {
        return null;
    }
}