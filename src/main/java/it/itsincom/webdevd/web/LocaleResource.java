package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;


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
}
