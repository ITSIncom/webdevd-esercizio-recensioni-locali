package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.io.FileWriter;
import java.io.IOException;


@Path("/locale")
public class LocaleResource {

    private final Template locale;

    public LocaleResource(Template locale) {
        this.locale = locale;
    }

    @GET
    public TemplateInstance drawLocale() {
        return locale.instance();
    }

    @POST
    public Response processLocale(@FormParam("nome") String nomeLocale
                                ,@FormParam("indirizzo") String indirizzoLocale)  {
        try(FileWriter fw = new FileWriter("locale.txt")) {
            fw.write(nomeLocale + "," + indirizzoLocale);
            return Response.ok().build();
        }
        catch (IOException e) {
            return Response.serverError().build();
        }
    }
}
