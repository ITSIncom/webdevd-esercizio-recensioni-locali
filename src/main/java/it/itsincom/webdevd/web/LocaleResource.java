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
import java.net.URI;


@Path("/restaurant")
public class LocaleResource {

    private final Template restaurant;

    public LocaleResource(Template restaurant) {
        this.restaurant = restaurant;
    }

    @GET
    public TemplateInstance drawLocale() {
        return restaurant.instance();
    }

    @POST
    public Response processLocale(@FormParam("nome") String nomeLocale
                                ,@FormParam("indirizzo") String indirizzoLocale)  {
        try(FileWriter fw = new FileWriter("restaurant.csv",true)) {
            fw.write(nomeLocale + "," + indirizzoLocale);
            return Response.seeOther(URI.create("/")).build();
        }
        catch (IOException e) {
            return Response.serverError().build();
        }
    }
}
