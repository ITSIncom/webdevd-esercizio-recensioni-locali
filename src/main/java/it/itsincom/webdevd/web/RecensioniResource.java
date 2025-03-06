package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/recensione")
public class RecensioniResource {

    private final Template recensione;

    public RecensioniResource(Template recensione) {
        this.recensione = recensione;
    }

    @GET
    public TemplateInstance drawRecensione() {
        return recensione.instance();
    }
}
