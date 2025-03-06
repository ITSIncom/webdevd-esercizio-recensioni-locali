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

    @POST
    public TemplateInstance aggiungiLocalePost(
            @FormParam("Nome") String nome,
            @FormParam("Indirizzo") String indirizzo
    )
    {
        String filePath = Paths.get("files", "locali.csv").toString();
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("File trovato: " + file.getAbsolutePath());
        } else {
            System.out.println("File NON trovato!");
            return null;
        }
        try (FileWriter writer = new FileWriter(filePath,true)) {
            String data = "\n" + nome + "," + indirizzo;
            writer.write(data);
            System.out.println("Data: " + data + "appended succesfully");
            return aggiungiLocale.instance();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
