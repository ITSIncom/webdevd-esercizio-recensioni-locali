package it.itsincom.webdevd.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import it.itsincom.webdevd.Locale;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Path("/ricerca")
public class RicercaResource {

    private final Template ricerca;

    public RicercaResource(Template ricerca) {
        this.ricerca = ricerca;
    }

    @POST
    public TemplateInstance mostraPaginaRicerca(@FormParam("search") String search)
    {
        List<Locale> locali = new ArrayList<>();
        String filePath = Paths.get("files", "locali.csv").toString();
        File f = new File(filePath);
        //CARICA TUTTI I RECORD DEL FILE NELLA LISTA 'locali'
        try(BufferedReader r = new BufferedReader(new FileReader(f)))
        {
            String line;
            String[] splittedLine;
            r.readLine();
            while((line = r.readLine()) != null)
            {
                splittedLine = line.split(",");
                //FORMATO nome,indirizzo
                locali.add(new Locale(splittedLine[0], splittedLine[1]));
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        List<Locale> filteredLocali = new ArrayList<>();
        locali.forEach(l -> {
            if((l.getNome().toLowerCase()).contains(search.toLowerCase()))
            {
                filteredLocali.add(l);
            }
        });

        return ricerca.data("ricerca", search).data("locali",filteredLocali);
    }
}
