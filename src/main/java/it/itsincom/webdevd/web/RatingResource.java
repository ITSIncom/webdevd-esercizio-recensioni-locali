package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/rating")
public class RatingResource {

    private final Template rating;

    public RatingResource(Template rating) {
        this.rating = rating;
    }

    @GET
    public TemplateInstance drawRecensione() {
        return rating.instance();
    }
}
