package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/recensione")
public class RatingResource {

    private final Template recensione;

    public RatingResource(Template recensione) {
        this.recensione = recensione;
    }

    @GET
    public TemplateInstance drawRecensione() {
        return recensione.instance();
    }
}
