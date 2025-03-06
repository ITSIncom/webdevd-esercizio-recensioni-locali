package it.itsincom.webdevd.model;

public class Locale {
    private String id;
    private String nome;
    private String indirizzo;
    private String foto;

    public Locale(String id, String nome, String indirizzo, String foto) {
        this.id = id;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getFoto() {
        return foto;
    }
}
