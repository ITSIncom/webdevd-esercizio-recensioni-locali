package it.itsincom.webdevd.web;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/locale")
public class LocaleResource {

    @GET
    @Path("/{idLocale}")
    public void visualizzaLocale(@PathParam("idLocale") String id){

    }
}
